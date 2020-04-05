package com.soufang.esproject.service.user;

import com.google.common.collect.Lists;
import com.soufang.esproject.base.LoginUserUtil;
import com.soufang.esproject.service.IUserService;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import com.soufang.esproject.entity.Role;
import com.soufang.esproject.entity.User;
import com.soufang.esproject.repository.RoleReposity;
import com.soufang.esproject.repository.UserRepository;
import com.soufang.esproject.service.ServiceResult;
import com.soufang.esproject.web.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description: es-project
 * Create by liangxifeng on 19-4-20
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleReposity roleReposity;
    private ModelMapper modelMapper = new ModelMapper();

    private final Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

    @Override
    public User findUserByName(String userName) {
        User user = userRepository.findByName(userName);
        if( user == null ){
            return null;
        }
        List<Role> roles = roleReposity.findRolesByUserId(user.getId());
        if( roles == null || roles.isEmpty() ){
            throw new DisabledException("权限非法");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        //java8的流式方法, 使用lavb表达式
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName())));
        user.setAuthorityList(authorities);
        return user;
    }

    @Override
    public ServiceResult<UserDTO> findById(Long userId) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            return ServiceResult.notFound();
        }
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return ServiceResult.of(userDTO);
    }

    @Override
    public User findUserByTelephone(String telephone) {
        User user = userRepository.findUserByPhoneNumber(telephone);
        if (user == null) {
            return null;
        }
        List<Role> roles = roleReposity.findRolesByUserId(user.getId());
        if (roles == null || roles.isEmpty()) {
            throw new DisabledException("权限非法");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        user.setAuthorityList(authorities);
        return user;
    }

    @Override
    @Transactional
    public User addUserByPhone(String telephone) {
        User user = new User();
        user.setPhoneNumber(telephone);
        user.setName(telephone.substring(0, 3) + "****" + telephone.substring(7, telephone.length()));
        Date now = new Date();
        user.setCreateTime(now);
        user.setLastLoginTime(now);
        user.setLastUpdateTime(now);
        user = userRepository.save(user);

        Role role = new Role();
        role.setName("USER");
        role.setUserId(user.getId());
        roleReposity.save(role);
        user.setAuthorityList(Lists.newArrayList(new SimpleGrantedAuthority("ROLE_USER")));
        return user;
    }

    /**
     * 根据指定属性值修改目标value
     * @param profile 指定属性名
     * @param value　修改的值
     * @return
     */
    @Override
    @Transactional
    public ServiceResult modifyUserProfile(String profile, String value) {
        Long userId = LoginUserUtil.getLoginUserId();
        if (profile == null || profile.isEmpty()) {
            return new ServiceResult(false, "属性不可以为空");
        }
        switch (profile) {
            case "name":
                userRepository.updateUsername(userId, value);
                break;
            case "email":
                userRepository.updateEmail(userId, value);
                break;
            case "password":
                userRepository.updatePassword(userId, this.passwordEncoder.encodePassword(value, userId));
                break;
            default:
                return new ServiceResult(false, "不支持的属性");
        }
        return ServiceResult.success();
    }
}
