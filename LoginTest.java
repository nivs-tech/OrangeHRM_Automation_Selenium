package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void setupTest() {
        // BaseTest already sets up driver + login before each test
        loginPage = new LoginPage(driver);
        System.out.println("✅ Login page ready");
    }

    @Test(priority = 1, description = "Valid login - Redirect to dashboard")
    public void TC01_ValidLogin() {
        System.out.println("🧪 TC01: Valid login");
        loginPage.login("Admin", "admin123");
        Assert.assertTrue(loginPage.isOnDashboard(), "Should redirect to dashboard");
        System.out.println("✅ TC01 PASSED");
    }
    
    @Test(priority = 2, description = "Invalid password")
    public void TC02_InvalidPassword() {
        System.out.println("🧪 TC02: Invalid password");
        loginPage.login("Admin", "wrongpass");
        Assert.assertTrue(loginPage.isOnLoginPage(), "Should remain on login page");
        System.out.println("✅ TC02 PASSED");
    }
    
    @Test(priority = 3, description = "Invalid username")
    public void TC03_InvalidUsername() {
        System.out.println("🧪 TC03: Invalid username");
        loginPage.login("wronguser", "admin123");
        Assert.assertTrue(loginPage.isOnLoginPage(), "Should remain on login page");
        System.out.println("✅ TC03 PASSED");
    }
    
    @Test(priority = 4, description = "Empty username validation")
    public void TC04_EmptyUsername() {
        System.out.println("🧪 TC04: Empty username");
        loginPage.login("", "admin123");
        Assert.assertTrue(loginPage.isRequiredErrorVisible(), "Required error expected");
        System.out.println("✅ TC04 PASSED");
    }
    
    @Test(priority = 5, description = "Empty password validation")
    public void TC05_EmptyPassword() {
        System.out.println("🧪 TC05: Empty password");
        loginPage.login("Admin", "");
        Assert.assertTrue(loginPage.isRequiredErrorVisible(), "Required error expected");
        System.out.println("✅ TC05 PASSED");
    }
    
    @Test(priority = 6, description = "Logout functionality")
    public void TC06_Logout() {
        System.out.println("🧪 TC06: Logout");
        loginPage.login("Admin", "admin123");
        
        driver.findElement(By.cssSelector(".oxd-userdropdown-img")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
        
        Assert.assertTrue(loginPage.isOnLoginPage(), "Should return to login page");
        System.out.println("✅ TC06 PASSED");
    }
    
} 