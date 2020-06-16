package StepDefinitions;

import Utils.BrowserUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    // We have before and after annotations. Those are coming from cucumber. They will run before and after each scenario.
    // We have one Scenario interface to get the details from every scenario.

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("This one will run before each scenario");
        System.out.println(scenario.getName());
    }

    @After
    // after annotation -> will run after each scenario even if it is failed or undefined
    public void tearDown(Scenario scenario) {
        System.out.println("This one will will run after each scenario");
        if (scenario.isFailed()) {
            System.out.println("This scenario is failed!");
            BrowserUtils.takeScreenshot();
        }
    }

    // run this before annotation if scenario has @conditional tag
    @Before("@conditional")
    public void conditionalAnnotation() {
        // I want to run this annotation only before conditional tag
        System.out.println("Conditional annotation");
        // It will run only the scenarios which have conditional tag
    }

}
