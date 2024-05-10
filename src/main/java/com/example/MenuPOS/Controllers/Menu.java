package com.example.MenuPOS.Controllers;


import com.example.MenuPOS.Models.BebidaModel;
import com.example.MenuPOS.Models.PlatilloModel;
import com.example.MenuPOS.Services.BebidaService;
import com.example.MenuPOS.Services.PlatilloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class Menu {

    @Autowired
    PlatilloService platilloService;
    @Autowired
    BebidaService bebidaService;


    //////////////////////////////////////////////Controlador vistas
    @GetMapping("/menu")
    public String showMenu(Model model){
        List<BebidaModel> list = bebidaService.findAll();
        model.addAttribute("bebidas", list);
        model.addAttribute("platillos", platilloService.findAllPlatillos()); //
        return "index";
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
        List<BebidaModel> list = bebidaService.findAll();
        model.addAttribute("bebidas", list);
        System.out.println(list);
        model.addAttribute("titulo","Lista de Bebidas");
        //model.addAttribute("platillos", platilloService.findAllPlatillos()); //
        return "drinks";
    }


    @GetMapping({"/registro2"})
    public String showRegistrer2(Model model){
        BebidaModel bebida = new BebidaModel();

        model.addAttribute("titulo","Registro");
        model.addAttribute("bebidas", bebida);
        return "registro2";
    }

    @GetMapping({"/home", "/blank"})
    public String showHome(Model model){
        model.addAttribute("titulo","Blank");
        return "blank";
    }

    ////////////////////////////////////////////////////////////////////////////

    @GetMapping({"/edit/{id}"})
    public String showEdit(@PathVariable("id") Long id, Model model, RedirectAttributes attribute){
        BebidaModel bebida = null;
        if (id > 0){
            bebida = bebidaService.findById(id);
            if (bebida == null){
                System.out.println("Error: El id no existe");
                attribute.addFlashAttribute("error","Atencion: El id no existe");
                return "registro2";
            }
        }else{
            System.out.println("Error: id invalido");
            attribute.addFlashAttribute("error","Atencion: El id es invalido");
            return "registro2";
        }
        model.addAttribute("titulo"," Editar Bebida ");
        model.addAttribute("bebidas", bebida);

        return "registro2";
    }

    @GetMapping({"/delete/{id}"})
    public String showDelete(@PathVariable("id") Long id, RedirectAttributes attribute){
        BebidaModel bebida = null;
        if (id > 0){
            bebida = bebidaService.findById(id);
            if (bebida == null){
                System.out.println("Error: El id no existe");

                return "redirect:/registro2";
            }
        }else{
            System.out.println("Error: id invalido");
            return "redirect:/registro2";
        }
        bebidaService.deleteBebida(id);
        System.out.println("!Registro " + id + " Eliminado con exito!");
        attribute.addFlashAttribute("Warning","Registro eliminado con exito");

        return "redirect:/menu";
    }


    @GetMapping({"/details/{id}"})
    public String showDetails(@PathVariable("id") Long id, Model model, RedirectAttributes attribute){
        BebidaModel bebida = null;
        if (id > 0){
            bebida = bebidaService.findById(id);
            if (bebida == null){
                System.out.println("Error: El id no existe");
                attribute.addFlashAttribute("error","Atencion: El id no existe");
                return "registro2";
            }
        }else{
            System.out.println("Error: id invalido");
            attribute.addFlashAttribute("error","Atencion: El id es invalido");
            return "registro2";
        }
        model.addAttribute("titulo","Detalle del producto " + bebida.getNombre());
        model.addAttribute("bebidas", bebida);

        return "Detalles";
    }


    @GetMapping("/bebidasByName")
    public String showBebidasByName(@RequestParam("platilloNombre") String nombre, Model model){
        if(nombre==""){
            List<BebidaModel> list = bebidaService.findAll();
            model.addAttribute("bebidas", list);
            model.addAttribute("titulo","Lista de Bebidas");
            return "drinks";
        }
        List<BebidaModel> list = bebidaService.findByNombre(nombre);
        model.addAttribute("bebidas", list);
        model.addAttribute("titulo","Lista de Bebidas");
        return "drinks";
    }




















    //Get imagen
    @GetMapping("/imagen/{platilloId}")
    public ResponseEntity<byte[]> obtenerImagen(@PathVariable Long platilloId) {
        byte[] imageData = platilloService.obtenerImagen(platilloId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
    }

    //Guardar platillo
    @PostMapping(value = "/guardar-platillo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PlatilloModel savePlatillo(@RequestBody PlatilloModel platillo) {
        return platilloService.savePlatillo(platillo);
    }

    /*@PostMapping("/save")
    public String guardar()*/



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














