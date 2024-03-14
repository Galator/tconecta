package com.tconecta.loyalty.api.controller;

import com.tconecta.loyalty.api.model.PointModel;
import com.tconecta.loyalty.api.service.PointService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    private PointService pointService;

    @ApiOperation(value = "Consulta saldo", notes = "Consulta el saldo de puntos por usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. La consulta se hace de manera exitosa con resultados.", response = PointModel.class),
            @ApiResponse(code = 204, message = "No content. La consulta se hace de manera exitosa sin resultados conicidentes.")
    })
    @GetMapping(value = "/{userId}")
    public ResponseEntity<Object> checkBalance (@RequestParam(name = "userId") Long userId) {
        HttpStatus httpStatus = HttpStatus.OK;
        PointModel pointModel = pointService.getPointByUser(userId);
        if (null == pointModel) {
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(pointModel, httpStatus);
    }
}
