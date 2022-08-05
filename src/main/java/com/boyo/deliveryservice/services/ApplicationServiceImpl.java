package com.boyo.deliveryservice.services;

import com.boyo.deliveryservice.dtos.UserResponse;
import com.boyo.deliveryservice.dtos.UpdateUserRequest;
import com.boyo.deliveryservice.models.*;
import com.boyo.deliveryservice.models.Package;
import com.boyo.deliveryservice.services.interfaces.*;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.boyo.deliveryservice.models.DeliveryStatus.DELIVERED;
import static com.boyo.deliveryservice.models.DeliveryStatus.STILL_AT_WAREHOUSE;
import static com.boyo.deliveryservice.models.TransactionType.ADD_MONEY;
import static com.boyo.deliveryservice.models.TransactionType.MAKE_PAYMENT;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final UserService userService;
    private final AccountService accountService;
    private final PackageService packageService;
    private final ShipmentService shipmentService;
    private final TransactionHistoryService transactionHistoryService;
    private final CourierService courierService;
    private final PaymentService paymentService;
    private final InvoiceService invoiceService;


    @Override
    public Shipment sendParcel(Package package_, String courierID, String senderID,
                               String receiverID, Integer amount) {
        var courier = courierService.getCourier(courierID);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm");
        var sender = userService.getUserFromDatabase(senderID);
        var account = sender.getAccount();
        if(amount > Integer.parseInt(getBalance(senderID))){
            throw new IllegalArgumentException("insufficient balance");
        }
        setUpTxHistory(amount, formatter, account);
        var receiver = userService.getUserFromDatabase(receiverID);
        Shipment shipment = new Shipment();
        var pack = packageService.addPackage(package_);
        sender.getSentPackages().add(pack);
        receiver.getReceivedPackages().add(pack);
        shipment.getPackages().add(pack);
        modifyShipment(shipment, sender, receiver);
        shipment.setInvoice(invoiceService.addInvoice(setPaymentAndInvoice(sender,pack, amount)));
        var shipment_ = shipmentService.addShipment(shipment);
        courier.getShipments().add(shipment_);
        courierService.addCourier(courier);
        userService.save(sender);
        userService.save(receiver);
        return shipment_;
    }
    private void modifyShipment(Shipment shipment, User sender, User receiver) {
        shipment.setSender(new UserResponse(sender.getFirstName()+" "+sender.getLastName(),
                sender.getAddress(),sender.getPhoneNumber()));
        shipment.setReceiver(new UserResponse(receiver.getFirstName()+" "+receiver.getLastName(),
                receiver.getAddress(),receiver.getPhoneNumber()));
        shipment.setTrackingNo(generateRandomNumber());
        shipment.setDeliveryStatus(STILL_AT_WAREHOUSE);
    }
    private Invoice setPaymentAndInvoice(User sender, Package pack, Integer amount) {
        Payment payment = new Payment(amount);
        return new Invoice(new UserResponse(sender.getFirstName()+" "+sender.getLastName(),
                sender.getAddress(),sender.getPhoneNumber()),paymentService.addPayment(payment), pack);
    }
    private String generateRandomNumber() {
        SecureRandom random = new SecureRandom();
        return String.valueOf(Math.abs(random.nextInt()));
    }

    private void setUpTxHistory(Integer amount, DateTimeFormatter formatter, Account account) {
        account.getTransactionHistory()
                .add(transactionHistoryService
                        .addTransaction(new TransactionHistory(BigDecimal.valueOf(amount),
                                MAKE_PAYMENT, LocalDateTime.now().format(formatter))));
        accountService.addAccount(account);
    }

    @Override
    public void fundAccount(String userId, Integer amount) {
        var user = userService.getUserFromDatabase(userId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm");
        var account = user.getAccount();
        account.getTransactionHistory()
                .add(transactionHistoryService
                        .addTransaction(new TransactionHistory(BigDecimal.valueOf(amount),
                                ADD_MONEY, LocalDateTime.now().format(formatter))));
        accountService.addAccount(account);
        userService.save(user);
    }

    @Override
    public String getBalance(String userId) {
        var user = userService.getUserFromDatabase(userId);
        BigDecimal currentBalance = new BigDecimal(0);
        final BigDecimal[] balance = {currentBalance};
        user.getAccount().getTransactionHistory().forEach((tx)->{
            switch (tx.getType()){
                case ADD_MONEY -> balance[0] = balance[0].add(tx.getAmount());
                case MAKE_PAYMENT -> balance[0] = balance[0].subtract(tx.getAmount());
            }
        });
        System.out.println(balance[0]);
        currentBalance = balance[0];
        return String.valueOf(currentBalance);
    }

    @Override
    public void addPackage(String shipmentId, Package pack) {
        shipmentService.addPackage(shipmentId,packageService.addPackage(pack));
    }

    @Override
    public void updateUser(String userId, UpdateUserRequest updateUserRequest) {
        userService.updateUser(userId,updateUserRequest);
    }

    @Override
    public UserResponse viewUserDetails(String userId) {
        var user = userService.getUserFromDatabase(userId);
        return new UserResponse(user.getFirstName()+" "+ user.getLastName(),
                user.getAddress(),user.getPhoneNumber());
    }

    @Override
    public List<UserResponse> getAllUsers() {

        return userService.getALlUsers();
    }

    @Override
    public void deliverGoods(String courierId,String shipmentID) {
        var courier = courierService.getCourier(courierId);

        var shipment_ = shipmentService.getShipment(shipmentID);
        shipment_.setDeliveryStatus(DELIVERED);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm");
        courier.getAccount().getTransactionHistory()
                .add(
                        new TransactionHistory(
                        BigDecimal.valueOf(shipment_.getInvoice().getPayment().getAmount())
                        ,ADD_MONEY
                        ,LocalDateTime.now().format(formatter)
                ));
        courierService.addCourier(courier);
        shipmentService.addShipment(shipment_);
    }

    @Override
    public void sendPackage(Package package_, String courierID, String senderID, String receiverID, Integer amount) {
        package_.setDeliveryStatus(STILL_AT_WAREHOUSE);

        var courier = courierService.getCourier(courierID);
        var sender = userService.getUserFromDatabase(senderID);
        var receiver = userService.getUserFromDatabase(receiverID);

        var payment = new Payment(amount,LocalDateTime.now());
        UserResponse senderResponse = new UserResponse(sender.getFirstName()+" "+sender.getLastName(),
                sender.getAddress(),sender.getPhoneNumber());

        UserResponse receiverResponse = new UserResponse(receiver.getFirstName()+" "+receiver.getLastName(),
                receiver.getAddress(),receiver.getPhoneNumber());

        var invoice = new Invoice(package_.getPackageName(),senderResponse,receiverResponse,
                paymentService.addPayment(payment));
        package_.setInvoice(invoiceService.addInvoice(invoice));
        var pack = packageService.addPackage(package_);
        courier.getUndeliveredPackages().add(pack);
        sender.getSentPackages().add(pack);
    }
}