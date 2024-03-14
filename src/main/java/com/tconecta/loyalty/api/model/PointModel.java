package com.tconecta.loyalty.api.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointModel {

    private Long id;
    private String user;
    private Integer amount;
}
