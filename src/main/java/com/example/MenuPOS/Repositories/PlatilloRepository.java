package com.example.MenuPOS.Repositories;

import com.example.MenuPOS.Models.PlatilloModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PlatilloRepository extends CrudRepository<PlatilloModel,Long> {

    public abstract ArrayList<PlatilloModel> findByDescripcion(String descripcion); //Consultas especificas
    public abstract ArrayList<PlatilloModel> findByNombre(String nombre); //Consultas especificas

    public abstract ArrayList<PlatilloModel> findByPrecio(double precio); //Consultas especificas

}

