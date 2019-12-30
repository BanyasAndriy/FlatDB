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



 public void AddFlatToBD(Scanner scanner){
  try { String district;
   String address;
   String square;
   int countOfRooms;
   int price;

   System.out.print("Введіть район : ");
   district=scanner.next();
   System.out.print("Введіть адрес");
   address=scanner.next();
   System.out.print("Введіть площу : ");
   square=scanner.next();
   System.out.print("Введіть кількість кімнат ");
   countOfRooms=scanner.nextInt();
   System.out.print("Введіть ціну ");
   price=scanner.nextInt();

   PreparedStatement pr = conn.prepareStatement("insert into information (District, address, square, countOfRooms, price)  values (?,?,?,?,?)");

  pr.setString(1,district);
   pr.setString(2,address);
   pr.setString(3,square);
   pr.setInt(4,countOfRooms);
   pr.setInt(5,price);
   pr.executeUpdate();

  } catch (SQLException e) {
   e.printStackTrace();
  }

 }

 public void getDataOfTable() {

  try (PreparedStatement pr = conn.prepareStatement("SELECT * FROM information");) {

   ResultSet resultSet = pr.executeQuery();

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


 public String createSqlQuery(){

  Scanner scan = new Scanner(System.in);
  int entered;
  String parametr="";
  String valueOfParametr ;

  System.out.println("Виберіть параметр по якому буде проходити пошук:(Доступні параметри)\n1 - District\n" +
          "2 - Address\n3 - Square\n4 - countOfRooms\n5 - Price" );

  parametr=scan.next();

  System.out.println("Введіть значення заданого параметру " +parametr);

  valueOfParametr=scan.next();


  String query="Select * From information where "+parametr+"='"+valueOfParametr+"'";

  return query;

 }



 public void findElementByQuery(){
  String query = createSqlQuery();

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
if(iterator==0){
 System.out.println("Введеного значення параметру немає у бд!!!");
}
  } catch (SQLException e) {
   e.printStackTrace();
  }


 }

 }
