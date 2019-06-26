package org.matsim.tree;

import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;

public class SingleMatsimRun extends Thread {

    String[] args;
    Logger LOG = Logger.getLogger(this.getClass());

    SingleMatsimRun(String[] args){
        this.args = args;
    }

    public void run() {
        LOG.warn("{}{}{}{}{} Running a MATSim Controler... {}{}{}{}{}");

        Config config = ConfigUtils.loadConfig( this.args ) ;
        Scenario scenario = ScenarioUtils.loadScenario(config) ;
        Controler controler = new Controler( scenario ) ;

        LOG.warn("{}{}{}{}{} DONE {}{}{}{}{}");

    }

}
