package Interfaces;

import Excepciones.StockInsuficiente;
import Excepciones.VidaUtilInsuficiente;

public interface IDespensable {
    String sacar(int cantidad) throws VidaUtilInsuficiente, StockInsuficiente;
}
