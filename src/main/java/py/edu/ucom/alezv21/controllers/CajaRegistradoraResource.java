package py.edu.ucom.alezv21.controllers;

import java.util.Map;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import py.edu.ucom.alezv21.entities.Pago;
import py.edu.ucom.alezv21.servicios.CajaRegistradoraService;

@Path("caja")
public class CajaRegistradoraResource {

    @Inject 
    private CajaRegistradoraService service;

    @POST
    @Path("{documentoCliente}/{documentoUsuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Map<String, String> registrarPago(@PathParam("documentoCliente") String documentoCliente, 
                                             @PathParam("documentoUsuario") String documentoUsuario, 
                                             Pago pago) {
        return this.service.procesarPago(documentoCliente, documentoUsuario, pago);
    }
}