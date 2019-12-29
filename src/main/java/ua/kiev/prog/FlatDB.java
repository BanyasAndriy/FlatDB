package ua.kiev.prog;

import java.sql.*;
import java.util.Scanner;

public class FlatDB {


 final static String url = "jdbc:mysql://localhost:3306/FlatDB?useLegacyDatetimeCode=false&serverTimezone=Europe/Kiev" ;
 final static String login="root";
 final static String parol="280200";
 private static FlatDB flatDB = new FlatDB();
 private Connection conn;
 {
  try {
   conn = DriverManager.getConnection(url,login,parol);
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

private FlatDB(){};

 public static FlatDB getFlatBD() throws SQLException {
  return flatDB ;
 }



 public void getDataOfTable() {

  try (PreparedStatement pr = conn.prepareStatement("SELECT * FROM information");) {

   ResultSet resultSet = pr.executeQuery();
if (resultSet.next()==false){
 return;
}
   ResultSetMetaData md = pr.getMetaData();

   for (int i = 1; i <= md.getColumnCount(); i++) {
    System.out.print(md.getColumnName(i) + "\t\t");
   }
   System.out.println();

   while (resultSet.next()) {
    for (int i = 1; i <= md.getColumnCount(); i++) {
     System.out.print(resultSet.getString(i) + "\t\t");
    }
    System.out.println();

   }


  } catch (SQLException e) {
   e.printStackTrace();
  }

 }



 public void findElementByParametr(Scanner scan){
  int entered;
  String parametr="";
  String valueOfParametr ;

  System.out.println("Виберіть номер параметра по якому буде проходити пошук:\n1 - District\n" +
          "2 - Address\n3 - Square\n4 - countOfRooms\n5 - Price" );

  entered=scan.nextInt();

  switch (entered){
   case 1 : {
    parametr="District";
   break;
   }
   case 2 :{
   parametr="address";
   break;
   }
   case 3 : {
    parametr="square";
    break;
   }
   case 4 : {
    parametr="countOfRooms";
    break;
   }
   case 5 : {
    parametr="price";
    break;
   }
  }



  System.out.println("Введіть значення заданого параметру " +parametr);


  valueOfParametr=scan.nextLine();
  valueOfParametr=scan.nextLine();

  String query="Select * From information where "+parametr+"='"+valueOfParametr+"'";


  try (PreparedStatement pr = conn.prepareStatement(query)) {

   ResultSet result = pr.executeQuery();



   ResultSetMetaData md = pr.getMetaData();
int iterator=0;
   while (result.next()) {
    iterator++;
    for (int i = 1; i <= md.getColumnCount(); i++) {
     System.out.print(result.getString(i) + "\t\t");
    }
    System.out.println();

   }
   System.out.println("count iter = " + iterator);
if(iterator==0){
 System.out.println("Введеного значення параметру немає у бд!!!");
}

  } catch (SQLException e) {
   e.printStackTrace();
  }


 }




 }
