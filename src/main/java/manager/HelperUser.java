package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;

public class HelperUser extends HelperBase{

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginRegistrationForm(){
        //a[text()='LOGIN']
        WebElement loginTab=wd.findElement(By.xpath(" //a[text()='LOGIN']"));
        loginTab.click();
    }
}
