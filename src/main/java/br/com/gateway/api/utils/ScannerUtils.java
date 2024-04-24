package br.com.gateway.api.utils;


import java.util.Scanner;

public class ScannerUtils {

    private static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static String lerDadoTexto() {


        return scanner.next();
    }


    public static int lerDadoInteiro() {

        return scanner.nextInt();
    }

    public static void closeScanner() {
        scanner.close();
    }


}
