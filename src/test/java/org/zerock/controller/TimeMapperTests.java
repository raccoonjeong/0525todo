package org.zerock.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.mapper.TimeMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
public class TimeMapperTests {
	
	@Setter(onMethod_= {@Autowired})
	private TimeMapper mapper;
	
	@Test
	public void getTime() {
		log.info(mapper.getTime());
	}
	
}
