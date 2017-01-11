package com.zheng.rabbit;
import java.util.Random;


public class TestStatic {
	public static int add(int x, int y) {
		return x + y;
	}

	public int getARandomNumber() {
		System.out.println("getARandomNumber() is called");
		Random rand = new Random();
		return rand.nextInt(10);
	}
}

