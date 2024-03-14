package com.tconecta.loyalty.api.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "AUX_POINT")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_POINT_PK")
    private Long pointId;

    @Column(name = "NUM_AMOUNT")
    private Integer amount;

    @OneToOne
    @JoinColumn(name = "ID_USER_FK", referencedColumnName = "ID_USER_PK")
    private UserEntity userId;
}
