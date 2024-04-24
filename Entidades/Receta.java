package Entidades;

import java.util.List;
import java.util.stream.Collectors;

public class Receta {
    protected int tiempoCoccion;
    List<Ingrediente> ingredientes;
    protected String preparacion;
    List<Utensilio> utensilios;

    public Receta(int tiempoCoccion, List<Ingrediente> ingredientes, String preparacion, List<Utensilio> utensilios){
        this.tiempoCoccion = tiempoCoccion;
        this.preparacion = preparacion;
        this.ingredientes = ingredientes;
        this.utensilios = utensilios;
    }

    public List<Ingrediente> getIngredientes(){
        return ingredientes;
    }

    public String getPreparacion(){
        return preparacion;
    }

    public List<Utensilio> getUtensilios() {
        return utensilios;
    }

    public void setUtensilios(List<Utensilio> utensilios) {
        this.utensilios = utensilios;
    }

    @Override
    public String toString() {
        String receta = "Los ingredientes son:\n";
        receta += ingredientes.stream()
                .map(Ingrediente::toString)
                .collect(Collectors.joining("\n"));
        receta += "\nTiempo de coccion: " + tiempoCoccion + " minutos\n";
        receta += "Preparacion: " + preparacion + "\n";
        return receta;
    }
}
