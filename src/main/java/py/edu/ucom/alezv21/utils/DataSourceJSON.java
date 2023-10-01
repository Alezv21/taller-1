package py.edu.ucom.alezv21.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import py.edu.ucom.alezv21.entities.Libros;

@ApplicationScoped

public class DataSourceJSON {

    public List<Libros> obtenerLibros(){
        ObjectMapper mapper = new ObjectMapper();
        List <Libros> libros = new ArrayList<>();
        try {
            libros = mapper.readValue(new File("src/main/java/py/edu/ucom/alezv21/utils/libros.json"), new TypeReference<List<Libros>>() {});
        }catch(Exception e){
            e.printStackTrace();
        }

    return libros;
    }
    
}
