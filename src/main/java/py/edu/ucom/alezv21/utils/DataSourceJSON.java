package py.edu.ucom.alezv21.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import py.edu.ucom.alezv21.entities.Clientes;
import py.edu.ucom.alezv21.entities.Factura;
import py.edu.ucom.alezv21.entities.Libros;
import py.edu.ucom.alezv21.entities.Monedas;
import py.edu.ucom.alezv21.entities.Productos;
import py.edu.ucom.alezv21.entities.Usuario;
import py.edu.ucom.alezv21.entities.Usuarios;

@ApplicationScoped
public class DataSourceJSON {
    public String SRC_MONEDAS = "src/main/java/py/edu/ucom/alezv21/utils/monedas.json";
    public String SRC_USUARIOS = "src/main/java/py/edu/ucom/alezv21/utils/usuarios.json";
    public String SRC = "src/main/java/py/edu/ucom/alezv21/utils/libros.json";
    public String SRC_CLIENTES = "src/main/java/py/edu/ucom/alezv21/utils/clientes.json";
    public String SRC_PRODUCTOS = "src/main/java/py/edu/ucom/alezv21/utils/productos.json";
    public String SRC_FACTURAS = "src/main/java/py/edu/ucom/alezv21/utils/factura.json";
    public String SRC_USUARIO = "src/main/java/py/edu/ucom/alezv21/utils/usuario.json";

    public List<Factura> obtenerFacturas() {
        ObjectMapper mapper = new ObjectMapper();
        List<Factura> facturas = new ArrayList<>();
        try {
            facturas = mapper.readValue(
                    new File(this.SRC_FACTURAS),
                    new TypeReference<List<Factura>>() {
                    });

        } catch (Exception e) {
            // Manejar la excepción, por ejemplo, imprimir el mensaje de error
            e.printStackTrace();
        }

        return facturas;
    }


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
     public List<Monedas> obtenerMonedas(){
        ObjectMapper mapper = new ObjectMapper();
        List<Monedas> lista = new ArrayList();

        try {
            lista = mapper.readValue(
                    new File(this.SRC_MONEDAS),
                    new TypeReference<List<Monedas>>() {
                    });

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return lista;
    }

    public Monedas buscarMonedas(String codigo) {
        Monedas data = null;
        List<Monedas> lista = obtenerMonedas();

        for (Monedas item : lista) {
            if (item.getCodigo().equals(codigo)) {
                data = item;
                break;
            }
        }
        return data;
    }

    public void guardarProducto(Productos producto) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Productos> lista = obtenerProductos();
            lista.add(producto);
            mapper.writeValue(new File(this.SRC_PRODUCTOS), lista);
        } catch (Exception e) {
            // Manejar la excepción, por ejemplo, imprimir el mensaje de error
            e.printStackTrace();
        }
    }

    public List<Productos> obtenerProductos() {
        ObjectMapper mapper = new ObjectMapper();
        List<Productos> productos = new ArrayList<>();
        try {
            productos = mapper.readValue(
                    new File(this.SRC_PRODUCTOS),
                    new TypeReference<List<Productos>>() {
                    });

        } catch (Exception e) {
            // Manejar la excepción, por ejemplo, imprimir el mensaje de error
            e.printStackTrace();
        }

        return productos;
    }

    public void guardarCliente(Clientes cliente) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Clientes> lista = obtenerClientes();
            lista.add(cliente);
            mapper.writeValue(new File(this.SRC_CLIENTES), lista);
        } catch (Exception e) {
            // Manejar la excepción, por ejemplo, imprimir el mensaje de error
            e.printStackTrace();
        }
    }

    public List<Clientes> obtenerClientes() {
        ObjectMapper mapper = new ObjectMapper();
        List<Clientes> clientes = new ArrayList<>();
        try {
            clientes = mapper.readValue(
                    new File(this.SRC_CLIENTES),
                    new TypeReference<List<Clientes>>() {
                    });

        } catch (Exception e) {
            // Manejar la excepción, por ejemplo, imprimir el mensaje de error
            e.printStackTrace();
        }

        return clientes;
    }

    public Productos buscarProducto(String codigo) {
        Productos producto = null;
        List<Productos> data = obtenerProductos();

        for (Productos item : data) {
            if (item.getCodigo().equals(codigo)) {
                producto = item;
                break;
            }
        }
        return producto;
    }

    public Clientes buscarCliente(String documento) {
        Clientes cliente = null;
        List<Clientes> data = obtenerClientes();

        for (Clientes item : data) {
            if (item.getDocumento().equals(documento)) {
                cliente = item;
                break;
            }
        }
        return cliente;
    }
    
    public void guardarFacturas(List<Factura> facturas) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(this.SRC_FACTURAS), facturas);
        } catch (Exception e) {
            // Manejar la excepción, por ejemplo, imprimir el mensaje de error
            e.printStackTrace();
        }
    }

    public void guardarFactura(Factura factura) {
        List<Factura> facturas = obtenerFacturas();
        facturas.add(factura);
        guardarFacturas(facturas);
    }

    public List<Usuario> obtenerUsuariosC() {
        ObjectMapper mapper = new ObjectMapper();
        List<Usuario> usuarios = new ArrayList<>();
        try {
            usuarios = mapper.readValue(
                    new File(this.SRC_USUARIO),
                    new TypeReference<List<Usuario>>() {
                    });
        } catch (Exception e) {
            // Manejar la excepción, por ejemplo, imprimir el mensaje de error
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario buscarUsuarioC(String documento) {
        Usuario usuario = null;
        List<Usuario> data = obtenerUsuariosC();

        for (Usuario item : data) {
            if (item.getDocumento().equals(documento)) {
                usuario = item;
                break;
            }
        }
        return usuario;
    }

    
}
