package com.boyo.deliveryservice.services.implementations;

import com.boyo.deliveryservice.dtos.UserResponse;
import com.boyo.deliveryservice.dtos.RegistrationRequest;
import com.boyo.deliveryservice.dtos.UpdateUserRequest;
import com.boyo.deliveryservice.models.*;

import com.boyo.deliveryservice.repositories.*;
import com.boyo.deliveryservice.services.interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.boyo.deliveryservice.models.Role.*;
@Service
@Primary
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CourierService courierService;
    private final AccountService accountService;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(()->{
           throw  new UsernameNotFoundException("username: "+username+" does not exist");
        });
    }

    @Override
    public void register(RegistrationRequest request, String pin) {
        System.out.println("used impl 1");
        Account account = new Account(request.getUsername(),pin);
        var newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.addRole(USER);
        newUser.setAccount(accountService.addAccount(account));
        userRepository.save(newUser);
    }

    @Override
    public void registerAdmin(RegistrationRequest request) {
        var newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.addRole(Role.ADMIN);
        userRepository.save(newUser);
    }

    @Override
    public void registerCourier(Courier courier) {
        var newUser = new User();
        newUser.setUsername(courier.getUsername());
        newUser.setPassword(passwordEncoder.encode(courier.getPassword()));
        courier.setPassword(passwordEncoder.encode(courier.getPassword()));
        newUser.addRole(COURIER);
        newUser.addRole(USER);
        userRepository.save(newUser);
        courierService.addCourier(courier);
    }

    @Override
    public void updateUser(String userId,UpdateUserRequest updateUserRequest) {
        var user = getUserFromDB(userId);
        if(updateUserRequest.getAddress()!= null){
            user.setAddress(updateUserRequest.getAddress());
        }
        if(updateUserRequest.getFirstName()!= null){
            user.setFirstName(updateUserRequest.getFirstName());
        }
        if(updateUserRequest.getLastName()!= null){
            user.setLastName(updateUserRequest.getLastName());
        }
        if (updateUserRequest.getPhoneNumber()!= null){
            user.setPhoneNumber(updateUserRequest.getPhoneNumber());
        }
        userRepository.save(user);
    }

    @Override
    public User getUserFromDatabase(String userId) {
        return userRepository.findById(userId).orElseThrow(()->{
            throw new UsernameNotFoundException("user with "+userId+" not found");
        });
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> getALlUsers() {
        var users = userRepository.findAll();
        List<UserResponse> userResponse = new ArrayList<>();
        users.forEach(user->{
            var cr = new UserResponse(user.getFirstName()+user.getLastName(),
                    user.getAddress(),user.getPhoneNumber());
            userResponse.add(cr);
        });
        return userResponse;
    }

    private User getUserFromDB(String userId) {
        return userRepository.findById(userId).orElseThrow(()->{
            throw new UsernameNotFoundException("user with "+userId+" not found");
        });
    }
}
