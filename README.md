# OrangeHRM Automation Suite

A robust UI test automation framework for the [OrangeHRM](https://opensource-demo.orangehrmlive.com/) web application, built using Selenium WebDriver, TestNG, and the Page Object Model design pattern.

---

## 🧪 What This Framework Covers

- ✅ Login & Authentication
- ✅ Admin Module — Add, Search, Edit, Delete users
- ✅ 30+ automated test cases
- ✅ Explicit waits & dynamic locators for test stability
- ✅ Page Object Model (POM) for maintainable, reusable code
- ✅ TestNG annotations for structured test execution

---

## 🗂️ Project Structure

```
OrangeHRM_Automation_Selenium/
│
├── pages/                         # Page Object classes (package: pages)
│   ├── LoginPage.java
│   ├── AdminPage.java
│   ├── AdminSearchPage.java
│   ├── AdminEditUserPage.java
│   └── AdminDeleteUserPage.java
│
├── tests/                         # TestNG test classes (package: tests)
│   ├── LoginTest.java
│   ├── AdminTest.java
│   ├── AdminSearchTest.java
│   ├── AdminEditUserTest.java
│   └── AdminDeleteUserTest.java
│
├── config/
│   └── BaseTest.java              # WebDriver setup & teardown
│
├── testng.xml                     # TestNG suite configuration
├── pom.xml                        # Maven dependencies
└── README.md
```

---

## ⚙️ Tech Stack

| Tool | Purpose |
|---|---|
| Java | Programming language |
| Selenium WebDriver | Browser automation |
| TestNG | Test execution & reporting |
| Maven | Build & dependency management |
| Page Object Model | Framework design pattern |

---

## ▶️ How to Run

### Prerequisites
- Java JDK 8 or above installed
- Maven installed
- Chrome browser installed
- ChromeDriver matching your Chrome version

### Steps

1. Clone the repository
```bash
git clone https://github.com/nivs-tech/OrangeHRM_Automation_Selenium.git
```

2. Open the project in your IDE (Eclipse or IntelliJ)

3. Run via TestNG XML
   - Right-click `testng.xml`
   - Select **Run As → TestNG Suite**

---

## 📌 Notes

- Application under test: [https://opensource-demo.orangehrmlive.com/](https://opensource-demo.orangehrmlive.com/)
- Framework uses **explicit waits** throughout to handle dynamic elements
- All locators are maintained inside their respective **Page Object classes**

---

Built by [Nivetha Elango]
