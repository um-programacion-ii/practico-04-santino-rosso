package Servicios;

import Entidades.*;
import Excepciones.RecursoBloqueado;
import Excepciones.StockInsuficiente;
import Excepciones.VidaUtilInsuficiente;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;


public class CocinaService {
    private static volatile CocinaService instancia;

    private CocinaService() {}

    public static synchronized CocinaService getInstanciaCocinaService() {
        if (instancia == null) {
            instancia = new CocinaService();
        }
        return instancia;
    }

    public void prepararReceta(Receta receta, Despensa despensa, Estante estante) throws VidaUtilInsuficiente, StockInsuficiente, RecursoBloqueado {
        if(DespensaService.verificar(despensa, receta, estante)){

            List<Utensilio> utensiliosOriginales = new ArrayList<>(receta.getUtensilios());
            for (Utensilio utensilio : receta.getUtensilios()) {
                int vidaUtilNecesaria = utensilio.getVidaUtil();
                String nombreUtensilio = utensilio.getNombre();
                Lock lock = estante.obtenerLock(nombreUtensilio);
                try {
                    if (!lock.tryLock()) {
                        receta.setUtensilios(utensiliosOriginales);
                        throw new RecursoBloqueado("El utensilio " + nombreUtensilio + " está ocupado.");
                    }
                    try {
                        estante.getEstante(nombreUtensilio, vidaUtilNecesaria);
                    } finally {
                        lock.unlock();
                    }
                } catch (VidaUtilInsuficiente e) {
                    throw new RuntimeException(e);
                } catch (StockInsuficiente e) {
                    throw new RuntimeException(e);
                }
            }

            for (Ingrediente ingrediente : receta.getIngredientes()) {
                int cantidadNecesaria = ingrediente.getCantidad();
                String nombreIngrediente = ingrediente.getNombre();
                try {
                    despensa.getDespensa(nombreIngrediente, cantidadNecesaria);
                } catch (VidaUtilInsuficiente e) {
                    throw new RuntimeException(e);
                } catch (StockInsuficiente e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println(receta.getPreparacion());
            System.out.println("Cantidad de objetos restantes en la despensa:");
            System.out.println(despensa);
            System.out.println("Cantidad de objetos restantes en el Estante:");
            System.out.println(estante);
        }
    }
}