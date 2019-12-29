package ua.kiev.prog;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

Scanner sc = new Scanner(System.in);
        try {
            FlatDB flatDB = FlatDB.getFlatBD();
            int input;
            while(true){

                System.out.println("Введіть 1 для виводу всієї таблиці!");
                System.out.println("Введіть 2 для пошуку квартири по параметрах!");
                input=sc.nextInt();
                if (input==1){
                    flatDB.getDataOfTable();
                    System.out.println("Для виходу введіть 0 , для продовження будь-якe інше число!");
                    input=sc.nextInt();
                    if (input==0)
                        break;
                }else if(input==2){
                    flatDB.findElementByParametr(sc);
                    System.out.println("Для виходу введіть 0 , для продовження будь-якe інше число!");
                    input=sc.nextInt();
                    if (input==0)
                        break;
                }






            }



        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
