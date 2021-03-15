package com.lxf.mappers.db_user;
import com.lxf.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface UserMapper {
    @Insert("INSERT INTO user(id,name) VALUES(#{id},#{name})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    public void insert(User user);
}
