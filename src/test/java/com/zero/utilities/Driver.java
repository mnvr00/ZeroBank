package com.zero.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    /*
    Creating the private constructor so this class' object
    is not reachable from outside
     */
    private Driver() {
    }

    /*
    Making our 'driver' instance private so that it is not reachable from outside of the class.
    We make it static, because we want it to run before everything else, and also we will use it in a static method
     */
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    /*
    Creating re-usable utility method that will return same 'driver' instance everytime we call it.
     */
    public static WebDriver getDriver() {

        if (driverPool.get() == null) {

            synchronized (Driver.class) {
            /*
            We read our browser type from configuration.properties file using
            .getProperty method we creating in ConfigurationReader class.
             */
                String browserType = ConfigurationReader.get("browser");

            /*
            Depending on the browser type our switch statement will determine
            to open specific type of browser/driver
             */
                switch (browserType) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driverPool.set(new ChromeDriver());
                        driverPool.get().manage().window().maximize();
                        driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driverPool.set(new FirefoxDriver());
                        driverPool.get().manage().window().maximize();
                        driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        break;
                    case "chromeSSL":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions capability = new ChromeOptions();
                        capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                        capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
                        driverPool.set(new ChromeDriver(capability));
                        driverPool.get().manage().window().maximize();
                        driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        break;
                    case "remote-chromeSSL":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions remoteCapability = new ChromeOptions();
                        remoteCapability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                        remoteCapability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                        remoteCapability.setCapability("platform",Platform.ANY);
                        try{
                            driverPool.set(new RemoteWebDriver(new URL("http://18.212.6.39:4444/wd/hub"),remoteCapability));
                        } catch (MalformedURLException e){
                            e.printStackTrace();
                        }
                        driverPool.get().manage().window().maximize();
                        driverPool.get().manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
                        break;



                }
            }
        }

        /*
        Same driver instance will be returned every time we call Driver.getDriver(); method
         */
        return driverPool.get();


    }

    /*
    This method makes sure we have some form of driver sesion or driver id has.
    Either null or not null it must exist.
     */
    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }



}