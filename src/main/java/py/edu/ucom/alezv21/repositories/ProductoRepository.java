package py.edu.ucom.alezv21.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import py.edu.ucom.alezv21.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {

    @Modifying
    @Query("UPDATE Producto p SET p.stock = p.stock - :cantidad WHERE p.productoId = :productoId")
    void reduceStock(@Param("productoId") Integer productoId, @Param("cantidad") int cantidad);

}
