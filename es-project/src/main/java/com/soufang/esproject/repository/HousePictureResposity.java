package com.soufang.esproject.repository;

import com.soufang.esproject.entity.HousePicture;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * Description: es-project
 * Create by liangxifeng on 19-5-13
 */
public interface HousePictureResposity extends CrudRepository<HousePicture,Long> {
    List<HousePicture> findAllByHouseId(Long id);
}
