package com.zheng.rabbit;

public class Dependency {
	public void test() {
		System.out.println("Dependency.test() is called");
	}
	
	public int addOne(int x) {
		return x + 1;
	}

}
