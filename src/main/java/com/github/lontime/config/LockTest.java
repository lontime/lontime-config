package com.github.lontime.config;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.ibm.asyncutil.locks.AsyncLock;
import com.ibm.asyncutil.locks.FairAsyncNamedLock;

public class LockTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String name = "123";
        FairAsyncNamedLock<String> lock = new FairAsyncNamedLock();
        final CompletionStage<AsyncLock.LockToken> result = lock.acquireLock(name).handleAsync((h, ex) -> {
            System.out.println("12323");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("zzxx");
            return h;
        });
        System.out.println("3232343");
        result.thenRun(() -> {
            System.out.println("1232343");
        });
        result.toCompletableFuture().get().releaseLock();

        lock.acquireLock(name).handleAsync((ex, h) -> {
            System.out.println("546557");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ytuu");
            return null;
        });
        System.out.println("232424");
        try {
            TimeUnit.MINUTES.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
