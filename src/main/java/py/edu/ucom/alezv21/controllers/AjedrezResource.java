package py.edu.ucom.alezv21.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import py.edu.ucom.alezv21.entities.Usuarios;
import py.edu.ucom.alezv21.entities.ajedrez.AjedrezParam;
import py.edu.ucom.alezv21.entities.ajedrez.Peon;

@Path("/ajedrez")
public class AjedrezResource {
    @POST
    @Path("/peon")
    public Boolean validarMovimiento(AjedrezParam ajedrezParam){
        Peon p = new Peon();  
        return p.movimientoValido(ajedrezParam.getInicioX(), ajedrezParam.getInicioY(), ajedrezParam.getFinalX(), ajedrezParam.getFinalY());

    }
}

    

