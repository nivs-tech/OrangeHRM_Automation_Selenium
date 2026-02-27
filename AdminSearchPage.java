package pages;

import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminSearchPage {
    WebDriver driver;
    WebDriverWait wait;

    // Navigation
    By adminMenu = By.xpath("//span[text()='Admin']");
    By systemUsersHeader = By.xpath("//h5[text()='System Users']");

    // Search form locators
    By usernameField = By.xpath("(//input[contains(@class,'oxd-input')])[2]");
    By roleDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[1]");
    By statusDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[2]");
    By searchButton = By.xpath("//button[@type='submit']");
    By resetButton = By.xpath("//button[normalize-space()='Reset']");

    // Results
    By noRecordsMessage = By.xpath("//span[text()='No Records Found']");
    By resultsTable = By.xpath("//div[contains(@class,'oxd-table-card')]");
    By dropdownOptions = By.xpath("//div[@role='listbox']//span");

    public AdminSearchPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void navigateToSystemUsers() {
        wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(systemUsersHeader));
    }

    public void enterUsername(String username) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(usernameField));
        field.click();
        field.clear();
        field.sendKeys(username);
    }

    public void selectRole(String role) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(roleDropdown));
        dropdown.click();
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownOptions));
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(role)) {
                option.click();
                return;
            }
        }
        throw new RuntimeException("Role option not found: " + role);
    }

    public void selectStatus(String status) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(statusDropdown));
        dropdown.click();
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownOptions));
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(status)) {
                option.click();
                return;
            }
        }
        throw new RuntimeException("Status option not found: " + status);
    }

    public void clickSearch() {
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchBtn.click();
        wait.until(ExpectedConditions.or(
            ExpectedConditions.visibilityOfElementLocated(resultsTable),
            ExpectedConditions.visibilityOfElementLocated(noRecordsMessage)
        ));
    }

    public void clickReset() {
        WebElement resetBtn = wait.until(ExpectedConditions.elementToBeClickable(resetButton));
        resetBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultsTable));
    }

    public boolean isNoRecordsFound() {
        return !driver.findElements(noRecordsMessage).isEmpty();
    }

    public boolean isResultsDisplayed() {
        return !driver.findElements(resultsTable).isEmpty();
    }

    public int getResultCount() {
        return driver.findElements(resultsTable).size();
    }
}




