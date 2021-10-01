package com.tchokoapps.springboot.ecommerce.training;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan({"com.tchokoapps.springboot.ecommerce.training.services"})
class TrainingApplicationTests {

	@Test
	void contextLoads() {
	}

}
