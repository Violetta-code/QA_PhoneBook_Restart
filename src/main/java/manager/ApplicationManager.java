package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public class ApplicationManager {

    WebDriver wd;

    // ссылка на HelperUser (нуждается в созданнии getHelperUser)
    HelperUser helperUser;

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    HelperContact helperContact;
    String browser;

    //-----------------------------------------------------------------------------------------------------------------
    public void init(){

        if(browser.equals(Browser.CHROME.browserName())) {
            wd = new ChromeDriver();
            logger.info("All tests runs in Chrome Browser");
        } else if (browser.equals(Browser.FIREFOX.browserName())) {
            wd = new FirefoxDriver();
            logger.info("All tests runs in FIREFOX Browser");
        } else //if (browser.equals(Browser.EDGE.browserName()))
           {
            wd = new EdgeDriver();
            logger.info("All tests runs in EDGE Browser");
           }
    //инициализация WebDriver wd и открытие нового ChromeDriver
  //  wd=new ChromeDriver();
   // logger.info("All tests runs in Chrome Browser");
    //открытие браузера на весь экран
    wd.manage().window().maximize();
    //открытие браузера науменьшеном экране
    //wd.manage().window().minimize();
    //некое ожидание с указанием времени. работает как пауза для ожидания появления желаемого элемента
    wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    //знакомство с WebDriverListener
    WebDriverListener webDriverListener=new WDListener();
    //говорим браузеру что у него теперь есть слушатель
    wd=new EventFiringDecorator(webDriverListener).decorate(wd);

        //WebDriver wd открытие веб страницы которую будем тестировать
    wd.navigate().to("https://telranedu.web.app/");

    logger.info("The link -->" + wd.getCurrentUrl());

    // создание экземпляра HelperUser в который передаем браузер
    helperUser=new HelperUser(wd);
    // создание экземпляра HelperContact в который передаем браузер
    helperContact=new HelperContact(wd);

}

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void stop(){
    //закрытие одного окна и браузера
    //    wd.close();
    //закрытие всех окон и браузера
     wd.quit();
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperContact getHelperContact() {
        return helperContact;
    }
}
