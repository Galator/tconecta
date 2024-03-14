package com.tconecta.loyalty.api.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private Double price;
    private Integer points;
    private Boolean isActive;

}
