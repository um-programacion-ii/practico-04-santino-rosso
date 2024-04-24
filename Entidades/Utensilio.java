package Entidades;

import Excepciones.VidaUtilInsuficiente;
import Interfaces.IDespensable;
import Interfaces.IReutilizable;

public class Utensilio implements IDespensable, IReutilizable {

    private int vidaUtil;
    private String nombre;

    public Utensilio(String nombre, int vidaUtil) {
        this.vidaUtil = vidaUtil;
        this.nombre = nombre;
    }

    public int getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(int vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public String getNombre(){return nombre;}

    public void setNombre(String nombre){this.nombre = nombre;}

    @Override
    public String sacar(int vidaASacar) throws VidaUtilInsuficiente {
        if (vidaUtil >= vidaASacar){
            vidaUtil -= vidaASacar;
            return "Se saco " + vidaASacar + " de vida util";
        }else {
            throw new VidaUtilInsuficiente("No hay suficiente vida util, falta " + (vidaASacar - vidaUtil) + " de vida util");
        }
    }

    @Override
    public String toString() {
        return "Nombre = " + nombre + ", Vida util = " + vidaUtil;
    }
}
