package examen1labprogra2;

import java.time.LocalDate;

public class Movie extends RentItem {
    private LocalDate fecha;

    public Movie(String itemCode, String itemName, double rentalPrice) {
        super(itemCode, itemName, rentalPrice);
        this.fecha = LocalDate.now(); 
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fechaEstreno) {
        this.fecha = fechaEstreno;
    }

   
    public String getEstado() {
        LocalDate now = LocalDate.now();
        int diferenciaAnios = now.getYear() - fecha.getYear();
        int diferenciaMeses = now.getMonthValue() - fecha.getMonthValue();
        int mesesTotales = diferenciaAnios * 12 + diferenciaMeses;

        return mesesTotales <= 3 ? "Estreno" : "Normal";
    }

    @Override
    public String toString() {
        return super.toString() + ", Estado: " + getEstado() + " - Pelicula";
    }

    
    @Override
    public double pagoRenta(int dias) {
        if ("Estreno".equals(getEstado()) && dias > 2) {
            return getPrecioRenta() + 50 * (dias - 2);
        } else if ("Normal".equals(getEstado()) && dias > 5) {
            return getPrecioRenta() + 30 * (dias - 5);
        }
        return getPrecioRenta();
    }
}