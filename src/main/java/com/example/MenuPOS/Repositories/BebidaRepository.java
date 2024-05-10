package com.example.MenuPOS.Repositories;
import com.example.MenuPOS.Models.BebidaModel;
import com.example.MenuPOS.Models.PlatilloModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface BebidaRepository  extends CrudRepository<BebidaModel, Long> {

    public abstract ArrayList<BebidaModel> findByNombre(String nombre); //Consultas especificas


}
