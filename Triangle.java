package com.homework.string;

import java.util.Scanner;

public class Triangle {
    public static void triangle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("!");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Number :");
        int input = scan.nextInt();
        triangle(input);
    }
}
