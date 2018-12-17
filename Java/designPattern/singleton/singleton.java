/*
 *  // 不用锁，也能实现懒加载（不用一开始加载，在需要时候加载）
 *  
**/

import java.util.*;

class Singleton {
	private Singleton() {
		System.out.println("signle");
	}
	private static class Inner {
		private static Singlton s = new Singleton();
	}
	public static Singleton getSingle() {
		return Inner.s;
	}
}
