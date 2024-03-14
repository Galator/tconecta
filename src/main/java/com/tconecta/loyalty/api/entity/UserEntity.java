package com.tconecta.loyalty.api.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "MAE_USER")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER_PK")
    private Long userId;

    @Column(name = "TXT_NAME", unique = true)
    private String userName;

    @Column(name = "TXT_PSW")
    private String userPsw;

}
