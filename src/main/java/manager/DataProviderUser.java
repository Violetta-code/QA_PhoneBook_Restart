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

    @DataProvider
    public Iterator<Object[]> loginFile() throws IOException {
        List<Object[]>listData = new ArrayList<>();
        //read from file --->add to list
        //BufferedReader вычитывает данные из File в которые мы положили валидные данные для PhoneBook
        // указываем что он будет читать данные из FileReader а ему уже указываем ссылку
        // на тот файл который мы хотим чтоб он вычитал для FileReader нужен Exception
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/ValidDataPhoneBook.csv")));
        String line = reader.readLine(); //читай по одной строчке
        while (line!=null){//пока не закончатся строчки
            String[]all =  line.split(",");//разрезаем данные первой строки по запятой складываем в массив
            //добовляем в наш лист обект в который мы передаем обьект User в который мы положили index0=email index1=password
            listData.add(new Object[]{new User().withEmail(all[0]).withPassword(all[1])});
            line = reader.readLine();//читай следующую строку
        }

        return listData.iterator();
    }

}