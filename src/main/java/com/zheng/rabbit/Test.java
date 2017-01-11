package com.zheng.rabbit;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.Stack;
import java.util.TreeMap;

public class Test {

	private Dependency dependency = null;
	private UserDependency userDependency = null;

	public Test() {

	}

	public Test(Dependency dependency) {
		this.dependency = dependency;
	}

	public void callDependency() {
		this.dependency.test();
	}

	public void setUserDependency(UserDependency userDependency) {
		this.userDependency = userDependency;
	}

	public int getUserDependencyUserId() {
		return this.userDependency.getUserId();
	}

	public int callDependencyPlusOne(int x) {
		return this.dependency.addOne(x);
	}

	public static <T> T addAndReturn(T element, Collection<T> collection) {
		collection.add(element);
		return element;
	}

	public static void iterate(List<? extends A> list) {
		for (A a : list) {
			System.out.println(a);
		}
	}

	public String concatenate(String x, String y) {
		if (x == null || y == null) {
			return null;
		}
		if (x.toLowerCase().equals("exception")) {
			throw new RuntimeException("test this exception");
		}
		return x + y;
	}

	public static void main(String[] args)
	        throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		// List<A> list = new LinkedList<>();
		List<B> list = new LinkedList<>();
		// list.add(new A());
		list.add(new B());
		Test.iterate(list);

		System.out.println("---------------");
		A a = new A();
		Field[] fields = A.class.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
			field.setAccessible(true);
			System.out.println(field.get(a));
		}
		System.out.println("---------------");
		Method[] methods = A.class.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
			if (method.getName().equals("nothing")) {
				System.out.println(method.invoke(a, null));
			}
			// method.setAccessible(true);
			// System.out.println(method.invoke(a, null));
		}
		Collection list2 = new ArrayList<A>();
		Collection list3 = new ArrayList<A>();
		A a1 = new A();
		A a2 = new A();
		A a3 = new A();
		list2.add(a1);
		list2.add(a2);
		list3.addAll(list2);
		System.out.println(list3.contains(a3));
		Iterator i = list3.iterator();

		Map map = new HashMap<>();
		map.put("first", "value");
		map.put(1, 2);
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			Object obj = iterator.next();
			if (obj instanceof String) {
				System.out.println("yes");

			}
			System.out.println(map.get(obj));
		}

		Map<Integer, String> map1 = new TreeMap<>();
		map1.put(1, "a");
		map1.put(2, "a");
		map1.put(3, "a");
		SortedMap<Integer, String> headMap1 = ((SortedMap) map1).headMap(3);
		for (Integer k : headMap1.keySet()) {
			System.out.println(k);
		}

		Deque<Integer> deque = new LinkedList<>();
		deque.addLast(1);
		deque.addLast(2);
		deque.addLast(3);
		deque.addLast(4);
		deque.removeLast();
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println(stack.search(3));

		List<Integer> list4 = new LinkedList<>();
		list4.add(1);
		list4.add(1);
		list4.add(1);
		list4.add(1);
		int sum = list4.stream().reduce((acc, x) -> acc + x).get();
		// 2nd approach
		sum = list4.stream().reduce(0, (acc, x) -> acc + x);
		System.out.println(sum);

		System.out.println("a" instanceof java.lang.String);

		List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		for (int ele : intList) {
			System.out.print(ele + ",");
		}
	}

}

class A {
	private int a = 1;
	public int b = 2;

	private String getStr() {
		return "hello";
	}

	public String toString() {
		return "class A!";
	}

	public void nothing() {
		System.out.println("nothing!");
	}
}

class B extends A {
	public B() {
		super();
	}

	public String toString() {
		return "class B!";
	}
}
