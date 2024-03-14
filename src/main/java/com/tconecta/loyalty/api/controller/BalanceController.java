package com.tconecta.loyalty.api.controller;

import com.tconecta.loyalty.api.model.PointModel;
import com.tconecta.loyalty.api.service.PointService;
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
