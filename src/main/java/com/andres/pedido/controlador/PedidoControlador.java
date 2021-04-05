package com.andres.pedido.controlador;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import com.andres.pedido.dto.RespuestaPedido;
import com.andres.pedido.modelo.Pedido;
import com.andres.pedido.servicio.PedidoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = {"api/pedido"}
)
public class PedidoControlador {
    private final PedidoServicio pedidoServicio;

    @Autowired
    public PedidoControlador(PedidoServicio pedidoServicio) {
        this.pedidoServicio = pedidoServicio;
    }

    @GetMapping
    public List<Pedido> obtenerPedidos() {
        return this.pedidoServicio.getPedidos();
    }

    @PostMapping
    public Pedido nuevoPedido(@RequestBody Pedido pedido, @RequestParam(required = true) String descripcion, @RequestParam(required = true) Long idProducto) {
        return this.pedidoServicio.nuevoPedido(pedido, descripcion, idProducto);
    }

    @PutMapping({"/{idPedido}/{idProducto}"})
    public Pedido reemplazarPedido(@PathVariable Long idPedido, @PathVariable Long idProducto) {
        return this.pedidoServicio.actualizarPedido(idPedido, idProducto);
    }

    @DeleteMapping(
            path = {"/{idPedido}"}
    )
    public RespuestaPedido cancelarPedido(@PathVariable("idPedido") Long idPedido) {
        return this.pedidoServicio.cancelarPedido(idPedido);
    }
}
