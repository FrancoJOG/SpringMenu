package com.example.MenuPOS.Controllers;


import com.example.MenuPOS.Models.PlatilloModel;
import com.example.MenuPOS.Services.PlatilloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Optional;

@Controller
public class Menu {

    @Autowired
    PlatilloService platilloService;

    //Controlador vista Menu
    @GetMapping("/menu")
    public String showMenu(Model model){
        model.addAttribute("platillos", platilloService.findAllPlatillos()); //
        return "index";
    }


    //Get imagen
    @GetMapping("/imagen/{platilloId}")
    public ResponseEntity<byte[]> obtenerImagen(@PathVariable Long platilloId) {
        byte[] imageData = platilloService.obtenerImagen(platilloId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
    }

    //Controlador vista Registro
    @GetMapping("/registrar")
    public String showRegistrer(Model model){
        return "registrer";
    }

    //Controlador vista platillos
    @GetMapping("/platillos")
    public String showPlatillos(Model model){
        model.addAttribute("platillos", platilloService.findAllPlatillos()); //
        return "dishes";
    }

    //Controlador vista bebidas
    @GetMapping("/bebidas")
    public String showBebidas(Model model){
        //model.addAttribute("platillos", platilloService.findAllPlatillos()); //
        return "drinks";
    }


    //Guardar platillo
    @PostMapping(value = "/guardar-platillo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlatilloModel savePlatillo(@RequestBody PlatilloModel platillo) {
        // Aquí puedes manejar la lógica para guardar el platillo en la base de datos
        return platilloService.savePlatillo(platillo);
    }


    //Eliminar platillo
    @DeleteMapping("/eliminar-platillo/{platilloId}")
    public ResponseEntity<String> deletePlatillo(@PathVariable Long platilloId) {
        platilloService.deletePlatillo(platilloId);
        return ResponseEntity.ok().body("Platillo eliminado correctamente");
    }

    //editar
    @PostMapping("/editar-platillo")
    public ResponseEntity<String> editarPlatillo(@RequestBody PlatilloModel platillo) {
        PlatilloModel platilloExistente = platilloService.findById(platillo.getId());
        if (platilloExistente == null) {
            return ResponseEntity.notFound().build();
        }

        platilloExistente.setNombre(platillo.getNombre());
        platilloExistente.setDescripcion(platillo.getDescripcion());
        platilloExistente.setPrecio(platillo.getPrecio());

        PlatilloModel platilloActualizado = platilloService.savePlatillo(platilloExistente);

        if (platilloActualizado != null) {
            return ResponseEntity.ok().body("Platillo actualizado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el platillo");
        }
    }

    @GetMapping("/find-by-id") //http://tudominio.com/find-by-name?name=name_value
    public Optional<PlatilloModel> findById(@RequestParam("id") long id){ //  El valor del parámetro "nombre" se asignará a la variable name
        return  platilloService.findById(id);
    }

}














