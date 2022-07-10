package ro.ieti.licentaBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ieti.licentaBE.entity.AuthEnum;
import ro.ieti.licentaBE.entity.CustomerUser;
import ro.ieti.licentaBE.repository.UserRepository;
import ro.ieti.licentaBE.repository.UserRoleRepository;

import java.util.Date;
import java.util.List;

@Service
public class CustomUserService {
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    @Autowired
    public CustomUserService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public List<CustomerUser> listAllRoles() {
        return userRoleRepository.findAll();
    }

    public CustomerUser createNewCustomerAfterLoginWithGoogle(String email, String name, AuthEnum authenticationProvider) {
        CustomerUser user = new CustomerUser();
        user.setEmail(email);
        user.setFirstName(name);
        user.setActive(true);
        user.setCreatedTime(new Date());
        user.setAuthProvider(authenticationProvider);

        userRepository.save(user);

        return user;
    }

    public CustomerUser getCustomerByEmail(String email) {
        return null;
    }

    public void updateCustomerAfterLoginWithGoogle(CustomerUser customer, String name, AuthEnum provider) {
        customer.setFirstName(name);
        customer.setAuthProvider(provider);
        userRepository.save(customer);
    }
}
