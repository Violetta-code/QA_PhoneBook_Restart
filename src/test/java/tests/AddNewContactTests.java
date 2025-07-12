package tests;

import manager.DataProviderContact;
import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase{

    @BeforeClass(alwaysRun = true)
    public void preCondition(){
        //если не залогинен тогда залогинься
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("solodka1998@mail.ru").withPassword("Atteloiv369!"));
            logger.info("Before method finish logout");
        }
    }

    @Test(groups = {"smoke","retest","reset"})
    public void addContactSuccessAllFields(){
        // создание рандома можно так же с классом System написать
        int i = new Random().nextInt(1000)+1000;
        // создание контакта и заполнение строк спомощью builder().build()
        Contact contact = Contact.builder()
                .name("Back"+i)
                .lastName("Masha")
                .phone("0528765"+i)
                .email("masha"+i+"@mail.ru")
                .address("Haifa")
                .description("all fields")
                .build();
      app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(15000);
        //вызываем скрин и указываем путь к папке куда складывать и под каким названием
        // сохранение каждый раз нового файла
       app.getHelperContact().getScreen("src/test/screenshots/screen-"+i +".png");
        //вызываем скрин и указываем путь к папке куда складывать и под каким названием
        // перезапись одного и того же файла
       // app.getHelperContact().getScreen("src/test/screenshots/screen.png");
        app.getHelperContact().saveContact();
        //проверка добавился ли контакт с тем именем которое мы написали
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        //проверка добавился ли контакт с тем номером которое мы написали
         Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));

    }

   @Test (dataProvider = "contactCSV", dataProviderClass = DataProviderContact.class)
    public void addContactSuccessAllFieldsCSV(Contact contact){
        int i = new Random().nextInt(1000)+1000;

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        logger.info("Test data --> email : "+contact.toString());
        //app.getHelperContact().pause(15000);
        app.getHelperContact().getScreen("src/test/screenshots/screen-"+i +".png");
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));

    }

    @Test
    public void addContactSuccessReqFields(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Black"+i)
                .lastName("Sasha")
                .phone("0528765"+i)
                .email("sasha"+i+"@mail.ru")
                .address("Harish")
                .description("all fields")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
      //  app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test
    public void addNewContactEmptyName(){
        Contact contact = Contact.builder()
                .name("")
                .lastName("Masha")
                .phone("0528765855")
                .email("masha57@mail.ru")
                .address("Haifa")
                .description("Empty Name")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
       // app.getHelperContact().pause(10000);
        app.getHelperContact().saveContact();
       Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());
    }

    @Test
    public void addNewContactEmptyLastName(){
        Contact contact = Contact.builder()
                .name("Back7")
                .lastName("")
                .phone("0528765768")
                .email("masha96@mail.ru")
                .address("Haifa")
                .description("Empty Last Name")
                .build();
        app.getHelperContact().openContactForm();
       app.getHelperContact().fillContactForm(contact);
      //  app.getHelperContact().pause(10000);
     app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());
    }

    @Test(dataProvider = "contactWrongPhone",dataProviderClass = DataProviderContact.class)
    public void addNewContactWrongPhone(Contact contact){

        logger.info("Test run with data :" + contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        // app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));

    }


    @Test
    public void addNewContactShortPhoneNumber(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Boock"+i)
                .lastName("Misha")
                .phone("98765"+i)
                .email("masha"+i+"@mail.ru")
                .address("Haifa")
                .description("Short Phone Number")
                .build();
        logger.info("Test run with data :" + contact.getPhone());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        // app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact()
                .isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));

    }

    @Test
    public void addNewContactLongPhoneNumber(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Boock"+i)
                .lastName("Misha")
                .phone("987657890543"+i)
                .email("masha"+i+"@mail.ru")
                .address("Haifa")
                .description("Long Phone Number")
                .build();
        //вся информация контакта
        //logger.info("Test run with data :" + contact.toString());
        //только номер контакта
        logger.info("Test run with data :" + contact.getPhone());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        // app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact()
                .isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));

    }

    @Test(dataProvider = "contactWrongEmail",dataProviderClass = DataProviderContact.class)
    public void addNewContactWrongEmail(Contact contact){

        logger.info("Test run with data :" + contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        // app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid: must be a well-formed email address"));

    }

    @Test
    public void addNewContactWrongEmailSymbolTest(){
        Contact contact = Contact.builder()
                .name("Backlick")
                .lastName("Masha")
                .phone("0528765765")
                .email("masha67mail.ru")
                .address("Haifa")
                .description("Email Symbol")
                .build();
        app.getHelperContact().openContactForm();
       app.getHelperContact().fillContactForm(contact);
       // app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
       Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());
       Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid: must be a well-formed email address"));

    }

    @Test
    public void addNewContactWrongEmailSymbol2Test(){
        Contact contact = Contact.builder()
                .name("Backlick2")
                .lastName("Masha")
                .phone("0528765765")
                .email("masha67@@mail.ru")
                .address("Haifa")
                .description("Email 2 Symbol")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid"));
    }

    @Test
    public void addNewContactWrongEmailRUTest(){
        Contact contact = Contact.builder()
                .name("Backlick3")
                .lastName("Masha")
                .phone("0528765765")
                .email("masha67mail.")
                .address("Haifa")
                .description("RU")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
       // app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid"));
    }

    @Test
    public void addNewContactWrongEmailMailTest(){
        Contact contact = Contact.builder()
                .name("Backlick")
                .lastName("Masha")
                .phone("0528765765")
                .email("masha67@.ru")
                .address("Haifa")
                .description("Mail")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
       // app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid"));
    }

    @Test
    public void addNewContactEmptyAddress(){
        Contact contact = Contact.builder()
                .name("Zip")
                .lastName("Dasha")
                .phone("0528765765")
                .email("masha67@mail.ru")
                .address("")
                .description("Empty Address")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
      //  app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());
    }
}
