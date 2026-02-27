package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators for OrangeHRM demo site
    By usernameField = By.xpath("//input[@placeholder='Username']");
    By passwordField = By.xpath("//input[@placeholder='Password']");
    By loginButton = By.xpath("//button[@type='submit']");
    By dashboard = By.xpath("//h6[text()='Dashboard']");
    By loginPageTitle = By.xpath("//h5[text()='Login']");
    By requiredError = By.xpath("//span[text()='Required']");
    By invalidCredentialsError = By.xpath("//p[text()='Invalid credentials']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // LOGIN METHOD - explicit waits only
    public void login(String username, String password) {
        try {
            System.out.println("Logging in with username: " + username);

            // Wait for username field
            WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
            userField.clear();
            userField.sendKeys(username);

            // Wait for password field
            WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
            passField.clear();
            passField.sendKeys(password);

            // Wait for login button
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginBtn.click();

            // Wait for either dashboard or error
            wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(dashboard),
                ExpectedConditions.visibilityOfElementLocated(invalidCredentialsError),
                ExpectedConditions.visibilityOfElementLocated(requiredError)
            ));

            System.out.println("✓ Login attempt completed");
        } catch (Exception e) {
            System.out.println("✗ Login failed: " + e.getMessage());
            throw new RuntimeException("Login failed: " + e.getMessage());
        }
    }

    // CHECK IF ON DASHBOARD
    public boolean isOnDashboard() {
        try {
            List<WebElement> dashboardElements = driver.findElements(dashboard);
            boolean onDashboard = !dashboardElements.isEmpty() && dashboardElements.get(0).isDisplayed();
            System.out.println(onDashboard ? "✓ On Dashboard page" : "✗ Not on Dashboard page");
            return onDashboard;
        } catch (Exception e) {
            System.out.println("✗ Dashboard check failed: " + e.getMessage());
            return false;
        }
    }

    // CHECK IF ON LOGIN PAGE
    public boolean isOnLoginPage() {
        try {
            List<WebElement> loginTitles = driver.findElements(loginPageTitle);
            List<WebElement> loginButtons = driver.findElements(loginButton);
            boolean onLoginPage = (!loginTitles.isEmpty() && loginTitles.get(0).isDisplayed()) ||
                                  (!loginButtons.isEmpty() && loginButtons.get(0).isDisplayed());
            System.out.println(onLoginPage ? "✓ On Login page" : "✗ Not on Login page");
            return onLoginPage;
        } catch (Exception e) {
            System.out.println("✗ Login page check failed: " + e.getMessage());
            return false;
        }
    }

    // CHECK IF REQUIRED ERROR VISIBLE
    public boolean isRequiredErrorVisible() {
        try {
            List<WebElement> errors = driver.findElements(requiredError);
            boolean errorVisible = !errors.isEmpty() && errors.get(0).isDisplayed();
            System.out.println(errorVisible ? "✓ Required error displayed" : "✗ Required error not displayed");
            return errorVisible;
        } catch (Exception e) {
            System.out.println("✗ Error check failed: " + e.getMessage());
            return false;
        }
    }

    // CHECK IF INVALID CREDENTIALS ERROR VISIBLE
    public boolean isInvalidCredentialsErrorVisible() {
        try {
            List<WebElement> errors = driver.findElements(invalidCredentialsError);
            boolean errorVisible = !errors.isEmpty() && errors.get(0).isDisplayed();
            System.out.println(errorVisible ? "✓ Invalid credentials error displayed" : "✗ Invalid credentials error not displayed");
            return errorVisible;
        } catch (Exception e) {
            System.out.println("✗ Error check failed: " + e.getMessage());
            return false;
        }
    }
}
