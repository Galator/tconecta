package com.tconecta.loyalty.api.service;

import com.tconecta.loyalty.api.entity.PointEntity;
import com.tconecta.loyalty.api.entity.UserEntity;
import com.tconecta.loyalty.api.model.PointModel;
import com.tconecta.loyalty.api.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PointServiceImpl implements PointService {

    @Autowired
    private PointRepository pointRepository;

    @Override
    public PointModel saveUpdateUserPoint(Long userId, Integer points) {
        UserEntity userEntity = UserEntity.builder().userId(userId).build();
        Optional<PointEntity> pointGetEntity = pointRepository.getByUserId(UserEntity.builder().userId(userId).build());
        PointEntity pointEntitySave = new PointEntity();
        if (pointGetEntity.isPresent()) {
            pointEntitySave = pointGetEntity.get();
            pointEntitySave.setAmount(pointGetEntity.get().getAmount() + points);
            pointRepository.save(pointEntitySave);
        } else {
            pointEntitySave = pointRepository.save(PointEntity.builder()
                    .amount(points)
                    .userId(userEntity)
                    .build());
        }

        return converterEntityToModel(pointEntitySave);
    }

    @Override
    public PointModel updatePoint(PointModel pointModel, Long userId) {
        PointEntity pointEntitySave = pointRepository.save(converterModelToEntity(pointModel, userId));
        return converterEntityToModel(pointEntitySave);
    }

    @Override
    public PointModel getPointByUser(Long userId) {
        return pointRepository.getByUserId(UserEntity.builder().userId(userId).build()).map(this::converterEntityToModel).orElse(null);
    }

    @Override
    public List<PointModel> getAll() {
        List<PointModel> pointModelList = new ArrayList<>();
        pointRepository.findAll().forEach(pointEntity -> pointModelList.add(converterEntityToModel(pointEntity)));
        return pointModelList;
    }

    private PointModel converterEntityToModel (PointEntity pointEntity) {
        return PointModel.builder()
                .id(pointEntity.getPointId())
                .amount(pointEntity.getAmount())
                .user(pointEntity.getUserId().getUserName())
                .build();
    }

    private PointEntity converterModelToEntity (PointModel pointModel, Long userId) {
        return PointEntity.builder()
                .pointId(pointModel.getId())
                .userId(UserEntity.builder().userId(userId).build())
                .amount(pointModel.getAmount())
                .build();
    }

}
