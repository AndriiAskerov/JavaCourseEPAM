package com.company.lesson7.intreactive.mvc.guessing;

public class Client {
    public static void main(String[] args) {
        Game game = new Game(0, 100, 8);
        GameView view = new GameView();
        GameController controller = new GameController(game, view);
        controller.startTheGame();
    }
}