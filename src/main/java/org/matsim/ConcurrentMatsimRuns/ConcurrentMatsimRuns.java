package org.matsim.ConcurrentMatsimRuns;


import org.matsim.core.controler.Controler;

public class ConcurrentMatsimRuns {

    SingleMatsimRun[] concurrentRuns;
    Controler mainController;

    ConcurrentMatsimRuns(Controler mainController, Integer numberOfConcurrentRuns) {
        this.mainController = mainController;
        this.concurrentRuns = new SingleMatsimRun[numberOfConcurrentRuns];
    }

    public void init(String[] args) {
        for (int i = 0; i < this.concurrentRuns.length; i++) {
            String[] s = {args[i]};
            this.concurrentRuns[i] = new SingleMatsimRun(s, mainController, ("cRun_"+i));
        }
    }

    public void run() {
        for (SingleMatsimRun run :
                concurrentRuns) {
            run.start();
        }
    }
}
