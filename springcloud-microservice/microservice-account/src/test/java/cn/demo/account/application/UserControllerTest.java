package cn.demo.account.application;

import cn.demo.account.domain.UserDomain;
import cn.demo.account.infra.mapper.UserMapper;
import cn.demo.account.infra.valueobject.User;
import com.alibaba.fastjson2.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/** 
* UserContrller Tester. 
* 
* @author <Authors name> 
* @since <pre>7月 14, 2023</pre> 
* @version 1.0 
*/

//@AutoConfigureMockMvc
//@SpringBootTest
@WebMvcTest(UserContrller.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDomain userDomain;

    @MockBean
    private UserMapper userMapper;
    /**
     * .perform() : 执行一个MockMvcRequestBuilders的请求；MockMvcRequestBuilders有.get()、.post()、.put()、.delete()等请求。
     * .andDo() : 添加一个MockMvcResultHandlers结果处理器,可以用于打印结果输出(MockMvcResultHandlers.print())。
     * .andExpect : 添加MockMvcResultMatchers验证规则，验证执行结果是否正确。
     */
    @Test
    @DisplayName("测试Controller创建用户")
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setUserName("用户1");
        user.setUserId(1);
        user.setMobile("手机号1");
        user.setPassword("密码1");
        Mockito.when(userDomain.insertUser(user)).thenReturn(user.getUserId());
        this.mockMvc.perform(
                post("/user/createUser")
                        .content(JSONObject.toJSONString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId",is(1)));
    }

    @Test
    @DisplayName("测试Controller查询用户")
    public void testFindUser() throws Exception {
        User user = new User();
        user.setUserName("用户1");
        user.setUserId(1);
        user.setMobile("手机号1");
        user.setPassword("密码1");
        Mockito.when(userDomain.findUserById(Mockito.anyInt())).thenReturn(user);
        this.mockMvc.perform(get("/user/find/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId",is(1)));
    }
} 
