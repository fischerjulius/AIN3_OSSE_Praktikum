package de.hfu;
import java.util.Scanner;
public class App {
    public  static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Eingabe:");
        String str = sc.nextLine();
        System.out.println("Ausgabe = " + str.toUpperCase());
        sc.close();
    }
}
