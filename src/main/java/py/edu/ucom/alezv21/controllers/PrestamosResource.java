package py.edu.ucom.alezv21.controllers;

import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import py.edu.ucom.alezv21.entities.Libros;
import py.edu.ucom.alezv21.servicios.PrestamoService;

@Path("prestamos")
public class PrestamosResource {

    @Inject 
    private PrestamoService service;

    @POST
    @Path("{documento}/{isbn}")
    public Map<String, String> prestamo(@PathParam("documento")String documento, @PathParam("isbn") String isbn){
        
        return this.service.prestar(documento, isbn);
    }
    @POST
    @Path("devolver/{documento}/{isbn}")
    public Map<String, String> devolver(@PathParam("documento")String documento, @PathParam("isbn") String isbn){
        
        return this.service.devolver(documento, isbn);
    }

    @GET
    @Path("{documento}")
    public List<Libros> obtenerLibrosPorUsuario(@PathParam("documento")String documento){
        return this.service.obtenerLibrosPorUsuario(documento);
    }   
    
}
