import java.util.Random;

public class Deck {
    
    private Card[] cards;
    private int index;
    private Random rand;
    
    public Deck() {
        rand = new Random();
        cards = new Card[52];
        for (int i = 0, r = 2; r < 15; i += 4, r++) {
            cards[i] = new Card(r, Suit.HEARTS);
            cards[i + 1] = new Card(r, Suit.DIAMONDS);
            cards[i + 2] = new Card(r, Suit.CLUBS);
            cards[i + 3] = new Card(r, Suit.SPADES);
        }
        shuffle();
    }
    
    public Card getCard() {
        if (index > 51) {
            shuffle();
        }
        return cards[index++]; // **will shuffle cards currently dealt**
    }
    public void shuffle() {
        int r;
        Card temp;
        for (int i = 0; i < 52; i++) {
            r = rand.nextInt(52);
            temp = cards[r];
            cards[r] = cards[i];
            cards[i] = temp;
        }
        index = 0;
    }
    
}

