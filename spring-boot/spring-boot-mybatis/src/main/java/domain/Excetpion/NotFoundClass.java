package domain.Excetpion;

import domain.domain.DomainResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 定义NotFountException 的错误处理器
 * @author liangxifeng 2018-02-10
 */
public class NotFoundClass  {
    @ExceptionHandler(NotFountException.class)
    public ResponseEntity<DomainResponse> UserNotFound(NotFountException e){
        long id = e.getId();
        DomainResponse error = new DomainResponse(4,"id = "+id+" not found",0);
        return new ResponseEntity<DomainResponse>(error, HttpStatus.NOT_FOUND);
    }
}
