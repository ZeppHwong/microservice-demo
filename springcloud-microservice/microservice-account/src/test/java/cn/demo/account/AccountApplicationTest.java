package cn.demo.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "cn.demo.account" )
@MapperScan("cn.demo.account.infra.mapper")
public class AccountApplicationTest {
}
