package tests;

import config.BaseTest;
import pages.AdminPage;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminTest extends BaseTest {
    LoginPage loginPage;
    AdminPage adminPage;

    @BeforeMethod
    public void setupTest() {
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver, wait);
    }

    @Test(priority = 7)
    public void TC07_AddNewUserValidDetails() {
        adminPage.navigateToAdmin();
        adminPage.clickAddUser();
        adminPage.selectDropdown("User Role", "Admin");
        adminPage.enterEmployeeName("a");
        adminPage.selectDropdown("Status", "Enabled");
        adminPage.enterUsername("user" + System.currentTimeMillis());
        adminPage.enterPassword("Admin@123");
        adminPage.clickSaveExpectSuccess();
        Assert.assertTrue(adminPage.isSuccessMessageDisplayed());
    }

 // TC08: Add user with missing required fields
    @Test(priority = 8)
    public void TC08_AddUserMissingFields() {
        adminPage.navigateToAdmin();
        adminPage.clickAddUser();
        adminPage.clickSaveExpectValidation();

        Assert.assertTrue(adminPage.isValidationErrorDisplayed(), "Validation messages should be displayed");
        Assert.assertTrue(adminPage.isOnAddUserPage(), "Should remain on Add User page");
    }

    // TC09: Add user with duplicate username
    @Test(priority = 9)
    public void TC09_AddUserDuplicateUsername() {
        adminPage.navigateToAdmin();
        adminPage.clickAddUser();

        adminPage.selectDropdown("User Role", "Admin");
        adminPage.enterEmployeeName("a");
        adminPage.selectDropdown("Status", "Enabled");
        adminPage.enterUsername("Admin"); // Duplicate username
        adminPage.enterPassword("Admin@123");
        adminPage.clickSaveExpectValidation();

        Assert.assertTrue(adminPage.isValidationErrorDisplayed(), "Error: Username already exists");
        Assert.assertTrue(adminPage.isOnAddUserPage(), "Should remain on Add User page");
    }

    // TC10: Add user with invalid password format
    @Test(priority = 10)
    public void TC10_AddUserInvalidPassword() {
        adminPage.navigateToAdmin();
        adminPage.clickAddUser();

        adminPage.selectDropdown("User Role", "Admin");
        adminPage.enterEmployeeName("a");
        adminPage.selectDropdown("Status", "Enabled");
        adminPage.enterUsername("user" + System.currentTimeMillis());
        adminPage.enterPassword("123"); // Weak password
        adminPage.clickSaveExpectValidation();

        Assert.assertTrue(adminPage.isValidationErrorDisplayed(), "Password validation error should be shown");
        Assert.assertTrue(adminPage.isOnAddUserPage(), "Should remain on Add User page");
    }

    // TC11: Cancel user creation
    @Test(priority = 11)
    public void TC11_CancelUserCreation() {
        adminPage.navigateToAdmin();
        adminPage.clickAddUser();
        adminPage.enterUsername("testcancel");
        adminPage.clickCancel();

        Assert.assertTrue(adminPage.isOnSystemUsersPage(), "Should return to System Users page");
    }

    // TC12: Add user with different role
    @Test(priority = 12)
    public void TC12_AddUserWithDifferentRole() {
        adminPage.navigateToAdmin();
        adminPage.clickAddUser();

        adminPage.selectDropdown("User Role", "ESS");
        adminPage.enterEmployeeName("a");
        adminPage.selectDropdown("Status", "Enabled");
        adminPage.enterUsername("essuser" + System.currentTimeMillis());
        adminPage.enterPassword("Admin@123");
        adminPage.clickSaveExpectSuccess();

        Assert.assertTrue(adminPage.isSuccessMessageDisplayed(), "Success toast should be displayed");
    }

    // TC13: Add user with disabled status
    @Test(priority = 13)
    public void TC13_AddUserWithDisabledStatus() {
        adminPage.navigateToAdmin();
        adminPage.clickAddUser();

        adminPage.selectDropdown("User Role", "Admin");
        adminPage.enterEmployeeName("a");
        adminPage.selectDropdown("Status", "Disabled");
        adminPage.enterUsername("disabled" + System.currentTimeMillis());
        adminPage.enterPassword("Admin@123");
        adminPage.clickSaveExpectSuccess();

        Assert.assertTrue(adminPage.isSuccessMessageDisplayed(), "Success toast should be displayed");
    }

    // TC14: Verify success message after adding user
    @Test(priority = 14)
    public void TC14_VerifySuccessMessage() {
        adminPage.navigateToAdmin();
        adminPage.clickAddUser();

        adminPage.selectDropdown("User Role", "Admin");
        adminPage.enterEmployeeName("a");
        adminPage.selectDropdown("Status", "Enabled");
        adminPage.enterUsername("user" + System.currentTimeMillis());
        adminPage.enterPassword("Admin@123");
        adminPage.clickSaveExpectSuccess();

        Assert.assertTrue(adminPage.isSuccessMessageDisplayed(), "Success toast should be displayed");
    }
}
