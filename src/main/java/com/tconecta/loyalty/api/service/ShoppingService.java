package com.tconecta.loyalty.api.service;

import com.tconecta.loyalty.api.model.BuyRequest;
import com.tconecta.loyalty.api.model.BuyResponse;

import java.util.List;

public interface ShoppingService {

    BuyResponse shopping(BuyRequest buyRequest, Integer transactionType);

    List<BuyResponse> getShoppingByUserId (Long userId);

    List<BuyResponse> getShoppingAll (Integer transactionType);

}
