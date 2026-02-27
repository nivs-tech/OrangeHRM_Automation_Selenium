package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By adminMenu = By.xpath("//span[text()='Admin']");
    By addUserButton = By.xpath("//button[normalize-space()='Add']");
    By employeeNameField = By.xpath("//input[@placeholder='Type for hints...']");
    By usernameField = By.xpath("//label[text()='Username']/following::input[1]");
    By passwordField = By.xpath("//label[text()='Password']/following::input[1]");
    By confirmPasswordField = By.xpath("//label[text()='Confirm Password']/following::input[1]");
    By saveButton = By.xpath("//button[@type='submit']");
    By cancelButton = By.xpath("//button[normalize-space()='Cancel']");
    By searchUsernameField = By.xpath("//input[@placeholder='Search']");
    By searchButton = By.xpath("//button[normalize-space()='Search']");
    By systemUsersPage = By.xpath("//h5[text()='System Users']");
    By addUserPage = By.xpath("//h6[text()='Add User']");
    By validationError = By.xpath("//span[contains(@class,'oxd-input-field-error-message')]");
    By successToast = By.xpath("//div[contains(@class,'oxd-toast-content')]");

    public AdminPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Navigate to Admin
    public void navigateToAdmin() {
        wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
    }

    // Click Add User
    public void clickAddUser() {
        wait.until(ExpectedConditions.elementToBeClickable(addUserButton)).click();
    }

    // Generic dropdown selection
    public void selectDropdown(String label, String value) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//label[text()='" + label + "']/following::div[@class='oxd-select-text-input'][1]")
        ));
        dropdown.click();

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
            By.xpath("//div[@role='listbox']//div[@class='oxd-select-option']")
        ));

        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(value)) {
                option.click();
                break;
            }
        }
    }

    // Employee Name autocomplete
    public void enterEmployeeName(String name) {
        WebElement empField = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameField));
        empField.sendKeys(name);

        WebElement suggestion = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@role='listbox']//span")
        ));
        suggestion.click();
    }

    // Username
    public void enterUsername(String username) {
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        userField.clear();
        userField.sendKeys(username);
    }

    // Password
    public void enterPassword(String password) {
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passField.clear();
        passField.sendKeys(password);

        WebElement confirmField = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField));
        confirmField.clear();
        confirmField.sendKeys(password);
    }

    // Save for positive scenarios
    public void clickSaveExpectSuccess() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
    }

    // Save for negative scenarios
    public void clickSaveExpectValidation() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(validationError));
    }

    // Cancel
    public void clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(systemUsersPage));
    }

    // Search by username
    public void searchByUsername(String username) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(searchUsernameField));
        searchField.clear();
        searchField.sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    // Validation error
    public boolean isValidationErrorDisplayed() {
        return !driver.findElements(validationError).isEmpty();
    }

    // Success toast
    public boolean isSuccessMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successToast)).isDisplayed();
    }

    // Page checks
    public boolean isOnAddUserPage() {
        return !driver.findElements(addUserPage).isEmpty();
    }

    public boolean isOnSystemUsersPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(systemUsersPage)).isDisplayed();
    }

    public boolean isUserDisplayed() {
        return driver.findElements(By.xpath("//div[@class='oxd-table-card']")).size() > 0;
    }
}

