package py.edu.ucom.alezv21.controllers;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import py.edu.ucom.alezv21.services.VentaService;
import py.edu.ucom.alezv21.entities.Venta;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;


@Path("/venta")
public class VentaResource {
    
    @Inject
    public VentaService service;

    @GET
    public List<Venta> listar(){
        return this.service.listar();
    }
    @DELETE
    @Path("{id}")
    public void eliminar(Integer id) {
        this.service.eliminar(id);
    }
    @PUT
    public Venta modificar (Venta param){
        return this.service.modificar(param);
    }

    @POST
    public Response agregar(Venta param) {
    try {
        Venta venta = this.service.agregar(param);
        return Response.ok(venta).build(); // Venta procesada correctamente
    } catch (Exception e) {
        String mensajeError = "Error al procesar la venta: " + e.getMessage();
        return Response.status(Response.Status.BAD_REQUEST).entity(mensajeError).build();
    }
}
    @GET
    @Path("{id}")
    public Venta obtener(@PathParam("id")Integer param){
        return this.service.obtener(param);     
    }
    /*@GET
    @Path("resumen/{id}")
    public ResumenVentaDTO obtenerResumen(@PathParam("id")Integer param){
        return null;     
    }*/
}
