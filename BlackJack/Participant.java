package BlackJack;

public abstract class Participant {
    protected final Hand hand = new Hand();

    public abstract void takeTurn(Deck deck);

    public Hand getHand() {
        return hand;
    }

    public void resetHand() {
        hand.clear();
    }
}