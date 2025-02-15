/* *********************************************************************** *
 * project: org.matsim.*												   *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2008 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */


package org.matsim.ConcurrentMatsimRuns;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.gbl.Gbl;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.tools.MineConfig;

public class RunMainMatsim {

     public static void main(String[] args) {
        if ( args.length==0 ) {
            args = new String [] { "scenarios/equil/config.xml" } ;
        } else {
            Gbl.assertIf( args[0] != null && !args[0].equals( "" ) );
        }

        Config config = ConfigUtils.loadConfig( args ) ;
        config = new MineConfig(config).modifyConfig();

        Scenario scenario = ScenarioUtils.loadScenario(config) ;

        Controler controler = new Controler( scenario ) ;

        args = new String [] { "scenarios/equil/config.xml",  "scenarios/equil/config.xml",  "scenarios/equil/config.xml",  "scenarios/equil/config.xml",  "scenarios/equil/config.xml",  "scenarios/equil/config.xml",  "scenarios/equil/config.xml"} ;

        ConcurrentMatsimRuns concurrentMatsimRuns = new ConcurrentMatsimRuns(controler, 5);
        concurrentMatsimRuns.init(args);
        concurrentMatsimRuns.run();

    }

}
