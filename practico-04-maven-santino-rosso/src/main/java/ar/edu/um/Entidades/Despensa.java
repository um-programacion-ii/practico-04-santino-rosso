package Entidades;

import Excepciones.StockInsuficiente;
import Interfaces.IDespensable;
import Excepciones.VidaUtilInsuficiente;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Despensa {
    private Map<String,IDespensable> despensaMap;

    public Despensa() {
        despensaMap = new HashMap<>();
    }

    public int getCantidad(Ingrediente ingredienteNecesario) {
        int cantidad = 0;
        if (despensaMap.containsKey(ingredienteNecesario.getNombre())) {
            IDespensable despensable = despensaMap.get(ingredienteNecesario.getNombre());
            if (despensable instanceof Ingrediente) {
                cantidad = ((Ingrediente) despensable).getCantidad();
            }
        }
        return cantidad;
    }

    public void addDespensa(String nombre, IDespensable despensable) {
        despensaMap.put(nombre, despensable);
    }

    public String getDespensa(String nombre, int cantidadASacar) throws VidaUtilInsuficiente, StockInsuficiente {
        IDespensable ingrediente = despensaMap.get(nombre);
        if (ingrediente != null){
            return ingrediente.sacar(cantidadASacar);
        }
        return "No se encontro el objeto";
    }

    @Override
    public String toString() {
        String despensa = "";
        despensa += despensaMap.values().stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
        return despensa;
    }


}
