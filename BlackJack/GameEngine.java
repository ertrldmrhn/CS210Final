package BlackJack;
// File: GameEngine.java
public class GameEngine {
    private final Deck deck = new Deck();
    private final Player player = new Player();
    private final Dealer dealer = new Dealer();
    private final CLI cli = new CLI();

    public static void main(String[] args) {
        new GameEngine().startGame();
    }

    public void startGame() {
        cli.printWelcome();
        do {
            deck.shuffle();
            player.resetHand();
            dealer.resetHand();

            double bet = cli.promptBet(player);
            player.placeBet(bet);

            // === CORRECT INITIAL DEAL ===
            // Deal two cards each, alternating player and dealer
            player.draw(deck.drawCard());
            dealer.getHand().addCard(deck.drawCard());

            player.draw(deck.drawCard());
            dealer.getHand().addCard(deck.drawCard());
            // =============================

            // show initial hands (dealer's second card hidden)
            cli.showHands(player, dealer, false);

            // Player's turn
            while (!player.getHand().isBust()) {
                String move = cli.promptMove();
                if ("hit".equals(move)) {
                    player.draw(deck.drawCard());
                    cli.showHands(player, dealer, false);
                } else if ("stand".equals(move)) {
                    break;
                } else {
                    System.out.println("Invalid—type 'hit' or 'stand'.");
                }
            }

            // Dealer's turn if player didn't bust
            if (!player.getHand().isBust()) {
                cli.showHands(player, dealer, true);
                dealer.takeTurn(deck);
            }

            // Reveal and decide
            cli.showHands(player, dealer, true);
            String result;
            if (player.getHand().isBust()) {
                player.loseBet();
                result = "You busted. You lose.";
            } else if (dealer.getHand().isBust()) {
                player.winBet();
                result = "Dealer busted! You win!";
            } else {
                int pTotal = player.getHand().getTotal();
                int dTotal = dealer.getHand().getTotal();
                if (pTotal > dTotal) {
                    player.winBet();
                    result = "You win!";
                } else if (pTotal < dTotal) {
                    player.loseBet();
                    result = "You lose.";
                } else {
                    // push: return bet
                    player.placeBet(0); // no net change
                    result = "Push (tie).";
                }
            }

            cli.showResult(result);
            cli.printBalance(player);

        } while (cli.askToContinue());

        System.out.println("Thanks for playing. Goodbye!");
    }
}