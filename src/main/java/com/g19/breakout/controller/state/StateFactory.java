package com.g19.breakout.controller.state;

import com.g19.breakout.controller.*;
import com.g19.breakout.controller.commands.input.CommandL;
import com.g19.breakout.controller.commands.input.CommandP;
import com.g19.breakout.controller.commands.input.CommandQ;
import com.g19.breakout.model.utilities.Dimensions;
import com.g19.breakout.model.utilities.Position;
import com.g19.breakout.model.ArenaModel;
import com.g19.breakout.model.PlayerModel;
import com.g19.breakout.view.*;
import com.g19.breakout.view.factory.ViewFactory;

public class StateFactory {
    public GameState createMainMenuGameState(GameController controller) {
        Dimensions gameDimensions = controller.getModel().getDimensions();
        ViewFactory viewFactory = controller.getView().getViewFactory();
        PlayerModel playerModel = controller.getModel().getModelFactory().createPlayerModel(
                new Position(gameDimensions.getDiscreteX()/2., gameDimensions.getDiscreteY() - 4));

        MenuView menuView = viewFactory.createMenuView(
                controller.getView().getGraphics(),
                new Dimensions(gameDimensions.getDiscreteX(), gameDimensions.getDiscreteY()/2.),
                new Position(0, gameDimensions.getDiscreteY() - gameDimensions.getDiscreteY()/2.)
        );
        MenuController menu = new MenuController(gameDimensions, menuView);

        menu.addButton(new CommandP(controller), viewFactory.createMenuButtonView("Start Game (P)", "#1da50b", controller.getView().getGraphics()));
        menu.addButton(new CommandL(controller), viewFactory.createMenuButtonView("Leaderboard (L)", "#e0b20b", controller.getView().getGraphics()));
        menu.addButton(new CommandQ(controller), viewFactory.createMenuButtonView("Quit Game (Q)", "#a30d0d", controller.getView().getGraphics()));

        MainMenuView view = viewFactory.createMainMenuView(controller.getView().getGraphics(), controller.getModel().getDimensions());
        view.addView(menuView);
        view.addView(viewFactory.createPlayerView(playerModel, controller.getView().getGraphics()));

        return new MainMenuGameState(controller, view, menu, playerModel, this);
    }

    public GameState createLeaderboardState(GameController gameController) {
        Dimensions gameDimensions = gameController.getModel().getDimensions();
        ViewFactory viewFactory = gameController.getView().getViewFactory();
        PlayerModel playerModel = gameController.getModel().getModelFactory().createPlayerModel(
                new Position(gameDimensions.getDiscreteX()/2., gameDimensions.getDiscreteY() - 4));

        MenuView menuView = viewFactory.createMenuView(
                gameController.getView().getGraphics(),
                new Dimensions(gameDimensions.getDiscreteX(), gameDimensions.getDiscreteY()/5.),
                new Position(0, gameDimensions.getDiscreteY() - gameDimensions.getDiscreteY()/5.)
        );
        MenuController menu = new MenuController(gameDimensions, menuView);
        menu.addButton(new CommandQ(gameController), viewFactory.createMenuButtonView("Return (Q)", "#a30d0d", gameController.getView().getGraphics()));

        LeaderboardView view = viewFactory.createLeaderboardView(gameController.getView().getGraphics(), gameController.getModel().getDimensions(), gameController.getModel().getLeaderboard());
        view.addView(menuView);
        view.addView(viewFactory.createPlayerView(playerModel, gameController.getView().getGraphics()));

        return new LeaderboardGameState(gameController, view, menu, playerModel, this);
    }

    public GameState createPlayingGameState(GameController gameController) {
        ArenaModel arena = gameController.getModel().getModelFactory().createArenaModel(gameController.getModel().getDimensions());
        ArenaView arenaView = gameController.getView().getViewFactory().createArenaView(arena, gameController.getView().getGraphics());

        TilesController tilesController = new TilesController(arena.getTiles(), new Chronometer());

        return new PlayingGameState(arenaView, gameController, new BallController(new BallCollisionChecker(arena, tilesController)), tilesController, this);
    }

    public GameState createPauseGameState(GameController controller) {
        Dimensions gameDimensions = controller.getModel().getDimensions();

        PlayingGameState playingGameState = (PlayingGameState) controller.getState();
        ViewFactory viewFactory = controller.getView().getViewFactory();

        Position playerPosition = new Position(playingGameState.getArena().getPlayer().getPosition().getDiscreteX(), gameDimensions.getDiscreteY() - 4);
        PlayerModel playerModel = controller.getModel().getModelFactory().createPlayerModel(playerPosition);

        PauseView pauseView = viewFactory.createPauseView(controller.getView().getGraphics(), gameDimensions);

        MenuView menuView = viewFactory.createMenuView(
                controller.getView().getGraphics(),
                new Dimensions(gameDimensions.getDiscreteX(), gameDimensions.getDiscreteY()/2.),
                new Position(0, gameDimensions.getDiscreteY() - gameDimensions.getDiscreteY()/2.)
        );
        MenuController menu = new MenuController(gameDimensions, menuView);

        menu.addButton(new CommandP(controller), viewFactory.createMenuButtonView("Resume Game (P)", "#1da50b", controller.getView().getGraphics()));
        menu.addButton(new CommandQ(controller), viewFactory.createMenuButtonView("Give Up (Q)", "#a30d0d", controller.getView().getGraphics()));

        pauseView.addView(menu.getView());
        pauseView.addView(viewFactory.createPlayerView(playerModel, controller.getView().getGraphics()));

        return new PauseGameState(playingGameState, playerModel, pauseView, controller, menu, this);
    }

    public GameState createGameOverGameState(GameController controller, boolean lost) {
        Dimensions gameDimensions = controller.getModel().getDimensions();

        PlayingGameState playingGameState = (PlayingGameState) controller.getState();
        ViewFactory viewFactory = controller.getView().getViewFactory();


        PlayerModel playerModel = playingGameState.getArena().getPlayer();
        Position playerPosition = new Position(playerModel.getPosition().getDiscreteX(), gameDimensions.getDiscreteY() - 4);
        playerModel.setPosition(playerPosition);


        GameOverView gameOverView = viewFactory.createGameOverView(controller.getView().getGraphics(), gameDimensions, playerModel, lost);

        MenuView menuView = viewFactory.createMenuView(
                controller.getView().getGraphics(),
                new Dimensions(gameDimensions.getDiscreteX(), gameDimensions.getDiscreteY()/2.),
                new Position(0, gameDimensions.getDiscreteY() - gameDimensions.getDiscreteY()/2.)
        );
        MenuController menu = new MenuController(gameDimensions, menuView);

        menu.addButton(new CommandP(controller), viewFactory.createMenuButtonView("Play Again (P)", "#1da50b", controller.getView().getGraphics()));
        menu.addButton(new CommandQ(controller), viewFactory.createMenuButtonView("Give Up (Q)", "#a30d0d", controller.getView().getGraphics()));

        gameOverView.addView(menu.getView());
        gameOverView.addView(viewFactory.createPlayerView(playerModel, controller.getView().getGraphics()));

        return new GameOverGameState(playerModel, gameOverView, controller, menu, this, new TextReader());
    }
}
