package com.andres.pedido.controlador;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import com.andres.pedido.modelo.Producto;
import com.andres.pedido.servicio.ProductoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = {"api/producto"}
)
public class ProductoControlador {
    private final ProductoServicio productoServicio;

    @Autowired
    public ProductoControlador(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @GetMapping
    public List<Producto> obtenerPedidos() {
        return this.productoServicio.getProductos();
    }

    @PostMapping
    public Producto nuevoProducto(@RequestBody Producto producto) {
        return this.productoServicio.nuevoProducto(producto);
    }
}

