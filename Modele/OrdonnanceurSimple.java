package Modele;

public class OrdonnanceurSimple extends Thread {

    public Runnable monRunnable;



    public OrdonnanceurSimple(Runnable _monRunnable) {
        monRunnable = _monRunnable;

    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            monRunnable.run();


        }
    }
}
