/**
 *  设计同步容器，两个生产者线程，10个消费者线程
 *  使用reentrantLock 和 condition, condtions 可以更加精确的指定哪个线程被唤醒
 */

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

class ProducerConsumer3<T> {
    final private LinkedList<T> lists = new LinkedList<T>();
	final private int MAX = 10;
	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition producer = lock.newCondition();
	private Condition consumer = lock.newCondition();
    public void put(T t) {
		try {
			lock.lock();
			while (lists.size() == MAX) {
				producer.await();
			}
			lists.add(t);
			count++;
			consumer.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	public T get(){
		T t  = null;
		try {
			lock.lock();
			while (lists.size() == 0) {
				consumer.await();
			}
			t = lists.removeFirst();
			count--;
			producer.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return t;
	}
	public static void main(String[] args) {
		ProducerConsumer3<String> pc = new ProducerConsumer3<>();
		// 10 consumer
		for (int i = 0; i < 10; i++) {
			new Thread(()->{
					// consume 5 product
					for (int j = 0; j < 5; j++) {
						System.out.println(Thread.currentThread().getName() + " consumes: " + pc.get());
					}
			}, "Consumer" + i).start();
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		// 2 producer
		for (int i = 0; i < 2; i++) {
			new Thread(()->{
					// provide 25 product
					for (int j = 0; j < 25; j++) {
						pc.put(Thread.currentThread().getName() + "_product" + j);
					}
			}, "Producer" + i).start();
		}
	}
}
