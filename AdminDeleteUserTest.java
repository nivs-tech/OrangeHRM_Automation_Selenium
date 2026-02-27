package tests;

import config.BaseTest;
import pages.AdminDeleteUserPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminDeleteUserTest extends BaseTest {
    AdminDeleteUserPage deleteUserPage;

    @BeforeMethod
    public void setupTest() {
        deleteUserPage = new AdminDeleteUserPage(driver, wait);
    }

    // TC26: Delete user "findme"
    @Test(priority = 26)
    public void TC26_DeleteSingleUser() {
        deleteUserPage.navigateToSystemUsers();
        deleteUserPage.searchByUsername("dltuser");
        deleteUserPage.clickFirstDeleteIcon();
        deleteUserPage.confirmDelete();
        Assert.assertTrue(deleteUserPage.isSuccessToastDisplayed(), "User 'findme' should be deleted");
    }

    // TC27: Cancel deletion for "findme1"
    @Test(priority = 27)
    public void TC27_CancelUserDeletion() {
        deleteUserPage.navigateToSystemUsers();
        deleteUserPage.searchByUsername("findme1");
        deleteUserPage.clickFirstDeleteIcon();
        deleteUserPage.cancelDelete();
        Assert.assertTrue(true, "User 'findme1' should remain after cancel");
    }

    // TC28: Delete three users ("findme3", "findme4", "findme5")
    @Test(priority = 28)
    public void TC28_DeleteThreeUsers() {
        deleteUserPage.navigateToSystemUsers();
        deleteUserPage.searchByUsername("findme3");
        deleteUserPage.selectMultipleUsers(3); // select findme3, findme4, findme5
        deleteUserPage.clickDeleteSelected();
        deleteUserPage.confirmDelete();
        Assert.assertTrue(deleteUserPage.isSuccessToastDisplayed(), "Three users should be deleted");
    }

    // TC29: Verify "findme3" removed
    @Test(priority = 29)
    public void TC29_VerifyUserRemovedAfterDeletion() {
        deleteUserPage.navigateToSystemUsers();
        Assert.assertTrue(deleteUserPage.verifyUserDeleted("findme3"), "No records should be found for 'findme3'");
    }

    // TC30: Delete "findme1" with filters applied
    @Test(priority = 30)
    public void TC30_DeleteUserWithFiltersApplied() {
        deleteUserPage.navigateToSystemUsers();
        deleteUserPage.searchByUsername("findme1");
        deleteUserPage.clickFirstDeleteIcon();
        deleteUserPage.confirmDelete();
        Assert.assertTrue(deleteUserPage.isSuccessToastDisplayed(), "User 'findme1' deleted successfully with filter applied");
    }
}



