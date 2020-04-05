package com.soufang.esproject.web.controller.user;

import com.soufang.esproject.base.ApiResponse;
import com.soufang.esproject.base.HouseSubscribeStatus;
import com.soufang.esproject.base.LoginUserUtil;
import com.soufang.esproject.service.IUserService;
import com.soufang.esproject.service.ServiceMultiResult;
import com.soufang.esproject.service.ServiceResult;
import com.soufang.esproject.service.house.IHouseService;
import com.soufang.esproject.web.dto.HouseDTO;
import com.soufang.esproject.web.dto.HouseSubscribeDTO;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Description: es-project
 * Create by liangxifeng on 19-4-25
 */
@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IHouseService houseService;
    /**
     * 用户登录页面
     * @return
     */
    @GetMapping("/user/login")
    public String loginPage(){
        return "user/login";
    }

    @PostMapping("user/infoNew")
    @ResponseBody
    public ApiResponse testLxf() {
        return ApiResponse.ofSuccess("nihao");
    }

    /**
     * 用户中心页面
     * @return
     */
    @GetMapping("/user/center")
    public String center(){
        return "user/center";
    }

    /**
     * 通过用户指定字段值修改用户表指定字段
     * @param profile
     * @param value
     * @return
     */
    @PostMapping("user/info")
    @ResponseBody
    public ApiResponse updateUserInfo(@RequestParam(value = "profile") String profile,
                                      @RequestParam(value = "value") String value) {
        if (value.isEmpty()) {
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }

        if ("email".equals(profile) && !LoginUserUtil.checkEmail(value)) {
            return ApiResponse.ofMessage(HttpStatus.SC_BAD_REQUEST, "不支持的邮箱格式");
        }

        ServiceResult result = userService.modifyUserProfile(profile, value);
        if (result.isSuccess()) {
            return ApiResponse.ofSuccess("");
        } else {
            return ApiResponse.ofMessage(HttpStatus.SC_BAD_REQUEST, result.getMessage());
        }

    }

    /**
     * 预约房源操作
     * @param houseId
     * @return
     */
    @PostMapping(value = "user/house/subscribe")
    @ResponseBody
    public ApiResponse subscribeHouse(@RequestParam(value = "house_id") Long houseId) {
        ServiceResult result = houseService.addSubscribeOrder(houseId);
        if (result.isSuccess()) {
            return ApiResponse.ofSuccess("");
        } else {
            return ApiResponse.ofMessage(HttpStatus.SC_BAD_REQUEST, result.getMessage());
        }
    }

    /**
     * 我的预约房源列表
     * @param start
     * @param size
     * @param status
     * @return
     */
    @GetMapping(value = "user/house/subscribe/list")
    @ResponseBody
    public ApiResponse subscribeList(
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam(value = "status") int status) {
        ServiceMultiResult<Pair<HouseDTO, HouseSubscribeDTO>> result = houseService.querySubscribeList(HouseSubscribeStatus.of(status), start, size);

        if (result.getResultSize() == 0) {
            return ApiResponse.ofSuccess(result.getResult());
        }

        ApiResponse response = ApiResponse.ofSuccess(result.getResult());
        response.setMore(result.getTotal() > (start + size));
        return response;
    }

    /**
     * 预约房源
     * @param houseId
     * @param orderTime
     * @param desc
     * @param telephone
     * @return
     */
    @PostMapping(value = "user/house/subscribe/date")
    @ResponseBody
    public ApiResponse subscribeDate(
            @RequestParam(value = "houseId") Long houseId,
            @RequestParam(value = "orderTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderTime,
            @RequestParam(value = "desc", required = false) String desc,
            @RequestParam(value = "telephone") String telephone
    ) {
        if (orderTime == null) {
            return ApiResponse.ofMessage(HttpStatus.SC_BAD_REQUEST, "请选择预约时间");
        }

        if (!LoginUserUtil.checkTelephone(telephone)) {
            return ApiResponse.ofMessage(HttpStatus.SC_BAD_REQUEST, "手机格式不正确");
        }

        ServiceResult serviceResult = houseService.subscribe(houseId, orderTime, telephone, desc);
        if (serviceResult.isSuccess()) {
            return ApiResponse.ofStatus(ApiResponse.Status.SUCCESS);
        } else {
            return ApiResponse.ofMessage(HttpStatus.SC_BAD_REQUEST, serviceResult.getMessage());
        }
    }
}
