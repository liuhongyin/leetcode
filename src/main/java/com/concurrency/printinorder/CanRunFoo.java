package com.concurrency.printinorder;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

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
class CanRunFoo {

    Semaphore semaphoreSecond, semaphoreThird;

    public CanRunFoo() {
        semaphoreSecond = new Semaphore(0);
        semaphoreThird = new Semaphore(0);
    }


    Runnable printFirst = new Runnable() {
        @Override
        public void run() {
            System.out.println("Thread:" + Thread.currentThread().getName() + ": first");
            semaphoreSecond.release();
        }
    };
    Runnable printSecond = new Runnable() {
        @Override
        public void run() {
            try {
                semaphoreSecond.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread:" + Thread.currentThread().getName() + ": second");

            semaphoreThird.release();
        }
    };
    Runnable printThird = new Runnable() {
        @Override
        public void run() {
            try {
                semaphoreThird.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread:" + Thread.currentThread().getName() + ": third");

        }
    };

    public static void main(String[] args) {

        CanRunFoo canRunFoo = new CanRunFoo();

//        ExecutorService executorService = Executors.newCachedThreadPool();

        //  Manually create thread pool
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("print-in-order-%d").build();

        ExecutorService executorService = new ThreadPoolExecutor(
                0,
                Integer.MAX_VALUE,
                60L,
                TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        executorService.execute(canRunFoo.printSecond);
        executorService.execute(canRunFoo.printThird);
        executorService.execute(canRunFoo.printFirst);

        executorService.shutdown();
    }


}