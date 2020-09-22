package org.example;

import java.util.concurrent.TimeUnit;

public class Player extends Box {
    final Box switcher;

    public Player(Box switcher) {
        this.switcher = switcher;
        System.out.println("Игрок готов к игре");
    }

    @Override
    public void run(){
        int i=1;
        while (i<=countIter) {
            setSwitcherOn(i);
            try {
                TimeUnit.MILLISECONDS.sleep(100 + (int) (Math.random() * 1000));
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setSwitcherOn(int countIter){
        synchronized (switcher){
            try {
                while (switcher.isSwitcher()) {
                    switcher.wait();
                }
                switcher.setSwitcher(true);
                System.out.println(countIter + ". Тумблер включен");
                switcher.notify();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
