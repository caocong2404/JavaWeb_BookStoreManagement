/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congcv.registration;

import java.io.Serializable;

/**
 *
 * @author CONG
 */
public class RegistrationCreateError implements Serializable {

    private String usernameLengthError;
    private String passwordLengthError;
    private String confirmIsMatch;
    private String fullnameLengthError;
    private String usernameIsExisted;
    private String accountNotMatch;

    public RegistrationCreateError() {
    }

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getFullnameLengthError() {
        return fullnameLengthError;
    }

    public void setFullnameLengthError(String fullnameLengthError) {
        this.fullnameLengthError = fullnameLengthError;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }

    public String getAccountNotMatch() {
        return accountNotMatch;
    }

    public void setAccountNotMatch(String accountNotMatch) {
        this.accountNotMatch = accountNotMatch;
    }

    public String getConfirmIsMatch() {
        return confirmIsMatch;
    }

    public void setConfirmIsMatch(String confirmIsMatch) {
        this.confirmIsMatch = confirmIsMatch;
    }

}
