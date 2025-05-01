package BlackJack;

public class Card {
    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getValue() {
        switch (rank) {
            case TWO: return 2;
            case THREE: return 3;
            case FOUR: return 4;
            case FIVE: return 5;
            case SIX: return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE: return 9;
            case TEN:
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            case ACE:
                return 11;
            default:
                return 0;
        }
    }

    public String[] getCardVisual() {
        String rankStr = rank.toString();
        String rankDisplay = (rankStr.equals("10")) ? rankStr : rankStr + " ";
        String suitSymbol = getSuitSymbol();

        return new String[]{
            "|" rankDisplay + suitSymbol + " |",
        };
    }

    private String getSuitSymbol() {
        switch (suit) {
            case HEARTS: return "♥";
            case DIAMONDS: return "♦";
            case CLUBS: return "♣";
            case SPADES: return "♠";
            default: return "?";
        }
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    public Rank getRank() {
        return rank;
    }
}
