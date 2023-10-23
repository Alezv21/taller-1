package py.edu.ucom.alezv21.controllers;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import py.edu.ucom.alezv21.config.Globales;
import py.edu.ucom.alezv21.entities.MetodoPago;
import py.edu.ucom.alezv21.entities.params.ObtenerDatosError;
import py.edu.ucom.alezv21.entities.params.RespuestaLista;
import py.edu.ucom.alezv21.services.MetodoPagoService;

@Path("/metodo-pago")
public class MetoPagoResource {
    
    @Inject
    public MetodoPagoService service;

    @GET
    public RespuestaLista<MetodoPago> listar(){
        RespuestaLista<MetodoPago> respuesta = new RespuestaLista<>(this.service.listar());
        respuesta.setMensaje(Globales.CRUD.LISTADO_OK);
        return respuesta;
    }
    @DELETE
    @Path("{id}")
    public void eliminar(Integer id) {
        this.service.eliminar(id);
    }
    @POST
    public MetodoPago agregar (MetodoPago param){
        return this.service.agregar(param);
    }
    @PUT
    public MetodoPago modificar (MetodoPago param){
        return this.service.modificar(param);
    }
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id")Integer param) throws Exception{
        MetodoPago entity = this.service.obtener(param);
        if(entity==null){
            ObtenerDatosError data = new ObtenerDatosError();
            data.setCodigo("0001");
            data.setMensaje(Globales.CRUD.RECUPERADO_ERR);
            return Response.status(Response.Status.NOT_FOUND).entity(data).build();
        }
        return Response.ok(entity).build();     
    }
}
