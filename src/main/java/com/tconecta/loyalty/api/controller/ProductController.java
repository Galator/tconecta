package com.tconecta.loyalty.api.controller;

import com.tconecta.loyalty.api.model.ProductRequest;
import com.tconecta.loyalty.api.model.ProductResponse;
import com.tconecta.loyalty.api.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Alta producto", notes = "Dar de alta/crear un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created. El producto se creo con éxito.", response = ProductResponse.class),
    })
    @PostMapping
    public ResponseEntity<Object> productCreate (@RequestBody @Valid ProductRequest productRequest) {
        ProductResponse productResponse = productService.createProduct(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Consulta producto", notes = "Consulta los productos existentes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. La consulta se hace de manera exitosa con resultados.", response = ProductResponse.class),
            @ApiResponse(code = 204, message = "No content. La consulta se hace de manera exitosa sin resultados conicidentes.")
    })
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
