package BlackJack;

public class Dealer extends Participant {
    @Override
    public void takeTurn(Deck deck) {
        while (hand.getTotal() < 17) {
            hand.addCard(deck.drawCard());
        }
    }

    public Card showFirstCard() {
        return hand.getCards().get(0);
    }
}


