/**
 *  
 *  
 *  此方法使用，优点是。缺点是
 */

import java.util.*;
import java.util.concurrent.TimeUnit;
class MyContainer1 {
	// volatile使得t2能够得到通知
	volatile List lists = new ArrayList();
	public void add(Object o) {
		lists.add(o);
	}
	public int size() {
		return lists.size();
	}
	public static void main(String[] args) {
		MyContainer c = new MyContainer();
		final Object lock = new Object();
		new Thread(() -> {
				System.out.println("t1启动");
				for (int i = 0; i < 10; i++) {
					c.add(i);
					System.out.println("add " + i);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		}, "t1").start();
		new Thread(() -> {
				System.out.println("t2启动");
				while (true) {
					if (c.size() == 5) break;
				}
				System.out.println("t2结束");
		}, "t2").start();
	}
}
