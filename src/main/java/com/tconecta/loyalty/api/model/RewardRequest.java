package com.tconecta.loyalty.api.model;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RewardRequest {

    @NotEmpty
    private String name;

    @Min(1)
    private Integer points;
}
