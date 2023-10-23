package py.edu.ucom.alezv21.services;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import py.edu.ucom.alezv21.Repositories.VentaRepository;
import py.edu.ucom.alezv21.config.IDAO;
import py.edu.ucom.alezv21.entities.Cliente;
import py.edu.ucom.alezv21.entities.Venta;
import py.edu.ucom.alezv21.entities.VentaDetalle;
import py.edu.ucom.alezv21.entities.dto.ResumenVentaDTO;
import py.edu.ucom.alezv21.entities.dto.VentaDetalleDTO;


@ApplicationScoped
public class VentaService implements IDAO<Venta,Integer> {
    @Inject
    public VentaRepository repository;

    @Override
    public Venta obtener(Integer param) {
        // TODO Auto-generated method stub
        return this.repository.findById(param).orElse(null);
    }

    @Override
    public Venta agregar(Venta param) {
        // TODO Auto-generated method stub
        return this.repository.save(param);
    }

    @Override
    public Venta modificar(Venta param) {
        // TODO Auto-generated method stub
        return this.repository.save(param);
    }

    @Override
    public void eliminar(Integer param) {
        // TODO Auto-generated method stub

        this.repository.deleteById(param);
    }

    @Override
    public List<Venta> listar() {
        return this.repository.findAll();
    }
    public ResumenVentaDTO obtenerResumen(Integer ventaId){
        ResumenVentaDTO data = new ResumenVentaDTO();
        Venta v = this.repository.findById(ventaId).orElse(null);
        Cliente clie = v.getClienteId();
        data.setRazonSocial(clie.getNombres()+" "+clie.getApellidos() );
        data.setDocumento(clie.getDocumento());
        data.setFecha(v.getFecha());
        List<VentaDetalleDTO> detalle= new ArrayList<>();
        for(VentaDetalle item : v.getVentaDetalleList()){
                VentaDetalleDTO vdto = new VentaDetalleDTO();
                vdto.setCantidad(item.getCantidad());
                vdto.setSubtotal(item.getSubtotal());
                vdto.setDescripcion( item.getProductoId().getDescripcion());
                detalle.add( vdto);
        }
        data.setDetalle( detalle);

        return data;
    }
    
}