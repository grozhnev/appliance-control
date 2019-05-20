package com.github.grozhnev.appliancecontrol.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ApplianceEntity {

    private Long id;

    private String modelName;

    private EntityState state;


    public ApplianceEntity() {
        state = EntityState.POWER_OFF;
    }


    public ApplianceEntity(String modelName) {
        this.modelName = modelName;
        this.state = EntityState.POWER_OFF;
    }


    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getModelName() {
        return modelName;
    }

    public void setModelName(String name) {
        this.modelName = name;
    }

    public EntityState getState() {
        return state;
    }

    public void setState(EntityState state) {
        this.state = state;
    }
}
