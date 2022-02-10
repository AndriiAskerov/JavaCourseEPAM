package ua.basics.lesson07.intreactive.mvc;

public class Client {
    public static void main(String[] args) {
        //Game game = new Game();
        GameModel gameModel = new GameModel(0, 100, 7);
        GameView view = new GameView();
        GameController controller = new GameController(gameModel, view);
        controller.startTheGame();
    }
}