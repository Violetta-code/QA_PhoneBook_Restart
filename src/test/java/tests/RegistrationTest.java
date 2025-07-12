package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTest extends TestBase{


    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        //if Sign Out present ---> logout
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("Before method finish registration logout");
        }
    }

    @Test(groups = {"smoke"})
    public void registrationSuccess(){
        logger.info("Start test: Registration Success");
        //рандомная переменная для подставления числа в маил чтоб каждый раз при рег был новый маил
        int i = new Random().nextInt(1000)+1001;
        //обьект типа User вызывающий withEmail(setEmail) и пароль для рег пользоваеля
        User user = new User().withEmail("via"+i+"@mail.ru").withPassword("Via12345$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);

        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());
    }


    @Test(priority = 1)
    public void registrationWrongEmailSymbolTest() {
        logger.info("Start test: Registration Wrong Email Symbol");

        User user = new User().withEmail("solodka19mail.ru").withPassword("Via12345$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));

    }

    @Test
    public void registrationWrongEmailRuTest() {
        logger.info("Start test: Registration Wrong Email RU");

        User user = new User().withEmail("solodka19@mail.com").withPassword("Via12345$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));

    }

    @Test
    public void registrationWrongPasswordIsShort() {
        logger.info("Start test: Registration Wrong Password Is Short");

        User user = new User().withEmail("solodka19@mail.ru").withPassword("Viol23!");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        app.getHelperUser().pause(3000);

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));

    }

    @Test
    public void registrationPasswordHasNoUppercaseLetterTest() {
        logger.info("Start test: Registration Wrong Password Has No Upper Case Letter");

        User user = new User().withEmail("solodka19@mail.ru").withPassword("atteloiv369!");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }

    @Test
    public void registrationPasswordNoUppercaseNumberTest() {
        User user = new User().withEmail("solodka19@mail.ru").withPassword("Atteloiv!");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }

    @Test
    public void registrationWrongPasswordNoUppercaseSymbolTest() {
        User user = new User().withEmail("solodka19@mail.ru").withPassword("Atteloiv369");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }

    @Test(groups = {"smoke"})
    public void registrationExistsUser() {
        User user = new User().withEmail("solodka1998@mail.ru").withPassword("Atteloiv369!");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));

    }

}
