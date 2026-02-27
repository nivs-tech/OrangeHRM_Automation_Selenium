package tests;

import config.BaseTest;
import pages.AdminSearchPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminSearchTest extends BaseTest {
    AdminSearchPage searchPage;

    @BeforeMethod
    public void setupTest() {
        searchPage = new AdminSearchPage(driver, wait);
    }

    // TC15: Search existing user by username
        @Test(priority = 15)
        public void TC15_SearchExistingUser() {
            searchPage.navigateToSystemUsers();
            searchPage.enterUsername("Admin");
            searchPage.clickSearch();
            Assert.assertTrue(searchPage.isResultsDisplayed(), "Existing user should appear in results");
        }

        // TC16: Search non-existing user
        @Test(priority = 16)
        public void TC16_SearchNonExistingUser() {
            searchPage.navigateToSystemUsers();
            searchPage.enterUsername("ZZZZZZZZZZZZZZZZ"); // guaranteed non-existent
            searchPage.clickSearch();
            Assert.assertTrue(searchPage.isNoRecordsFound(), "No records should be found");
        }

        // TC17: Search user by role
        @Test(priority = 17)
        public void TC17_SearchByRole() {
            searchPage.navigateToSystemUsers();
            searchPage.selectRole("Admin");
            searchPage.clickSearch();
            int count = searchPage.getResultCount();
            Assert.assertTrue(count > 0, "Users with role Admin should be displayed");
            System.out.println("✓ TC17 PASSED - Found " + count + " users with Admin role");
        }

        // TC18: Search user by status
        @Test(priority = 18)
        public void TC18_SearchByStatus() {
            searchPage.navigateToSystemUsers();
            searchPage.selectStatus("Enabled");
            searchPage.clickSearch();
            int count = searchPage.getResultCount();
            Assert.assertTrue(count > 0, "Enabled users should be displayed");
            System.out.println("✓ TC18 PASSED - Found " + count + " enabled users");
        }

        // TC19: Reset search filters
        @Test(priority = 19)
        public void TC19_ResetFilters() {
            searchPage.navigateToSystemUsers();
            searchPage.enterUsername("Admin");
            searchPage.selectRole("Admin");
            searchPage.selectStatus("Enabled");
            searchPage.clickSearch();

            int beforeResetCount = searchPage.getResultCount();
            Assert.assertTrue(beforeResetCount > 0, "Filtered results should be displayed");

            searchPage.clickReset();
            int afterResetCount = searchPage.getResultCount();
            Assert.assertTrue(afterResetCount >= beforeResetCount,
                    "Reset should show full list (>= filtered count)");
            System.out.println("✓ TC19 PASSED - Reset restored full list");
        }

        // TC20: Search with partial username should not return results
        @Test(priority = 20)
        public void TC20_SearchPartialUsernameNotAccepted() {
            searchPage.navigateToSystemUsers();
            searchPage.enterUsername("Adm"); // partial value
            searchPage.clickSearch();

            Assert.assertTrue(searchPage.isNoRecordsFound(),
                    "Partial username should not return results");
            System.out.println("✓ TC20 PASSED - Partial username not accepted, no records found");
        }
    }



