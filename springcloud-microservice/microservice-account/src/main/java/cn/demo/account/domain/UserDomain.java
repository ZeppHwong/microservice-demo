package cn.demo.account.domain;


import cn.demo.account.infra.mapper.UserMapper;
import cn.demo.account.infra.valueobject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDomain {
    @Autowired
    UserMapper userMapper;

    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    public User findUserById(String id) {
        return userMapper.getUserById(id);
    }
}
