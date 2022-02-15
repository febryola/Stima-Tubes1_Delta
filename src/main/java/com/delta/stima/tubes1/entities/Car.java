package com.delta.stima.tubes1.entities;

import com.google.gson.annotations.SerializedName;
import com.delta.stima.tubes1.enums.PowerUps;
import com.delta.stima.tubes1.enums.State;

public class Car {
    @SerializedName("id")
    public int id;

    @SerializedName("position")
    public Position position;

    @SerializedName("speed")
    public int speed;

    @SerializedName("state")
    public State state;

    @SerializedName("damage")
    public int damage;

    @SerializedName("powerups")
    public PowerUps[] powerups;

    @SerializedName("boosting")
    public Boolean boosting;

    @SerializedName("boostCounter")
    public int boostCounter;

    @SerializedName("score")
    public int score;

    public Car() {}
    public Car(Car copy) {
        this.id = copy.id;
        this.position = new Position(copy.position);
        this.speed = copy.speed;
        this.state = copy.state;
        this.damage = copy.damage;

        this.powerups = new PowerUps[copy.powerups.length];
        for(int i = 0; i < copy.powerups.length; i++){
            this.powerups[i] = copy.powerups[i];
        }

        this.boosting = copy.boosting;
        this.boostCounter= copy.boostCounter;
        this.score = copy.score;
    }
}
