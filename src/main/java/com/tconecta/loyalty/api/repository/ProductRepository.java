package com.tconecta.loyalty.api.repository;

import com.tconecta.loyalty.api.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    List<ProductEntity> findAll();

    List<ProductEntity> getByIsActive (Integer isActive);

    Optional<ProductEntity> getByProductId(Long productId);

}
