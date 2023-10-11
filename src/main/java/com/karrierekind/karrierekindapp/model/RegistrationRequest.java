package com.karrierekind.karrierekindapp.model;

// Create a DTO (Data Transfer Object) to receive the registration data from the request body.
public class RegistrationRequest {
    private String email;
    private String password;
    private String userType;

    private String firstName;
    private String lastName;// This will be converted to UserType enum in the registerUser method

    // Getters, setters, and default constructor omitted for brevity

    public RegistrationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
