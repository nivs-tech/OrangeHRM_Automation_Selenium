package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class AdminEditUserPage {
    WebDriver driver;
    WebDriverWait wait;

    // Navigation
    By adminMenu = By.xpath("//span[text()='Admin']");
    By systemUsersHeader = By.xpath("//h5[text()='System Users']");

    // Table and actions
    By usersTable = By.xpath("//div[contains(@class,'oxd-table-card')]");
    By firstEditIcon = By.xpath("(//i[@class='oxd-icon bi-pencil-fill'])[1]");

    // Edit form fields
    By roleDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[1]");
    By statusDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[2]");
    By employeeNameField = By.xpath("//input[@placeholder='Type for hints...']");
    By usernameField = By.xpath("(//input[contains(@class,'oxd-input')])[2]");

    By saveButton = By.xpath("//button[@type='submit']");
    By cancelButton = By.xpath("//button[normalize-space()='Cancel']");
    By dropdownOptions = By.xpath("//div[@role='listbox']//span");

    // Loader and messages
    By formLoader = By.xpath("//div[contains(@class,'oxd-form-loader')]");
    By successToast = By.xpath("//p[contains(@class,'oxd-text') and text()='Success']");
    By errorMessage = By.xpath("//span[contains(@class,'oxd-input-field-error-message')]");

    public AdminEditUserPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void navigateToSystemUsers() {
        wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(systemUsersHeader));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usersTable));
    }

    public void clickFirstEditIcon() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usersTable));
        wait.until(ExpectedConditions.elementToBeClickable(firstEditIcon)).click();
        waitUntilFormReady();
    }

    private void waitUntilFormReady() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
        } catch (TimeoutException ignored) {
            // proceed if loader never appeared
        }
    }

    public void selectRole(String role) {
        waitUntilFormReady();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(roleDropdown));
        dropdown.click();
        for (WebElement option : wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownOptions))) {
            if (option.getText().trim().equalsIgnoreCase(role)) {
                option.click();
                waitUntilFormReady();
                return;
            }
        }
        throw new RuntimeException("Role option not found: " + role);
    }

    public void selectStatus(String status) {
        waitUntilFormReady();
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(statusDropdown));
        dropdown.click();
        for (WebElement option : wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownOptions))) {
            if (option.getText().trim().equalsIgnoreCase(status)) {
                option.click();
                waitUntilFormReady();
                return;
            }
        }
        throw new RuntimeException("Status option not found: " + status);
    }

    public void enterEmployeeName(String name) {
        waitUntilFormReady();
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(employeeNameField));
        field.clear();
        field.sendKeys(name);
    }

    public void clickSave() {
        waitUntilFormReady();
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        waitUntilFormReady();
    }

    public void clickCancel() {
        waitUntilFormReady();
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
        waitUntilFormReady();
    }

    public boolean isSuccessToastDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        return !driver.findElements(errorMessage).isEmpty();
    }

    public String getRoleText() {
        waitUntilFormReady();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(roleDropdown)).getText().trim();
    }

    public String getStatusText() {
        waitUntilFormReady();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(statusDropdown)).getText().trim();
    }
}

