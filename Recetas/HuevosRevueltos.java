package Recetas;

import Entidades.Ingrediente;
import Entidades.Receta;
import Entidades.Utensilio;

import java.util.List;

public class HuevosRevueltos extends Receta {

    public HuevosRevueltos(){
        super(5, List.of(new Ingrediente("Huevo", 4), new Ingrediente("Aceite", 1)), "Batir los huevos en un tazón. Colocar aceite en un sartén y vertir los huevos batidos. Cocinar revolviendo constantemente hasta que estén cocidos.", List.of(new Utensilio("Tazón", 1), new Utensilio("Sartén", 1), new Utensilio("Cucharon", 1)));
    }
}
