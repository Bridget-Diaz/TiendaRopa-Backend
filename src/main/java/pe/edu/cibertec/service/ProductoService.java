package pe.edu.cibertec.service;

import org.springframework.stereotype.Service;

import pe.edu.cibertec.dto.ComponenteDTO;
import pe.edu.cibertec.dto.ProductoResponse;
import pe.edu.cibertec.dto.TallaStockDTO;
import pe.edu.cibertec.entity.PackComponente;
import pe.edu.cibertec.entity.Producto;
import pe.edu.cibertec.feign.ProductoFeignClient;
import pe.edu.cibertec.repository.ProductoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

	private final ProductoRepository productoRepository;
	private final ProductoFeignClient productoFeignClient;

	public ProductoService(
	        ProductoRepository productoRepository,
	        ProductoFeignClient productoFeignClient) {

	    this.productoRepository = productoRepository;
	    this.productoFeignClient = productoFeignClient;
	}
	public ProductoResponse obtenerProductoDesdeFeign(Integer id) {
	    return productoFeignClient.obtenerProducto(id);
	}

    public List<ProductoResponse> obtenerTodosLosProductos() {
        return productoRepository.findByActivoTrue()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public ProductoResponse obtenerProductoPorId(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return convertirAResponse(producto);
    }

    public List<ProductoResponse> obtenerProductosDestacados() {
        return productoRepository.findByDestacadoTrueAndActivoTrue()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public List<ProductoResponse> obtenerProductosNuevos() {
        return productoRepository.findByNuevoTrueAndActivoTrue()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public List<ProductoResponse> obtenerProductosPorCategoria(Integer idCategoria) {
        return productoRepository.findByCategoriaIdCategoriaAndActivoTrue(idCategoria)
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public List<ProductoResponse> buscarProductos(String termino) {
        return productoRepository.findByNombreContainingIgnoreCaseAndActivoTrue(termino)
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public List<ProductoResponse> obtenerProductosConDescuento() {
        return productoRepository.findProductosConDescuento()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public List<ProductoResponse> filtrarProductos(Integer categoria, BigDecimal precioMin, 
                                                   BigDecimal precioMax, Boolean destacado) {
        return productoRepository.buscarConFiltros(categoria, precioMin, precioMax, destacado)
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    // Método helper para convertir Producto a ProductoResponse
    private ProductoResponse convertirAResponse(Producto producto) {
        ProductoResponse response = new ProductoResponse();
        response.setIdProducto(producto.getIdProducto());
        response.setNombre(producto.getNombre());
        response.setDescripcion(producto.getDescripcion());
        response.setPrecio(producto.getPrecio());
        response.setPrecioDescuento(producto.getPrecioDescuento());
        response.setStock(producto.getStock());
        response.setCategoria(producto.getCategoria().getNombreCategoria());
        response.setIdCategoria(producto.getCategoria().getIdCategoria());
        response.setDestacado(producto.getDestacado());
        response.setNuevo(producto.getNuevo());

        // Verificar si es un pack
        boolean esPack = producto.getComponentes() != null && !producto.getComponentes().isEmpty();
        response.setEsPack(esPack);

        if (esPack) {
            // Es un PACK: mapear componentes con sus tallas
            List<ComponenteDTO> componentes = producto.getComponentes()
                    .stream()
                    .map(this::convertirComponenteADTO)
                    .collect(Collectors.toList());
            response.setComponentes(componentes);
        } else {
            // Es un producto normal: mapear tallas directas
            if (producto.getTallas() != null) {
                List<TallaStockDTO> tallas = producto.getTallas()
                        .stream()
                        .map(pt -> new TallaStockDTO(
                                pt.getTalla().getIdTalla(),
                                pt.getTalla().getNombreTalla(),
                                pt.getStockPorTalla()
                        ))
                        .collect(Collectors.toList());
                response.setTallasDisponibles(tallas);
            }
        }

        return response;
    }
    
 // NUEVO: Convertir componente de pack a DTO
    private ComponenteDTO convertirComponenteADTO(PackComponente componente) {
        List<TallaStockDTO> tallas = componente.getTallas()
                .stream()
                .map(pct -> new TallaStockDTO(
                        pct.getTalla().getIdTalla(),
                        pct.getTalla().getNombreTalla(),
                        pct.getStockPorTalla()
                ))
                .collect(Collectors.toList());

        return new ComponenteDTO(
                componente.getIdPackComponente(),
                componente.getNombreComponente(),
                tallas
        );
    }
}
