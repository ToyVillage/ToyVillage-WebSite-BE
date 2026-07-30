package com.command.toyvillage_server.domain.app.auth.account.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@Table(name = "tbl_app_admin")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AppAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_account_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppRole role;

    public static AppAccount createAdmin(String username, String name, String encodedPassword) {
        return create(username, name, encodedPassword, AppRole.APP_ADMIN);
    }

    public static AppAccount createEmployee(String username, String name, String encodedPassword) {
        return create(username, name, encodedPassword, AppRole.EMPLOYEE);
    }

    private static AppAccount create(String username, String name, String encodedPassword, AppRole role) {
        return AppAccount.builder()
                .username(username)
                .name(name)
                .password(encodedPassword)
                .role(role)
                .build();
    }

    public void changePassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
