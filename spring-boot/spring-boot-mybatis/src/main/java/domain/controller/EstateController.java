package domain.controller;

import domain.domain.DomainResponse;
import domain.domain.Estate;
import domain.service.interfaces.estate.*;
import domain.util.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;

/**
 * 物业资源表表对外curd接口
 * @author  liangxifeng 2018-02-07
 */
@RestController
@RequestMapping("/estate")
//增加该注解
@Slf4j
public class EstateController {
    @Autowired
    private InsertEstateService insertService;
    @Autowired
    private UpdateEstateService updateServcie;
    @Autowired
    private GetOneEstateService getOneService;
    @Autowired
    private GetListEstateService getListServcie;
    @Autowired
    private DeleteEstateService deleteServcie;

    //新增
    @PostMapping
    public DomainResponse addByEntity(Estate entity){
        log.info("=================================add Area = "+entity);
        return insertService.insert(entity);
    }
    //修改
    @PutMapping
    public DomainResponse updateByEntity(Estate entity){
        log.info("=================================update Area = "+entity);
        return updateServcie.update(entity);
    }
    //根据主键删除区域
    @DeleteMapping(value = "{id}")
    public DomainResponse deleteById(@PathVariable(value = "id",required = true ) Long id ){
        return deleteServcie.delete(id);
    }


    //根据主键进行查询
    @GetMapping(value="{id}")
    public DomainResponse getOneByEntity(@PathVariable(value = "id",required = true) Long id){
        return getOneService.getOne(id);
    }
    //查询多条记录
    @GetMapping
    public DomainResponse getList(@RequestParam Map<String,Object> params) throws Exception {
        //判断参数是否为空
        if(params.isEmpty())
        {
            return  new DomainResponse<Integer>(10,"参数不能为空",0);
        }

        Map paramsMap = JsonTool.paramsToMap(params);
        return getListServcie.getList(paramsMap);
    }
}
