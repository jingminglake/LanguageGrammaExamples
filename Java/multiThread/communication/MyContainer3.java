/**
 *  实现一个容器，提供两个方法， add新元素和返回size
 *  写两个线程，线程1新增10个元素到容器中，线程2监视元素个数，当元素个数为5时候，线程2给出提示，并结束线程2
 *  此方法使用CountDownLatch，优点是替换复杂的wait、notify交互过程。原因是：只涉及到通信不涉及同步的时候，synchronized方法太重，此时不需要锁，只需要信号量即可。
 */

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CountDownLatch;
class MyContainer3 {
	// volatile使得t2能够得到通知
	volatile List lists = new ArrayList();
	public void add(Object o) {
		lists.add(o);
	}
	public int size() {
		return lists.size();
	}
	public static void main(String[] args) {
		MyContainer3 c = new MyContainer3();
		CountDownLatch latch = new CountDownLatch(1);

		
		new Thread(() -> {
				System.out.println("t2启动");
				if (c.size() != 5) {
					try {
						latch.await();
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("t2结束");
		}, "t2").start();

		
		new Thread(() -> {
				System.out.println("t1启动");
				for (int i = 0; i < 10; i++) {
					c.add(i);
					System.out.println("add " + i);
					if (c.size() == 5) {
						latch.countDown();
					}
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		}, "t1").start();
	}
}
