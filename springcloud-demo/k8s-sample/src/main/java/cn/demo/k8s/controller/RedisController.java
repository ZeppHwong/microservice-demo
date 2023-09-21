package cn.demo.k8s.controller;

import cn.demo.k8s.utils.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisController {
	@Autowired
	RedisHelper redisHelper;
	@GetMapping("redis/get/{key}")
	public String getRedisValue(@PathVariable String key){
		return (String) redisHelper.get(key);
	}

	@PostMapping("redis/put")
	public String putRedisValue(@RequestParam("key") String key,@RequestParam("value") String value){
		redisHelper.set(key,value);
		return "set ok...";
	}

	@PostMapping("redis/del/{key}")
	public String delRedisValue(@PathVariable String key){
		redisHelper.del(key);
		return "delete ok...";
	}
}
