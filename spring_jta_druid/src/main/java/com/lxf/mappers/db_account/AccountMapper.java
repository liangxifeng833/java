package com.lxf.mappers.db_account;
import com.lxf.bean.Account;
import org.apache.ibatis.annotations.Insert;
public interface AccountMapper {
    @Insert("INSERT INTO account(user_id,money) VALUES(#{userId},#{money})")
    void insert(Account account);
}
