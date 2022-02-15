package com.delta.stima.tubes1.entities;

import com.google.gson.annotations.SerializedName;

public class Position {
    @SerializedName("y")
    public int lane;

    @SerializedName("x")
    public int block;

    public Position(){}
    public Position(Position copy){
        this.lane = copy.lane;
        this.block = copy.block;
    }
}
