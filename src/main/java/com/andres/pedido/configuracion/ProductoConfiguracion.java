package com.andres.pedido.configuracion;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.andres.pedido.modelo.Producto;
import com.andres.pedido.repositorio.ProductoRepositorio;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductoConfiguracion {
    public ProductoConfiguracion() {
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductoRepositorio productoRepositorio) {
        return (args) -> {
            Producto mesa = new Producto("Mesa", 50000.0F);
            Producto pala = new Producto("Pala", 21000.0F);
            Producto escoba = new Producto("Escoba", 71000.0F);
            Producto pc = new Producto("Pc", 200000.0F);
            Producto teclado = new Producto("Teclado", 20000.0F);
            productoRepositorio.saveAll(List.of(mesa, pala, escoba, pc, teclado));
        };
    }
}

