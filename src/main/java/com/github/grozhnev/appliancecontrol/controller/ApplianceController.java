package com.github.grozhnev.appliancecontrol.controller;

import com.github.grozhnev.appliancecontrol.entity.ApplianceEntity;
import com.github.grozhnev.appliancecontrol.entity.EntityState;
import com.github.grozhnev.appliancecontrol.exception.NotFoundException;
import com.github.grozhnev.appliancecontrol.exception.WrongStateOperation;
import com.github.grozhnev.appliancecontrol.repository.ApplianceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
public class ApplianceController {

    private ApplianceRepository applianceRepository;

    @Autowired
    public ApplianceController(ApplianceRepository applianceRepository) {
        this.applianceRepository = applianceRepository;
    }


    @PostMapping(value = "/add/{model}")
    @Produces(MediaType.APPLICATION_JSON)
    public ApplianceEntity addApplianceEntity(@PathVariable("model") String model){
        ApplianceEntity entity = new ApplianceEntity(model);
        applianceRepository.save(entity);

        return entity;
    }

    @PostMapping(value = "/del/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ApplianceEntity> removeEntityById(@PathVariable("id") Long id){

        ApplianceEntity entity = applianceRepository.getOne(id);
        applianceRepository.delete(entity);

        return applianceRepository.findAll();
    }


    @PostMapping(value = "/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ApplianceEntity getEntityById(@PathVariable("id") Long id){
        return applianceRepository.findById(id).orElseThrow(NotFoundException::new);
    }


    @GetMapping(value = "/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ApplianceEntity> findAllEntities(){
        return applianceRepository.findAll();
    }



    @PostMapping(value = "/turnOn/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    private ApplianceEntity turnOn(@PathVariable("id") Long id){
        if (EntityState.POWER_OFF == getEntityById(id).getState()){

            ApplianceEntity entity = getEntityById(id);
            entity.setState(EntityState.STAND_BY);

            applianceRepository.save(entity);

            return applianceRepository.getOne(id);
        } else {
            throw  new WrongStateOperation();
        }

    }


    @PostMapping(value = "/loadDish/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    private ApplianceEntity loadDish(@PathVariable("id") Long id){

        if (getEntityById(id).getState() == EntityState.STAND_BY){

            ApplianceEntity entity = getEntityById(id);
            entity.setState(EntityState.LOADING_DISH);

            applianceRepository.save(entity);

            return applianceRepository.getOne(id);
        } else {
            throw new WrongStateOperation();
        }

    }

    @PostMapping(value = "/cook/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    private ApplianceEntity startCooking(@PathVariable("id") Long id){
        if (getEntityById(id).getState() == EntityState.LOADING_DISH){

            ApplianceEntity entity = getEntityById(id);
            entity.setState(EntityState.COOKING);

            applianceRepository.save(entity);

            return applianceRepository.getOne(id);
        } else {
            throw new WrongStateOperation();
        }
    }


    @PostMapping(value = "/stop/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    private ApplianceEntity getDish(@PathVariable("id") Long id){

        if (getEntityById(id).getState() == EntityState.COOKING){

            ApplianceEntity entity = getEntityById(id);
            entity.setState(EntityState.GETTING_DISH_OUT);

            applianceRepository.save(entity);

            return applianceRepository.getOne(id);
        } else {
            throw new WrongStateOperation();
        }

    }

    @PostMapping(value = "/turnOff/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    private ApplianceEntity turnOff(@PathVariable("id") Long id){

        if (getEntityById(id).getState() == EntityState.GETTING_DISH_OUT){

            ApplianceEntity entity = getEntityById(id);
            entity.setState(EntityState.POWER_OFF);

            applianceRepository.save(entity);

            return applianceRepository.getOne(id);
        } else {
            throw new WrongStateOperation();
        }

    }

}
