public class Card {
    
    private static final int JACK = 11;
    private static final int QUEEN = 12;
    private static final int KING = 13;
    private static final int ACE = 14;
    
    private int rank;
    private Suit suit;
    
    public Card(int rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }
    
    public void set(int rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }
    public int getRank() {
        return rank;
    }
    public Suit getSuit() {
        return suit;
    }
    public String toString() {
        String name = "[";
        if (rank <= 10) {
            name = name.concat(Integer.toString(rank));
        } else {
            switch (rank) {
                case JACK:
                    name = name.concat("J");
                    break;
                case QUEEN:
                    name = name.concat("Q");
                    break;
                case KING:
                    name = name.concat("K");
                    break;
                case ACE:
                    name = name.concat("A");
            }
        }
        switch (suit) {
            case HEARTS:
                name += (char)3;
                break;
            case DIAMONDS:
                name += (char)4;
                break;
            case CLUBS:
                name += (char)5;
                break;
            case SPADES:
                name += (char)6;
        }
        name = name.concat("] ");
        return name;
    }
    
}

