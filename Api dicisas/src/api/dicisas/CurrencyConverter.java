/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.dicisas;

import java.io.IOException;
import java.util.Scanner;

public class CurrencyConverter {

    private CurrencyService currencyService;

    public CurrencyConverter(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Conversor de Monedas (COP a otras divisas)");
            System.out.println("Seleccione la divisa de destino:");
            System.out.println("1. USD (Dólar estadounidense)");
            System.out.println("2. EUR (Euro)");
            System.out.println("3. GBP (Libra esterlina)");
            System.out.println("4. JPY (Yen japonés)");
            System.out.println("5. CAD (Dólar canadiense)");
            System.out.println("6. Salir");
            System.out.print("Ingrese el número correspondiente a la divisa: ");

            int option = scanner.nextInt();
            String toCurrency = "";

            switch (option) {
                case 1:
                    toCurrency = "USD";
                    break;
                case 2:
                    toCurrency = "EUR";
                    break;
                case 3:
                    toCurrency = "GBP";
                    break;
                case 4:
                    toCurrency = "JPY";
                    break;
                case 5:
                    toCurrency = "CAD";
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida.");
                    continue;
            }

            System.out.print("Ingrese la cantidad en pesos colombianos (COP): ");
            double amount = scanner.nextDouble();

            try {
                double convertedAmount = currencyService.convert("COP", toCurrency, amount);
                System.out.println(amount + " COP = " + convertedAmount + " " + toCurrency);
            } catch (IOException e) {
                System.out.println("Error en la conversión: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        CurrencyService currencyService = new ApiCurrencyService();
        CurrencyConverter converter = new CurrencyConverter(currencyService);
        converter.run();
    }
}

