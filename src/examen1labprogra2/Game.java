/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;

/**
 *
 * @author Junior Nuñez
 */
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class Game extends RentItem implements MenuActions {
    private Calendar fechaSalida;
    private ArrayList<String> especificaciones;

    public Game(String itemCode, String itemName) {
        super(itemCode, itemName, 20.0);
        this.fechaSalida = Calendar.getInstance(); 
        this.especificaciones = new ArrayList<>();
    }

    @Override
    public String toString() {
        return super.toString() + ", Fecha Salida: " + fechaSalida.getTime() + " - Juego PS3 ";
    }

    public void setFechaPublicacion(int year, int month, int day) {
        fechaSalida.set(year, month - 1, day); 
    }

    public void addEspecificacion(String especificacion) {
        especificaciones.add(especificacion);
    }

    public String getEspecificaciones() {
        StringBuilder unir = new StringBuilder();
        for (String spec : especificaciones) {
            unir.append(spec).append("\n");
        }
        return unir.toString();
    }

    @Override
    public double pagoRenta(int dias) {
        return 20 * dias;
    }

    @Override
    public void submenu() {
      
    }

    @Override
public void ejecutarOpcion(int opcion) {
    switch (opcion) {
        case 1:
            
            try {
                int year = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año de publicacion:"));
                
                int month = 0;
                while (true) {
                    try {
                        month = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el mes de publicación (1-12):"));
                        if (month >= 1 && month <= 12) {
                            break; 
                        } else {
                            JOptionPane.showMessageDialog(null, "Mes no valido. Por favor, ingrese un numero entre 1 y 12.");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Entrada no valida. Ingrese un número entre 1 y 12.");
                    }
                }

                int day = 0;
                while (true) {
                    try {
                        day = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el dia de publicación (1-31):"));
                        if (day >= 1 && day <= 31) {
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Dia no valido. Por favor, ingrese un numero entre 1 y 31.");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Entrada no valida. Ingrese un numero entre 1 y 31.");
                    }
                }

                setFechaPublicacion(year, month, day);
                JOptionPane.showMessageDialog(null, "Fecha de publicacion actualizada.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Año no valido. Operacion cancelada.");
            }
            break;

        case 2:
            
            String especificacion = JOptionPane.showInputDialog("Ingrese una especificacion:");
            if (especificacion != null && !especificacion.trim().isEmpty()) {
                addEspecificacion(especificacion.trim());
                JOptionPane.showMessageDialog(null, "Especificacion agregada.");
            } else {
                JOptionPane.showMessageDialog(null, "Especificacion no valida.");
            }
            break;

        case 3:
            
            String especificaciones = getEspecificaciones();
            JOptionPane.showMessageDialog(null, especificaciones.isEmpty() ? "No hay especificaciones." : especificaciones, "Especificaciones", JOptionPane.INFORMATION_MESSAGE);
            break;

        default:
            
            JOptionPane.showMessageDialog(null, "Opción no valida.");
    }
}
}
