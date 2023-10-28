package py.edu.ucom.alezv21.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.ucom.alezv21.entities.VentaDetalle;

public interface VentaDetalleRepository extends JpaRepository<VentaDetalle,Integer> {
    
}
