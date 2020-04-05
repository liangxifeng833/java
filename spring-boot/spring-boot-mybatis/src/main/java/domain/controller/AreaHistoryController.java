package domain.controller;

import domain.domain.Area;
import domain.domain.DomainResponse;
import domain.service.interfaces.area.GetListAreaService;
import domain.service.interfaces.area.GetOneAreaService;
import domain.service.interfaces.area.InsertAreaService;
import domain.service.interfaces.area.UpdateAreaService;
import domain.util.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 区域历史记录表对外curd接口
 * @author  liangxifeng 2018-02-07
 */
@RestController
@RequestMapping("/areaHistory")
//增加该注解
@Slf4j
public class AreaHistoryController {
    @Autowired
    private GetOneAreaService getOneService;
    @Autowired
    private GetListAreaService getListServcie;

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
