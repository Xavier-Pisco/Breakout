package com.g19.breakout.controller.commands.input;


import com.g19.breakout.controller.GameController;

public class CommandExit extends GameCommand {
    public CommandExit(GameController controller) {
        super(controller);
    }

    @Override
    public void execute() {
        controller.exit();
    }
}
