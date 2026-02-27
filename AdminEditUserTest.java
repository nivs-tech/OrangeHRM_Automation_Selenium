package tests;

import config.BaseTest;
import pages.AdminEditUserPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminEditUserTest extends BaseTest {
    AdminEditUserPage editUserPage;

    @BeforeMethod
    public void setupTest() {
        editUserPage = new AdminEditUserPage(driver, wait);
    }

    // TC21: Edit user role (Admin -> ESS) and save
    @Test(priority = 21)
    public void TC21_EditUserRole() {
        editUserPage.navigateToSystemUsers();
        editUserPage.clickFirstEditIcon();
        editUserPage.selectRole("ESS");
        editUserPage.clickSave();
        Assert.assertTrue(editUserPage.isSuccessToastDisplayed(), "Role should be updated successfully");
    }

    // TC22: Edit user status (Enabled -> Disabled) and save
    @Test(priority = 22)
    public void TC22_EditUserStatus() {
        editUserPage.navigateToSystemUsers();
        editUserPage.clickFirstEditIcon();
        editUserPage.selectStatus("Disabled");
        editUserPage.clickSave();
        Assert.assertTrue(editUserPage.isSuccessToastDisplayed(), "Status should be updated successfully");
    }

    // TC23: Edit user with invalid employee name -> expect validation error
    @Test(priority = 23)
    public void TC23_EditUserInvalidData() {
        editUserPage.navigateToSystemUsers();
        editUserPage.clickFirstEditIcon();
        editUserPage.enterEmployeeName("A"); // invalid input
        editUserPage.clickSave();
        Assert.assertTrue(editUserPage.isErrorMessageDisplayed(), "Validation error should be shown for invalid employee name");
    }

    // TC24: Cancel edit operation -> changes not saved
    @Test(priority = 24)
    public void TC24_CancelEditOperation() {
        editUserPage.navigateToSystemUsers();
        editUserPage.clickFirstEditIcon();
        String originalRole = editUserPage.getRoleText();
        editUserPage.selectRole(originalRole.equalsIgnoreCase("Admin") ? "ESS" : "Admin");
        editUserPage.clickCancel();

        // reopen and verify role remains original
        editUserPage.clickFirstEditIcon();
        String roleAfterCancel = editUserPage.getRoleText();
        Assert.assertEquals(roleAfterCancel, originalRole, "Changes should not be saved after cancel");
    }

    // TC25: Verify success message after edit
    @Test(priority = 25)
    public void TC25_VerifySuccessMessageAfterEdit() {
        editUserPage.navigateToSystemUsers();
        editUserPage.clickFirstEditIcon();
        editUserPage.selectRole("ESS");
        editUserPage.selectStatus("Enabled");
        editUserPage.clickSave();
        Assert.assertTrue(editUserPage.isSuccessToastDisplayed(), "Success notification should be displayed after edit");
    }
}
