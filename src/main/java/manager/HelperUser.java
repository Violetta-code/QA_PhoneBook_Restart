package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperUser extends HelperBase{

    //реализация конструктора от родителя поскольку родитель имеет конструктор веб драйвера
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginRegistrationForm(){
        //a[text()='LOGIN']
        WebElement loginTab=wd.findElement(By.xpath(" //a[text()='LOGIN']"));
        loginTab.click();
      //  click(By.xpath(" //a[text()='LOGIN']"));
    }

    public void fillLoginRegistrationForm(String email,String password){

        //создание элемента типа Input(окно для заполнения данных) найдя его по name= email
      //  WebElement emailInput=wd.findElement(By.name("email"));
        //нажатие на найденный элемент(поле ввода)
      //  emailInput.click();
        //очищение поля для ввода данных
      //  emailInput.clear();
        //в найденный элемент печатаем email который нам передали
      //  emailInput.sendKeys(email);

        type(By.name("email"), email);

        //создание элемента типа Input(окно для заполнения данных) найдя его по name= password
      //  WebElement passwordInput= wd.findElement(By.name("password"));
        //нажатие на найденный элемент(поле ввода)
      //  passwordInput.click();
        //очищение поля для ввода данных
      //  passwordInput.clear();
        //в найденный элемент печатаем password который нам передали
      //  passwordInput.sendKeys(password);
        // By.xpath"//input[last()]"

        type(By.name("password"), password);
    }

    public void submitLogin(){
        click(By.xpath("//button[text()='Login']"));
    }

    public boolean isLogged() {
        //верни мне ответ есть ли такой элемент с таким локатором
        return isElementPresent(By.xpath("//button[text()='Sign Out']"));
    }

    public void logout() {
        // нажать на кнопку Sign Out
        click(By.xpath("//button[text()='Sign Out']"));
    }

    public void fillLoginRegistrationForm(User user) {
        type(By.name("email"), user.getEmail());
        type(By.name("password"), user.getPassword());
    }

    public void submitRegistration() {
        click(By.xpath("//button[text()='Registration']"));
    }

    public boolean isNoContactsHereDisplayed() {
        //WebDriverWait ждет отрисовку ожидаеммого элемента
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        // ждем ожидание которое может выйти раньше времени если элемент отрисуется до истечения времени
        //textToBePresentInElement дожидается и элемент и дождется ожидаемый текс
        boolean res = wait.until(ExpectedConditions.textToBePresentInElement
                (wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1")),
                        "No Contacts here!"));
        return res;
    }

    public void login(User user) {
        //вход акаунт
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();
    }
}
