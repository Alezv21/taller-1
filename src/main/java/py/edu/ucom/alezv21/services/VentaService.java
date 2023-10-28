package py.edu.ucom.alezv21.services;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import py.edu.ucom.alezv21.repositories.VentaRepository;
import py.edu.ucom.alezv21.repositories.VentaDetalleRepository;


import py.edu.ucom.alezv21.config.IDAO;
import py.edu.ucom.alezv21.entities.Venta;
import py.edu.ucom.alezv21.entities.VentaDetalle;


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
                item.setVentaId(saved);
                this.repositoryDetalle.save(item);

            }
        }
        catch(Exception e){
            
        }
        return this.repository.save(param);
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