package com.command.toyvillage_server.domain.auth.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_admin")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public static Admin create(String username, String password) {
        return Admin.builder()
                .username(username)
                .password(password)
                .build();
    }
}
