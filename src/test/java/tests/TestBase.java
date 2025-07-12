package tests;

import manager.ApplicationManager;
import manager.TestNGListener;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Listeners(TestNGListener.class)

public class TestBase {

   //подключаем Logger для отслеживания наших тестов
   Logger logger = LoggerFactory.getLogger(TestBase.class);
   //это представляет собой ссылку на класс ApplicationManager и тем самым связывает их иделаем ее статической переменной
   static ApplicationManager app = new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));

   //перед каждым методом будет писать название теста
   @BeforeMethod(alwaysRun = true)
   public void startLogger(Method m){
      logger.info("Name of method -->" + m.getName());
   }

   @AfterMethod(alwaysRun = true)
   public void end(){
      logger.info("=================================================================");
   }

   //
   @BeforeSuite(alwaysRun = true)
   public void setUp(){
   // Вызов метода  init() через  static ApplicationManager
   app.init();
   }

   //
   @AfterSuite(alwaysRun = true)
   public void tearDown(){
   //  Вызов метода  stop() через  static ApplicationManager
  // app.stop();
   }
}
