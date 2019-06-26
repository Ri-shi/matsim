package org.matsim.tools;

import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigWriter;
import org.matsim.core.config.groups.StrategyConfigGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class MineConfig {
    Config config;

    private static Config mySettings(Config config_obj, Collection<StrategyConfigGroup.StrategySettings> settingsList) {
        // Make a new "map"
        HashMap<String, StrategyConfigGroup.StrategySettings> map = new HashMap<String, StrategyConfigGroup.StrategySettings>();

        // Populate the map with the ("key", "value/setting") (note that "keys" must be
        // unique)
        for (StrategyConfigGroup.StrategySettings i : settingsList)
            map.put(i.getStrategyName(), i);

        // Set whatever settings you want (access the StrategySetting from the map using
        // "get" and use the "setWeight" method to apply settings)
//        map.get("BestScore").setWeight(0.2);
//        map.get("ReRoute").setWeight(0.8);

        // Convert (or "cast") it back to a "collection" (an "ArrayList" is a type of
        // "collection" that is easy to loop through)
        settingsList = new ArrayList<StrategyConfigGroup.StrategySettings>(map.values());

        // Add the settings to the config
        for (StrategyConfigGroup.StrategySettings setting : settingsList) {
            config_obj.strategy().addStrategySettings(setting);
        }

        // Return the edited config
        return config_obj;
    }

    public Config modifyConfig() {
        System.out.println("+++++++++++++MODIFYING CONFIG+++++++++++++");

        config.controler().setOutputDirectory("./rr_output");
        Collection<StrategyConfigGroup.StrategySettings> setting = config.strategy().getStrategySettings();
        config.strategy().clearStrategySettings();
        config = mySettings(config, setting);

        System.out.println("+++++++++++++ DONE +++++++++++++");

        return config;

    }

    public String[] writeOutConfigToFile(String outputConfigFile) {
        if (outputConfigFile.equals(""))
            return new String[0];

        ConfigWriter writer = new ConfigWriter(config);
        writer.write(outputConfigFile);
        String[] outpt = { outputConfigFile };
        return outpt;
    }

    public MineConfig(Config config) {
        this.config = config;
    }
}
