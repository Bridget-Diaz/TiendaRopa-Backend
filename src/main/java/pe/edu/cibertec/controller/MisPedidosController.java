package pe.edu.cibertec.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.service.MisPedidosService;
import pe.edu.cibertec.service.PedidoDetalleService;

@RestController
@RequestMapping("/api/pedidos")
public class MisPedidosController {

    private final MisPedidosService misPedidosService;
    private final PedidoDetalleService pedidoDetalleService;

    public MisPedidosController(MisPedidosService misPedidosService,
                                 PedidoDetalleService pedidoDetalleService) {
        this.misPedidosService = misPedidosService;
        this.pedidoDetalleService = pedidoDetalleService;
    }

    @GetMapping("/mis-pedidos")
    public ResponseEntity<?> misPedidos(Authentication auth) {
        return ResponseEntity.ok(
                misPedidosService.obtenerMisPedidos(auth.getName())
        );
    }

    @GetMapping("/{idPedido}/detalle")
    public ResponseEntity<?> detallePedido(
            @PathVariable Integer idPedido,
            Authentication auth) {

        return ResponseEntity.ok(
                pedidoDetalleService.obtenerDetalle(idPedido, auth.getName())
        );
    }
}