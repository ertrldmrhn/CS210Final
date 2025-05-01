package BlackJack;

public class Player extends Participant {
    private double balance = 100.0;
    private double currentBet = 0.0;

    public void placeBet(double amount) {
        if (!canAfford(amount)) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
        currentBet = amount;
    }

    public void winBet() {
        balance += currentBet * 2;
        currentBet = 0;
    }

    public void loseBet() {
        currentBet = 0;
    }

    public void draw(Card card) {
        hand.addCard(card);
    }

    @Override
    public void takeTurn(Deck deck) {
        Card c = deck.drawCard();
        draw(c);
    }

    public boolean canAfford(double amount) {
        return balance >= amount;
    }

    public double getBalance() {
        return balance;
    }
}