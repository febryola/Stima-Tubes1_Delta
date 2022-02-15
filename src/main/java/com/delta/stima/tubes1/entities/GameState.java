package com.delta.stima.tubes1.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    @SerializedName("currentRound")
    public int currentRound;

    @SerializedName("maxRounds")
    public int maxRounds;

    @SerializedName("player")
    public Car player;

    @SerializedName("opponent")
    public Car opponent;

    @SerializedName("worldMap")
    public List<Lane[]> lanes;

    public GameState() {}
    public GameState(GameState copy){
        this.currentRound = copy.currentRound;
        this.maxRounds = copy.maxRounds;
        this.player = copy.player;
        this.player = new Car(copy.player);
        this.opponent = new Car(copy.opponent);

        this.lanes = new ArrayList<>();
        for(int i = 0; i < lanes.size(); i++) {
            Lane[] lanes = new Lane[this.lanes.get(i).length];
            this.lanes.add(lanes);
        }
    }
}
