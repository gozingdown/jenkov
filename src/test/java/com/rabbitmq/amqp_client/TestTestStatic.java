package com.rabbitmq.amqp_client;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.zheng.rabbit.TestStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TestStatic.class)
public class TestTestStatic {
	
	@Test
	public void testStaticMethod() {
		PowerMockito.mockStatic(TestStatic.class);
	
		Mockito.when(TestStatic.add(1, 2)).thenReturn(4);
		int result = TestStatic.add(1, 2);
		assertEquals(4, result);
		PowerMockito.verifyStatic(Mockito.times(1));
		TestStatic.add(eq(1),eq(2));
		
	}
	
	@Test
	public void testPartialMocking(){
		TestStatic testStaticMock = PowerMockito.spy(new TestStatic());
		doReturn(222).when(testStaticMock).getARandomNumber();
		// this will call the acutal getARandomNumber()
		//Mockito.when(testStaticMock.getARandomNumber()).thenReturn(222);
		
		int result = testStaticMock.getARandomNumber();
		System.out.println("Result = "+ result);
		assertEquals(222, result);
		verify(testStaticMock, times(1)).getARandomNumber();
		
		//wrong:spyList is yet empty, so IndexOutOfBoundsException is thrown
//		List<Integer> list = new LinkedList<>();
//		List<Integer> spyList = PowerMockito.spy(list);
//		Mockito.when(spyList.get(0)).thenReturn(1);
//		assertThat(spyList.get(0), equalTo(1));
	}
}
