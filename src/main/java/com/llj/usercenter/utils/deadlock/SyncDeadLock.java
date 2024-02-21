package com.llj.usercenter.utils.deadlock;

public class SyncDeadLock {

  private static Object objectA = new Object();
  private static Object objectB = new Object();

  public static void deadLock() {
    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
        synchronized (objectA) {
          try {
            System.out.println(Thread.currentThread().getName() + " get objectA ing!");
            Thread.sleep(500);
          } catch (Exception e) {
            e.printStackTrace();
          }
          System.out.println(Thread.currentThread().getName() + " need objectB! Just waiting!");
          synchronized (objectB) {
            System.out.println(Thread.currentThread().getName() + " get objectB ing!");
          }
        }
      }
    }, "thread1");

    Thread thread2 = new Thread(() -> {
      synchronized (objectB) {
        try {
          System.out.println(Thread.currentThread().getName() + " get objectB ing!");
          Thread.sleep(500);
        } catch (Exception e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " need objectA! Just waiting!");
        synchronized (objectA) {
          System.out.println(Thread.currentThread().getName() + " get objectA ing!");
        }
      }
    }, "thread2");

    thread1.start();
    thread2.start();
  }
}
