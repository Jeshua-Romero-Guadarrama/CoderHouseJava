/*
 * Nombre del archivo: NumeroFlotanteJeshuaRomeroGuadarrama.java
 * Descripción: Pide al usuario un número (float), mostrando su parte entera y decimal.
 */

import java.util.Scanner;

public class NumeroFlotanteJeshuaRomeroGuadarrama {
    public static void main(String[] args) {

        // Se crea un objeto Scanner para leer datos desde el teclado
        Scanner sc = new Scanner(System.in);

        // Se solicita al usuario ingresar un número de tipo float
        System.out.print("Ingresa un número con decimales: ");

        // Se lee el número ingresado y se almacena en la variable 'numero'
        float numero = sc.nextFloat();

        // Se cierra el Scanner
        sc.close();

        // Se obtiene la parte entera del número haciendo un cast a int
        int parteEntera = (int) numero;

        // La parte decimal se obtiene restando la parte entera al número original
        float parteDecimal = numero - parteEntera;

        // Se muestran los resultados en consola
        System.out.println("Número original: " + numero);
        System.out.println("Parte entera: " + parteEntera);
        System.out.println("Parte decimal: " + parteDecimal);
    }
}