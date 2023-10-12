package py.edu.ucom.alezv21.entities;

import java.util.List;

public class Factura {
    private String documentoCliente;
    private String documentoUsuario;
    private List<Productos> productos;
    private double total;

    
    public String getDocumentoCliente() {
        return documentoCliente;
    }
    public void setDocumentoCliente(String documentoCliente) {
        this.documentoCliente = documentoCliente;
    }
    public String getDocumentoUsuario() {
        return documentoUsuario;
    }
    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }
    public List<Productos> getProductos() {
        return productos;
    }
    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    
}
