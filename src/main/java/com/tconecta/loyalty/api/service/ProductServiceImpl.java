package com.tconecta.loyalty.api.service;

import com.tconecta.loyalty.api.entity.ProductEntity;
import com.tconecta.loyalty.api.model.ProductRequest;
import com.tconecta.loyalty.api.model.ProductResponse;
import com.tconecta.loyalty.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Integer PRODUCT_STATUS_ACTIVE = 1;
    private static final Integer PRODUCT_STATUS_INACTIVE = 0;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {

        ProductEntity productEntity = productRepository.save(convertRequestToEntity(productRequest));
        return convertEntityToResponse(productEntity);
    }

    @Override
    public List<ProductResponse> getAll() {

        List<ProductResponse> productResponseList = new ArrayList<>();
        productRepository.findAll().forEach(productEntity -> productResponseList.add(convertEntityToResponse(productEntity)));

        return productResponseList;
    }

    @Override
    public List<ProductResponse> getByIsActive() {
        List<ProductResponse> productResponseList = new ArrayList<>();
        productRepository.getByIsActive(PRODUCT_STATUS_ACTIVE).forEach(productEntity -> productResponseList.add(convertEntityToResponse(productEntity)));

        return productResponseList;
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        return productRepository.getByProductId(productId).map(this::convertEntityToResponse).orElse(null);
    }

    @Override
    public void disableProductById(Long productId) {
        productRepository.getByProductId(productId).map(productEntity -> {
            productEntity.setIsActive(PRODUCT_STATUS_INACTIVE);
            return productRepository.save(productEntity);
        });
    }

    private ProductResponse convertEntityToResponse (ProductEntity productEntity) {
        return ProductResponse.builder()
                .id(productEntity.getProductId())
                .name(productEntity.getProductName())
                .price(productEntity.getPrice())
                .points(productEntity.getRewardPoint())
                .isActive(productEntity.getIsActive() == 0 ? Boolean.FALSE : Boolean.TRUE)
                .build();
    }

    private ProductEntity convertRequestToEntity (ProductRequest productRequest) {
        return ProductEntity.builder()
                .price(productRequest.getPrice())
                .isActive(PRODUCT_STATUS_ACTIVE)
                .productName(productRequest.getName().trim().toUpperCase())
                .rewardPoint(productRequest.getPoints())
                .build();
    }
}
