package pe.edu.cibertec.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.cibertec.dto.ProductoResponse;
import pe.edu.cibertec.service.ProductoService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Obtener todos los productos activos
    @GetMapping
    public ResponseEntity<List<ProductoResponse>> obtenerTodos() {
        return ResponseEntity.ok(
                productoService.obtenerTodosLosProductos()
        );
    }

    // Obtener producto por ID (NORMAL - Repository)
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerPorId(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                productoService.obtenerProductoPorId(id)
        );
    }

    // Obtener producto usando Feign Client
    @GetMapping("/feign/{id}")
    public ResponseEntity<ProductoResponse> obtenerProductoFeign(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                productoService.obtenerProductoDesdeFeign(id)
        );
    }

    // Obtener productos destacados
    @GetMapping("/destacados")
    public ResponseEntity<List<ProductoResponse>> obtenerDestacados() {
        return ResponseEntity.ok(
                productoService.obtenerProductosDestacados()
        );
    }

    // Obtener productos nuevos
    @GetMapping("/nuevos")
    public ResponseEntity<List<ProductoResponse>> obtenerNuevos() {
        return ResponseEntity.ok(
                productoService.obtenerProductosNuevos()
        );
    }

    // Obtener productos por categoría
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<ProductoResponse>> obtenerPorCategoria(
            @PathVariable Integer idCategoria) {

        return ResponseEntity.ok(
                productoService.obtenerProductosPorCategoria(idCategoria)
        );
    }

    // Buscar productos por nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoResponse>> buscar(
            @RequestParam String termino) {

        return ResponseEntity.ok(
                productoService.buscarProductos(termino)
        );
    }

    // Obtener productos con descuento
    @GetMapping("/ofertas")
    public ResponseEntity<List<ProductoResponse>> obtenerOfertas() {
        return ResponseEntity.ok(
                productoService.obtenerProductosConDescuento()
        );
    }

    // Filtrar productos con múltiples criterios
    @GetMapping("/filtrar")
    public ResponseEntity<List<ProductoResponse>> filtrar(
            @RequestParam(required = false) Integer categoria,
            @RequestParam(required = false) BigDecimal precioMin,
            @RequestParam(required = false) BigDecimal precioMax,
            @RequestParam(required = false) Boolean destacado) {

        return ResponseEntity.ok(
                productoService.filtrarProductos(
                        categoria,
                        precioMin,
                        precioMax,
                        destacado
                )
        );
    }
}