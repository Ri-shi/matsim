package org.matsim.population;


import jdk.internal.jline.internal.Log;
import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.*;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.gbl.MatsimRandom;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.utils.geometry.CoordUtils;
import org.matsim.core.utils.misc.Counter;
import org.matsim.core.utils.misc.Time;

public class PopulationSynthesis {
    private final Logger LOG = Logger.getLogger(PopulationSynthesis.class);

    public final String  ATTR_AGE = "age";
    public final String NAME_HOME = "h";
    public final String NAME_WORK = "w";
    public final String NAME_CAR = "car";
//    public static final String ATTR_OVER = "overloaded";

    public void synthesize(String[] args) {
        LOG.info("Starting Population Synthesis");
        run(args);
        Log.info("Done Population Synthesis");
    }

    public void run(String[] args)
    {
        //# people to create
        //# where to print
        int personsToCreate = Integer.parseInt(args[0]);
        String file = args[1];

        //Fixed seed
        long seed = 20190417L;
        MatsimRandom.reset(seed);

        //New scenario (placeholder)
        Scenario sc = ScenarioUtils.createScenario(ConfigUtils.createConfig());
        Population pop = sc.getPopulation();
        PopulationFactory factory = pop.getFactory();



        Counter counter = new Counter(" persons #");

        //This makes people
        for(int i = 0; i < personsToCreate; i++){
            Person person = factory.createPerson(Id.createPersonId(i));
            person.getAttributes().putAttribute(ATTR_AGE, MatsimRandom.getRandom().nextInt(50));
//            if (i <= 10){
//                person.getAttributes().putAttribute(ATTR_OVER, Boolean.TRUE);
//            } else {
//                person.getAttributes().putAttribute(ATTR_OVER, Boolean.FALSE);
//            }

            Plan plan = factory.createPlan();

            //First Home activity
//            double homex = MatsimRandom.getRandom().nextDouble() * 1000.00;
//            double homey = MatsimRandom.getRandom().nextDouble() * 1000.00;
            double homex = -20000.0;
            double homey = 0.0;

            Coord homeCoord = CoordUtils.createCoord(homex, homey);

            //Home coords
            Activity hOne = factory.createActivityFromCoord(NAME_HOME, homeCoord);

            //Added activity to plan
            hOne.setEndTime(Time.parseTime("06:00:00") + MatsimRandom.getRandom().nextDouble()*Time.parseTime("01:00:00")); //Will automatically convert it to seconds
            plan.addActivity(hOne);


            //Travel to work
//            Id<Link> homeLink = Id.create(1, Link.class);
//            List<Id<Link>> routeKeyLink = Arrays.asList(Id.create(2, Link.class), Id.create(5, Link.class));
//            Id<Link> workLink = Id.create(8, Link.class);

            /*
             *Legs
             * */
            Leg legToWork = factory.createLeg(NAME_CAR);
//            Route toWork = RouteUtils.createLinkNetworkRouteImpl(homeLink, routeKeyLink, workLink);
//            legToWork.setRoute(toWork);
            plan.addLeg(legToWork);


//            double workx = MatsimRandom.getRandom().nextDouble() * 1000.00;
//            double worky = MatsimRandom.getRandom().nextDouble() * 1000.00;

            //First Work Activity

            double workx = 5000.0;
            double worky = 0.0;

            Coord workCoord = CoordUtils.createCoord(workx, worky);
            Activity work = factory.createActivityFromCoord(NAME_WORK, workCoord);
            work.setMaximumDuration(Time.parseTime("08:00:00"));
            plan.addActivity(work);

            //Travel to Home
            Leg legToHome = factory.createLeg(NAME_CAR);
            plan.addLeg(legToHome);

            //Final Home Activity
            Activity hTwo = factory.createActivityFromCoord(NAME_HOME, homeCoord);
            plan.addActivity(hTwo);


            person.addPlan(plan);
            pop.addPerson(person);

        }

        //Writes out file
        new PopulationWriter(pop).write(file);

    }
}
