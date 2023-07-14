package cn.demo.account.domain;

import cn.demo.account.infra.mapper.UserMapper;
import cn.demo.account.infra.valueobject.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

/**
 * UserDomain Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>7月 12, 2023</pre>
 */

@ExtendWith(SpringExtension.class)
public class UserDomainTest {
//    @Autowired
//    UserMapper userMapper;

    @Mock
    UserMapper userMapper;
    @InjectMocks
    UserDomain userDomain;

    @Test
    @DisplayName("测试分库分表插入数据")
    public void testInsertUser() {
        User user = new User();
        user.setUserName("用户1");
        user.setUserId(103);
        user.setMobile("手机号1");
        user.setPassword("密码1");
        Mockito.when(userMapper.insertUser(user)).thenReturn(user.getUserId());
        int result = userDomain.insertUser(user);
        Mockito.verify(userMapper,times(1)).insertUser(user);
        assertEquals(103, result);
    }

    @Test
    @DisplayName("测试分库分表读取数据")
    public void testDomainFindUser() {
        User user = new User();
        user.setUserName("用户1");
        user.setUserId(103);
        user.setMobile("手机号1");
        user.setPassword("密码1");
        Mockito.when(userMapper.getUserById(103)).thenReturn(user);
        User findUser = userDomain.findUserById(103);
        Mockito.verify(userMapper,times(1)).getUserById(103);
        assertEquals(103, findUser.getUserId());
    }


} 
