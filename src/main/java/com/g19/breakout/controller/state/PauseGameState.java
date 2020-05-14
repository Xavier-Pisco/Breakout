package com.g19.breakout.controller.state;

import com.g19.breakout.controller.GameController;
import com.g19.breakout.controller.MenuController;
import com.g19.breakout.elements.Chronometer;
import com.g19.breakout.model.PlayerModel;
import com.g19.breakout.view.PauseView;

public class PauseGameState implements GameState {
    private final PlayingGameState playingGameState;
    private final PauseView view;
    private final GameController controller;
    private final StateFactory stateFactory;
    private final PlayerModel playerModel;
    private final MenuController menu;

    PauseGameState(PlayingGameState playingGameState, PauseView view, GameController controller, MenuController menu, StateFactory stateFactory) {
        this.playingGameState = playingGameState;
        this.controller = controller;
        this.view = view;
        this.stateFactory = stateFactory;
        this.playerModel = view.getPlayerModel();
        this.menu = menu;
    }

    public boolean commandL() {
        controller.moveElement(playerModel.getPosition().left(), playerModel);
        return true;
    }

    public boolean commandR() {
        controller.moveElement(playerModel.getPosition().right(), playerModel);
        return true;
    }

    public boolean commandP() {
        controller.setState(playingGameState, new Chronometer());
        return true;
    }

    public boolean commandQ() {
        controller.setState(stateFactory.createMainMenuGameState(controller), new Chronometer());
        return true;
    }

    public boolean commandENTER() {
        menu.getCommand(playerModel.getPosition()).execute(controller);
        return true;
    }

    public PauseView getView() {
        return view;
    }
}
