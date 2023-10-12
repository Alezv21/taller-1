package py.edu.ucom.alezv21.entities;

import java.util.List;

public class Clientes {
    public String documento;
    public String nombre;
    public List<Productos> factura;

    
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Productos> getFactura() {
        return factura;
    }
    public void setFactura(List<Productos> factura) {
        this.factura = factura;
    }

    



    
}
