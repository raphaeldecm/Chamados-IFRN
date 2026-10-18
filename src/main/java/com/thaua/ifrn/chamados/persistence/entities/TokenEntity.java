package com.thaua.ifrn.chamados.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "tokens_tb")
@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "token_id")
    private UUID id;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    private boolean expired = false;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
