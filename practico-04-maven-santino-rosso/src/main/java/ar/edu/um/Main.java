import Entidades.*;
import Interfaces.IDespensable;
import Recetas.ArrozBlanco;
import Recetas.HuevoDuro;
import Recetas.HuevosRevueltos;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        List<Receta> recetas = List.of(new HuevoDuro(), new HuevosRevueltos(), new ArrozBlanco());

        List<IDespensable> Ingredientes = List.of(
                new Ingrediente("Arroz", 100),
                new Ingrediente("Agua", 100),
                new Ingrediente("Sal", 100),
                new Ingrediente("Huevo", 100),
                new Ingrediente("Aceite", 100)
        );

        List<Despensa> despensas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Despensa despensa = new Despensa();
            for (IDespensable ingrediente : Ingredientes) {
                String nombre_ingrediente = ((Ingrediente) ingrediente).getNombre();
                int cantidad_ingrediente = ((Ingrediente) ingrediente).getCantidad();
                Ingrediente nuevoIngrediente = new Ingrediente(nombre_ingrediente, cantidad_ingrediente);
                despensa.addDespensa(nombre_ingrediente, nuevoIngrediente);
            }
            despensas.add(despensa);
        }

        List<IDespensable> Utensilios = List.of(new Utensilio("Tazón", 100),
                new Utensilio("Sartén", 100),
                new Utensilio("Olla", 100),
                new Utensilio("Cucharon", 100)
        );

        Estante estante = Estante.getInstanciaEstante();
        for (IDespensable utensilio : Utensilios) {
            String nombreUtensilio = "";
            nombreUtensilio = ((Utensilio) utensilio).getNombre();
             estante.addEstante(nombreUtensilio, utensilio);
        }

        LocalDate fechaActual = LocalDate.now();
        DayOfWeek diaSemana = fechaActual.getDayOfWeek();
        int numChefs = 0;
        if (diaSemana.compareTo(DayOfWeek.MONDAY) >= 0 && diaSemana.compareTo(DayOfWeek.THURSDAY) <= 0) {
            numChefs = 3;
        } else {
            numChefs = 5;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(numChefs);

        for (int i = 0; i < numChefs; i++) {
            Chef chef = new Chef("Chef " + (i+1), (i % 3) + 1, despensas.get(i));
            Receta receta1 = recetas.get(2);
            Receta receta2 = recetas.get(1);
            chef.agregarRecetaPendiente(receta1);
            chef.agregarRecetaPendiente(receta2);
            executorService.submit(chef);
        }
        executorService.shutdown();
    }
}
