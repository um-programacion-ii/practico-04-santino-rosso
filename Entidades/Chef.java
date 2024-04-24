package Entidades;


import Excepciones.RecursoBloqueado;
import Excepciones.StockInsuficiente;
import Excepciones.VidaUtilInsuficiente;
import Servicios.CocinaService;
import Servicios.DespensaService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static Servicios.CocinaService.getInstanciaCocinaService;

public class Chef implements Callable<Void>{
    private String nombre;
    private int estrellasMichelin;
    private Despensa despensa;
    private List<Receta> recetasPendientes;
    private Estante estante;
    private CocinaService cocina;

    public Chef(String nombre, int estrellasMichelin, Despensa despensa){
        this.estrellasMichelin = estrellasMichelin;
        this.nombre = nombre;
        this.despensa = despensa;
        this.estante =  Estante.getInstanciaEstante();
        this.cocina = getInstanciaCocinaService();
        this.recetasPendientes = new ArrayList<>();
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public int getEstrellasMichelin(){
        return estrellasMichelin;
    }

    public void setEstrellasMichelin(int estrellasMichelin){
        this.estrellasMichelin = estrellasMichelin;
    }

    public Despensa getDespensa() {
        return despensa;
    }

    public void setDespensa(Despensa despensa) {
        this.despensa = despensa;
    }

    public void agregarRecetaPendiente(Receta receta) {
        this.recetasPendientes.add(receta);
    }

    public Receta obtenerRecetaPendiente(){
        Receta recetaPendiente = this.recetasPendientes.get(0);
        return recetaPendiente;
    }

    public void eliminarRecetaPendiente(Receta receta){
        this.recetasPendientes.remove(receta);
    }

    public void cocinar() throws VidaUtilInsuficiente, StockInsuficiente, InterruptedException {
        System.out.println("Hola soy: " + this.nombre);
        while (!recetasPendientes.isEmpty()) {
            Receta receta = obtenerRecetaPendiente();
            try {
                eliminarRecetaPendiente(receta);
                this.cocina.prepararReceta(receta, this.despensa, this.estante);
            } catch (RecursoBloqueado e) {
                System.out.println(e.getMessage());
                if (recetasPendientes.isEmpty()){
                    Thread.sleep(3000);
                }
                agregarRecetaPendiente(receta);
            }
        }
    }

    public void renovarEstante(){
        DespensaService.renovarUtensilios(this.estante);
    }

    @Override
    public String toString(){
        return "Nombre: " + this.nombre + ", Cantidad de estrellas michelin: " + this.estrellasMichelin;
    }

    @Override
    public Void call() throws Exception{
        cocinar();
        return null;
    }
}


