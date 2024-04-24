package Entidades;

import Excepciones.StockInsuficiente;
import Excepciones.VidaUtilInsuficiente;
import Interfaces.IDespensable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.concurrent.locks.Lock;

public class Estante {
    private Map<String, IDespensable> estanteMap;
    private Map<String, Lock> lockMap;
    private static volatile Estante instancia;

    private Estante() {
        estanteMap = new HashMap<>();
        lockMap = new HashMap<>();
    }

    public static synchronized Estante getInstanciaEstante() {
        if (instancia == null) {
            instancia = new Estante();
        }
        return instancia;
    }

    public Map<String,IDespensable> getEstanteMap() {
        return estanteMap;
    }


    public int getVidaUtil(Utensilio utensilioNecesario){
        int vidaUtil = 0;
        if (estanteMap.containsKey(utensilioNecesario.getNombre())){
            IDespensable despensable = estanteMap.get(utensilioNecesario.getNombre());
            if(despensable instanceof Utensilio){
                vidaUtil = ((Utensilio) despensable).getVidaUtil();
            }
        }
        return vidaUtil;
    }

    public void addEstante(String nombre, IDespensable despensable) {
        estanteMap.put(nombre, despensable);
        lockMap.put(nombre, new ReentrantLock());
    }

    public String getEstante(String nombre, int cantidadASacar) throws VidaUtilInsuficiente, StockInsuficiente {
        IDespensable utensilio = estanteMap.get(nombre);
        if (utensilio != null){
            return utensilio.sacar(cantidadASacar);
        }
        return "No se encontro el objeto";
    }

    public Lock obtenerLock(String nombreUtensilio) {
        return lockMap.get(nombreUtensilio);
    }

    @Override
    public String toString() {
        String despensa = "";
        despensa += estanteMap.values().stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
        return despensa;
    }
}
