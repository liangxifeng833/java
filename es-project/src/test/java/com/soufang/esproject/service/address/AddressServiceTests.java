package com.soufang.esproject.service.address;

import com.soufang.esproject.ApplicationTests;
import com.soufang.esproject.service.ServiceResult;
import com.soufang.esproject.service.house.IAddressService;
import com.soufang.esproject.service.search.BaiduMapLocation;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by 瓦力.
 */
public class AddressServiceTests extends ApplicationTests {
    @Autowired
    private IAddressService addressService;

    /**
     * 通过指定城市和具体地址获取百度地图的经纬度
     */
    @Test
    public void testGetMapLocation() {
        String city = "北京";
        String address = "北京市昌平区巩华家园1号楼2单元";
        ServiceResult<BaiduMapLocation> serviceResult = addressService.getBaiduMapLocation(city, address);
        System.out.println(serviceResult.toString());

        Assert.assertTrue(serviceResult.isSuccess());

        Assert.assertTrue(serviceResult.getResult().getLongitude() > 0 );
        Assert.assertTrue(serviceResult.getResult().getLatitude() > 0 );

    }
}
