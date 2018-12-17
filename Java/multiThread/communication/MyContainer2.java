/**
 *  实现一个容器，提供两个方法， add新元素和返回size
 *  写两个线程，线程1新增10个元素到容器中，线程2监视元素个数，当元素个数为5时候，线程2给出提示，并结束线程2
 *  此方法使用synchronized，优点是不需要busy wait。缺点是wait、notify交互过程略复杂
 */

import java.util.*;
import java.util.concurrent.TimeUnit;
class MyContainer2 {
	// volatile使得t2能够得到通知
	volatile List lists = new ArrayList();
	public void add(Object o) {
		lists.add(o);
	}
	public int size() {
		return lists.size();
	}
	public static void main(String[] args) {
		MyContainer2 c = new MyContainer2();
		final Object lock = new Object();

		
		new Thread(() -> {
			synchronized(lock) {
				System.out.println("t2启动");
				if (c.size() != 5) {
					try {
						lock.wait();
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				// notify t1 because t1 will wait t2
				lock.notify();
				System.out.println("t2结束");
			}
		}, "t2").start();

		
		new Thread(() -> {
			synchronized(lock) {
				System.out.println("t1启动");
				for (int i = 0; i < 10; i++) {
					c.add(i);
					System.out.println("add " + i);
					if (c.size() == 5) {
						lock.notify(); // will not release the lock
						// release the lock with wait()
						try {
							lock.wait();
						} catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "t1").start();
	}
}
