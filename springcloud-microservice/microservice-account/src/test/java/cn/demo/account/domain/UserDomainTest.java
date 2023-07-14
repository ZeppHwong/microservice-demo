package cn.demo.account.domain;

import cn.demo.account.AccountApplicationTest;
import cn.demo.account.infra.mapper.UserMapper;
import cn.demo.account.infra.valueobject.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

/**
 * UserDomain Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>7月 12, 2023</pre>
 */

@SpringBootTest(classes = {AccountApplicationTest.class}, webEnvironment = NONE)
@ActiveProfiles("test")
public class UserDomainTest {
    @Autowired
    UserMapper userMapper;

    @Test
    @DisplayName("测试分库分表插入数据")
    public void testInsertUser() {
        User user = new User();
        user.setUserId(102);
        user.setUserName("102");
        user.setPassword("102");
        user.setMobile("102");
        int result = userMapper.insertUser(user);

        assertEquals(1, result);
    }

} 
