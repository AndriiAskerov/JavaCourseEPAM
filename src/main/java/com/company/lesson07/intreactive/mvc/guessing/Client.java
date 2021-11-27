package com.company.lesson07.intreactive.mvc.guessing;

public class Client {
    public static void main(String[] args) {
        //Game game = new Game();
        Game game = new Game(0, 100, 7);
        GameView view = new GameView();
        GameController controller = new GameController(game, view);
        controller.startTheGame();
    }
}