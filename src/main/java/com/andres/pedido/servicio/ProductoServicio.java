package com.andres.pedido.servicio;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.andres.pedido.modelo.Producto;
import com.andres.pedido.repositorio.ProductoRepositorio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServicio {
    Map<String, Object> response = new HashMap();
    private final ProductoRepositorio productoRepositorio;

    @Autowired
    public ProductoServicio(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    public List<Producto> getProductos() {
        return this.productoRepositorio.findAll();
    }

    public Producto nuevoProducto(Producto producto) {
        this.productoRepositorio.save(producto);
        return producto;
    }
}
