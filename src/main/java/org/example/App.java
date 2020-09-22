package org.example;

public class App 
{
    public static void main( String[] args ) {
        Box switcher = new Box();

        Thread playerThread = new Thread(new Player(switcher));
        Thread toyThread = new Thread(new Toy(switcher));

        playerThread.start();
        toyThread.start();
        try {
            playerThread.join();
            toyThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("\nИгра завершена!");
        }
    }
}
