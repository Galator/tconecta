package com.tconecta.loyalty.api.model;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RewardResponse {

    private Long id;
    private String name;
    private Integer points;
}
