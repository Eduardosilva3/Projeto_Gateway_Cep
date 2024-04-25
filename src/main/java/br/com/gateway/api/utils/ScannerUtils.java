package br.com.gateway.api.utils;


import br.com.gateway.api.exception.GatewayException;

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

        String dado = scanner.next();

        if(!dado.matches("[0-9]+")){
            throw new GatewayException("Dado inválido, digite um número inteiro!");
        }

        return Integer.parseInt(dado);
    }

    public static void closeScanner() {
        scanner.close();
    }


}
