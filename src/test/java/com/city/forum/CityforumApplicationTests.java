package com.city.forum;

import com.city.forum.mapper.UserMapper;
import com.city.forum.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CityforumApplicationTests {

	@Autowired
	UserMapper userMapper;


	@Test
	void contextLoads() {
		User user = userMapper.selectByUserAccount("11111111111");
		System.out.println(user);

	}

}
