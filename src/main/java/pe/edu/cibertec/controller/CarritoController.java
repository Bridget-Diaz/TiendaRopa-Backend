package pe.edu.cibertec.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.dto.AgregarCarritoDTO;
import pe.edu.cibertec.service.CarritoService;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(
            @RequestBody AgregarCarritoDTO dto,
            Authentication auth) {

        carritoService.agregarProducto(dto, auth.getName());
        return ResponseEntity.ok("Producto agregado al carrito");
    }

    // ✅ Ya no recibe idUsuario en la URL — lo saca del JWT
    @GetMapping
    public ResponseEntity<?> obtenerCarrito(Authentication auth) {
        return ResponseEntity.ok(carritoService.obtenerItems(auth.getName()));
    }

    @PutMapping("/actualizar/{idItem}")
    public ResponseEntity<?> actualizarCantidad(
            @PathVariable Integer idItem,
            @RequestParam Integer cantidad,
            Authentication auth) {

        carritoService.actualizarCantidad(idItem, cantidad, auth.getName());
        return ResponseEntity.ok("Cantidad actualizada");
    }

    @DeleteMapping("/eliminar/{idItem}")
    public ResponseEntity<?> eliminar(
            @PathVariable Integer idItem,
            Authentication auth) {

        carritoService.eliminarItem(idItem, auth.getName());
        return ResponseEntity.ok("Producto eliminado");
    }
}