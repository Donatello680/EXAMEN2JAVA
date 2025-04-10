/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hotelreservas;
import javax.swing.JOptionPane;
/**
 *
 * @author genes
 */
public class HotelReservas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
          // Matrices 3x3 para el hotel, para que sea más sencillo
        String[][] estado = new String[3][3];
        String[][] tipo = new String[3][3];
        int[][] precio = new int[3][3];
        String[][] numero = new String[3][3];

        // Precarga de habitaciones
        int habitacionNum = 301;
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                numero[i][j] = Integer.toString(habitacionNum); //Guarda numero de habitacion
                estado[i][j] = "Libre"; //Todas van libres, como si el hotel estuviera abriendo, y vayan guardadno todo

                // Usamos if-else en vez de ternario, o sea el símbolo ? porque no lo vimos en clase
                //el j%2 es para que si j es par la habitacion sera simple, si j es impar la habitacion sera doble
                //ya que % es residuo de una division
                //"El precio base es $50, y le sumamos 5 multiplicado por el número de la habitación (j)."
                if (j % 2 == 0) {
                    tipo[i][j] = "Simple";
                } else {
                    tipo[i][j] = "Doble";
                }

                precio[i][j] = 50 + (j * 5);
                habitacionNum++;
            }
            habitacionNum -= 10;
        }

        // Bucle principal
        boolean seguirModificando = true;

        while (seguirModificando) {
            int piso = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de piso (1-3):")) - 1;
            int hab = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de habitación (1-3):")) - 1;

            if (piso >= 0 && piso < 3 && hab >= 0 && hab < 3) {
                // Mostrar datos actuales
                String mensaje = "Habitación " + numero[piso][hab]
                        + "\nEstado actual: " + estado[piso][hab]
                        + "\nTipo actual: " + tipo[piso][hab]
                        + "\nPrecio actual: $" + precio[piso][hab];
                JOptionPane.showMessageDialog(null, mensaje);

                // Modificar estado (0 = sí). Esto lo usé en el examen pasado, el 0 equivale a sí y la verdad sirve mucho
                int cambiarEstado = JOptionPane.showConfirmDialog(null, "¿Deseas modificar el estado?", "Modificar", JOptionPane.YES_NO_OPTION);
                if (cambiarEstado == 0) {
                    String nuevoEstado = JOptionPane.showInputDialog("Ingrese nuevo estado (Libre / Ocupada / Sucia):");
                    estado[piso][hab] = nuevoEstado;
                }

                // Modificar tipo, uso el mismo truco de si la variable equivale a sí o no
                int cambiarTipo = JOptionPane.showConfirmDialog(null, "¿Deseas modificar el tipo?", "Modificar", JOptionPane.YES_NO_OPTION);
                if (cambiarTipo == 0) {
                    String nuevoTipo = JOptionPane.showInputDialog("Ingrese nuevo tipo (Simple / Doble):");
                    tipo[piso][hab] = nuevoTipo;
                }

                // Modificar precio, básicamente repetí el truco, esto se usa en el proyecto igual 
                int cambiarPrecio = JOptionPane.showConfirmDialog(null, "¿Deseas modificar el precio?", "Modificar", JOptionPane.YES_NO_OPTION);
                if (cambiarPrecio == 0) {
                    int nuevoPrecio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese nuevo precio:"));
                    precio[piso][hab] = nuevoPrecio;
                }

                JOptionPane.showMessageDialog(null, "¡Modificación completada!");
            } else {
                JOptionPane.showMessageDialog(null, "Piso o habitación inválidos.");
            }

            // Preguntar si desea seguir
            int continuar = JOptionPane.showConfirmDialog(null, "¿Deseas modificar otro piso?", "Continuar", JOptionPane.YES_NO_OPTION);
            if (continuar != 0) { // Si no es "sí" (0), salir
                seguirModificando = false;
            }
        }

        // Mostrar matriz final en consola usando for y eso sería todo, pero antes hay que declarar las variables en 0
        //Para hacer los calculos
        System.out.println("\n--- Estado final del hotel ---\n");
        int libres = 0;
        int ocupadas = 0;
        int sucias = 0;
        int ganancia = 0;
        int total = 0;

        for (int i = 2; i >= 0; i--) {
            System.out.println("Piso " + (i + 1));
            for (int j = 0; j < 3; j++) {
                System.out.println("Habitación " + numero[i][j]
                        + " | Estado: " + estado[i][j]
                        + " | Tipo: " + tipo[i][j]
                        + " | Precio: $" + precio[i][j]);

                // Contar estado y sumar ganancia si está ocupada
                //Ocupo comparar dos textos Strings
                //Usemos Compare To y el 0 y 1 
                total++;
                if (estado[i][j].compareToIgnoreCase("Libre") == 0) {
                    libres++;
                } else if (estado[i][j].compareToIgnoreCase("Ocupada") == 0) {
                    ocupadas++;
                    ganancia += precio[i][j];
                } else if (estado[i][j].compareToIgnoreCase("Sucia") == 0) {
                    sucias++;
                }
            }
            System.out.println();
        }

        // Mini resumen final del hotel
        System.out.println("--- Resumen del hotel ---");
        System.out.println("Habitaciones libres: " + libres + " (" + (libres * 100 / total) + "%)");
        System.out.println("Habitaciones ocupadas: " + ocupadas + " (" + (ocupadas * 100 / total) + "%)");
        System.out.println("Habitaciones sucias: " + sucias + " (" + (sucias * 100 / total) + "%)");
        System.out.println("Ganancia total del hotel: $" + ganancia);
    }
}