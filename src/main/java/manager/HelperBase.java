package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class HelperBase {
    WebDriver wd;

    Logger logger = LoggerFactory.getLogger(HelperBase.class);

    //создание конструктора драйвера для того чтоб не указывать его повторно
    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text){
        WebElement element= wd.findElement(locator);
            element.click();
            element.clear();
           // pause(1000);
            clearNew(element);
        if (text!=null) {
            element.sendKeys(text);
        }
    }

    public void clearNew(WebElement el){
        //очистка строки
         //у элемента вызываем чтоб напечатать
        el.sendKeys(" ");
        //и снова стираем эту строку
        el.sendKeys(Keys.BACK_SPACE);
    }
    public void click(By locator){
        WebElement element= wd.findElement(locator);
        element.click();
    }

    public boolean isElementPresent(By locator){
        // проверка наличия элемента по лакатору . предотвращает получение EXCEPTOIN
        // если есть локатор который мы передали хотя бы один раз значит метод вернет True
        List<WebElement> list = wd.findElements(locator);
        return list.size()>0;
    }

    public boolean isAlertPresent(String message) {

        //ожидания Alert (всплывающее окно с ошибкой например в пароле или майле)
        Alert alert= new WebDriverWait(wd, Duration.ofSeconds(25))
                //либо до тех пор пока alertIsPresent() не вернет
                .until(ExpectedConditions.alertIsPresent());
        //проверка что alert не пустой и что он содержит текст который передали
        if (alert!=null&&alert.getText().contains(message)){
            //alert.accept(); -->click OK
            //alert.dismiss(); -->click cancel
            //alert.sendKeys("hello"); --> type into alert
            alert.accept();
            return true;
        }
        return false;
    }

    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void getScreen(String link) {
        //сообщаем веб драйверу что он теперь работает с скином и кастимизируем драйвер под TakesScreenshot
        TakesScreenshot ts= (TakesScreenshot) wd;
        //указываем в каком виде мы хотим получать наши скрины в данном случае типа FILE
       File fileTakesScreenshot = ts.getScreenshotAs(OutputType.FILE);
        try {
            //копируем скрин и складываем по указанному пути. оборачиваем в трай кетч для Exception
            Files.copy(fileTakesScreenshot, new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
