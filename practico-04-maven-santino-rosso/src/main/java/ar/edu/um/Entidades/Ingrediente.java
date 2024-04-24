package Entidades;

import Excepciones.StockInsuficiente;
import Interfaces.IDespensable;

public class Ingrediente implements IDespensable {
    private String nombre;
    private int cantidad;

    public Ingrediente(String nombre, int cantidad){
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public int getCantidad(){
        return cantidad;
    }

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }

    @Override
    public String sacar(int cantidadASacar) throws StockInsuficiente {
        if (cantidad >= cantidadASacar) {
            cantidad -= cantidadASacar;
            return "Se saco " + cantidad + " de " + nombre;
        } else {
            throw new StockInsuficiente("No hay suficiente " + nombre + ", falta " + (cantidad - this.cantidad) + " unidades");
        }
    }

    @Override
    public String toString(){
        return "Nombre = " + nombre +
                ", Cantidad = " + cantidad;
    }
}
