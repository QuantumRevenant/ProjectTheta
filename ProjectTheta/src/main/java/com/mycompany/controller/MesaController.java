package com.mycompany.controller;

import com.mycompany.model.entities.Mesa;
import com.mycompany.model.entities.Pedido;
import com.mycompany.model.entities.TipoPedido;
import com.mycompany.model.generics.Print;
import com.mycompany.services.MesaService;
import com.mycompany.services.PedidoService;
import static java.lang.Thread.sleep;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MesaController {

    private final MesaService mesaService;
    private List<Mesa> lstMesa = new ArrayList<>();
    private List<Pedido> lstPedPendientes = null;
    private List<Pedido> lstPedEnvios = null;
    private ProgramController pc = ProgramController.getProgramController();
    private PedidoController pec = new PedidoController(new PedidoService());

    public static MesaController theMesaController;

    public static MesaController getMesaController() {
        if (theMesaController == null) {
            theMesaController = new MesaController(new MesaService());
        }
        return theMesaController;
    }

    public MesaController(MesaService mS) {
        mesaService = mS;
        lstPedPendientes = pec.getStatusOrders(Pedido.PEDIDO_STATUS.PENDIENTE);
        lstPedEnvios = pec.getStatusOrders(Pedido.PEDIDO_STATUS.EN_ENVIO);
        updateQuantity();
        updateStatusMesas();
    }

    public void updateController() {
        lstPedPendientes = pec.getStatusOrders(Pedido.PEDIDO_STATUS.PENDIENTE);
        lstPedEnvios = pec.getStatusOrders(Pedido.PEDIDO_STATUS.EN_ENVIO);
        updateQuantity();
        updateStatusMesas();
    }

    public void updateQuantity() {
        pc.cargar();
        int cantidadDeMesas = pc.getCantidadMesas();

        while (mesaService.findMaxId() < cantidadDeMesas) {
            mesaService.save(new Mesa(mesaService.findMaxId() + 1));
        }
        lstMesa = mesaService.findMinorsTo(cantidadDeMesas);
    }

    public void updateStatusMesas() {
        for (Mesa x : lstMesa) {
            List<Pedido> lstPedidos = pec.getStatusTableOrders(Pedido.PEDIDO_STATUS.PENDIENTE, x);
            if (lstPedidos == null || lstPedidos.size() == 0) {
                x.LiberarMesa();
            } else {
                x.OcuparMesa();
            }
        }
    }

    public int getQuantityStatus(Mesa.MESA_STATUS status) {
        int output = 0;
        for (Mesa x : lstMesa) {
            if (x.getMesa_status() == status) {
                output++;
            }
        }
        return output;
    }

    public int getQTablebyTypeOrder(int idStatus) {
        if (idStatus == -1) {
            System.out.print("Error - " + new Object() {
            }.getClass().getEnclosingMethod().getName());
            return 0;
        }
        int output = 0;
        List<Pedido> lstPedidos = lstPedPendientes;
        for (Pedido x : lstPedidos) {
            if (x.getIdTipoPedido().getIdTipoPedido() == idStatus && x.getIdMesa() != null) {
                output++;
            }
        }
        return output;
    }

    public int getQuantityStatusPedido(Pedido.PEDIDO_STATUS status) {
        List<Pedido> lstPedidos = lstPedPendientes;
        if (status == Pedido.PEDIDO_STATUS.EN_ENVIO) {
            lstPedidos = lstPedEnvios;
        } else if (status != Pedido.PEDIDO_STATUS.PENDIENTE) {
            pec.getStatusOrders(status);
        }
        return lstPedidos.size();
    }

    public long getProximaMesaLibre() {
        if (size() < 0) {
            return 1;
        }
        long minutes = 0;
        List<Pedido> lstPedidos = lstPedPendientes;
        for (Pedido x : lstPedidos) {
            if (x.getIdMesa() != null) {
                LocalDateTime ldt = LocalDateTime.parse(x.getFechaPedido(), pc.getFormatDayTime());
                Duration d = Duration.between(ldt, LocalDateTime.now());
                if (d.toMinutes() > 0) {
                    minutes = d.toMinutes();
                }
                break;
            }
        }
        for (Pedido x : lstPedidos) {
            if (x.getIdMesa() != null) {
                LocalDateTime ldt = LocalDateTime.parse(x.getFechaPedido(), pc.getFormatDayTime());
                Duration d = Duration.between(ldt, LocalDateTime.now());
                if (d.toMinutes() < minutes && d.toMinutes() > 0) {
                    minutes = d.toMinutes();
                }
                break;
            }
        }
        return minutes;
    }

    public Mesa get(int x) {
        return lstMesa.get(x);
    }

    public void add(Mesa x) {
        lstMesa.add(x);
    }

    public void set(int x, Mesa y) {
        lstMesa.set(x, y);
    }

    public void remove(int x) {
        lstMesa.remove(x);
    }

    public void remove(Mesa x) {
        lstMesa.remove(x);
    }

    public int size() {
        return lstMesa.size();
    }
}
