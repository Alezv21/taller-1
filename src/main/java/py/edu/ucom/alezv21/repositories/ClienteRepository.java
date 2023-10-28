package py.edu.ucom.alezv21.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.ucom.alezv21.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
    
}
