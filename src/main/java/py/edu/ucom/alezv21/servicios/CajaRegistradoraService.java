package py.edu.ucom.alezv21.servicios;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import py.edu.ucom.alezv21.entities.Clientes;
import py.edu.ucom.alezv21.entities.Factura;
import py.edu.ucom.alezv21.entities.Pago;
import py.edu.ucom.alezv21.entities.Productos;
import py.edu.ucom.alezv21.utils.DataSourceJSON;

@ApplicationScoped
public class CajaRegistradoraService {
    @Inject
    private DataSourceJSON ds;

    public Map<String, String> procesarPago(String documentoCliente, String documentoUsuario, Pago pago) {
        Map<String, String> respuesta = new HashMap<>();
        Clientes cliente = this.ds.buscarCliente(documentoCliente);

        if (cliente == null) {
            respuesta.put("error", "Cliente no encontrado");
            return respuesta;
        }


        double total = 0;
        List<Productos> productos = pago.getProductos();
        for (Productos producto : productos) {

            Productos productoBD = this.ds.buscarProducto(producto.getCodigo());

            if (productoBD == null) {
                respuesta.put("error", "Producto con código " + producto.getCodigo() + " no encontrado");
                return respuesta;
            }

            producto.setPrecio(productoBD.getPrecio());
            double subtotal = producto.getPrecio().doubleValue() * producto.getCantidad();
            total += subtotal;
        }

        Factura factura = new Factura();
        factura.setDocumentoCliente(documentoCliente);
        factura.setDocumentoUsuario(documentoUsuario);
        factura.setProductos(productos);
        factura.setTotal(total);

        List<Factura> facturas = ds.obtenerFacturas();

        facturas.add(factura);

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("src/main/java/py/edu/ucom/alezv21/utils/factura.json"), facturas);
            respuesta.put("total", String.valueOf(total));
            respuesta.put("mensaje", "Pago procesado con éxito y factura guardada en facturas.json");
        } catch (Exception e) {
            respuesta.put("error", "Error al guardar la factura: " + e.getMessage());
        }

        return respuesta;
    }
}