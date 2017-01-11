package com.rabbitmq.amqp_client;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.AdditionalMatchers.gt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.zheng.rabbit.Dependency;
import com.zheng.rabbit.UserDependency;


@RunWith(MockitoJUnitRunner.class)
public class MockTest {

	
	
	@Test
	public void testDependency() {
		Dependency dependencyMock = Mockito.mock(Dependency.class);
		when(dependencyMock.addOne(anyInt())).thenReturn(21);
		com.zheng.rabbit.Test test = new com.zheng.rabbit.Test(dependencyMock);
		test.callDependency();
		test.callDependencyPlusOne(1);
		verify(dependencyMock, times(1)).test();
		verify(dependencyMock, times(1)).addOne(anyInt());
		verify(dependencyMock).addOne(eq(1));
	}
	
	
	@Test
	public void testReturnValueDependentOnMethodParameter()  {
	        Comparable c= mock(Comparable.class);
	        when(c.compareTo("Mockito")).thenReturn(1);
	        //assert
	        assertEquals(1,c.compareTo("Mockito"));
	}
	
	
	@Test
	public void testSpy() {
		List<Integer> list = new LinkedList<>();
		List<Integer> spyList = spy(list);
		
		list.add(1);
		doReturn(2).when(spyList).get(0);
		int result = spyList.get(0);
		assertEquals(2, result);
		verify(spyList).get(anyInt());
	}
	
	
	
	/**
	 * Dependency Injection via Mockito
	 * http://stackoverflow.com/questions/12668289/mockito-injection-not-working-for-constructor-and-setter-mocks-together
	 * 
	 * NOTE: 
	 * the @InjectMocks annotation makes Mockito EITHER do constructor injection, OR setter/field injection, 
	 * but NEVER both. The rules around which will be chosen are quite complicated, which is one reason why I try to avoid using @InjectMocks whenever possible.
	 */
	@Mock
	Dependency dependencyMock;
	@Mock
	UserDependency userDependency;
	@InjectMocks
	private com.zheng.rabbit.Test test;
	
	@Test
	public void testInjectMocks() {
		
		when(dependencyMock.addOne(anyInt())).thenReturn(999);
		int result = this.test.callDependencyPlusOne(1);
		assertEquals(999, result);
		verify(dependencyMock).addOne(anyInt());
		
	}
	
	// This will FAIL! since constructor injection is already in use, you have to remove the constructor with arguments to make this possible
	@Ignore
	public void testInjectUserDependency() {
		
		when(userDependency.getUserId()).thenReturn(1112);
		int result = this.test.getUserDependencyUserId();
		assertEquals(1112, result);
		verify(userDependency).getUserId();
		
	}
	
	
	
	@Captor
	private ArgumentCaptor<Integer> captor;
	
	@Test
	public void testCaptor() {
		Dependency dependencyMock = Mockito.mock(Dependency.class);
		when(dependencyMock.addOne(1)).thenReturn(21);
		com.zheng.rabbit.Test test = new com.zheng.rabbit.Test(dependencyMock);
		int result = test.callDependencyPlusOne(1);
		assertEquals(21, result);
		verify(dependencyMock).addOne(gt(0));
		
		verify(dependencyMock).addOne(captor.capture());
		final int capturedArgument = captor.getValue();
		assertEquals(capturedArgument, 1);
	}
	
	
}

/**
 * Alternative to above code
 */
//public class MockTest {
//
//	@Mock
//	Dependency dependencyMock;
//	
//	@Rule
//	public MockitoRule mockitoRule = MockitoJUnit.rule();
//	
//	@Test
//	public void testDependency() {
//		com.zheng.rabbit.Test test = new com.zheng.rabbit.Test(dependencyMock);
//		test.callDependency();
//		verify(dependencyMock).test();
//	}
//	
//	
//	
//}
