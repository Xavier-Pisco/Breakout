package com.g19.breakout.view;

import com.g19.breakout.model.BallModel;
import com.g19.breakout.view.graphics.Graphics;

public class BallView extends ElementView implements View {
    private final BallModel ballModel;
    public BallView(BallModel ball, Graphics graphics, String color, char charRep) {
        super(ball, graphics, color, charRep);
        this.ballModel = ball;
    }

    public void draw() {
        drawElementModel(ballModel);
    }
}
