package py.edu.ucom.alezv21.services;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import py.edu.ucom.alezv21.Repositories.ClienteRepository;
import py.edu.ucom.alezv21.config.IDAO;
import py.edu.ucom.alezv21.entities.Cliente;


@ApplicationScoped
public class ClienteService implements IDAO<Cliente,Integer> {
    @Inject
    private ClienteRepository repository;

    @Override
    public Cliente obtener(Integer param) {
        // TODO Auto-generated method stub
        return this.repository.findById(param).orElse(null);
    }

    @Override
    public Cliente agregar(Cliente param) {
        // TODO Auto-generated method stub
        return this.repository.save(param);
    }

    @Override
    public Cliente modificar(Cliente param) {
        // TODO Auto-generated method stub
        return this.repository.save(param);
    }

    @Override
    public void eliminar(Integer param) {
        // TODO Auto-generated method stub

        this.repository.deleteById(param);
    }

    @Override
    public List<Cliente> listar() {
        return this.repository.findAll();
    }
    
}