package cn.demo.account.infra.mapper;

import cn.demo.account.infra.valueobject.User;
import cn.demo.account.infra.valueobject.UserDetailInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    int insertUser(User user);

    User getUserById(Integer userId);

    int insertUserDetailInfo(UserDetailInfo userDetailInfo);

    UserDetailInfo getUserDetailInfoById(Integer userId);
}
