package com.example.MenuPOS.Controllers;

import com.example.MenuPOS.Models.PlatilloModel;
import com.example.MenuPOS.Services.PlatilloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/menu")
public class platilloController {
    @Autowired
    PlatilloService platilloService;

    //get
    @GetMapping()
    public ArrayList<PlatilloModel> findAllPlatillos(){
        return platilloService.findAllPlatillos();
    }

    @PostMapping()
    public PlatilloModel savePLatillo(@RequestBody PlatilloModel platillo){
        return platilloService.savePlatillo(platillo);
    }

    //edit
    @PutMapping()
    public PlatilloModel updatePLatillo(@RequestBody PlatilloModel platillo){
        return platilloService.savePlatillo(platillo);
    }

    //Encpontrar por Nombre
    @GetMapping(path = "/find-by-nombre") //http://tudominio.com/find-by-name?name=name_value
    public ArrayList<PlatilloModel> findByName(@RequestParam("nombre") String nombre){ //  El valor del parámetro "nombre" se asignará a la variable name
        return  platilloService.findByNombre(nombre);
    }

    //Encpontrar por Descripcion
    @GetMapping(path = "/find-by-des")
    public ArrayList<PlatilloModel> findByCode(@RequestParam("des")String descripcion){
        return  platilloService.findByDescripcion(descripcion);
    }


    //Encpontrar por Precio
    @GetMapping(path = "/find-by-cost")
    public ArrayList<PlatilloModel> findByCode(@RequestParam("cost")double precio){
        return  platilloService.findByPrecio(precio);
    }

}
