package Recetas;

import Entidades.Ingrediente;
import Entidades.Receta;
import Entidades.Utensilio;

import java.util.List;

public class HuevoDuro extends Receta {

    public HuevoDuro() {
        super(10, List.of(new Ingrediente("Huevo", 1), new Ingrediente("Agua", 1)), "Poner un huevo en agua hirviendo en una olla durante 10 minutos.", List.of(new Utensilio("Olla",1)));

    }
}
