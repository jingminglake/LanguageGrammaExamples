/**
*  设计同步容器，两个生产者线程，10个消费者线程
* 
*/

import java.util.*;
import java.util.concurrent.*;

class ProducerConsumer<T> {
    final private LinkedList<T> lists = new LinkedList<T>();
	final private int MAX = 10;
	private int count = 0;
    public synchronized void put(T t) {
		while (lists.size() == MAX){
			try {
				this.wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lists.add(t);
		count++;
		this.notifyAll();
    }
    public synchronized T get(){
		T t  = null;
		while (lists.size() == 0) {
			try{
				this.wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = lists.removeFirst();
		count--;
		this.notifyAll();
		return t;
    }
	public static void main(String[] args) {
		ProducerConsumer<String> pc = new ProducerConsumer<>();
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
