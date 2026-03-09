package pe.edu.cibertec.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.dto.CheckoutRequestDTO;
import pe.edu.cibertec.service.CheckoutService;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping
    public ResponseEntity<?> procesarCheckout(
            @RequestBody CheckoutRequestDTO dto,
            Authentication auth) {
        return ResponseEntity.ok(checkoutService.procesarCheckout(auth.getName(), dto));
    }
}