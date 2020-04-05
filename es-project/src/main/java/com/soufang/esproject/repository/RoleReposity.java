package com.soufang.esproject.repository;

import com.soufang.esproject.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 角色数据DAO
 * Description: es-project
 * Create by liangxifeng on 19-4-25
 */
public interface RoleReposity extends CrudRepository<Role,Long>{
    List<Role> findRolesByUserId(Long userId);
}
