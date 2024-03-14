package com.tconecta.loyalty.api.repository;

import com.tconecta.loyalty.api.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> getByUserId(Long userId);

    Optional<UserEntity> getByUserName(String userName);

    List<UserEntity> findAll();
}
