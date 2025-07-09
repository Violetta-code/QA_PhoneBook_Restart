package tests;

import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        //if Sign Out present ---> logout
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Before method finish logout");
        }
    }

    @Test(dataProvider = "loginData", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String password){
        logger.info("Start test with name 'loginSuccess'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email,password);
        logger.info("Test data --> email : "+email+" & password : "+password);
        app.getHelperUser().submitLogin();

//       Assert.assertEquals();
//       Assert.assertNotEquals();
//       Assert.assertTrue();
//       Assert.assertFalse();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Sign out' present");

    }

  //  @DataProvider
  //  public Iterator<Object[]> loginData(){
        //ПОДСТАВЛЕНИЕ ТЕСТОВЫХ ДАННЫХ В ТЕСТ
         //безопасно перебирает колекцию он никогда не выйдет за его приделы
         // на каждой итерацции вычитывает данные которыми он заполнен
  //      List<Object[]> listOfObjects=new ArrayList<>();
  //      listOfObjects.add(new Object[]{"solodka1998@mail.ru", "Atteloiv369!"});
  //      listOfObjects.add(new Object[]{"atteloiv963@gmail.com", "Atteloiv369!"});
  //      listOfObjects.add(new Object[]{"ttyfv1234@mail.ru", "Atteloiv369!"});

   //     return listOfObjects.iterator();
  //  }

    @Test(dataProvider = "loginFile",dataProviderClass = DataProviderUser.class)
    public void loginSuccessDPF(User user){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        logger.info("Test data --> " + user.toString());
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Sign out' present");

    }

    @Test(dataProvider = "loginModels", dataProviderClass = DataProviderUser.class)
    public void LoginSuccessModel(User user) {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        logger.info("Test data --> " + user.toString());
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Sign out' present");
    }

    @Test
    public void LoginWrongEmailSymbolTest() {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("solodka1998mail.ru", "Atteloiv369!");
        logger.info("Test data --> email : 'solodka1998mail.ru' & password : 'Atteloiv369!'");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is  alert present with text 'Wrong email or password'");


    }

    @Test
    public void LoginWrongEmailTest() {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("sol1998@mail.ru", "Atteloiv369!");
        logger.info("Test data --> email : 'sol1998@mail.ru' & password : 'Atteloiv369!'");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is  alert present with text 'Wrong email or password'");


    }

    @Test
    public void LoginEmptyEmailTest() {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("", "Atteloiv369!");
        logger.info("Test data --> email : '' & password : 'Atteloiv369!'");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is  alert present with text 'Wrong email or password'");


    }

    @Test
    public void LoginNoNumberEmailTest() {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("solsrgd@mail.ru", "Atteloiv369!");
        logger.info("Test data --> email : 'solsrgd@mail.ru' & password : 'Atteloiv369!'");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
       // Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());
        logger.info("Assert check is  alert present with text 'Wrong email or password'");


    }

    @Test
    public void LoginWrongEmailRuTest() {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("solodka1998@mail.com", "Atteloiv369!");
        logger.info("Test data --> email : 'solodka1998@mail.com' & password : 'Atteloiv369!'");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is  alert present with text 'Wrong email or password'");


    }

    @Test
    public void LoginUnRegisterUserTest() {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("solodka19@gmail.com", "Atteloiv369!");
        logger.info("Test data --> email : 'solodka1998@gmail.com' & password : 'Atteloiv369!'");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is  alert present with text 'Wrong email or password'");


    }
    //8-15 Symbol
    @Test
    public void LoginWrongPasswordNoUppercaseSymbolTest() {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("solodka1998@mail.ru", "Atteloiv369");
        logger.info("Test data --> email : 'solodka1998@mail.ru' & password : 'Atteloiv369'");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is  alert present with text 'Wrong email or password'");


    }

    @Test
    public void LoginWrongPasswordShortSymbolTest() {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("solodka1998@mail.ru", "Atteloiv369");
        logger.info("Test data --> email : 'solodka1998@mail.ru' & password : 'Att369!'");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is  alert present with text 'Wrong email or password'");


    }

    @Test
    public void LoginWrongPasswordLongSymbolTest() {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("solodka1998@mail.ru", "Atteloiv369");
        logger.info("Test data --> email : 'solodka1998@mail.ru' & password : 'Aaaaatteloiv369!'");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is  alert present with text 'Wrong email or password'");


    }

    @Test
    public void LoginWrongPasswordTest() {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("solodka1998@mail.ru", "Atkhiv369!");
        logger.info("Test data --> email : 'solodka1998@mail.ru' & password : 'Atkhiv369!'");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is  alert present with text 'Wrong email or password'");


    }

    @Test
    public void LoginEmptyPasswordTest() {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("solodka1998@mail.ru", "");
        logger.info("Test data --> email : 'solodka1998@mail.ru' & password : ''");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is  alert present with text 'Wrong email or password'");


    }

    @Test
    public void LoginPasswordNoUppercaseNumberTest() {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("solodka@mail.ru", "Atteloiv!");
        logger.info("Test data --> email : 'solodka1998@mail.ru' & password : 'Atteloiv!'");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is  alert present with text 'Wrong email or password'");



    }

    @Test
    public void LoginPasswordHasNoUppercaseLetterTest() {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("solodka@mail.ru", "atteloiv369!");
        logger.info("Test data --> email : 'solodka1998@mail.ru' & password : 'atteloiv369!'");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is  alert present with text 'Wrong email or password'");



    }
}
