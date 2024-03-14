package com.tconecta.loyalty.api.repository;

import com.tconecta.loyalty.api.entity.ShoppingEntity;
import com.tconecta.loyalty.api.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingRepository extends CrudRepository<ShoppingEntity, Long> {

    List<ShoppingEntity> findAll();

    List<ShoppingEntity> getByUserId (UserEntity userId);

    List<ShoppingEntity> getByProductId(Long productId);

    List<ShoppingEntity> getByTransactionType (Integer transactionType);

}
