package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class HelperContact extends HelperBase {

    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {
        click(By.xpath("//*[text()='ADD']"));
    }

    public void fillContactForm(Contact contact) {
        //заполнение формы контакта
        type(By.cssSelector("[placeholder='Name']"), contact.getName());
        type(By.cssSelector("[placeholder='Last Name']"), contact.getLastName());
        type(By.cssSelector("[placeholder='Phone']"), contact.getPhone());
        type(By.cssSelector("[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("[placeholder = 'Address']"), contact.getAddress());
        type(By.cssSelector("[placeholder='description']"), contact.getDescription());
    }

    public void saveContact() {
        click(By.xpath("//*[text()='Save']"));
    }

    public boolean isContactAddedByName(String name) {
     //собираем все локаторы h2 в лист со значениями внутри и получаем колекцию содержащую в себе список имен
        List<WebElement> list = wd.findElements(By.cssSelector("h2"));
        // запускаем цикл по нашему листу и проверяем каждое имя на соответствие с тем что мы добовляли
        for (WebElement el : list) {
            if (el.getText().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isContactAddedByPhone(String phone) {
        //собираем все локаторы h3 в лист со значениями внутри и получаем колекцию содержащую в себе список номеров
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        // запускаем цикл по нашему листу и проверяем каждый номер на соответствие с тем что мы добовляли
        for (WebElement el : list) {
            if (el.getText().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAddNewContactPageStillDisplayed() {
        //ожидаеммый элемент add который указывает что контакт не сохранился
        return isElementPresent(By.cssSelector("a.active[href='/add']"));
    }

    public void provideContacts() {
        //если лист менньше 3 добавь еще 1
        if (countOfContacts() < 3) {
            for (int i = 0; i < 3; i++) {
                addOneContact();
            }
        }
    }

    private void addOneContact() {
        // добавь еще 1
        //рандом
        int i = new Random().nextInt(1000) + 1000;
        //создание контакта
        Contact contact = Contact.builder()
                .name("Rap" + i)
                .lastName("Ziv")
                .email("xiv" + i + "@mail.ru")
                .phone("05464372" + i)
                .address("Tel Aviv")
                .description("friend")
                .build();
        openContactForm();
        fillContactForm(contact);
        saveContact();
        pause(500);
    }

    public int removeOneContact() {
        int before = countOfContacts();
        logger.info("Number of Contact list before remove is -->" + before);
        removeContact();
        int after = countOfContacts();
        logger.info("Number of Contact list after remove is -->" + after);

        return before - after;
    }

    private void removeContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[text()='Remove']"));
        pause(1000);
    }

    private int countOfContacts() {
        // количество контактов
        return wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
    }

    public void removeAllContacts() {
        //цикл удаления всех контактов
        while (countOfContacts() != 0) {
            removeContact();
        }
    }
}