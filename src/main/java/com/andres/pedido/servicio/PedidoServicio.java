package com.andres.pedido.servicio;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.andres.pedido.dto.RespuestaPedido;
import com.andres.pedido.modelo.Pedido;
import com.andres.pedido.modelo.Producto;
import com.andres.pedido.repositorio.PedidoRepositorio;
import com.andres.pedido.repositorio.ProductoRepositorio;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoServicio {
    Map<String, Object> response = new HashMap();
    private final PedidoRepositorio pedidoRepositorio;
    private final ProductoRepositorio productoRepositorio;

    @Autowired
    public PedidoServicio(PedidoRepositorio pedidoRepositorio, ProductoRepositorio productoRepositorio) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.productoRepositorio = productoRepositorio;
    }

    public List<Pedido> getPedidos() {
        return this.pedidoRepositorio.findAll();
    }

    public Pedido nuevoPedido(Pedido pedido, String descripcion, Long idProducto) {
        float total = 0.0F;

        try {
            pedido.setDescripcion(descripcion);
            pedido.setDireccion("Carrera 11# 14-08");
            pedido.setCedula(12345);
            Optional<Producto> productoId = this.productoRepositorio.findById(idProducto);
            if (productoId.isPresent()) {
                pedido.setProducto(((Producto)productoId.get()).getNombre());
                float iva;
                if (((Producto)productoId.get()).getPrecio() > 70000.0F && ((Producto)productoId.get()).getPrecio() < 100000.0F) {
                    pedido.setDomicilio(3000.0F);
                    iva = (float)((double)((Producto)productoId.get()).getPrecio() * 0.19D);
                    total = ((Producto)productoId.get()).getPrecio() + iva + pedido.getDomicilio();
                    pedido.setIva(iva);
                    pedido.setTotal(total);
                    pedido.setPrecioProducto(((Producto)productoId.get()).getPrecio());
                } else if (((Producto)productoId.get()).getPrecio() > 100000.0F) {
                    pedido.setDomicilio(0.0F);
                    iva = (float)((double)((Producto)productoId.get()).getPrecio() * 0.19D);
                    total = ((Producto)productoId.get()).getPrecio() + iva + pedido.getDomicilio();
                    pedido.setIva(iva);
                    pedido.setTotal(total);
                    pedido.setPrecioProducto(((Producto)productoId.get()).getPrecio());
                } else {
                    iva = (float)((double)((Producto)productoId.get()).getPrecio() * 0.19D);
                    pedido.setDomicilio(3000.0F);
                    pedido.setIva(iva);
                    total = ((Producto)productoId.get()).getPrecio() + iva + pedido.getDomicilio();
                    pedido.setTotal(total);
                    pedido.setPrecioProducto(((Producto)productoId.get()).getPrecio());
                }
            }

            pedido.setFecha(LocalDateTime.now());
            this.pedidoRepositorio.save(pedido);
            return pedido;
        } catch (RuntimeException var7) {
            throw new IllegalStateException("No se pudo guardar el pedido");
        }
    }

    @Transactional
    public Pedido actualizarPedido(Long idPedido, Long idProducto) {
        float total = 0.0F;
        Pedido pedido = (Pedido)this.pedidoRepositorio.findById(idPedido).orElseThrow(() -> {
            return new IllegalStateException("El pedido con el id: " + idPedido + ", No existe!");
        });
        Optional<Producto> productoId = this.productoRepositorio.findById(idProducto);
        LocalDateTime fechaPedido = pedido.getFecha();
        LocalDateTime current = LocalDateTime.now();
        Duration duration = Duration.between(fechaPedido, current);
        if (productoId.isPresent()) {
            if (duration.getSeconds() < 18000L) {
                if (((Producto)productoId.get()).getPrecio() >= pedido.getPrecioProducto()) {
                    float iva;
                    if (((Producto)productoId.get()).getPrecio() > 100000.0F) {
                        pedido.setProducto(((Producto)productoId.get()).getNombre());
                        pedido.setDomicilio(0.0F);
                        iva = (float)((double)((Producto)productoId.get()).getPrecio() * 0.19D);
                        total = ((Producto)productoId.get()).getPrecio() + iva + pedido.getDomicilio();
                        pedido.setTotal(total);
                        pedido.setIva(iva);
                        pedido.setPrecioProducto(((Producto)productoId.get()).getPrecio());
                    } else {
                        pedido.setProducto(((Producto)productoId.get()).getNombre());
                        pedido.setDomicilio(3000.0F);
                        iva = (float)((double)((Producto)productoId.get()).getPrecio() * 0.19D);
                        total = ((Producto)productoId.get()).getPrecio() + iva + pedido.getDomicilio();
                        pedido.setTotal(total);
                        pedido.setIva(iva);
                        pedido.setPrecioProducto(((Producto)productoId.get()).getPrecio());
                    }

                    return pedido;
                } else {
                    throw new IllegalStateException("El Producto seleccionado debe tener un precio mayor o igual al anterior");
                }
            } else {
                throw new IllegalStateException("El Pedido no puede ser editado, Han pasado más de 5 Horas");
            }
        } else {
            throw new IllegalStateException("El Producto con el id: " + idProducto + ", No existe!");
        }
    }

    public RespuestaPedido cancelarPedido(Long idPedido) {
        RespuestaPedido respuestaPedido = new RespuestaPedido();
        float nuevoTotal = 0.0F;
        Pedido pedido = (Pedido)this.pedidoRepositorio.findById(idPedido).orElseThrow(() -> {
            return new IllegalStateException("El pedido con el id: " + idPedido + ", No existe!");
        });
        LocalDateTime fechaPedido = pedido.getFecha();
        LocalDateTime current = LocalDateTime.now();
        Duration duration = Duration.between(fechaPedido, current);
        if (duration.getSeconds() < 43200L) {
            this.pedidoRepositorio.deleteById(idPedido);
            return null;
        } else {
            nuevoTotal = (float)((double)pedido.getTotal() * 0.1D);
            pedido.setTotal(nuevoTotal);
            pedido.setDomicilio(0.0F);
            pedido.setIva(0.0F);
            respuestaPedido.setPedido(pedido);
            respuestaPedido.setMensaje("Pedido Cancelado");
            this.pedidoRepositorio.deleteById(idPedido);
            return respuestaPedido;
        }
    }
}

