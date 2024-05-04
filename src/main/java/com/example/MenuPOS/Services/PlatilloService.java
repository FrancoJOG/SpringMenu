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
}
