package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class UiTest {

    @Test
    @Description("UI test example")
    public void testGoogleSearch() {
        // Автоматическая настройка ChromeDriver
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        // Настраиваем ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        // Отключаем всплывающие окна и уведомления
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");

        // Указываем параметр для автоматического принятия соглашений
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-infobars");

        // Отключаем показ pop-up окон Google (cookies)
        options.addArguments("start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--incognito");

        // Указываем региональные настройки
        options.addArguments("--lang=en-US");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.google.com");

        // Устанавливаем cookie для автоматического принятия соглашения
        Cookie consentCookie = new Cookie.Builder("CONSENT", "YES+")
                .domain(".google.com")
                .path("/")
                .isSecure(true)
                .build();
        driver.manage().addCookie(consentCookie);

        // Перезагружаем страницу
        driver.navigate().refresh();

        driver.findElement(By.name("q")).sendKeys("Selenium WebDriver");
        driver.findElement(By.name("btnK")).submit();
        driver.quit();
    }
}

