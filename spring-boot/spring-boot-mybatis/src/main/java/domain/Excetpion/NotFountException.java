package domain.Excetpion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义没有查找到实体异常
 */
public class NotFountException extends RuntimeException {
    private long id;
    public NotFountException(long id){
        this.id = id;
    }

    public void setId(long id)
    {
        this.id = id;
    }
    public long getId(){
        return this.id;
    }

}
