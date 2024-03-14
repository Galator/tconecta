package com.tconecta.loyalty.api.service;

import com.tconecta.loyalty.api.model.ProductRequest;
import com.tconecta.loyalty.api.model.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductResponse createProduct (ProductRequest productRequest);

    List<ProductResponse> getAll ();

    List<ProductResponse> getByIsActive ();

    ProductResponse getProductById (Long productId);

    void disableProductById (Long productId);

}
