/**
 *  实现一个容器，提供两个方法， add新元素和返回size
 *  写两个线程，线程1新增10个元素到容器中，线程2监视元素个数，当元素个数为5时候，线程2给出提示，并结束线程2
 *  此方法使用volatile，优点是简单。缺点是浪费CPU，因为t2一直是死循环等待
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
