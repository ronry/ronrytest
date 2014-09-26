package com.ronrytest.guava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class EventBusTest {

    public static void main(String[] args) {

        EventBus bus = new EventBus();
        bus.register(new Subscriber());

        bus.post(new Object());
        bus.post(new String("xxxx"));
    }

    public static class Subscriber {

        @Subscribe
        public void fire(Object obj1) throws InterruptedException {
            System.out.println("fire");
            System.out.println(obj1);
            Thread.sleep(5000);
        }

        @Subscribe
        public void fire2(String obj1) {
            System.out.println("fire2");
            System.out.println(obj1);
        }
    }


}
