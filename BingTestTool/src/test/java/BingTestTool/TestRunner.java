package BingTestTool;

//This class can be used to run all tests
//You can also right click > Run test on individual test files
//To run in netbeans, right click the .java and click Run File
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        //Create and run the all test cases as specified in BingParserTest
        Result result = JUnitCore.runClasses(BingParserTest.class);

        //If all cases were correct, returns true, otherwise returns false
        System.out.println("Is BingParser test successful?: " + result.wasSuccessful());
        
        //Print out all fail cases for information on debugging
        for (Failure failure : result.getFailures()) {
            System.out.println("Failure: " + failure.toString());
        }
        
        //WordGeneratorTest
        result = JUnitCore.runClasses(WordGeneratorTest.class);
        System.out.println("Is WordGenerator test successful?: " + result.wasSuccessful());
        for (Failure failure : result.getFailures()) {
            System.out.println("Failure: " + failure.toString());
        }
        
        //AutomatedTesterTest
        result = JUnitCore.runClasses(AutomatedTesterTest.class);
        System.out.println("Is AutomatedTester test successful?: " + result.wasSuccessful());
        for (Failure failure : result.getFailures()) {
            System.out.println("Failure: " + failure.toString());
        }
        
        //GoogleParserTest
        result = JUnitCore.runClasses(GoogleParserTest.class);
        System.out.println("Is AutomatedTester test successful?: " + result.wasSuccessful());
        for (Failure failure : result.getFailures()) {
            System.out.println("Failure: " + failure.toString());
        }
        
    }
}
