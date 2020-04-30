package com.g19.breakout.controller.commands;

import com.g19.breakout.controller.ArenaController;
import com.g19.breakout.elements.Position;
import com.g19.breakout.model.PlayerBarModel;

public class CommandLeft implements Command {

    @Override
    public boolean execute(ArenaController controller) {
        // maybe change to where the playerbar is moved based on velocity, the longer the key is pressed the faster it moves
        PlayerBarModel playerBar = controller.getArena().getPlayerBar();
        controller.movePlayerBar(playerBar.getPosition().left());
        return true;
    }
}
