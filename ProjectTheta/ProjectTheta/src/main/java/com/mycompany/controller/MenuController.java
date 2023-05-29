package com.mycompany.controller;

import com.mycompany.model.entities.Cliente;
import com.mycompany.model.entities.Servicios;
import com.mycompany.services.ClienteService;
import com.mycompany.services.MenuService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    public List<Servicios> getMenus(){
        return menuService.findAll();
    }

    public void addMenu(Servicios menu) {
        menuService.save(menu);
    }

    public void deleteMenu(Servicios menu){
        menuService.delete(menu);
    }

    public void updateMenu(Servicios menu){
        menuService.update(menu);
    }

    public Servicios findServicioById(int id){
        return menuService.findById(id);
    }
}
