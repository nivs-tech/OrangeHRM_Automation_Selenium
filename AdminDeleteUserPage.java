package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class AdminDeleteUserPage {
    WebDriver driver;
    WebDriverWait wait;

    // Navigation
    By adminMenu = By.xpath("//span[text()='Admin']");
    By systemUsersHeader = By.xpath("//h5[text()='System Users']");

    // Table and actions
    By usersTable = By.xpath("//div[@class='oxd-table-card']");
    By firstDeleteIcon = By.xpath("(//i[@class='oxd-icon bi-trash'])[1]");
    By checkboxes = By.xpath("//div[@class='oxd-table-card']//div[@class='oxd-checkbox-wrapper']//span");
    By deleteSelectedButton = By.xpath("//button[normalize-space()='Delete Selected']");

    // Confirmation modal
    By confirmDeleteButton = By.xpath("//button[normalize-space()='Yes, Delete']");
    By cancelDeleteButton = By.xpath("//button[normalize-space()='No, Cancel']");

    // Messages
    By successToast = By.xpath("//p[contains(@class,'oxd-text') and text()='Success']");
    By noRecordsMessage = By.xpath("//span[text()='No Records Found']");

    // Search filters
    By usernameField = By.xpath("//label[text()='Username']/../following-sibling::div//input");
    By searchButton = By.xpath("//button[normalize-space()='Search']");
    By resetButton = By.xpath("//button[normalize-space()='Reset']");

    public AdminDeleteUserPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void navigateToSystemUsers() {
        wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(systemUsersHeader));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usersTable));
    }

    public void clickFirstDeleteIcon() {
        WebElement icon = wait.until(ExpectedConditions.presenceOfElementLocated(firstDeleteIcon));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", icon);
        wait.until(ExpectedConditions.elementToBeClickable(icon));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", icon);
    }

    public void confirmDelete() {
        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmDeleteButton));
        wait.until(ExpectedConditions.elementToBeClickable(btn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public void cancelDelete() {
        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(cancelDeleteButton));
        wait.until(ExpectedConditions.elementToBeClickable(btn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public void selectMultipleUsers(int count) {
        List<WebElement> boxes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(checkboxes));
        for (int i = 0; i < count && i < boxes.size(); i++) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", boxes.get(i));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", boxes.get(i));
        }
    }

    public void clickDeleteSelected() {
        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(deleteSelectedButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public boolean isSuccessToastDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean verifyUserDeleted(String username) {
        searchByUsername(username);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(noRecordsMessage));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Search filter methods
    public void searchByUsername(String username) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(usernameField));
        field.clear();
        field.sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void resetSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(resetButton)).click();
    }
}
