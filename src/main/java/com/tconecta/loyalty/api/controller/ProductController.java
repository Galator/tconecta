package com.tconecta.loyalty.api.controller;

import com.tconecta.loyalty.api.model.ProductRequest;
import com.tconecta.loyalty.api.model.ProductResponse;
import com.tconecta.loyalty.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Object> productCreate (@RequestBody @Valid ProductRequest productRequest) {
        ProductResponse productResponse = productService.createProduct(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getProductAll() {
        HttpStatus httpStatus = HttpStatus.OK;
        List<ProductResponse> productResponseList = productService.getAll();
        if (productResponseList.isEmpty()){
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(productResponseList, httpStatus);
    }

    @GetMapping(value = "/active")
    public ResponseEntity<Object> getProductActive() {
        HttpStatus httpStatus = HttpStatus.OK;
        List<ProductResponse> productResponseList = productService.getByIsActive();
        if (productResponseList.isEmpty()){
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<>(productResponseList, httpStatus);
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<Object> getByProductId(@RequestParam(name = "productId") Long productId) {
        HttpStatus httpStatus = HttpStatus.OK;
        ProductResponse productResponse = productService.getProductById(productId);
        if (null == productResponse){
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<>(productResponse, httpStatus);
    }

    @PatchMapping(value = "/{productId}")
    public ResponseEntity<Object> disableProductById (@RequestParam(name = "productId") Long productId) {
        productService.disableProductById(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
