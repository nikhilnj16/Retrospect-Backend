package com.backend.Retrospect.user.DTO;

public class UserDetailsChangeDTO {
    String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public UserDetailsChangeDTO(String userEmail) {
        this.userEmail = userEmail;
    }

    public UserDetailsChangeDTO() {

    }

}
