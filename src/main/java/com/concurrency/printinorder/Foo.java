package com.concurrency.printinorder;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * originUrl:https://leetcode.com/problems/print-in-order/
 * <p>
 * Input: [1,2,3]
 * Output: "firstsecondthird"
 * Explanation: There are three threads being fired asynchronously. The input [1,2,3] means thread A calls first(),
 * thread B calls second(), and thread C calls third(). "firstsecondthird" is the correct output.
 * <p>
 * Input: [1,3,2]
 * Output: "firstsecondthird"
 * Explanation: The input [1,3,2] means thread A calls first(), thread B calls third(), and thread C calls second().
 * "firstsecondthird" is the correct output.
 */
class Foo {

    Semaphore semaphoreSecond, semaphoreThird;

    public Foo() {
        semaphoreSecond = new Semaphore(0);
        semaphoreThird = new Semaphore(0);
    }


    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        semaphoreSecond.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        semaphoreSecond.acquire();
        printSecond.run();
        semaphoreThird.release();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        semaphoreThird.acquire();
        printThird.run();
    }

}