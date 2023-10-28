package py.edu.ucom.alezv21.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.ucom.alezv21.entities.TipoDocumento;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento,Integer> {
    
}
