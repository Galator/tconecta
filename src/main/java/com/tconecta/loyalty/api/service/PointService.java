package com.tconecta.loyalty.api.service;

import com.tconecta.loyalty.api.model.PointModel;

import java.util.List;

public interface PointService {

    PointModel saveUpdateUserPoint (Long userId, Integer points);

    PointModel updatePoint (PointModel pointModel, Long userId);

    PointModel getPointByUser (Long userId);

    List<PointModel> getAll ();
}
