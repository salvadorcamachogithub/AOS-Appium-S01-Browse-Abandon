package org.opentext;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;

public class AppiumMobileTest {

    // Variable for the driver
    private static AndroidDriver driver;
    static int timeBetweenSteps = 3000;
    // For Android, the UiAutomator2 driver is used usually, and to define its capabilities in Appium 8, the UiAutomator2Options class is used.
    // For iOS, the XCUITest driver is used, and to define its capabilities in Appium 8, the XCUITestOptions class is used.
    UiAutomator2Options caps = new UiAutomator2Options();

    @Before
    public void AppiumSetUp()
    {
        // Digital Lab specific Appium capabilities are prefixed with “dl:”.
        // Instead of the dl:oauthClientId and dl:oauthClientSecret capabilities, you can also use the dl:userName and dl:password capabilities.
        caps.setCapability("ftlab:oauthClientId", Config.FTLAB_CLIENT_ID);
        caps.setCapability("ftlab:oauthClientSecret", Config.FTLAB_CLIENT_SECRET);
        caps.setCapability("ftlab:tenantId", Config.FTLAB_TENANT_ID);

        // Instead of selecting a specific device using the udid capability, you can also use dynamic device allocation by using the platformName,
        // platformVersion, deviceName, deviceType, deviceHostingType, and source capabilities. When using dynamic allocation, a device matching
        // the capabilities, if there is one and it is available, will be allocated.
        caps.setCapability("udid", Config.FTLAB_UDID);

        // Locking capabilities
        //caps.setCapability("unlockType", "pin");
        //caps.setCapability("unlockKey", "12345");

        // For selecting an app (on Android):
        // appPackage + appActivity

        caps.setCapability("appPackage", "com.Advantage.aShopping");
        //caps.setCapability("appPackage", "com.android.chrome");
        caps.setCapability("appActivity", "com.Advantage.aShopping.SplashActivity");
        //caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");

        // For using the packaged app, which is required for using Digital Lab simulations, set dl:installPackagedApp to true
        // caps.setCapability("ftlab:installPackagedApp", "true");
    }

    @After
    public void AppiumTearDown()
    {
        // Quit the Appium driver
        driver.quit();
    }

    @Test
    public void AppiumTestAOSLogIn() throws MalformedURLException, InterruptedException {
        // Creating the Appium driver and using the defined capabilities
        // When the script is executed and the driver is created, the device state in Digital Lab changes to In use.
        driver = new AndroidDriver(new URL(Config.FTLAB_SERVER), caps);
        Thread.sleep(10000);

        // Define explicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        WebElement el1 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"LAPTOPS\")"));
        el1.click();
        Thread.sleep(timeBetweenSteps);

        WebElement el2 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"HP CHROMEBOOK 14 G1(ENERGY STAR)\")"));
        el2.click();
        Thread.sleep(timeBetweenSteps);

        // Add To Cart
        WebElement el3 = driver.findElement(AppiumBy.accessibilityId("ADD TO CART"));
        el3.click();
        Thread.sleep(timeBetweenSteps);

        WebElement el4 = driver.findElement(AppiumBy.id("com.Advantage.aShopping:id/imageViewBack"));
        el4.click();
        Thread.sleep(timeBetweenSteps);

        WebElement el5 = driver.findElement(AppiumBy.accessibilityId("Back"));
        el5.click();
        Thread.sleep(timeBetweenSteps);

        WebElement el6 = driver.findElement(AppiumBy.id("com.Advantage.aShopping:id/imageViewMenu"));
        el6.click();
        WebElement el7 = driver.findElement(AppiumBy.id("com.Advantage.aShopping:id/textViewMenuHome"));
        // Artificial Error
        //WebElement el7 = driver.findElement(AppiumBy.id("com.Advantage.aShopping:id/textViewMenuHom"));
        el7.click();
    }
}
