/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;

/**
 *
 * @author Junior Nuñes
 */
public abstract class RentItem {
    private String codigoItem;
    private String nombreItem;
    private double precioRenta;
    private int copias;

    public RentItem(String itemCode, String itemName, double rentalPrice) {
        this.codigoItem = itemCode;
        this.nombreItem = itemName;
        this.precioRenta = rentalPrice;
        this.copias = 0;
    }

    public String getCodigoItem() {
        return codigoItem;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public double getPrecioRenta() {
        return precioRenta;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigoItem + ", Nombre: " + nombreItem + ", Precio: " + precioRenta;
    }

    public abstract double pagoRenta(int dias);
}
