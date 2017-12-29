public class Blackjack {
    
    public static void main(String[] args) {
        Game game = new Game(100);
        game.start();
        do {
            game.playRound();
        } while (game.getStatus());
        
    }
    
}

