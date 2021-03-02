package com.github.lontime.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.Vertx;
import io.vertx.core.shareddata.Lock;
import io.vertx.core.shareddata.SharedData;

/**
 * Test.
 * @author lontime
 * @since 1.0
 */
public class ConfigTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigTest.class);


    public static void main(String[] args) {
        LOGGER.info("====test======");

        final Vertx vertx = Vertx.vertx();

        SharedData sharedData = vertx.sharedData();

        sharedData.getLocalLock("mylocak");

        sharedData.getLocalLock("mylock", res -> {
            if (res.succeeded()) {
                // Local-only lock
                Lock lock = res.result();

                System.out.println("1234");

                // 5 seconds later we release the lock so someone else can get it

                vertx.setTimer(5000, tid -> {
                    System.out.println("789");
                    lock.release();
                });

                System.out.println("123456");

            } else {
                System.out.println("1");
            }
        });

        System.out.println("12121321");

        sharedData.getLocalLock("mylock").onSuccess(h -> {
            System.out.println("131232");
            h.release();
        });

//        EventBus eb = vertx.eventBus();
//        MessageConsumer<String> consumer = eb.<String>consumer("the-address");
//        Observable<Message<String>> observable = ObservableHelper.toObservable(consumer);
//        Disposable sub = observable.subscribe(msg -> {
//            // Got message
//            System.out.println(msg.body());
//        });
//
//        eb.send("the-address", "123");
//
//        // Unregisters the stream after 10 seconds
//        vertx.setTimer(10000, id -> {
//            sub.dispose();
//            vertx.close();
//        });
//
//        Observable.create(observableEmitter -> {
//            observableEmitter.onNext("aaa");
//        }).subscribe(msg -> {
//            // Got message
//            System.out.println(msg);
//        }, throwable -> {
//
//        }, () -> {
//
//        });


    }

}
