package com.mycompany.controller;

import com.mycompany.model.entities.Menu;
import com.mycompany.services.MenuService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    public List<Menu> getMenus(){
        return menuService.findAll();
    }

    public void addMenu(Menu menu) {
        menuService.save(menu);
    }

    public void deleteMenu(Menu menu){
        menuService.delete(menu);
    }

    public void updateMenu(Menu menu){
        menuService.update(menu);
    }

    public Menu findServicioById(int id){
        return menuService.findById(id);
    }
    
    public int getCorrelativo(){
        if(getMenus().isEmpty()){ return 1; }
        else{ return getMenus().get(getMenus().size()-1).getIdMenu()+1; }
    }
    
    public byte datosExist(String nombre, int id){
        List<Menu> menus = getMenus();
        for(Menu m:menus){
            if(m.getIdMenu() != id){
                if(m.getTipo().equalsIgnoreCase(nombre)){ return 1; }
            }
        }
        return 0;
    }
}
