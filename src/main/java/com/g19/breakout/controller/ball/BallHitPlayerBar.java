package com.g19.breakout.controller.ball;

import com.g19.breakout.elements.Direction;
import com.g19.breakout.model.BallModel;
import com.g19.breakout.model.PlayerModel;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class BallHitPlayerBar implements BallHit {
    private final PlayerModel playerBar;
    private final BallModel ball;


    public BallHitPlayerBar(BallModel ball, PlayerModel playerBar) {
        this.ball = ball;
        this.playerBar = playerBar;
    }

    public void updateDirection() {
        Direction direction = this.calculateNewDirection();
        ball.setDirection(direction);
    }

    private Direction calculateNewDirection(){
        double xOffset = ball.getPosition().getX() - playerBar.getPosition().getX();
        double xTotal = (playerBar.getDimensions().getDiscreteX() + ball.getDimensions().getDiscreteX())/2.;
        double cos = xOffset/xTotal;

        double sin = -sqrt(1 - pow(cos, 2)); // cos^2 + sin^2 = 1
        return new Direction(cos,sin);
    }
}
