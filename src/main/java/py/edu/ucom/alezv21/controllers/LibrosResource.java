package py.edu.ucom.alezv21.controllers;

import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import py.edu.ucom.alezv21.entities.Libros;
import py.edu.ucom.alezv21.utils.DataSourceJSON;

@Path("/libros")
public class LibrosResource {
    @Inject
    public DataSourceJSON ds;
    @GET
    public List<Libros> obtenerLibros(){
        List<Libros> listaDeLibros = new ArrayList<>();

        try {
            listaDeLibros = this.ds.obtenerLibros();
        } catch (Exception e) {
            // TODO: handle exception
        }


        return listaDeLibros;

    }

}
