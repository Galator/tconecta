package com.tconecta.loyalty.api.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuyResponse {

    private Long buyId;
    private String product;
    private String user;

}
