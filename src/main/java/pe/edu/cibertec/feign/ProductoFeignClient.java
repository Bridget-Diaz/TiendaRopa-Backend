package pe.edu.cibertec.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.cibertec.dto.ProductoResponse;

@FeignClient(
        name = "producto-service",
        url = "http://localhost:8080/api/productos"
)
public interface ProductoFeignClient {

    @GetMapping("/{id}")
    ProductoResponse obtenerProducto(@PathVariable Integer id);

}