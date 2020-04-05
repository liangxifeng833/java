package domain.controller;

import domain.domain.Area;
import domain.domain.DomainResponse;
import domain.service.interfaces.area.*;
import domain.util.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * 区域表对外curd接口
 * @author  liangxifeng 2018-02-07
 */
@RestController
@RequestMapping("/area")
//增加该注解
@Slf4j
public class AreaController {
    @Autowired
    private InsertAreaService insertService;
    @Autowired
    private UpdateAreaService updateServcie;
    @Autowired
    private GetOneAreaService getOneService;
    @Autowired
    private GetListAreaService getListServcie;
    @Autowired
    private DeleteAreaService deleteServcie;

    //新增
    @PostMapping
    public DomainResponse addByEntity(Area entity){
        log.info("=================================add Area = "+entity);
        return insertService.insert(entity);
    }
    //修改
    @PutMapping
    public DomainResponse updateByEntity(Area entity){
        log.info("=================================update Area = "+entity);
        return updateServcie.update(entity);
    }
    //根据主键删除区域
    @DeleteMapping(value = "{id}")
    public DomainResponse deleteById( @PathVariable(value = "id",required = true ) Long id ){
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
