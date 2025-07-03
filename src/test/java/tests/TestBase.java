package tests;

import manager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

   static ApplicationManager app = new ApplicationManager();

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
