package py.edu.ucom.alezv21.services;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import py.edu.ucom.alezv21.repositories.ClienteRepository;
import py.edu.ucom.alezv21.config.IDAO;
import py.edu.ucom.alezv21.entities.Cliente;


@ApplicationScoped
public class ClienteService implements IDAO<Cliente,Integer> {
    @Inject
    private ClienteRepository repository;

    @Override
    public Cliente obtener(Integer param) {
        return this.repository.findById(param).orElse(null);
    }

    @Override
    public Cliente agregar(Cliente param) {
        return this.repository.save(param);
    }

    @Override
    public Cliente modificar(Cliente param) {
        return this.repository.save(param);
    }

    public void eliminar(Integer clienteId) {
        Cliente cliente = this.repository.findById(clienteId).orElse(null);
        if (cliente != null && cliente.getVentaList() != null && !cliente.getVentaList().isEmpty()) {
            throw new RuntimeException("No se puede eliminar el cliente porque tiene ventas asociadas.");
        }
        this.repository.deleteById(clienteId);
    }

    @Override
    public List<Cliente> listar() {
        return this.repository.findAll();
    }
    
}