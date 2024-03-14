package com.tconecta.loyalty.api.service;

import com.tconecta.loyalty.api.entity.RewardEntity;
import com.tconecta.loyalty.api.model.RewardRequest;
import com.tconecta.loyalty.api.model.RewardResponse;
import com.tconecta.loyalty.api.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    @Override
    public RewardResponse createReward(RewardRequest rewardRequest) {
        RewardEntity rewardEntity = rewardRepository.save(convertRequestToEntity(rewardRequest));
        return convertEntityToResponse(rewardEntity);
    }

    @Override
    public List<RewardResponse> findAll() {
        List<RewardResponse> rewardResponseList = new ArrayList<>();

        rewardRepository.findAll().forEach(rewardEntity -> rewardResponseList.add(convertEntityToResponse(rewardEntity)));
        return rewardResponseList;
    }

    @Override
    public RewardResponse getByRewardId(Long rewardId) {
        return rewardRepository.getByRewardId(rewardId).map(this::convertEntityToResponse).orElse(null);
    }

    private RewardEntity convertRequestToEntity (RewardRequest rewardRequest) {
        return RewardEntity.builder()
                .name(rewardRequest.getName().trim())
                .amountPoint(rewardRequest.getPoints())
                .build();
    }

    private RewardResponse convertEntityToResponse (RewardEntity rewardEntity) {
        return RewardResponse.builder()
                .id(rewardEntity.getRewardId())
                .name(rewardEntity.getName())
                .points(rewardEntity.getAmountPoint())
                .build();
    }

}
