package com.tconecta.loyalty.api.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "MAE_REWARD")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RewardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REWARD_PK")
    private Long rewardId;

    @Column(name = "TXT_REWARD_NAME")
    private String name;

    @Column(name = "NUM_AMOUNT_POINT")
    private Integer amountPoint;
}
