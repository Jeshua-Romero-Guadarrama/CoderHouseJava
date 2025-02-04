/*
 * El archivo contiene dos clases:
 *    1) Clase "Persona": Define a una persona con dos atributos: nombre y apellido.
 *    2) Clase "ListarPersonasRomero": Contiene el método main y la lógica de ordenamiento.
 * 
 * El programa crea 5 objetos de tipo Persona con nombres y apellidos, los agrega a una lista y muestra la lista ordenada por:
 *    - Nombre (ascendente)
 *    - Apellido (ascendente)
 *    - Apellido (descendente)
 */

 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.List;
 
 /*
  * Clase que modela a una persona con atributos nombre y apellido, junto con sus métodos de acceso (getters y setters).
  */
 class Persona {
     
     private String nombre;
     private String apellido;
     
     // Constructor vacío
     public Persona() {
     }
     
     // Constructor que inicializa el nombre y el apellido
     public Persona(String nombre, String apellido) {
         this.nombre   = nombre;
         this.apellido = apellido;
     }
     
     // Getter para el nombre
     public String getNombre() {
         return nombre;
     }
     
     // Setter para el nombre
     public void setNombre(String nombre) {
         this.nombre = nombre;
     }
     
     // Getter para el apellido
     public String getApellido() {
         return apellido;
     }
     
     // Setter para el apellido
     public void setApellido(String apellido) {
         this.apellido = apellido;
     }
     
     // Método toString para imprimir de forma amigable la información de la persona
     @Override
     public String toString() {
         return nombre + " " + apellido;
     }
 }
 
 /*
  * Clase principal que contiene el método main. 
  * Aquí se crean los objetos, se ordenan y se muestra el resultado en consola.
  */
 public class ListarPersonasRomero {
 
     public static void main(String[] args) {
         
         // 1. Crear 5 objetos de tipo persona con nombres y apellidos en español.
         Persona p1 = new Persona("Carlos", "Ramírez");
         Persona p2 = new Persona("María",  "López");
         Persona p3 = new Persona("Ana",    "Martínez");
         Persona p4 = new Persona("Luis",   "Núñez");
         Persona p5 = new Persona("Sofía",  "Fernández");
         
         // 2. Agregar los objetos a una lista
         List<Persona> listaPersonas = new ArrayList<>();
         listaPersonas.add(p1);
         listaPersonas.add(p2);
         listaPersonas.add(p3);
         listaPersonas.add(p4);
         listaPersonas.add(p5);
         
         // ---------------------------------------------------------------------------------
         // ORDEN 1: Ordenado por nombre (ascendente)
         // ---------------------------------------------------------------------------------
         // Creamos un Comparator que compare los nombres de las personas
         Comparator<Persona> comparadorPorNombre = new Comparator<Persona>() {
             @Override
             public int compare(Persona o1, Persona o2) {
                 return o1.getNombre().compareToIgnoreCase(o2.getNombre());
             }
         };
         
         // Se crea una copia de la lista para no alterar el orden original en las siguientes pruebas
         List<Persona> listaOrdenNombre = new ArrayList<>(listaPersonas);
         Collections.sort(listaOrdenNombre, comparadorPorNombre);
         
         System.out.println("=== ORDENADO POR NOMBRE (ASCENDENTE) ===");
         for (Persona p : listaOrdenNombre) {
             System.out.println(p);
         }
         
         // ---------------------------------------------------------------------------------
         // ORDEN 2: Ordenado por apellido (ascendente)
         // ---------------------------------------------------------------------------------
         // Creamos un Comparator que compare los apellidos de las personas
         Comparator<Persona> comparadorPorApellidoAsc = new Comparator<Persona>() {
             @Override
             public int compare(Persona o1, Persona o2) {
                 return o1.getApellido().compareToIgnoreCase(o2.getApellido());
             }
         };
         
         List<Persona> listaOrdenApellidoAsc = new ArrayList<>(listaPersonas);
         Collections.sort(listaOrdenApellidoAsc, comparadorPorApellidoAsc);
         
         System.out.println("\n=== ORDENADO POR APELLIDO (ASCENDENTE) ===");
         for (Persona p : listaOrdenApellidoAsc) {
             System.out.println(p);
         }
         
         // ---------------------------------------------------------------------------------
         // ORDEN 3: Ordenado por apellido (descendente)
         // ---------------------------------------------------------------------------------
         // Comparator que compara los apellidos de forma inversa
         Comparator<Persona> comparadorPorApellidoDesc = new Comparator<Persona>() {
             @Override
             public int compare(Persona o1, Persona o2) {
                 return o2.getApellido().compareToIgnoreCase(o1.getApellido());
             }
         };
         
         List<Persona> listaOrdenApellidoDesc = new ArrayList<>(listaPersonas);
         Collections.sort(listaOrdenApellidoDesc, comparadorPorApellidoDesc);
         
         System.out.println("\n=== ORDENADO POR APELLIDO (DESCENDENTE) ===");
         for (Persona p : listaOrdenApellidoDesc) {
             System.out.println(p);
         }
         
     }
 }
 