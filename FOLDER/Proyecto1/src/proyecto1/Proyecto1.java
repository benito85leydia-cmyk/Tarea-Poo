/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1;
import java.util.Scanner;
// Clase Padre
class CuentaBancaria {
    protected int saldo;

    public CuentaBancaria(int saldoInicial) {
        saldo = saldoInicial;
    }

    public int getSaldo() {
        return saldo;
    }

    public void retirar(int cantidad) {
        saldo -= cantidad;
    }
}

// Clase Hija
class CajeroAutomatico extends CuentaBancaria {

    public CajeroAutomatico(int saldoInicial) {
        super(saldoInicial);
    }
}

public class Proyecto1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        
        int saldo = 1000;
        int intentos = 3;
        
        System.out.println("--- BIENVENIDO AL BANCO JUMAL ---");
        
        //  WHILE para validar el acceso Control de intentos
        while (intentos > 0) {
            System.out.print("Introduce tu PIN (Intentos restantes: " + intentos + "): ");
            String pin = scanner.nextLine();
            
            System.out.println("\nTotal saldo: = 1000");
            saldo = 1000;
            
            if (pin.equals("1234")) {
                System.out.println("Acceso concedido.");
                break; 
            } else {
                intentos -= 1;
                System.out.println("PIN incorrecto.");
            }
        }
        
        //  IF para verificar si bloqueamos la cuenta
        if (intentos == 0) {
            System.out.println("Cuenta bloqueada por seguridad.");
        } else {
            // Bucle FOR para denominaciones de billetes disponibles
            System.out.println("\nBilletes disponibles en este cajero:");
            int[] billetes = {10, 20, 50, 100};
            
            for (int b : billetes) {
                System.out.println("Cajero con billetes de: $" + b);
            }
            
            // Menú final con IF/ELIF 
            System.out.print("\n¿Desea retirar saldo? (si/no): ");
            String opcion = scanner.nextLine().toLowerCase();
            
            if (opcion.equals("si")) {
                System.out.print("¿Cuánto desea retirar?: ");
                int retiro = scanner.nextInt();
                
                if (retiro <= saldo) {
                    System.out.println("Retiro exitoso. Su nuevo saldo es: $" + (saldo - retiro));
                } else {
                    System.out.println("Saldo insuficiente.");
                }
            }
            
            System.out.println("Gracias por visitarnos.");
        }
        
        scanner.close(); 
    }
}