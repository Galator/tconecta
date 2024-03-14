package com.tconecta.loyalty.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {

    @NotEmpty
    private String name;

    @Min(1)
    private Integer points;

    @Min(1)
    private Double price;

}
