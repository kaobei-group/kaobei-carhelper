package com.kaobei.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kaobei.dto.ParkDto;
import com.kaobei.entity.ParkEntity;

import java.util.List;

public interface ParkService {

    ParkEntity insertPark(ParkEntity parkEntity);

    ParkEntity findParkById(Long parkId);

    ParkEntity updateParkById(ParkEntity parkEntity);

    void deleteParkById(Long parkId);

    IPage findAreaParkPage(Long areaId,IPage iPage);




    /*
    获取 最近距离

    radius 某一半径内
    count 元素个数
     */
    List<ParkDto> findParkListByPosNear(Double lng, Double lat, Double radius, Integer count);


    /*
    关键词搜索
     */
    List<ParkDto> findParkByKeyword(String keyword, IPage iPage);

    List<ParkEntity> getInitializationParkList();


}
