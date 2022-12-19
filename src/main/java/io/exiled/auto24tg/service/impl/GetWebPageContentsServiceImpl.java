package io.exiled.auto24tg.service.impl;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GetWebPageContentsServiceImpl {
    private static final String URL = "https://www.auto24.ee/";
    private WebDriver driver;
    private ExtentReports report;
    private ExtentTest test;
    @BeforeTest
    protected void initialize() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/Drivers/geckodriver.exe");
        LocalDateTime timeStamp = LocalDateTime.now();
        String timeString = getTimeString(timeStamp, 10, 16, ":");
        String dateString = getTimeString(timeStamp, 5, 10, "-");

        report = new ExtentReports("src/main/resources/Reports/Report" + dateString + timeString + ".html", false);
        test = report.startTest("Verifying Browser Opened");
        test.log(LogStatus.INFO, "OpenBrowser test is initiated.");

        driver = new FirefoxDriver();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        test.log(LogStatus.PASS, "Browser is open and window is maximized");
        test.log(LogStatus.PASS, "String url is open in Firefox browser");

        report.endTest(test);
    }

    @Test
    public void verifyPageLoad() {
        test = report.startTest("Verify correct page loaded");

        String actual = "Contact information";
        String expected = driver.getTitle();

        test.log(LogStatus.INFO, "Verifying correct page.");
        logData(expected.equals(actual), "Correct page is opened successfully.", "Correct page is failed to open.");

        report.endTest(test);
    }

    @Test
    public void verifyRequiredFieldName() {
        List<String> namesInput = Arrays.asList(" ", "-", "Shy");

        test = report.startTest("Verify Name field.");
        test.log(LogStatus.INFO, "Initiating Name field verification.");

        report.endTest(test);
    }

    @Test
    public void verifyRequiredFieldEmail() {
        List<String> emailsInput = Arrays.asList(" ", "@", " @.ru", ".x", "empty", " @ .  ");

        test = report.startTest("Verify Email field");
        test.log(LogStatus.INFO, "Initiating Email field verification");

        report.endTest(test);
    }

    @Test
    public void verifyRequiredFieldAddress() {
        List<String> inputData = Arrays.asList(" ", "1", "xx", "short");

        test = report.startTest("Verify Address field");
        test.log(LogStatus.INFO, "Initiating Address field verification");

        report.endTest(test);
    }

    @Test
    public void submitOtherFields() {
        test = report.startTest("Verify other fields.");
        test.log(LogStatus.INFO, "verifying non required fields.");

        for (int i = 5; i < 20; i += 3) {
            WebElement element = driver.findElement(By.id("i" + i));
            element.click();
            logData(submissionCheck(), "Current radio button didn't submit form without required fields.",
                    "Form submitted without all required fields.");

            if (element.isSelected()) {
                test.log(LogStatus.INFO, "Current radio button worked.");
            }
        }
        logData(submissionCheck(), "Field Phone number didn't submit form without required fields.",
                "Field Phone number submit form without required fields.");

        logData(submissionCheck(), "Field Comment didn't submit form without required fields.",
                "Field Comment submit form without required fields.");

        report.endTest(test);
    }

    @AfterMethod
    public void AfterMethods() {
        report.flush();
    }

    @AfterClass
    public void submitCompleteForm() {
        test = report.startTest("Form submit");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        test.log(LogStatus.INFO, "All fields filled");

        logData(submissionCheck(), "Form successfully submitted", "Form submission has failed");

        report.endTest(test);
        report.flush();
        report.close();
    }

    /**
     * Method will create String for report name forming from current date and time.
     *
     * @param timeStamp  current date and time.
     * @param startIndex specify starting index for forming file name string.
     * @param endIndex   specify ending index for forming file name string.
     * @param replacer   specify element to be replaced by specific symbol.
     * @return String which will be used for report file name.
     */
    private static String getTimeString(LocalDateTime timeStamp, int startIndex, int endIndex, String replacer) {
        return timeStamp.toString().substring(startIndex, endIndex).replace(replacer, "_");
    }

    /**
     * Method makes inputs one by one from List and checks if form accepts the input.
     *
     * @param errorId    ID of error that would appear if input is not accepted.
     * @param inputs     List of inputs to insert into field for validation.
     * @param inputField specific field that will be used for inputs and validation.
     * @return will return true if validation has passed.
     */
    private boolean checkInputs(List<String> inputs, WebElement inputField, String errorId) {
        boolean result = true;

        for (String input : inputs) {
            inputField.sendKeys(input);
            if (!driver.findElement(By.id(errorId)).isDisplayed()) {
                result = false;
                System.err.println("Input validation failed with input: " + input);
                test.log(LogStatus.ERROR, "Input validation failed with input: " + input);
            }
            inputField.clear();
        }

        return result;
    }

    /**
     * Method that will write data to test logs and console.
     *
     * @param condition   this will specify the state of current test block.
     * @param passMessage message that will be added if condition is true.
     * @param failMessage message that will be added if condition is false.
     */
    private void logData(Boolean condition, String passMessage, String failMessage) {
        if (condition) {
            test.log(LogStatus.PASS, passMessage);
            System.out.println(passMessage);
        } else {
            test.log(LogStatus.FAIL, failMessage);
            System.err.println(failMessage);
        }
    }

    /**
     * Method verifies fields initialization at the beginning of the test.
     *
     * @param condition is the state of the current reference, is it null.
     * @param name      of the field that being verified.
     */
    private void verifyField(Boolean condition, String name) {
        if (condition) {
            throw new RuntimeException(name + " field is set incorrectly.");
        }
        test.log(LogStatus.INFO, name + " field is set correctly.");
        System.out.println(name + " field is set correctly.");
    }

    /**
     * Method check if form was submitted successfully.
     */
    private boolean submissionCheck() {
        return !driver.getCurrentUrl().equals(URL);
    }
}

