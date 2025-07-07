package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;


public class WDListener implements WebDriverListener {

    Logger logger= LoggerFactory.getLogger(WDListener.class);

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        WebDriverListener.super.beforeFindElement(driver, locator);
        logger.info("Before Find Element:"+locator);
    }

    @Override
    public void beforeClick(WebElement element) {
        WebDriverListener.super.beforeClick(element);
        logger.info("Before Click Element:"+element.getTagName()+"text = "+element.getText());
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        WebDriverListener.super.afterSendKeys(element, keysToSend);
        logger.info("After Send Keys Element:"+ element.getTagName() + "text" + element.getText());
    }

    @Override
    public void afterClick(WebElement element) {
        WebDriverListener.super.afterClick(element);
        logger.info("After Click Element:"+element.getTagName()+"text = "+element.getText());
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        WebDriverListener.super.afterFindElement(driver, locator, result);
        logger.info("After Find Element:"+locator);
        logger.info("Location of element :" + result.getLocation());
    }

    @Override
    public void beforeTo(WebDriver.Navigation navigation, String url) {
        WebDriverListener.super.beforeTo(navigation, url);
        logger.info("Url-->" + url);
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        //работает всегда
         //уведомляет об ошибке слушателя
        WebDriverListener.super.onError(target, method, args, e);

        logger.info("I have a problem!!!");
        //дай мне обьект из за которого возниклв проблемма
        logger.info("Object target --> " + target.toString());
        logger.info("******************");
        //дай мне метод в котором произошла проблема
        logger.info("Method name -->" + method.getName());
        logger.info("******************");
        //дай мне Exception
        logger.info("InvocationTargetException" + e.getTargetException());
        logger.info("******************");

        //скриншот проблемы
        int i = new Random().nextInt(1000) + 1000;
        String link = "src/test/screenshotsException/screen_" + i + ".png";
        //переключаем драйвер на наш Object target и делаем кастинг
        WebDriver wd = (WebDriver) target;

        //переключаем драйвер на наш TakesScreenshot и делаем кастинг
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp,new File(link));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        logger.info("Screen with Error is--->" + link);
    }
}
