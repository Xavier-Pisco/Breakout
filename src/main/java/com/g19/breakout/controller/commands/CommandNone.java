package com.g19.breakout.controller.commands;

import com.g19.breakout.controller.GameController;

public class CommandNone implements Command {

    @Override
    public boolean execute(GameController controller) {
        return true;
    }
}
