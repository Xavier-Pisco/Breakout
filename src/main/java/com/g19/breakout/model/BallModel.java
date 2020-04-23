package com.g19.breakout.model;

import com.g19.breakout.elements.Dimensions;
import com.g19.breakout.elements.Direction;
import com.g19.breakout.elements.Position;

public class BallModel extends ElementModel {
    private Direction direction;
    private double velocity;

    public enum HIT {TOP, BOTTOM, SIDE, PLAYERBARMIDDLE, PLAYERBARLEFT, PLAYERBARRIGHT, TILE, NOTHING};

    public BallModel(Position position, double startVelocity) {
        super(position, new Dimensions(2, 1));
        this.direction = new Direction(0, -1); // the ball starts by going upwards
        velocity = startVelocity;
    }

    public double getVelocity() {
        return velocity;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
}
