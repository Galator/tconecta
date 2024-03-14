package com.tconecta.loyalty.api.service;

import com.tconecta.loyalty.api.model.RewardRequest;
import com.tconecta.loyalty.api.model.RewardResponse;

import java.util.List;

public interface RewardService {

    RewardResponse createReward (RewardRequest rewardRequest);

    List<RewardResponse> findAll();

    RewardResponse getByRewardId(Long rewardId);

}
