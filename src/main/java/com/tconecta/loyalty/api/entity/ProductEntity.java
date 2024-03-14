package com.tconecta.loyalty.api.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "MAE_PRODUCT")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCT_PK")
    private Long productId;

    @Column(name = "TXT_PRODUCT_NAME")
    private String productName;

    @Column(name = "NUM_REWARD_POINT")
    private Integer rewardPoint;

    @Column(name = "NUM_PRODUCT_PRICE")
    private Double price;

    @Column(name = "FLG_STATUS")
    private Integer isActive;

}