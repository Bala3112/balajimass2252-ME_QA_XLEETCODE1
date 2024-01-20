package demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }
    public void navigate(){
        driver.get("https://leetcode.com/");

    }

    public  void testCase01() throws InterruptedException {
        System.out.println("Start Test case: testCase01");
        navigate();
        Thread.sleep(3000);
        if(driver.getCurrentUrl().contains("leetcode")){
            System.out.println("yes url contains leetcode");
        }
        System.out.println("end Test case: testCase01");
    }

    public void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement viewQuestions = driver.findElement(By.xpath("//p[text()='View Questions ']"));


        js.executeScript("arguments[0].scrollIntoView();", viewQuestions);
        viewQuestions.click();

        if(driver.getCurrentUrl().contains("problemset")){
            System.out.println("yes it contains problem set");
        }
        Thread.sleep(5000);
        List<WebElement> problemTitle = driver.findElements(By.xpath("//a[@class='h-5 hover:text-blue-s dark:hover:text-dark-blue-s']"));
        for(int i=1;i<=5;i++){
            System.out.println(problemTitle.get(i).getText());
        }
//       WebElement firstProblem = driver.findElement(By.xpath("//a[text()='Two Sum']"));
//        js.executeScript("arguments[0].scrollIntoView();",firstProblem);
//        firstProblem.click();
        System.out.println("end Test case: testCase02");
    }

    public void testCase03(){
        System.out.println("Start Test case: testCase03");
        WebElement firstProblem = driver.findElement(By.xpath("//a[text()='Two Sum']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",firstProblem);
        firstProblem.click();
        if(driver.getCurrentUrl().contains("two-sum")){
            System.out.println("yes it contains two sum");
        }
        System.out.println("end Test case: testCase03");
    }
    public void testCase04() throws InterruptedException {
        System.out.println("Start Test case: testCase04");
        WebElement clickDynamicLayout = driver.findElement(By.xpath("//button[text()='Enable Dynamic Layout']"));
        clickDynamicLayout.click();
        Thread.sleep(1000);
        WebElement closeBtn = driver.findElement(By.xpath("//button[@role='button']"));
        closeBtn.click();
        Thread.sleep(3000);
        WebElement submissionTab = driver.findElement(By.xpath("//*[@id='description_tabbar_outer']/div[1]/div/div[7]"));

        submissionTab.click();
        WebElement registerBtn = driver.findElement(By.xpath("//a[text()='Register or Sign In']"));
        String verify = registerBtn.getText();
        if(verify.contains("Register or Sign In")){
            System.out.println("yes it is displayed");
        }
        System.out.println("end Test case: testCase04");
    }
}