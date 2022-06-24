package com.project.projectTest;

import com.project.Methods;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Warehouse {

    public static int choseWhatToDo(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please choose what to do (1 - List all items; 2 - Add new delivery; 3 - List deliveries for time period)");
        int numberOfPlayers = 0;
        String input;

        while (!validateInput(input = scan.nextLine())) {
        }

        numberOfPlayers = Integer.parseInt(input);

        return numberOfPlayers;
    }

    public static boolean validateInput(String input) {
        if (input.length() == 1 && input.charAt(0) > 48 && input.charAt(0) < 52) {
            return true;
        }
        System.out.println("Invalid players! Try again!");
        return false;
    }


    private static final int shelfCapacity = 500;
    private static final List<String> shelfLiters = new ArrayList<>();
    private static final List<String> shelfKg = new ArrayList<>();
    private static final Map<String, Object> warehouse = new HashMap<>();

    public static void main(String[] args) {

      switch (choseWhatToDo()) {
          case 1:
              printProductData();
              break;
          case 2:
              addNewDelivery();
              break;
          case 3:
//              checkDate();
      }

              System.out.println("Enter Product type : ");


              System.out.println("Shelf KG");
              printShelf(shelfKg);
              System.out.println("\n" + "Shelf Liters");
              printShelf(shelfLiters);




    }
    private static void putProductOnTheShelf(List shelf, int quantity) {
         for (int i = 0; i < quantity; i++) {
             if( shelf.size()-1 == shelfCapacity){
                 System.out.println("This shelf is full! There is no more space for :" + (shelf.size() - shelfCapacity));
                 break;
             }
                 shelf.add(i, "1");
             if( i == quantity-1 ){
                 System.out.println("Product was added successfully!");
             }
         }
    }
    private static void printShelf(List shelf) {
        if( shelf.isEmpty() ){
            System.out.println("This shelf is empty");
        }
        for (int i = 0; i < shelf.size(); i++) {

            if( i % 50 == 0 && i != 0){
                System.out.println();
            }
                System.out.print(shelf.get(i));
            if( i == shelfCapacity ){
                System.out.println( "\n"+ 500 + "\n" + "This shelf is full!" + "\n"  + "There is no more space for: "+ (shelf.size() - shelfCapacity) + " Units" );
                break;
            }
            if( i == shelf.size()-1){
                System.out.println("\n"+ "\n" + "All quantity for this shelf is:" + shelf.size());
            }
        }
    }
    private static int getQuantity(String product, String brand, String quantity) {
        return Integer.parseInt((String)((HashMap<String, Object>)((HashMap<String, Object>)warehouse.get(product)).get(brand)).get(quantity));
    }
    private static void printProductData() {
        for (Map.Entry<String, Object> a : warehouse.entrySet()) {
            for (Map.Entry<String, Object> b : ((HashMap<String, Object>) a.getValue()).entrySet()) {
                System.out.println(b.getKey());
                System.out.println(b.getValue());
            }
        }
    }

    private static void printSelectedProduct(Map<String, Object> entryDate){
        for (Map.Entry<String, Object> a : entryDate.entrySet()) {
            a.getValue();
            System.out.println((String) a.getValue());
        }
    }


    private static void checkDate(String product, String brand, LocalDate date1, LocalDate date2 ){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd,MM, yyyy");
        HashMap<String, Object> currentProductDate = (HashMap<String, Object>)((HashMap<String, Object>)warehouse.get(product)).get(brand);

        String date = (String)(currentProductDate).get("entryDay");
        LocalDate currentDate = LocalDate.parse(date, formatter);
        if ( currentDate.isAfter(date1) && currentDate.isBefore(date2)){
            printSelectedProduct(currentProductDate);
        }
    }
    public static void addNewDelivery(){

        String product = "";
        String brand = "";
        String entryDay = "";
        String expiryDay = "";
        String unit = "";
        String quantity = "";
        String comment = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter product:");
        while (!(product = scan.nextLine()).equals("end")) {

            if (warehouse.get(product) == null) {
                warehouse.put(product, new HashMap<>());
            }
            System.out.println("Enter brand:");
            brand = scan.nextLine();
            System.out.println("Enter comment:");
            comment = scan.nextLine();
            System.out.println("Enter entry day:");
            entryDay = scan.nextLine();
            System.out.println("Enter expiry day:");
            expiryDay = scan.nextLine();
            System.out.println("Enter unit ( liters or kg): ");
            while ( !(unit = scan.nextLine()).equalsIgnoreCase("liters") && !unit.equalsIgnoreCase("kg") ) {
                System.out.println("Invalid input ! Enter liters or kg :");
            }
            System.out.println("Enter quantity:");
            quantity = scan.nextLine();

            ((HashMap<String, Object>) warehouse.get(product)).put(brand, new HashMap<>());
            HashMap<String, Object> brands = ((HashMap<String, Object>) ((HashMap<String, Object>) warehouse.get(product)).get(brand));
            brands.put("entryDay", entryDay);
            brands.put("expiryDay", expiryDay);
            brands.put("unit", unit);
            brands.put("comment", comment);
            brands.put("quantity", quantity);

            int currentQuantity = getQuantity(product, brand, "quantity");

               checkShelfSpace(unit, currentQuantity );
            System.out.println("Enter product or close program (input command - end) ");
        }
    }
    public static void checkShelfSpace(String unit, int currentQuantity) {
        if (unit.equalsIgnoreCase("Kg")) {
            putProductOnTheShelf(shelfKg, currentQuantity);
        } else if (unit.equalsIgnoreCase("liters")) {
            putProductOnTheShelf(shelfLiters, currentQuantity);
        }
    }

    private static void toDate(Scanner scan){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd,MM, yyyy");
        System.out.println("After date :");
        String afterDate = scan.nextLine();
        LocalDate date1 = LocalDate.parse(afterDate, formatter);

        System.out.println("Before date :");
        String beforeDate = scan.nextLine();
        LocalDate date2 = LocalDate.parse(beforeDate, formatter);
//        checkDate();
    }


}
