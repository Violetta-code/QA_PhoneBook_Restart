package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {

    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]>listExample = new ArrayList<>();

        return listExample.iterator();
    }



    @DataProvider
    public Iterator<Object[]> loginData(){
        List<Object[]> listOfObjects=new ArrayList<>();
        //создание колекции Object[]
             listOfObjects.add(new Object[]{"solodka1998@mail.ru", "Atteloiv369!"});
             listOfObjects.add(new Object[]{"atteloiv963@gmail.com", "Atteloiv369!"});
             listOfObjects.add(new Object[]{"ttyfv1234@mail.ru", "Atteloiv369!"});
        return listOfObjects.iterator();
    }


    @DataProvider
    public Iterator<Object[]> loginModels(){
        List<Object[]>list = new ArrayList<>();
        //создание колекции User
        list.add(new Object[]{new User().withEmail("solodka1998@mail.ru").withPassword("Atteloiv369!")});
        list.add(new Object[]{new User().withEmail("atteloiv963@gmail.com").withPassword("Atteloiv369!")});
        list.add(new Object[]{new User().withEmail("ttyfv1234@mail.ru").withPassword("Atteloiv369!")});
        return list.iterator();
    }


}