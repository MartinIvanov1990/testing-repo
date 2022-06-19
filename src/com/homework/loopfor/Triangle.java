package com.homework.loopfor;

import java.util.Scanner;

public class Triangle {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int length = s.nextInt();
        String st = "0";
        int symbol = 1;
        for (int i = length; 1 <= i; i--) {
            for (int space = i - 1; 1 <= space; space--) {
                System.out.print(" ");
            }
            for (int j = 1; j <= symbol; j++) {
                System.out.print(st);
            }
            System.out.println();
            symbol +=2;
        }
    }
}
