package com.g19.breakout.controller.ball;

import com.g19.breakout.elements.Direction;
import com.g19.breakout.model.BallModel;
import com.g19.breakout.model.PlayerBarModel;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class BallHitPlayerBar extends BallHit {
    private BallHit ballHit;
    private final PlayerBarModel playerBar;

    public BallHitPlayerBar(BallModel ball, BallHit ballHit, PlayerBarModel playerBar) {
        super(ball);
        this.ballHit = ballHit;
        this.playerBar = playerBar;
    }

    public void updateDirection() {
        double xOffset = ball.getPosition().getX() - playerBar.getPosition().getX();
        double xTotal = (playerBar.getDimensions().getDiscreteX() + ball.getDimensions().getDiscreteX())/2.;
        double cos = xOffset/xTotal;

        double sin = -sqrt(1 - pow(cos, 2)); // cos^2 + sin^2 = 1
        ball.setDirection(new Direction(cos, sin));
        if (ballHit != null) ballHit.updateDirection();
    }
}
