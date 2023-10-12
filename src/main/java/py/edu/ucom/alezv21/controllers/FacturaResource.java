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
import py.edu.ucom.alezv21.utils.DataSourceJSON;

@Path("factura")
public class FacturaResource {

    @Inject
    private DataSourceJSON ds;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Factura> obtenerFacturas() {
        List<Factura> facturas = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            facturas = mapper.readValue(new File("src/main/java/py/edu/ucom/alezv21/utils/factura.json"), new TypeReference<List<Factura>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción, por ejemplo, enviar una respuesta de error al cliente
        }
        return facturas;
    }
}