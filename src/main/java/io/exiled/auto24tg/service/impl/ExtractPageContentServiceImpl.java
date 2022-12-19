package io.exiled.auto24tg.service.impl;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ExtractPageContentServiceImpl {
//    @PostConstruct
    public void init() {
        try {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/Drivers/geckodriver.exe");
            WebDriver driver = new FirefoxDriver();
            //implicit wait
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            //URL launch
//            driver.get("https://www.auto24.ee");
            driver.get("https://www.auto24.ee/kasutatud/nimekiri.php?b=12&ae=8&bw=154&f2=2011&f1=2005&ssid=80201607");
            //get page source
            String p = driver.getPageSource();
            WebElement el = driver.findElement(By.className("item-first"));
            System.out.println("Page Source is : " + el.getText());
            System.out.println("Page link is : " + el.findElement(By.tagName("a")).getAttribute("href"));
            driver.close();
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String getFirstCarInSearch() {
        try {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/Drivers/geckodriver.exe");
            WebDriver driver = new FirefoxDriver();
            //implicit wait
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            //URL launch
//            driver.get("https://www.auto24.ee");
            driver.get("https://www.auto24.ee/kasutatud/nimekiri.php?b=12&ae=8&bw=154&f2=2011&f1=2005&ssid=80201607");
            //get page source
            String p = driver.getPageSource();
            WebElement el = driver.findElement(By.className("item-first"));
            System.out.println("Page Source is : " + el.getText());
            String carUrl = el.findElement(By.tagName("a")).getAttribute("href");
            System.out.println("Page link is : " + carUrl);
            driver.close();
            return carUrl;
        }catch (Exception e) {
            System.err.println(e.getMessage());
            return "";
        }
    }
}


    //get page source with getText method
//    WebElement l= driver.findElement(By.tagName("body"));
//    String p = l.getText();
//      System.out.println("Page Source is : " + p);
