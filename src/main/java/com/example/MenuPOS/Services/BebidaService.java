package com.example.MenuPOS.Services;

import com.example.MenuPOS.Models.BebidaModel;
import com.example.MenuPOS.Repositories.BebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BebidaService {

    @Autowired
    BebidaRepository bebida;

    public ArrayList<BebidaModel> findAll(){
        return (ArrayList<BebidaModel>) bebida.findAll(); //casteo
    }

    public BebidaModel save(BebidaModel bebidaModel){
        return bebida.save(bebidaModel);
    }



    /*public ArrayList<BebidaModel> findByNombre(String nombre){
        return (ArrayList<BebidaModel>) bebida.findByNombre(nombre); //casteo
    }*/

    /*public ArrayList<BebidaModel> findByDescripcion(String descripcion){
        return (ArrayList<BebidaModel>) bebida.findByDescripcion(descripcion); //casteo
    }*/

    /*public ArrayList<BebidaModel> findByPrecio(double precio){
        return (ArrayList<BebidaModel>) bebida.findByPrecio(precio); //casteo
    }*/

    public Optional<BebidaModel> findById(long id){
        return bebida.findById(id); //casteo
    }

    //test
    /*public byte[] obtenerImagen(Long platilloId) {
        Optional<PlatilloModel> optionalPlatillo = platilloRepository.findById(platilloId);
        if (optionalPlatillo.isPresent()) {
            PlatilloModel platillo = optionalPlatillo.get();
            return platillo.getImagen(); // Suponiendo que el modelo PlatilloModel tiene un atributo "imagen" que es un array de bytes
        } else {
            // Manejar el caso en el que el platillo no sea encontrado
            return null;
        }
    }*/

    public void deleteBebida(Long id) {
        bebida.deleteById(id);
    }

    public BebidaModel findById(Long id) {
        Optional<BebidaModel> Optional = bebida.findById(id);
        return Optional.orElse(null);
    }


    public ArrayList<BebidaModel> findByNombre(String nombre) {
        return (ArrayList<BebidaModel>) bebida.findByNombre(nombre);
        /////////////////////////
    }
}
