package com.github.lontime.config;

import java.util.concurrent.TimeUnit;

import com.ibm.asyncutil.locks.FairAsyncNamedLock;

public class Lock2Test {

    public static void main(String[] args) {
        String name = "123";
        FairAsyncNamedLock lock = new FairAsyncNamedLock();
        lock.tryLock(name).ifPresent(h -> {
            System.out.println("12323");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("zzxx");
        });
        System.out.println("3232343");
        lock.tryLock(name).ifPresent(h -> {
            System.out.println("546557");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ytuu");
        });

        try {
            TimeUnit.MINUTES.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
