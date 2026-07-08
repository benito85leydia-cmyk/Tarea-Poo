
package proyectojava03;
import java.util.Scanner;

public class ProyectoJava03 {

   
    
    public static void main(String[] args)  {

        Scanner entrada = new Scanner(System.in);

        // Simulación de Cajero Automático
        int saldo = 1000;
        int intentos = 3;
        int pin;

        System.out.println(" BIENVENIDO AL BANCO JUMAL ");

        // Bucle WHILE para validar el acceso
        while (intentos > 0) {
            System.out.print("Introduce tu PIN (Intentos restantes: " + intentos + "): ");
            pin = entrada.nextInt();

            System.out.println("Total saldo: $" + saldo);

            if (pin == 1234) {
                System.out.println("Acceso concedido.");
                break;
            } else {
                intentos--;
                System.out.println("PIN incorrecto.");
            }
        }

        // Verificar si la cuenta quedó bloqueada
        if (intentos == 0) {
            System.out.println("Cuenta bloqueada por seguridad.");
        } else {

            // Mostrar denominaciones de billetes
            System.out.println("\nBilletes disponibles en este cajero:");
            int[] billetes = {10, 20, 50, 100};

            for (int b : billetes) {
                System.out.println("Cajero con billetes de: $" + b);
            }

            // Menú para retirar dinero
            System.out.print("\n¿Desea retirar saldo? (si/no): ");
            String opcion = entrada.next();

            if (opcion.equalsIgnoreCase("si")) {

                System.out.print("¿Cuánto desea retirar?: ");
                int retiro = entrada.nextInt();

                if (retiro <= saldo) {
                    saldo -= retiro;
                    System.out.println("Retiro exitoso.");
                    System.out.println("Su nuevo saldo es: $" + saldo);
                } else {
                    System.out.println("Saldo insuficiente.");
                }
            }

            System.out.println("Gracias por visitarnos.");
        }

        entrada.close();
    }
}
        