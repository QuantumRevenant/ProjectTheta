/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.Mesa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebap
 */
public class MesaController {

    private List<Mesa> lstMesa = new ArrayList<>();
    private ProgramController pc = new ProgramController();

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
