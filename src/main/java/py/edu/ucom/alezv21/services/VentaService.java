package py.edu.ucom.alezv21.services;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import py.edu.ucom.alezv21.config.IDAO;
import py.edu.ucom.alezv21.entities.Cliente;
import py.edu.ucom.alezv21.entities.Venta;
import py.edu.ucom.alezv21.entities.VentaDetalle;
import py.edu.ucom.alezv21.entities.dto.ResumenVentaDTO;
import py.edu.ucom.alezv21.entities.dto.VentaDetalleDTO;
import py.edu.ucom.alezv21.repositories.VentaDetalleRepository;
import py.edu.ucom.alezv21.repositories.VentaRepository;
import py.edu.ucom.alezv21.repositories.ProductoRepository;
import java.util.Optional;

@ApplicationScoped
public class VentaService implements IDAO<Venta, Integer> {

    @Inject
    private VentaRepository repository;

    @Inject
    private VentaDetalleRepository repositoryDetalle;

    @Inject
    private ProductoRepository productoRepository;

    @Override
    public Venta obtener(Integer param) {
        return this.repository.findById(param).orElse(null);
    }

    @Override
    @Transactional
    public Venta agregar(Venta param) {
        try {
            Venta aux = new Venta();
            aux.setClienteId(param.getClienteId());
            aux.setFecha(param.getFecha());
            aux.setMetodoPagoId(param.getMetodoPagoId());
            aux.setTotal(param.getTotal());

            Venta saved = this.repository.save(aux);
            System.out.println("Venta creada correctamente. ID de venta: " + saved.getVentaId());

            List<VentaDetalle> vdList = param.getVentaDetalleList();
            for (VentaDetalle item : vdList) {
                VentaDetalle vdt = new VentaDetalle();
                vdt.setVentaId(saved);
                vdt.setProductoId(item.getProductoId());
                vdt.setCantidad(item.getCantidad());
                vdt.setSubtotal(item.getSubtotal());

                this.repositoryDetalle.save(vdt);

                Integer productoId = item.getProductoId().getProductoId();
                int cantidadVendida = item.getCantidad();
                int stockActual = productoRepository.findById(productoId).orElseThrow().getStock();

                if (stockActual < cantidadVendida) {
                    throw new RuntimeException("No hay suficiente stock disponible para el Producto ID: " + productoId);
                }                
                productoRepository.reduceStock(productoId, cantidadVendida);
                System.out.println(
                        "Stock reducido para Producto ID: " + productoId + ", Cantidad vendida: " + cantidadVendida);
            }

            System.out.println("Venta procesada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al procesar la venta: " + e.getMessage());
            throw new RuntimeException("Error al procesar la venta: " + e.getMessage());

        }
        return param;
    }

    @Override
    @Transactional
    public Venta modificar(Venta param) {
        try {
            Integer ventaId = param.getVentaId().intValue();

            Optional<Venta> ventaExistente = this.repository.findById(ventaId);

            if (ventaExistente.isPresent()) {
                Venta ventaActualizada = ventaExistente.get();
                ventaActualizada.setClienteId(param.getClienteId());
                ventaActualizada.setFecha(param.getFecha());
                ventaActualizada.setMetodoPagoId(param.getMetodoPagoId());
                ventaActualizada.setTotal(param.getTotal());

                Venta ventaGuardada = this.repository.save(ventaActualizada);
                System.out.println(ventaGuardada.toString());

                this.repositoryDetalle.deleteByVentaId(ventaGuardada.getVentaId());

                List<VentaDetalle> vdList = param.getVentaDetalleList();
                for (VentaDetalle item : vdList) {
                    VentaDetalle vdt = new VentaDetalle();
                    vdt.setVentaId(ventaGuardada);
                    vdt.setProductoId(item.getProductoId());
                    vdt.setCantidad(item.getCantidad());
                    vdt.setSubtotal(item.getSubtotal());

                    this.repositoryDetalle.save(vdt);
                }

                return ventaGuardada;
            } else {
                throw new RuntimeException("Venta no encontrada con ID: " + param.getVentaId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al modificar la venta: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Integer param) {

        this.repository.deleteById(param);
    }

    @Override
    public List<Venta> listar() {
        return this.repository.findAll();
    }

}