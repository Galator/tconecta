package com.tconecta.loyalty.api.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "AUX_SHOPPING")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SHOPPING_PK")
    private Long shoppingId;

    @OneToOne
    @JoinColumn(name = "ID_USER_FK", referencedColumnName = "ID_USER_PK")
    private UserEntity userId;

    @Column(name = "ID_PRODUCT_FK")
    private Long productId;

    @Column(name = "NUM_TRANSACTION_TYPE")
    private Integer transactionType;

}
