package pe.edu.cibertec.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.dto.CategoriaResponse;
import pe.edu.cibertec.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // Obtener todas las categorías activas
    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> obtenerTodas() {
        return ResponseEntity.ok(categoriaService.obtenerTodasLasCategorias());
    }

    // Obtener una categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(categoriaService.obtenerCategoriaPorId(id));
    }
}
