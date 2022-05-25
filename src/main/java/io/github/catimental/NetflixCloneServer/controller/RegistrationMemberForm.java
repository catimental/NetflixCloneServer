package io.github.catimental.NetflixCloneServer.controller;

public class RegistrationMemberForm {
    private String loginId;
    private String loginPassword;

    public String getLoginPassword() {
        return loginPassword;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Override
    public String toString() {
        return "RegistrationMemberForm{" +
                "loginId='" + loginId + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                '}';
    }
}
