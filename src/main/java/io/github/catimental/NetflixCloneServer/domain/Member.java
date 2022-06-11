package io.github.catimental.NetflixCloneServer.domain;


import javax.persistence.*;

@Entity
public class Member {
    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 생성된 값을 IDENTITY라고 함
    private Long id;

    private String loginId;

    private String loginPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
