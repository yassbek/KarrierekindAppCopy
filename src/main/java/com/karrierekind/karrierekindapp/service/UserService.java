package com.karrierekind.karrierekindapp.service;

import com.karrierekind.karrierekindapp.entity.Company;
import com.karrierekind.karrierekindapp.entity.Talent;
import com.karrierekind.karrierekindapp.entity.User;
import com.karrierekind.karrierekindapp.entity.UserType;
import com.karrierekind.karrierekindapp.model.RegistrationRequest;
import com.karrierekind.karrierekindapp.repository.UserRepository;
import com.karrierekind.karrierekindapp.utils.InvalidUserTypeException;
import com.karrierekind.karrierekindapp.utils.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TalentService talentService;

    @Autowired
    private CompanyService companyService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    // Possibly inject JWT or authentication provider if needed

    public User registerUser(RegistrationRequest registrationRequest) {
        UserType userType;
        try {
            userType = UserType.valueOf(registrationRequest.getUserType().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidUserTypeException("Invalid user type provided: " + registrationRequest.getUserType());
        }

        // Hash the password
        String hashedPassword = passwordEncoder.encode(registrationRequest.getPassword());

        // Create a new user object and save it
        User newUser = new User(
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getEmail(),
                hashedPassword, // Note: use the hashed password here, not the plain text one!
                userType
        );
        newUser = userRepository.save(newUser);

        // If user type is Talent, create a talent record
        if (userType == UserType.TALENT) {
            talentService.saveTalent(new Talent(newUser));




            // Note: This assumes that your Talent constructor can take a User object
            // and extract the necessary data to create a Talent record.
            // Adjust accordingly based on your Talent entity structure.
        }
        if (userType == UserType.COMPANY) {
            companyService.saveCompany(new Company(newUser));

        }
        return newUser;
    }



    public boolean areCredentialsValid(String email, String rawPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            return false;
        }

        User user = userOpt.get();
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }


    public User authenticateUser(String email, String password){
        User User = new User();
        return User;
    }
        // Verify email and password, return user details or authentication token
        // ...


    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found."));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }
        userRepository.deleteById(id);
    }

    // Other methods for user updates, password resets, etc.
}

