/**
 *   两个线程交替打印1到10.
 *
 *  思路：1. 生成两个线程，奇线程依次打印1，3，5...，偶线程依次打印2, 4, 6...
 *       2. 临界区设置为一个bool变量， 拿到该bool变量的线程，如果该bool变量表示当前打印的是奇数，那么奇线程阻塞自己；否则，打印自己并唤醒偶线程
*/

import java.util.*;

class PrintEvenOdd {
    
	public static void main(String[] args) {
		Printer printer = new Printer(10);
		new Thread(() -> {
				printer.print();
		}, "printer").start();
		//new Thread(() -> {
		//		printer.print();
		//}, "printer").start();
	}
    
}
    
class Printer {
	Printer(int maxNum) {
		number = 1;
		this.maxNum = maxNum;
	}
	int number;
	int maxNum;
	boolean isOdd = true;

	void print() {
		while (number <= maxNum) {
			if (isOdd)
				printOdd();
		    else
				printEven();
		}
	}
	synchronized void printEven() {
    
		while (isOdd == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Even:" + number);
		number++;
		isOdd = true;
		notifyAll();
	}
    
	synchronized void printOdd() {
		while (isOdd == false) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Odd:" + number);
		number++;
		isOdd = false;
		notifyAll();
	}
    
}
