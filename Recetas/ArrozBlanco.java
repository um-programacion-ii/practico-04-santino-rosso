package Recetas;

import Entidades.Ingrediente;
import Entidades.Receta;
import Entidades.Utensilio;

import java.util.List;

public class ArrozBlanco extends Receta {

    public ArrozBlanco() {
        super(20, List.of(new Ingrediente("Arroz", 1), new Ingrediente("Agua", 2), new Ingrediente("Sal", 1/2)), "Lava el arroz bajo agua fría hasta que el agua salga clara. Coloca el arroz en una olla con agua y añade la sal. Lleva a ebullición, luego reduce el fuego y cocina tapado hasta que el arroz absorba toda el agua y esté tierno.", List.of(new Utensilio("Olla", 1)));
    }
}
