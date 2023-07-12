package cn.demo.account.infra.mapper;

import cn.demo.account.infra.valueobject.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insertUser(User user);

    User getUserById(String userId);
}
