package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    @BeforeClass
    public void preCondition(){
        //если не залогинен тогда залогинься
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("solodka1998@mail.ru").withPassword("Atteloiv369!"));
        }

        app.getHelperContact().provideContacts(); //если лист менньше 3 добавь еще 3
    }

    @Test
    public void removeAAFirstContact(){
        //проверка что длинна листа стала на один меньше
        Assert.assertEquals(app.getHelperContact().removeOneContact(),1);
    }

    @Test
    public void removeAllContacts(){
        //проверка что список пуст
        app.getHelperContact().removeAllContacts();
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());
    }


}
