package com.rabbitmq.amqp_client;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterizedTestFields {

	@Parameter
	public int m1;
	@Parameter(value = 1)
	public int m2;

	// creates the test data
	@Parameters
	public static Collection<Integer[]> data() {
		Integer[][] data = new Integer[][] { { 1, 2 }, { 5, 3 }, { 121, 4 } };
		return Arrays.asList(data);
	}

	@Test
	public void testMultiplyException() {
		MyClass tester = new MyClass();
		assertEquals("Result", m1 * m2, tester.multiply(m1, m2));
	}

	// class to be tested
	class MyClass {
		public int multiply(int i, int j) {
			return i * j;
		}
	}

	public static void main(String[] args) {
		Integer[][] data = new Integer[][] { { 1, 2 }, { 5, 3 }, { 121, 4 } };
		List<Integer[]> list = Arrays.asList(data);
		for (Integer[] ol : list) {
			System.out.println(ol[0] + "-" + ol[1]);
		}
	}
}
