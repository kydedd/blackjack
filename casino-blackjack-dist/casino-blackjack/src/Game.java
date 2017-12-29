import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    
    private boolean status;
    private int roundCounter;
    private int playerMoney;
    private int playerBet;
    boolean roundOver;
    boolean playerDone;
    private Deck deck;
    private Scanner scan;
    private ArrayList<Card> dealerCards;
    private ArrayList<Card> playerCards;
    
    public Game(int startingMoney) {
        status = true;
        roundCounter = 0;
        playerMoney = startingMoney;
        playerDone = false;
        deck = new Deck();
        scan = new Scanner(System.in);
        dealerCards = new ArrayList<Card> ();
        playerCards = new ArrayList<Card> ();
    }
    
    public boolean getStatus() {
        return status;
    }
    public void start() {
        System.out.println("Blackjack!");
    }
    public void playRound() {
        roundCounter++;
        System.out.println("\n--------------------- Round #" + roundCounter + " ---------------------");
        placeBet();
        dealerCards.clear();
        playerCards.clear();
        playerDone = false;
        playerCards.add(deck.getCard()); // order cards are dealt?
        dealerCards.add(deck.getCard());
        playerCards.add(deck.getCard());
        dealerCards.add(deck.getCard());
        displayCards();
        roundOver = false;
        do {
            playerAction();
        } while (!roundOver);
        System.out.println("\n--------------------- Round #" + roundCounter + " ---------------------");
        if (playerMoney < 1) {
            System.out.println("\nYou have $" + playerMoney);
            status = false;
        }
    }
    
    private void placeBet() {
        System.out.println("You have $" + playerMoney);
        do {
            System.out.print("Place your bet: $");
            playerBet = scan.nextInt(); // **InputMismatchException**
        } while ((playerBet <= 0) || (playerBet > playerMoney));
    }
    private void displayCards() {
        System.out.print("\nDealer: " + dealerCards.get(0));
        int playerCount = getCount(playerCards);
        if (!playerDone) {
            System.out.print("[?]");
            System.out.print("\n>> You: ");
            for (int i = 0; i < (playerCards.size()); i++) {
                System.out.print(playerCards.get(i));
            }
            if (playerCount > 21) {
                System.out.print("<< BUST");
                playerMoney -= playerBet;
                roundOver = true;
            }
        } else {
            for (int i = 1; i < (dealerCards.size()); i++) {
                System.out.print(dealerCards.get(i));
            }
            System.out.print("\n   You: ");
            for (int i = 0; i < (playerCards.size()); i++) {
                System.out.print(playerCards.get(i));
            }
            int dealerCount = getCount(dealerCards);
            if ((dealerCount > 21) || (playerCount > dealerCount)) {
                System.out.print("<< WIN");
                playerMoney += playerBet;
            } else if (playerCount < dealerCount) {
                System.out.print("<< LOSE");
                playerMoney -= playerBet;
            } else {
                System.out.print("<< PUSH");
            }
            roundOver = true;
        }
        
    }
    private void playerAction() {
        char action;
        do {
            System.out.print("\n>> (H|S|D): ");
            action = scan.next().charAt(0);
            switch (action) {
                case 'H':
                    playerCards.add(deck.getCard());
                    break;
                case 'S':
                    getDealerCards();
                    break;
                case 'D':
                    playerBet *= 2;
                    playerCards.add(deck.getCard());
                    getDealerCards();
                    break;
            }
        } while ((action != 'H') && (action != 'S') && (action != 'D'));
        displayCards();
    }
    private void getDealerCards() {
        playerDone = true;
        boolean moreCards = true;
        int count;
        do {
            count = getCount(dealerCards);
            if (count > 16) { // dealer stands on soft 17
                moreCards = false;
            } else {
                dealerCards.add(deck.getCard());
            }
        } while (moreCards);
    }
    private int getCount(ArrayList<Card> cards) {
        int count = 0;
        int aces = 0;
        int rank;
        for (int i = 0; i < (cards.size()); i++) {
            rank = cards.get(i).getRank();
            if (rank < 11) {
                count += rank;
            } else if (rank < 14) {
                count += 10;
            } else {
                count += 11;
                aces++;
            }
        }
        while ((count > 21) && (aces > 0)) {
            count -= 10;
            aces--;
        }
        return count;
    }
    
}




















