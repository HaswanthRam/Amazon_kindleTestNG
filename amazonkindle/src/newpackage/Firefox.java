package newpackage;

import org.openqa.selenium.By;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.Reporter;

public class Firefox {
	
 
	String baseUrl = "http://amazon.ca";
	WebDriver driver;
	WebDriverWait wait;
	
  @BeforeTest
    public void launchBrowser() {
	  System.out.println("Initializing firefox browser"); 
	  System.setProperty("webdriver.gecko.driver","C:\\geckodriver.exe");
      driver = new FirefoxDriver();
      wait= new WebDriverWait(driver, Duration.ofSeconds(3));
      
    }
	
  @Test (priority=1)
  public void loadamazonwebpage() {
	  Reporter.log("Step-1: Go to amazon.ca"); 
	  driver.get(baseUrl);
      String expectedTitle = "Amazon.ca: Low Prices – Fast Shipping – Millions of Items";
      String actualTitle = driver.getTitle();
      Assert.assertTrue(actualTitle.equals(expectedTitle), "The amazon.ca webpage is not loaded");
      Reporter.log("The amazon.ca webpage was opened successfully."); 
  }
  
  @Test (priority=2)
  public void hamburgermenu() {
	  Reporter.log("Step-2: Click on hamburger menu (top left corner)"); 
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nav-hamburger-menu']")));
	  WebElement hamburgermenu=driver.findElement(By.xpath("//a[@id='nav-hamburger-menu']"));
	  Assert.assertTrue(hamburgermenu.isDisplayed(), "The hamburger menu is not found.");
	  hamburgermenu.click();
	  Reporter.log("The hamburger menu was clicked successfully."); 
  }
  
  @Test (priority=3)
  public void kindle_digitalcontent() {
	 Reporter.log("Step-3: Select Kindle under Digital Content and Devices");
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hmenu-content\"]/ul[1]/li[9]/a")));
     WebElement kindle=driver.findElement(By.xpath("//*[@id=\"hmenu-content\"]/ul[1]/li[9]/a"));
     Assert.assertTrue(kindle.isDisplayed(), "Kindle is not found under the Digital Content and Devices.");
     kindle.click();
     Reporter.log("Kindle was selected under Digital Content and devices."); 
  
  }
  
  @Test (priority=4)
  public void kindle_under_ereader() {
	 Reporter.log("Step-4: Click Kindle under Kindle E-Reader");
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hmenu-content\"]/ul[4]/li[3]/a")));
     WebElement kindlereader=driver.findElement(By.xpath("//*[@id=\"hmenu-content\"]/ul[4]/li[3]/a"));
     Assert.assertTrue(kindlereader.isDisplayed(), "Kindle is not displayed under Kinder e-reader");
     kindlereader.click();
     Reporter.log("Kindle option was selected under kindle e-reader section successfully."); 
  
  }
  
  @Test (priority=5)
  public void buynow() {
	  Reporter.log("Step-5: Click Buy Now");
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buy-now-button")));
	  String bodyText = driver.findElement(By.id("productTitle")).getText();
	  Assert.assertTrue(bodyText.contains("All-new Kindle (2022 release) – The lightest and most compact Kindle, now with a 6” 300 ppi high-resolution display, and 2x the storage - Black"), 
			  "The correct kindle product page is not displayed.");
	  WebElement buynow=driver.findElement(By.id("buy-now-button"));
	  Assert.assertTrue(buynow.isDisplayed(), "The buy now button is not found");
	  buynow.click();
	  Reporter.log("Buy Now option was clicked successfully from the kindle product page."); 
  }
  
  @Test (priority=6)
  public void usersignin() {
	  Reporter.log("Step-6: Verify User is asked for email or mobile number");
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"authportal-main-section\"]")));
	  String bodyText = driver.findElement(By.tagName("label")).getText();
	  Assert.assertTrue(bodyText.contains("E-mail address or mobile phone"), "The text box to enter email or phone number is not found");
	  Reporter.log("The User is asked for email or mobile number after clicking on 'buy now' button"); 
  }
  
  @AfterTest
  public void terminateBrowser(){
      driver.close();
  }
  
}
