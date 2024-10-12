import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();
        Map<String, Double> tasasDeCambio = consulta.obtenerTasasDeCambio();
        HistorialDeConversiones historial = new HistorialDeConversiones();

        if (tasasDeCambio == null) {
            System.out.println("No se pudieron obtener las tasas de cambio.");
            return;
        }

        String menu = """
                Escriba la moneda a convertir
                1 - Peso Argentino a Dolar
                2 - Dolar a Peso Argentino
                3 - Peso Boliviano a Dolar
                4 - Dolar a Peso Boliviano
                5 - Real a Dolar
                6 - Dolar a Real
                7 - Peso Chileno a Dolar
                8 - Dolar a Peso Chileno
                9 - PesoC olombiano a Dolar
                10 - Dolar a Peso Colombiano
                0 - Salir
                 """;

        int opcion = -1;
        while (opcion != 0) {
            System.out.println(menu);
            opcion = lectura.nextInt();

            switch (opcion) {
                case 1:
                    realizarConversion(lectura, tasasDeCambio, historial, "ARS", "USD");
                    break;
                case 2:
                    realizarConversion(lectura, tasasDeCambio, historial,  "USD", "ARS");
                    break;
                case 3:
                    realizarConversion(lectura, tasasDeCambio, historial,  "BOB", "USD");
                    break;
                case 4:
                    realizarConversion(lectura, tasasDeCambio, historial,  "USD", "BOB");
                    break;
                case 5:
                    realizarConversion(lectura, tasasDeCambio, historial,  "BRL", "USD");
                    break;
                case 6:
                    realizarConversion(lectura, tasasDeCambio, historial,  "USD", "BRL");
                    break;
                case 7:
                    realizarConversion(lectura, tasasDeCambio, historial,  "CLP", "USD");
                    break;
                case 8:
                    realizarConversion(lectura, tasasDeCambio, historial,  "USD", "CLP");
                    break;
                case 9:
                    realizarConversion(lectura, tasasDeCambio, historial,  "COP", "USD");
                    break;
                case 10:
                    realizarConversion(lectura, tasasDeCambio, historial,  "USD", "COP");
                    break;
                case 0:
                    System.out.println("Saliendo del conversor");
                    break;
                default:
                    System.out.println("Opción no válida, por favor elige otra.");
                    break;
            }
            System.out.println("**************************************");
        }
        historial.guardarHistorialEnJson("historial_conversiones.json");
        lectura.close();
    }

    private static void realizarConversion(Scanner lectura, Map<String, Double> tasasDeCambio,HistorialDeConversiones historial, String monedaOrigen, String monedaDestino) {
        System.out.println("¿Cuál es el monto a convertir?");
        double valorAConvertir = lectura.nextDouble();

        if (valorAConvertir < 0) {
            System.out.println("No es posible convertir un número negativo.");
            return;
        }

        double saldoConvertido = (monedaOrigen.equals("USD")) ? valorAConvertir * tasasDeCambio.get(monedaDestino)
                : valorAConvertir / tasasDeCambio.get(monedaOrigen);

        String registro = String.format("Convertido %.2f %s a %.2f %s", valorAConvertir, monedaOrigen, saldoConvertido, monedaDestino);
        historial.agregarConversion(registro);

        System.out.println("El saldo convertido es " + saldoConvertido + " " + monedaDestino);

    }
}