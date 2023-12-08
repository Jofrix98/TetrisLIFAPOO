package Modele;



public class OrdonnanceurSimple extends Thread {

    public Runnable monRunnable;

    private long tempsExecution = 500;

    private long getTempsExecutionmvt = 1;
    public OrdonnanceurSimple(Runnable _monRunnable) {
        monRunnable = _monRunnable;
    }

    public void setTempsExecution(long _tempsExecution){
        tempsExecution = _tempsExecution;
    }

    public void retourInitiale(){
        tempsExecution = 500;
    }

    @Override
    public void run() {


        while (true) {
            try {
                Thread.sleep(tempsExecution);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            monRunnable.run();

        }



    }
}
