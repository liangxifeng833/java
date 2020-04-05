package domain.controller;

import domain.Excetpion.NotFountException;
import domain.domain.Area;
import domain.domain.DomainResponse;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试返回ResponseEntity实体
 * @author liangxifeng 2018-02-10
 */
@RestController
@RequestMapping("/testResponse")
public class TestResonse {
    Map<String, User> areas = Collections.synchronizedMap(new HashMap<String, User>());

    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getUserById (@PathVariable("id") String id) throws Exception {
        Area area = new Area();
        area.setAreaName("东听家具");
        area = null;
        if (area == null){
            HttpStatus status = HttpStatus.NOT_FOUND;
            DomainResponse resDomain = new DomainResponse(4,"数据为null","ok");
            //return new ResponseEntity<DomainResponse>(resDomain,status);
            throw new Exception("user is not found");
            //throw new NotFountException(12L);
        }

        return new ResponseEntity<Area>(area,HttpStatus.OK);
    }
    @ExceptionHandler(NotFountException.class)
    public ResponseEntity<DomainResponse> UserNotFound(NotFountException e){
        long id = e.getId();
        DomainResponse error = new DomainResponse(4,"id = "+id+" not found",0);
        return new ResponseEntity<DomainResponse>(error, HttpStatus.NOT_FOUND);
    }

}
