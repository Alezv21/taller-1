package py.edu.ucom.alezv21.entities;

import java.util.HashMap;

public class Monedas {
    String codigo;
    HashMap<String,Double> cambios;
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public HashMap<String, Double> getCambios() {
        return cambios;
    }
    public void setCambios(HashMap<String, Double> cambios) {
        this.cambios = cambios;
    }
}
