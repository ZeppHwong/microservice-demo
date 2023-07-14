package cn.demo.account.application;


import cn.demo.account.domain.UserDomain;
import cn.demo.account.infra.valueobject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserContrller {
    @Autowired
    UserDomain userDomain;
    @PostMapping("user/createUser")
    public User createUser(@RequestBody User user){
      int result = userDomain.insertUser(user);
      return user;
    }


    @GetMapping("user/find/{userId}")
    public User findUser(@PathVariable("userId") Integer userId){
       return  userDomain.findUserById(userId);
    }
}
