package BlackJack;

public abstract class Participant {
	protected Hand hand;

    public Participant() {
        hand = new Hand();
    }

    public abstract void takeTurn(Deck deck);

    public Hand getHand() {
        return hand;
    }

    public void clearHand() {
        hand.clear();
    }
}
