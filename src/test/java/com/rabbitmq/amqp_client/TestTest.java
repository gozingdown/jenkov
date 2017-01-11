package com.rabbitmq.amqp_client;

import static org.hamcrest.CoreMatchers.instanceOf;
import static com.rabbitmq.amqp_client.util.CustomMatcher.matches;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import com.zheng.rabbit.*;

public class TestTest {

	
	@Test
	public void testType() {
		com.zheng.rabbit.Test test =  new com.zheng.rabbit.Test();
		String result = test.concatenate("a", "b");
		assertThat(result, is("ab"));
		assertThat(result, instanceOf(java.lang.String.class));
		assertThat(result, matches("ab"));
	}
	
	@Test(expected = RuntimeException.class)
	public void testExeption() {
		com.zheng.rabbit.Test test =  new com.zheng.rabbit.Test();
		String result = test.concatenate("exception", "b");
	}

}
