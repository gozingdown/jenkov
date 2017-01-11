package com.rabbitmq.amqp_client;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.rabbitmq.amqp_client.util.CustomRule;

public class TestTest2 {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	@Rule
	public CustomRule customRule = new CustomRule();
	
	@Test
	public void testConcatenate() {
		com.zheng.rabbit.Test test =  new com.zheng.rabbit.Test();
		String result = test.concatenate("a", "b");
		assertEquals("ab", result);
		
	}
	
	@Test
	public void testRules() {
		exception.expect(RuntimeException.class);
		exception.expectMessage("test this exception");
		com.zheng.rabbit.Test test =  new com.zheng.rabbit.Test();
		String result = test.concatenate("exception", "b");
	}
	
	@Test
	public void testCustomRule() {
		com.zheng.rabbit.Test test =  new com.zheng.rabbit.Test();
		String result = test.concatenate("a", "b");
	}
}
