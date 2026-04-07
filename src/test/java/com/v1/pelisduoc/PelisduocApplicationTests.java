package com.v1.pelisduoc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class PelisduocApplicationTests {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void dbConnectionWorks() {
		Integer result = jdbcTemplate.queryForObject("SELECT 1 FROM dual", Integer.class);
		assertEquals(1, result);
	}

}
