package py.edu.ucom.alezv21.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import py.edu.ucom.alezv21.entities.Libros;
import py.edu.ucom.alezv21.entities.Usuarios;

@ApplicationScoped
public class DataSourceJSON {
    public String SRC_USUARIOS = "rc/main/java/py/edu/ucom/alezv21/utils/libros.json";
    public String SRC = "rc/main/java/py/edu/ucom/alezv21/utils/libros.json";

   public void guardarLibro(Libros libro) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Libros> lista = obtenerLibros();
            lista.add(libro);
            mapper.writeValue(new File(this.SRC), lista);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public List<Libros> obtenerLibros() {
        ObjectMapper mapper = new ObjectMapper();
        List<Libros> libros = new ArrayList<>();
        try {
            libros = mapper.readValue(
                    new File(this.SRC),
                    new TypeReference<List<Libros>>() {
                    });

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return libros;
    }

    public void guardarUsuarios(Usuarios usuario) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Usuarios> lista = obtenerUsuarios();
            usuario.setLibrosPrestados( new ArrayList<>());
            lista.add(usuario);
            mapper.writeValue(new File(this.SRC_USUARIOS), lista);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public List<Usuarios> obtenerUsuarios() {

        ObjectMapper mapper = new ObjectMapper();
        List<Usuarios> data = new ArrayList<>();
        try {
            data = mapper.readValue(
                    new File(this.SRC_USUARIOS),
                    new TypeReference<List<Usuarios>>() {
                    });

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return data;
    }

    public Libros buscarLibro(String isbn) {
        Libros libro = null;
        List<Libros> data = obtenerLibros();

        for (Libros item : data) {
            if (item.getISBN().equals(isbn)) {
                libro = item;
            }
        }
        return libro;
    }

    public Usuarios buscarUsuario(String documento) {
        Usuarios usuario = null;
        List<Usuarios> data = obtenerUsuarios();

        for (Usuarios item : data) {
            if (item.getDocumento().equals(documento)) {
                usuario = item;
            }
        }
        return usuario;
    }

    /// actualizar libro y actualizar usuario
    public void actualizarLibro(Libros param) {
        List<Libros> data = obtenerLibros();

        for (Libros item : data) {
            if (item.getISBN().equals(param.getISBN())) {

                if(param.getPrestado()!=null){
                   item.setPrestado(param.getPrestado()); 
                }
                if(param.getTitulo()!=null && !param.getTitulo().isEmpty()){
                    item.setTitulo(param.getTitulo());
                }
                if(param.getAutor() != null && !param.getAutor().isEmpty()){
                 item.setAutor(param.getAutor());
                }
            }
        }
        
        try {
            ObjectMapper mapper = new ObjectMapper();

            mapper.writeValue(new File(this.SRC), data);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void actualizarUsuario(Usuarios param){
        List<Usuarios> data = obtenerUsuarios();
         for (Usuarios item : data) {

            if(item.getDocumento().equals(param.getDocumento())){
                item.setNombre(param.getNombre());
                item.setLibrosPrestados(param.getLibrosPrestados());
                break;
            }
        }

          try {
            ObjectMapper mapper = new ObjectMapper();

            mapper.writeValue(new File(this.SRC_USUARIOS), data);

        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}
