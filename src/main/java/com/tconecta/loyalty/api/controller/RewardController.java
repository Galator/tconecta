package com.tconecta.loyalty.api.controller;

import com.tconecta.loyalty.api.model.RewardRequest;
import com.tconecta.loyalty.api.model.RewardResponse;
import com.tconecta.loyalty.api.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/reward")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @PostMapping
    public ResponseEntity<Object> createReward (@RequestBody @Valid RewardRequest rewardRequest) {
        RewardResponse rewardResponse = rewardService.createReward(rewardRequest);
        return new ResponseEntity<>(rewardResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getRewardAll () {
        HttpStatus httpStatus = HttpStatus.OK;
        List<RewardResponse> rewardResponseList = rewardService.findAll();
        if (rewardResponseList.isEmpty()) {
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(rewardResponseList, httpStatus);
    }
}
