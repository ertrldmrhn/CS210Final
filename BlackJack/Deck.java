
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public Class Deck {
    private List<Card> cards;
    private final List<Card> fullDeck;

    public Deck () {
    cards = new ArrayList<>();
    fullDeck = new ArrayList<>();
    
}

shuffle();
};


    public void shuffle() {
        Collections.shuffle(cards);
    
    }
    public Card dealCard() {
        if (card.size() < 10) {
            system.out.println("Deck is low on cards. Resetting and Shuffling...");
            resetandShuffle();
            
        }    
        return cards.remove(0);
       
    }

public void resetandShuffle(){
    cards = new ArrayList<>(fullDeck);
    shuffle();
}

    public int cardsRemaining() {
        return card.size();
    }
