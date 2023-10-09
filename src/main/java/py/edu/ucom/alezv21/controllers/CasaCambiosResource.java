package py.edu.ucom.alezv21.controllers;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import py.edu.ucom.alezv21.entities.Monedas;
import py.edu.ucom.alezv21.servicios.CambiosService;
import py.edu.ucom.alezv21.utils.DataSourceJSON;

@Path("cambios")
public class CasaCambiosResource {
    
    @Inject
    public DataSourceJSON ds;

    @Inject
    public CambiosService service;
    @GET
    public List<Monedas> obtener(){
        return ds.obtenerMonedas();
    }

    @GET
    @Path("{codigoOrigen}/{monto}/{codigoDestino}")
    public Double cambio(
        @PathParam("monto")Integer monto,
        @PathParam("codigoDestino")String codigoDestino,
        @PathParam("codigoOrigen") String codigoOrigen){

            return this.service.realizarCambio(codigoOrigen, codigoDestino, monto);

    }
}