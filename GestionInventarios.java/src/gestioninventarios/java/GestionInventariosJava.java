/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestioninventarios.java;


public class GestionInventariosJava {

    
    public static void main(String[] args) {
           // Se crean los objetos utilizando polimorfismo
        Producto producto1 = new Electronico("Laptop HP", 18500, 10, 24);
        Producto producto2 = new Alimento("Leche", 35, 40, "12/08/2026");
        Producto producto3 = new Ropa("Playera", 250, 30, "M");

        // esta es parte del (Polimorfismo)
        Producto[] inventario = {producto1, producto2, producto3};

        System.out.println("========= SISTEMA DE GESTIÓN DE INVENTARIOS =========");

        for (Producto p : inventario) {

            p.mostrarInfo();

            p.agregarStock(5);

            p.vender(3);

            p.mostrarInfo();

            System.out.println("------------------------------------------");
        }

    }

}

// Interfaz
// Define las operaciones que todos los productos deben
// implementar.
interface GestionInventario {

    void agregarStock(int cantidad);

    void vender(int cantidad);

    void mostrarInfo();

}

// CLASE ABSTRACTA
// Sirve como base para las clases derivadas.

abstract class Producto implements GestionInventario {

    protected String nombre;
    protected double precio;
    protected int stock;

    // Constructor
    public Producto(String nombre, double precio, int stock) {

        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;

    }

    // Método heredado por todas las clases hijas
   
    public void agregarStock(int cantidad) {

        stock += cantidad;

        System.out.println("Se agregaron " + cantidad + " unidades de " + nombre);

    }

    // Método heredado por todas las clases hijas
   
    public void vender(int cantidad) {

        if (cantidad <= stock) {

            stock -= cantidad;

            System.out.println("Venta realizada de " + cantidad + " unidades.");

        } else {

            System.out.println("Stock insuficiente.");

        }

    }

    // Método abstracto
  
    public abstract void mostrarInfo();

}

// Hereda el producto

class Electronico extends Producto {

    private int garantia;

    public Electronico(String nombre, double precio, int stock, int garantia) {

        super(nombre, precio, stock);

        this.garantia = garantia;

    }
  
    public void mostrarInfo() {

        System.out.println("\n------ ELECTRÓNICO ------");
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: $" + precio);
        System.out.println("Stock: " + stock);
        System.out.println("Garantía: " + garantia + " meses");

    }

}
// CLASE DERIVADA
// HEREDA DE PRODUCTO

class Alimento extends Producto {

    private String fechaCaducidad;

    public Alimento(String nombre, double precio, int stock, String fechaCaducidad) {

        super(nombre, precio, stock);

        this.fechaCaducidad = fechaCaducidad;

    }
   
    public void mostrarInfo() {

        System.out.println("\n------ ALIMENTO ------");
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: $" + precio);
        System.out.println("Stock: " + stock);
        System.out.println("Fecha de caducidad: " + fechaCaducidad);

    }

}

//=====================================================
// CLASE DERIVADA
// HEREDA DE PRODUCTO
//=====================================================
class Ropa extends Producto {

    private String talla;

    public Ropa(String nombre, double precio, int stock, String talla) {

        super(nombre, precio, stock);

        this.talla = talla;

    }

  
    public void mostrarInfo() {

        System.out.println("\n------ ROPA ------");
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: $" + precio);
        System.out.println("Stock: " + stock);
        System.out.println("Talla: " + talla);

    }

}
    
    

