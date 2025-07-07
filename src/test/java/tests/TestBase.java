package tests;

import manager.ApplicationManager;
import manager.TestNGListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Listeners(TestNGListener.class)

public class TestBase {

   //подключаем Logger для отслеживания наших тестов
   Logger logger = LoggerFactory.getLogger(TestBase.class);
   //это представляет собой ссылку на класс ApplicationManager и тем самым связывает их иделаем ее статической переменной
   static ApplicationManager app = new ApplicationManager();

   //перед каждым методом будет писать название теста
   @BeforeMethod
   public void startLogger(Method m){
      logger.info("Name of method -->" + m.getName());
   }

   @AfterMethod
   public void end(){
      logger.info("=================================================================");
   }

   //
   @BeforeSuite
   public void setUp(){
   // Вызов метода  init() через  static ApplicationManager
   app.init();
   }

   //
   @AfterSuite
   public void tearDown(){
   //  Вызов метода  stop() через  static ApplicationManager
   app.stop();
   }
}
