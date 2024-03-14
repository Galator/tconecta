package com.tconecta.loyalty.api.repository;

import com.tconecta.loyalty.api.entity.RewardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RewardRepository extends CrudRepository<RewardEntity, Long> {

    List<RewardEntity> findAll();

    Optional<RewardEntity> getByRewardId(Long rewardId);
}
