package com.soufang.esproject.repository;

import com.soufang.esproject.entity.HouseDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Description: es-project
 * Create by liangxifeng on 19-5-13
 */
public interface HouseDetailResposity extends CrudRepository<HouseDetail,Long> {
    HouseDetail findAllByHouseId(Long id);

    HouseDetail findByHouseId(Long id);

    List<HouseDetail> findAllByHouseIdIn(List<Long> houseIds);
}
