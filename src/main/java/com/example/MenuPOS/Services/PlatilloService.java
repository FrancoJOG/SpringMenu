package com.example.MenuPOS.Services;

import com.example.MenuPOS.Models.PlatilloModel;
import com.example.MenuPOS.Repositories.PlatilloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PlatilloService {

    @Autowired //
    PlatilloRepository platilloRepository;

    //obtener todos los platillos
    public ArrayList<PlatilloModel> findAllPlatillos(){
        return (ArrayList<PlatilloModel>) platilloRepository.findAll(); //casteo
    }

    public PlatilloModel savePlatillo(PlatilloModel platillo){
        return platilloRepository.save(platillo);
    }



    public ArrayList<PlatilloModel> findByNombre(String nombre){
        return (ArrayList<PlatilloModel>) platilloRepository.findByNombre(nombre); //casteo
    }

    public ArrayList<PlatilloModel> findByDescripcion(String descripcion){
        return (ArrayList<PlatilloModel>) platilloRepository.findByDescripcion(descripcion); //casteo
    }

    public ArrayList<PlatilloModel> findByPrecio(double precio){
        return (ArrayList<PlatilloModel>) platilloRepository.findByPrecio(precio); //casteo
    }

    public Optional<PlatilloModel> findById(long id){
        return platilloRepository.findById(id); //casteo
    }

    //test
    public byte[] obtenerImagen(Long platilloId) {
        Optional<PlatilloModel> optionalPlatillo = platilloRepository.findById(platilloId);
        if (optionalPlatillo.isPresent()) {
            PlatilloModel platillo = optionalPlatillo.get();
            return platillo.getImagen(); // Suponiendo que el modelo PlatilloModel tiene un atributo "imagen" que es un array de bytes
        } else {
            // Manejar el caso en el que el platillo no sea encontrado
            return null;
        }
    }

    public void deletePlatillo(Long platilloId) {
        platilloRepository.deleteById(platilloId);
    }

    public PlatilloModel findById(Long id) {
        Optional<PlatilloModel> platilloOptional = platilloRepository.findById(id);
        return platilloOptional.orElse(null);
    }
}
