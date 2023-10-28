package py.edu.ucom.alezv21.services;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import py.edu.ucom.alezv21.repositories.TipoDocumentoRepository;
import py.edu.ucom.alezv21.config.IDAO;
import py.edu.ucom.alezv21.entities.TipoDocumento;


@ApplicationScoped
public class TipoDocumentoService implements IDAO<TipoDocumento,Integer> {
    @Inject
    private TipoDocumentoRepository repository;

    @Override
    public TipoDocumento obtener(Integer param) {
        return this.repository.findById(param).orElse(null);
    }

    @Override
    public TipoDocumento agregar(TipoDocumento param) {
        return this.repository.save(param);
    }

    @Override
    public TipoDocumento modificar(TipoDocumento param) {
        return this.repository.save(param);
    }

    @Override
    public void eliminar(Integer param) {

        this.repository.deleteById(param);
    }

    @Override
    public List<TipoDocumento> listar() {
        return this.repository.findAll();
    }
    
}