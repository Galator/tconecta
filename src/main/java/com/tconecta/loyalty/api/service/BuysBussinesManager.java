package com.tconecta.loyalty.api.service;

import com.tconecta.loyalty.api.model.BuyRequest;
import com.tconecta.loyalty.api.model.BuyResponse;

public interface BuysBussinesManager {

    BuyResponse buysProducts (BuyRequest buyRequest);

    BuyResponse redeemReward (BuyRequest buyRequest);
}
