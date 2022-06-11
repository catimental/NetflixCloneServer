package io.github.catimental.NetflixCloneServer.dto;

public class LoginMemberForm {
    private String loginId;
    private String loginPassword;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    @Override
    public String toString() {
        return "LoginMemberForm{" +
                "loginId='" + loginId + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                '}';
    }
}
