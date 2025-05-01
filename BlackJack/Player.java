package BlackJack;

public class Player extends Participant {
	private double balance;
    private double currentBet;

    public Player() {
        super();
        this.balance = 250; 
    }

    public void placeBet(double amount) {
        this.currentBet = amount;
        this.balance -= amount;
    }

    public void winBet() {
        this.balance += currentBet * 2;
    }

    public void loseBet() {
    }
    
    public void winBlackjack() {
        this.balance += currentBet * 2;
    }

    public void drawCard(Card card) {
        hand.addCard(card);
    }

    public boolean canAfford(double amount) {
        return balance >= amount;
    }

    public double getBalance() {
        return balance;
    }
    
    @Override
    public void takeTurn(Deck deck) {
    }
}
