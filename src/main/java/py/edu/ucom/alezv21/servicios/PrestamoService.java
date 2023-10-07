package py.edu.ucom.alezv21.servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import py.edu.ucom.alezv21.entities.Libros;
import py.edu.ucom.alezv21.entities.Usuarios;
import py.edu.ucom.alezv21.utils.DataSourceJSON;

@ApplicationScoped
public class PrestamoService {
    @Inject
    private DataSourceJSON ds;

    public Map<String,String> prestar(String documento, String isbn){
        Map<String,String> respuesta = new HashMap<>();
        Libros libro = this.ds.buscarLibro(isbn);
        Usuarios usuario = this.ds.buscarUsuario(documento);
        if(libro.getPrestado()){
            respuesta.put("mensaje", "El libro con ISBN :" +isbn+" ya se encuentra prestado.");
            return respuesta;
        }
        //1 cambiar de estado el libro
        libro.setPrestado(true);
        this.ds.actualizarLibro(libro);

        //2 agregar libro al listado de LP del usuario
        usuario.getLibrosPrestados().add(libro);
        this.ds.actualizarUsuario(usuario);
        respuesta.put("mensaje", "El libro con ISBN :" +isbn+" prestado con éxito.");

        return respuesta;

    }

    public Map<String,String> devolver(String documento, String isbn){
        Map<String,String> respuesta = new HashMap<>();
        Libros libro = this.ds.buscarLibro(isbn);
        Usuarios usuario = this.ds.buscarUsuario(documento);
       
        //2 agregar libro al listado de LP del usuario
        List<Libros> lista = usuario.getLibrosPrestados();

        for (Libros item : lista) {
           if(item.getISBN().equals(isbn)){   
                int indice = lista.indexOf(item);
                System.out.println("Indice" + indice);
                lista.remove(indice);
           }
        }
        libro.setPrestado(false);
        this.ds.actualizarLibro(libro);

        //eliminar libro de  LP
        this.ds.actualizarUsuario(usuario);
        respuesta.put("mensaje", "El libro con ISBN :" +isbn+" prestado con éxito.");

        return respuesta;

    }

    public List<Libros> obtenerLibrosPorUsuario(String documento){
        Usuarios usuario = this.ds.buscarUsuario(documento);

        return usuario.getLibrosPrestados();

    }

}
