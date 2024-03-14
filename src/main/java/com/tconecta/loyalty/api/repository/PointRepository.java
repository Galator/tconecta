package com.tconecta.loyalty.api.repository;

import com.tconecta.loyalty.api.entity.PointEntity;
import com.tconecta.loyalty.api.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PointRepository extends CrudRepository<PointEntity, Long> {

    List<PointEntity> findAll();

    Optional<PointEntity> getByUserId (UserEntity userId);

}
