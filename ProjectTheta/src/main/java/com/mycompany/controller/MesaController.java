package com.mycompany.controller;

import com.mycompany.model.entities.Mesa;
import com.mycompany.model.entities.Pedido;
import com.mycompany.model.entities.TipoPedido;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MesaController {

    private List<Mesa> lstMesa = new ArrayList<>();
    private ProgramController pc = ProgramController.getProgramController();

    public MesaController() {
        updateQuantity();
    }

    public void updateQuantity() {
        pc.cargar();
        int cantidadDeMesas = pc.getCantidadMesas();
        for (int i = size(); i > cantidadDeMesas; i--) {
            lstMesa.remove(i - 1);
        }
        for (int i = size(); i < cantidadDeMesas; i++) {
            lstMesa.add(new Mesa(i + 1));
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

    public int getQuantityTypePedido(TipoPedido.PEDIDOS status) {
        int output = 0;
        for (Mesa x : lstMesa) {
            if (x.getIdPedido() != null) {
                if (x.getIdPedido().getIdTipoPedido().getTipoPedido() == status) {
                    output++;
                }
            }
        }
        return output;
    }

    public int getQuantityStatusPedido(Pedido.PEDIDO_STATUS status) {
        int output = 0;
        for (Mesa x : lstMesa) {
            if (x.getIdPedido() != null) {
                if (x.getIdPedido().getStatus()== status) {
                    output++;
                }
            }
        }
        return output;
    }

    public long getProximaMesaLibre() {
        if (size() < 0) {
            return 1;
        }
        long output = get(0).getMinutosFaltantes();
        for (Mesa x : lstMesa) {
            if (x.getMinutosFaltantes() > 0 && x.getMinutosFaltantes() < output) {
                output = x.getMinutosFaltantes();
            }
        }
        return output;
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

    public List<Mesa> getLstMesa() {
        return lstMesa;
    }

    public void setLstMesa(List<Mesa> lstMesa) {
        this.lstMesa = lstMesa;
    }

    public ProgramController getPc() {
        return pc;
    }

    public void setPc(ProgramController pc) {
        this.pc = pc;
    }

}
