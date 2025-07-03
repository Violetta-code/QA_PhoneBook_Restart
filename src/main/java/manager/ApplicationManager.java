package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {

WebDriver wd;
HelperUser helperUser;

public void init(){
    //инициализация WebDriver wd и открытие нового ChromeDriver
    wd=new ChromeDriver();
    //открытие браузера на весь экран
    wd.manage().window().maximize();
    //открытие браузера науменьшеном экране
    //wd.manage().window().minimize();
    //WebDriver wd открытие веб страницы которую будем тестировать
    wd.navigate().to("https://telranedu.web.app/");

    helperUser=new HelperUser(wd);
}

    public void stop(){
    //закрытие одного окна и браузера
    //    wd.close();
    //закрытие всех окон и браузера
    // wd.quit();
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }
}
