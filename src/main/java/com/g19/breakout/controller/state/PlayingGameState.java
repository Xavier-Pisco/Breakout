package com.g19.breakout.controller.state;

import com.g19.breakout.controller.BallController;
import com.g19.breakout.controller.GameController;
import com.g19.breakout.model.utilities.Direction;
import com.g19.breakout.model.utilities.Position;
import com.g19.breakout.model.ArenaModel;
import com.g19.breakout.model.ElementModel;
import com.g19.breakout.model.PlayerModel;
import com.g19.breakout.view.ArenaView;
import com.g19.breakout.view.View;

public class PlayingGameState extends GameState {
    private final ArenaModel arena;
    private final ArenaView view;
    private final StateFactory stateFactory;
    private final BallController ballController;

    public PlayingGameState(ArenaView view, GameController controller, BallController ballController, StateFactory stateFactory) {
        super(controller);
        this.arena = view.getArena();
        this.view = view;
        this.stateFactory = stateFactory;
        this.ballController = ballController;
    }

    @Override
    public void update(int elapsedTime) {
        moveElement(ballController.update(elapsedTime), arena.getBall());
        updateTiles();
        if (arena.getBall().getDirection().equals(new Direction(0, 0))) gameOver();
    }

    protected void updateTiles() {
        arena.getTiles().removeIf(t -> t.getLife() == 0);
    }


    public void moveElement(Position position, ElementModel element) {
        if (arena.isInsideArena(position, element.getDimensions())) {
            element.setPosition(position);
        }
    }

    public void gameOver() {
        controller.setState(stateFactory.createGameOverGameState(controller));
    }

    @Override
    public boolean commandLeft() {
        // maybe change to where the playerbar is moved based on velocity, the longer the key is pressed the faster it moves
        PlayerModel playerBar = arena.getPlayer();
        moveElement(playerBar.getPosition().left(), playerBar);
        return true;
    }

    @Override
    public boolean commandRight() {
        PlayerModel playerBar = arena.getPlayer();
        moveElement(playerBar.getPosition().right(), playerBar);
        return true;
    }

    @Override
    public boolean commandP() {
        controller.setState(this.stateFactory.createPauseGameState(controller));
        return true;
    }

    public View getView() {
        return view;
    }

    public ArenaModel getArena() {
        return arena;
    }
}
