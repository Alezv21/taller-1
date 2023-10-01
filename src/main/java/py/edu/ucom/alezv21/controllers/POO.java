package py.edu.ucom.alezv21.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import py.edu.ucom.alezv21.entities.Cuadrado;
import py.edu.ucom.alezv21.entities.FiguraGeometrica;
import py.edu.ucom.alezv21.entities.Rectangulo;

@Path("poo")

public class POO {
    @GET
    @Path("cuadrado-area")
    public double obtenerPerimetroCuadrado(){
        Cuadrado c = new Cuadrado(4.0);

        return c.calcularPerimetro();
    }

    @GET
    @Path("rectangulo-area")
    public double obtenerPerimetroRectangulo(){
        Rectangulo r = new Rectangulo(4.0, 4.0);
        FiguraGeometrica f = r;

        return f.calcularPerimetro();
    }
}
