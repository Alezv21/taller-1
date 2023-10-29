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
public class VentaService implements IDAO<Venta,Integer> {

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

        try{
            
            Venta aux = new Venta();
            aux.setClienteId(param.getClienteId());
            aux.setFecha(param.getFecha());
            aux.setMetodoPagoId(param.getMetodoPagoId());
            aux.setTotal(param.getTotal());

            Venta saved = this.repository.save(aux);
            System.out.println(aux.toString());

            List<VentaDetalle> vdList = param.getVentaDetalleList();
            for(VentaDetalle item: vdList){
                VentaDetalle vdt = new VentaDetalle();
                vdt.setVentaId(saved);
                vdt.setProductoId(item.getProductoId());
                vdt.setCantidad(item.getCantidad());
                vdt.setSubtotal(item.getSubtotal());

                this.repositoryDetalle.save(vdt);

                Integer productoId = item.getProductoId().getProductoId();
                int cantidadVendida = item.getCantidad();
                productoRepository.reduceStock(productoId, cantidadVendida);

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return param;
    }

    

    @Override
    @Transactional
    public Venta modificar(Venta param) {
        try {
            // Convertir el ID a Integer si es necesario
            Integer ventaId = param.getVentaId().intValue();
    
            // Verificar si la venta existe en la base de datos
            Optional<Venta> ventaExistente = this.repository.findById(ventaId);
            
            if (ventaExistente.isPresent()) {
                // Actualizar los campos de la venta existente con los valores del parámetro
                Venta ventaActualizada = ventaExistente.get();
                ventaActualizada.setClienteId(param.getClienteId());
                ventaActualizada.setFecha(param.getFecha());
                ventaActualizada.setMetodoPagoId(param.getMetodoPagoId());
                ventaActualizada.setTotal(param.getTotal());
    
                // Guardar la venta actualizada en la base de datos
                Venta ventaGuardada = this.repository.save(ventaActualizada);
                System.out.println(ventaGuardada.toString());
    
                // Eliminar los detalles de venta existentes
                this.repositoryDetalle.deleteByVentaId(ventaGuardada.getVentaId());
    
                // Guardar los nuevos detalles de venta
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
                // Si la venta no existe, lanzar una excepción o manejar el caso según tu lógica de negocio
                throw new RuntimeException("Venta no encontrada con ID: " + param.getVentaId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar la excepción según tu lógica de negocio (lanzar otra excepción, loggear, etc.)
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