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


@ApplicationScoped
public class VentaService implements IDAO<Venta,Integer> {

    @Inject
    private VentaRepository repository;


    @Inject
    private VentaDetalleRepository repositoryDetalle;

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
                vdt.setSubtotal(item.getSubtotal());

                this.repositoryDetalle.save(vdt);

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return param;
    }

    

    @Override
    public Venta modificar(Venta param) {
        return this.repository.save(param);
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