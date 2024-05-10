package com.example.MenuPOS.Controllers;

import com.example.MenuPOS.Models.BebidaModel;
import com.example.MenuPOS.Models.PlatilloModel;
import com.example.MenuPOS.Services.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/drinks")
public class bebidaController {
    @Autowired
    BebidaService bebidaService;

    /*@PostMapping()
    public BebidaModel savePLatillo(@RequestBody BebidaModel bebida){
        return bebidaService.save(bebida);
    }*/


    @PostMapping("/save")
    public RedirectView save(@ModelAttribute BebidaModel bebida, RedirectAttributes atribute, @RequestParam("file") MultipartFile imagen ){

        if(!imagen.isEmpty()){
            Path dirImages = Paths.get("src//main//resources//static/img");
            String dirAbsolut = dirImages.toFile().getAbsolutePath();
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(dirAbsolut + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

                bebida.setImagen(imagen.getOriginalFilename());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        bebidaService.save(bebida);
        System.out.println("!Cliente Guardado con exito!");
        atribute.addFlashAttribute("success","!Platillo Guardado con exito!");
        return new RedirectView("/menu"); // Redirecciona a "/menu"
    }



}
