package Servicios;

import Entidades.Despensa;
import Entidades.Estante;
import Entidades.Receta;
import Entidades.Utensilio;
import Interfaces.IDespensable;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static Servicios.CocinaService.getInstanciaCocinaService;

public class DespensaService {

    public static boolean verificar(Despensa despensa, Receta receta, Estante estante) {

        AtomicBoolean cocinar = new AtomicBoolean(true);
        AtomicBoolean mensajeImpreso = new AtomicBoolean(false);

        receta.getIngredientes().stream().forEach(ingrediente -> {
            int cantidadNecesaria = ingrediente.getCantidad();
            int cantidadActual = despensa.getCantidad(ingrediente);
            if (cantidadActual < cantidadNecesaria) {
                cocinar.set(false);
                if (!mensajeImpreso.get()) {
                    System.out.println("Ingredientes que faltan: ");
                    mensajeImpreso.set(true);
                }
                System.out.println("No hay suficiente cantidad de " + ingrediente.getNombre() + " en la despensa.");
                System.out.println("Faltan " + (cantidadNecesaria - cantidadActual) + " unidades.");
            }
        });

        mensajeImpreso.set(false);
        receta.getUtensilios().stream().forEach(utensilio -> {
            int vidaNecesaria = utensilio.getVidaUtil();
            int vidaActual = estante.getVidaUtil(utensilio);
            if (vidaActual < vidaNecesaria) {
                cocinar.set(false);
                if (!mensajeImpreso.get()) {
                    System.out.println("Utensilios que faltan: ");
                    mensajeImpreso.set(true);
                }
                System.out.println("El utensilio " + utensilio.getNombre() + " no tiene suficiente vida util");
                System.out.println("Falta " + (vidaNecesaria - vidaActual) + " de vida util");
            }
        });
        return cocinar.get();
    }

    public static void renovarUtensilios(Estante estante) {

        Map<String, IDespensable> estanteMap = estante.getEstanteMap();
        for (Map.Entry<String, IDespensable> entry : estanteMap.entrySet()) {
            IDespensable despensable = entry.getValue();
            Utensilio utensilio = (Utensilio) despensable;
            if (utensilio.getVidaUtil() == 0) {
                utensilio.setVidaUtil(10);
                System.out.println("Utensilio " + utensilio.getNombre() + " renovado.");
            }
        }
    }
}
