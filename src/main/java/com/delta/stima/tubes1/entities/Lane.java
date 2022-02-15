package com.delta.stima.tubes1.entities;

import com.google.gson.annotations.SerializedName;
import com.delta.stima.tubes1.enums.Terrain;

public class Lane {
    @SerializedName("position")
    public Position position;

    @SerializedName("surfaceObject")
    public Terrain terrain;

    @SerializedName("occupiedByPlayerId")
    public int occupiedByPlayerId;

    @SerializedName("isOccupiedByCyberTruck")
    public Boolean isOccupiedByCyberTruck;

    public Lane() {}
    public Lane(Lane copy){
        this.position = new Position(copy.position);
        this.terrain = copy.terrain;
        this.occupiedByPlayerId = copy.occupiedByPlayerId;
        this.isOccupiedByCyberTruck = copy.isOccupiedByCyberTruck;
    }
}
