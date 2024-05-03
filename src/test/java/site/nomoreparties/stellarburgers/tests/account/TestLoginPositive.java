package site.nomoreparties.stellarburgers.tests.account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.model.BaseTest;
import site.nomoreparties.stellarburgers.model.Constants;
import site.nomoreparties.stellarburgers.pageobjects.*;

public class TestLoginPositive extends BaseTest implements Constants {
    WebDriver driver = new ChromeDriver();
    MainPage objMain = new MainPage(driver);
    EnterAccount objEnter = new EnterAccount(driver);
    ForgotPassword objForgot = new ForgotPassword(driver);
    Topline objTop = new Topline(driver);
    RegisterPage objReg = new RegisterPage(driver);

    @Before
    public void setUp(){
        createTestUser(TESTMAIL, TESTPASS, TESTNAME);
    }

    public void enterThroughTopline(){
        objTop.clickLK();
        checkString(TESTURL+"login", driver.getCurrentUrl());
    }
    public void enetrThroughButton(){
        objMain.mainPageButton().click();
        checkString(TESTURL+"login", driver.getCurrentUrl());
    }
    public void enterThoughRegister(){
        objMain.mainPageButton().click();
        objEnter.goToRegister();
        objReg.enterAccount();
        checkString(TESTURL+"login", driver.getCurrentUrl());
    }
    public void enterThroughForgot(){
        objMain.mainPageButton().click();
        objEnter.forgotPassword().click();
        objForgot.accountFromForgot().click();
        checkString(TESTURL+"login", driver.getCurrentUrl());
    }
    public void enterAccount() throws InterruptedException {
        objEnter.enterAccount(TESTMAIL, TESTPASS);
//        checkString(TESTURL, driver.getCurrentUrl());
        checkString("Оформить заказ", objMain.mainPageButton().getText());
    }

    @Test
    public void checkEnterMain() throws InterruptedException {
        driver.get(TESTURL);
        enetrThroughButton();
        enterAccount();
    }
    @Test
    public void checkEnterTop() throws InterruptedException {
        driver.get(TESTURL);
        enterThroughTopline();
        enterAccount();
    }
    @Test
    public void checkEnterForgot() throws InterruptedException {
        driver.get(TESTURL);
        enterThroughForgot();
        enterAccount();
    }
    @Test
    public void checkEnterRegister() throws InterruptedException {
        driver.get(TESTURL);
        enterThoughRegister();
        enterAccount();
    }
    @After
    public void clearUp(){
        deleteTestUser(TESTMAIL, TESTPASS);
        driver.quit();
    }
}