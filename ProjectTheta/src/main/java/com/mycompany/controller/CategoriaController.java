package com.mycompany.controller;

import com.mycompany.model.entities.Categoria;
import com.mycompany.services.CategoriaService;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoriaController {
    
    private final CategoriaService categoriaService;
    
    public List<Categoria> getCategories(){
        return categoriaService.findAll();
    }
    
    public Categoria findCategoryById(int id){
        return categoriaService.findById(id);
    }
    
    public void addCategory(Categoria categoria){
        categoriaService.save(categoria);
    }
    
    public byte datosExist(String nombre, int id){
        List<Categoria> categorias = getCategories();
        for(Categoria m : categorias){
            if(m.getIdCategoria() != id){
                if(m.getNombre().equalsIgnoreCase(nombre)){ return 1; }
            }
        }
        return 0;
    }
}
