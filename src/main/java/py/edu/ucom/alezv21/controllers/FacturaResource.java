package py.edu.ucom.alezv21.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import py.edu.ucom.alezv21.entities.Factura;
import py.edu.ucom.alezv21.entities.Productos;
import py.edu.ucom.alezv21.utils.DataSourceJSON;

@Path("factura")
public class FacturaResource {
    @Inject
    private DataSourceJSON ds;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Factura obtenerFactura() {
        Factura factura = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            factura = mapper.readValue(new File("factura.json"), Factura.class);

            List<Productos> productos = factura.getProductos();
            for (Productos producto : productos) {
                Productos productoBD = ds.buscarProducto(producto.getCodigo());
                if (productoBD != null) {
                    producto.setPrecio(productoBD.getPrecio()); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return factura;
    }
}