package BlackJack;

public class Dealer extends Participant {
    public Dealer() {
        super();
    }

    @Override
    public void takeTurn(Deck deck) {
        while (hand.getTotal() < 17) {
            drawCard(deck.drawCard()); // now this works
        }
    }

    public Card showFirstCard() {
        if (!hand.getCards().isEmpty()) {
            return hand.getCards().get(0);
        }
        return null;
    }

    public void drawCard(Card card) {
        hand.addCard(card);
    }
}
