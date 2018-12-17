/**
 *   两个线程交替打印1到10.
 *
 *  思路：1. 生成两个线程，奇线程依次打印1，3，5...，偶线程依次打印2, 4, 6...
 *       2. 临界区设置为一个bool变量， 拿到该bool变量的线程，如果该bool变量表示当前打印的是奇数，那么奇线程阻塞自己；否则，打印自己并唤醒偶线程
*/

import java.util.*;

public class PrintEvenOdd {
    
	public static void main(String[] args) {
		Printer print = new Printer();
		Thread t1 = new Thread(new TaskEvenOdd(print, 10, false));
		Thread t2 = new Thread(new TaskEvenOdd(print, 10, true));
		t1.start();
		t2.start();
	}
    
}
    
class TaskEvenOdd implements Runnable {
    
	private int max;
	private Printer print;
	private boolean isEvenNumber;
    
	TaskEvenOdd(Printer print, int max, boolean isEvenNumber) {
		this.print = print;
		this.max = max;
		this.isEvenNumber = isEvenNumber;
	}
    
	@Override
	public void run() {
    
		//System.out.println("Run method");
		int number = isEvenNumber == true ? 2 : 1; // start from 1 or start from 2
		while (number <= max) {
    
			if (isEvenNumber) {
				//System.out.println("Even :"+ Thread.currentThread().getName());
				print.printEven(number);
				//number+=2;
			} else {
				//System.out.println("Odd :"+ Thread.currentThread().getName());
				print.printOdd(number);
				// number+=2;
			}
			number += 2;
		}
    
	}
    
}
    
class Printer {
    
	boolean isOdd = false;
    
	synchronized void printEven(int number) {
    
		while (isOdd == false) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Even:" + number);
		isOdd = false;
		notifyAll();
	}
    
	synchronized void printOdd(int number) {
		while (isOdd == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Odd:" + number);
		isOdd = true;
		notifyAll();
	}
    
}
