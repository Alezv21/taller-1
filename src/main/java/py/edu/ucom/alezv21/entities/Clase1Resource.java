package py.edu.ucom.alezv21.entities;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/clase1")
public class Clase1Resource {

    @GET
    public double random() {
        return Math.random();
    }

    @GET
    @Path("obtener-alumno")
    @Produces(MediaType.APPLICATION_JSON)
    public Alumno obtenerAlumno() {
        Alumno data = new Alumno("hola", "ale");
        return data;
    }
}