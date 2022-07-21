package com.example.demo.dao;

import lombok.extern.slf4j.Slf4j;
import com.example.demo.entity.Offer;
import com.example.demo.mapper.OfferMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * (offer)数据DAO
 *
 * @author kancy
 * @since 2022-06-30 15:44:48
 * @description 由 Mybatisplus Code Generator 创建
 */
@Slf4j
@Repository
public class OfferDao extends ServiceImpl<OfferMapper, Offer> {

}