package cn.demo.account.infra;

import cn.demo.account.infra.mapper.UserMapper;
import cn.demo.account.infra.valueobject.User;
import cn.demo.account.infra.valueobject.UserDetailInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserMapperTest {

//    @Autowired
//    DataSource dataSource;
    @Autowired
    UserMapper userMapper;

    @Test
    @DisplayName("测试分库分表插入数据")
    public void testInsertUser() {
        User user = new User();
        user.setUserId(104);
        user.setUserName("104");
        user.setPassword("104");
        user.setMobile("104");
        int result = userMapper.insertUser(user);
        assertEquals(1, result);
    }

    @Test
    @DisplayName("测试分库分表读取数据")
    public void testFindUser() {
        User findUser = userMapper.getUserById(104);
        assertEquals(104, findUser.getUserId());
    }

    @Test
    @DisplayName("测试分库分表插入数据")
    public void testInsertUserDetailInfo() {
        UserDetailInfo userDetailInfo = new UserDetailInfo();
        userDetailInfo.setUserId(104);
        userDetailInfo.setAddress("shanghai");
        userDetailInfo.setAge(20);
        int result = userMapper.insertUserDetailInfo(userDetailInfo);
        assertEquals(1, result);
    }

    @Test
    @DisplayName("测试分库分表插入数据")
    public void testfindUserDetailInfo() {
        UserDetailInfo findUser = userMapper.getUserDetailInfoById(104);
        assertEquals(104, findUser.getUserId());
    }
}
