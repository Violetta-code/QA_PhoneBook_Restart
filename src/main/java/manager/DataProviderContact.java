package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact {

    @DataProvider
    public Iterator<Object[]> contactWrongEmail() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                Contact.builder()
                        .name("Xip")
                        .lastName("Masha")
                        .email("mashagmail.com")
                        .phone("0543678900")
                        .address("Haifa")
                        .description("Masha")
                        .build()
        });
        list.add(new Object[]{
                Contact.builder()
                        .name("Xip")
                        .lastName("Masha")
                        .email("masha@@gmail.com")
                        .phone("0543678900")
                        .address("Haifa")
                        .description("Masha")
                        .build()
        });
        //СОЗДАЛ!!!!!
       // list.add(new Object[]{
       //         Contact.builder()
        //                .name("Xip")
        //                .lastName("Masha")
         //               .email("masha@gmailcom")
         //               .phone("0543678900")
          ///              .address("Haifa")
          //              .description("Masha")
          //              .build()
        //});
        //СОЗДАЛ!!!!!
        //list.add(new Object[]{
        //        Contact.builder()
        //                .name("Xip")
        //                .lastName("Masha")
         //               .email("masha@gmail.ru")
         //               .phone("0543678900")
         //               .address("Haifa")
         //               .description("Masha")
         //               .build()
        //});
        //СОЗДАЛ!!!!!
       /// list.add(new Object[]{
        //        Contact.builder()
       //                 .name("Xip")
       //                 .lastName("Masha")
       //                 .email("masha@com")
        //                .phone("0543678900")
       //                 .address("Haifa")
        ///                .description("Masha")
        //                .build()
       // });
        list.add(new Object[]{
                Contact.builder()
                        .name("Xip")
                        .lastName("Masha")
                        .email("masha@gmail..com")
                        .phone("0543678900")
                        .address("Haifa")
                        .description("Masha")
                        .build()
        });
        list.add(new Object[]{
                Contact.builder()
                        .name("Xip")
                        .lastName("Masha")
                        .email("masha@gmail.")
                        .phone("0543678900")
                        .address("Haifa")
                        .description("Masha")
                        .build()
        });
        list.add(new Object[]{
                Contact.builder()
                        .name("Xip")
                        .lastName("Masha")
                        .email("masha@.com")
                        .phone("0543678900")
                        .address("Haifa")
                        .description("Masha")
                        .build()
        });

        //list.add(new Object[]{
        //        Contact.builder()
        //                .name("Xip")
         //               .lastName("Masha")
         //               .email("")
         //               .phone("0543678900")
         //               .address("Haifa")
         //               .description("Masha")
         //               .build()
       // });
        //СОЗДАЛ!!!!!
        // list.add(new Object[]{
        //        Contact.builder()
        //                .name("Xip")
       //                 .lastName("Masha")
       //                 .phone("0543678900")
       //                 .address("Haifa")
        //                .description("Masha")
        //                .build()
       // });
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> contactWrongPhone() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                Contact.builder()
                        .name("Xip")
                        .lastName("Masha")
                        .email("masha@gmail.com")
                        .phone("123456789")
                        .address("Haifa")
                        .description("Masha")
                        .build()
        });
        list.add(new Object[]{
                Contact.builder()
                        .name("Xip")
                        .lastName("Masha")
                        .email("masha@gmail.com")
                        .phone("1234567891234567")
                        .address("Haifa")
                        .description("Masha")
                        .build()
        });
        list.add(new Object[]{
                Contact.builder()
                        .name("Xip")
                        .lastName("Masha")
                        .email("masha@gmail.com")
                        .phone("aaaaaaaaaaaaa")
                        .address("Haifa")
                        .description("Masha")
                        .build()
        });
        list.add(new Object[]{
                Contact.builder()
                        .name("Xip")
                        .lastName("Masha")
                        .email("masha@gmail.com")
                        .phone("")
                        .address("Haifa")
                        .description("Masha")
                        .build()
        });
        list.add(new Object[]{
                Contact.builder()
                        .name("Xip")
                        .lastName("Masha")
                        .email("masha@gmail.com")
                        .phone("     ")
                        .address("Haifa")
                        .description("Masha")
                        .build()
        });

        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> contactCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/ValidDataContact.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] all = line.split(",");
            list.add(new Object[]{Contact.builder()
                    .name(all[0])
                    .lastName(all[1])
                    .phone(all[2])
                    .email(all[3])
                    .address(all[4])
                    .description(all[5])
                    .build()
            });
            line = reader.readLine();
        }
        return list.iterator();
    }
}