package com.tconecta.loyalty.api.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuyRequest {

    private Long productId;
    private Long userId;
}
