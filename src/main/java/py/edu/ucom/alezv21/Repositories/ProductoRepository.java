package py.edu.ucom.alezv21.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import py.edu.ucom.alezv21.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {

}
