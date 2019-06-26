package org.matsim.tree;

import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;

//public class SingleMatsimRun {
public class SingleMatsimRun extends Thread {

    String[] args;
    Logger LOG = Logger.getLogger(SingleMatsimRun.class);
    String identifier;

    SingleMatsimRun(String[] args, String identifier){
        this.args = args;
        this.identifier = identifier;
    }

    public void run() {
        LOG.warn("{}{}{}{}{} Running a MATSim Controler on Thread: "+this.identifier+"... {}{}{}{}{}");

        IndividualRunConfig individualRunConfig = new IndividualRunConfig();
        Config config = ConfigUtils.loadConfig( this.args ) ;
        config = individualRunConfig.modifyConfig(config, this.identifier);

        Scenario scenario = ScenarioUtils.loadScenario(config) ;
        Controler controler = new Controler( scenario ) ;
        controler.run();

        LOG.warn("{}{}{}{}{} DONE {}{}{}{}{}");

    }

}
