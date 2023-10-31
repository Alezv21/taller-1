package py.edu.ucom.alezv21.services;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import py.edu.ucom.alezv21.repositories.ProductoRepository;
import py.edu.ucom.alezv21.config.IDAO;
import py.edu.ucom.alezv21.entities.Producto;


@ApplicationScoped
public class ProductoService implements IDAO<Producto,Integer> {
    @Inject
    private ProductoRepository repository;

    @Override
    public Producto obtener(Integer param) {
        return this.repository.findById(param).orElse(null);
    }

    @Override
    public Producto agregar(Producto param) {
        return this.repository.save(param);
    }

    @Override
    public Producto modificar(Producto param) {
        return this.repository.save(param);
    }

    public void eliminar(Integer productoId) {
        Producto producto = this.repository.findById(productoId).orElse(null);
        if (producto != null && producto.getVentaDetalleList() != null && !producto.getVentaDetalleList().isEmpty()) {
            throw new RuntimeException("No se puede eliminar el producto porque tiene ventas asociadas.");
        }
        this.repository.deleteById(productoId);
    }

    @Override
    public List<Producto> listar() {
        return this.repository.findAll();
    }
    
}