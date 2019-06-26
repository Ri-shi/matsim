package org.matsim.tree;


import org.matsim.core.controler.Controler;

public class ConcurrentMatsimRuns {

    SingleMatsimRun[] concurrentRuns;
    Controler mainControler;

    ConcurrentMatsimRuns(Controler mainControler, Integer numberOfConcurrentRuns) {
        this.mainControler = mainControler;
        this.concurrentRuns = new SingleMatsimRun[numberOfConcurrentRuns];
    }

    public void init(String[] args) {
        for (int i = 0; i < this.concurrentRuns.length; i++) {
            String[] s = {args[i]};
            this.concurrentRuns[i] = new SingleMatsimRun(s, ("t"+i));
        }
    }

    public void run() {
        for (SingleMatsimRun run :
                concurrentRuns) {
            run.start();
        }
    }
}
