package BlackJack;

public class GameEngine {
    private final Deck deck;
    private final Player player;
    private final Dealer dealer;
    private final CLI cli;

    public GameEngine(Deck deck, Player player, Dealer dealer, CLI cli) {
        this.deck   = deck;
        this.player = player;
        this.dealer = dealer;
        this.cli    = cli;
    }

    public GameEngine() {
        this(new Deck(), new Player(), new Dealer(), new CLI());
    }

    public void startGame() {
        cli.printWelcome();
        double startingBalance = player.getBalance();
        boolean playing = true;
        while (playing) {
            playRound();
            playing = player.getBalance() > 0 && cli.askToContinue();
        }
        showFarewell();
        showProfitLoss(startingBalance, player.getBalance());
    }

    private void playRound() {
        double bet = cli.promptBet(player);
        if (!player.canAfford(bet)) {
            cli.showResult("You can't afford that bet!");
            return;
        }
        player.placeBet(bet);
        player.clearHand();
        dealer.clearHand();
        initialDeal();
        cli.showHands(player, dealer, true);
        if (handleBlackjack()) {
            return;
        }
        handlePlayerTurn();
        if (!player.getHand().isBust()) {
            handleDealerTurn();
        }
        cli.showHands(player, dealer, false);
        determineWinner();
        cli.printBalance(player);
    }

    private void initialDeal() {
        player.drawCard(deck.drawCard());
        dealer.drawCard(deck.drawCard());
        player.drawCard(deck.drawCard());
        dealer.drawCard(deck.drawCard());
    }

    private boolean handleBlackjack() {
        if (player.getHand().getTotal() == 21) {
            cli.showHands(player, dealer, false);
            int dealerTotal = dealer.getHand().getTotal();
            if (dealerTotal == 21) {
                cli.showResult("Both you and the dealer have Blackjack! It's a push.");
                player.winBet();
            } else {
                cli.showResult("Blackjack! You win!");
                player.winBlackjack();
            }
            cli.printBalance(player);
            if (player.getBalance() <= 0) {
                cli.showResult("You are out of money! Game over.");
            }
            return true;
        }
        return false;
    }

    private void handlePlayerTurn() {
        boolean continueDrawing = true;
        while (continueDrawing && !player.getHand().isBust()) {
            String move = cli.promptMove();
            if (move.equalsIgnoreCase("hit")) {
                player.drawCard(deck.drawCard());
                cli.showHands(player, dealer, true);
            } else {
                continueDrawing = false;
            }
        }
    }

    private void handleDealerTurn() {
        dealer.takeTurn(deck);
    }

    private void determineWinner() {
        if (player.getHand().isBust()) {
            cli.showResult("--- You busted! Dealer wins. ---");
            player.loseBet();
        } else if (dealer.getHand().isBust()) {
            cli.showResult("--- Dealer busted! You win! ---");
            player.winBet();
        } else {
            int playerTotal = player.getHand().getTotal();
            int dealerTotal = dealer.getHand().getTotal();
            if (playerTotal > dealerTotal) {
                cli.showResult("--- You win! ---");
                player.winBet();
            } else if (playerTotal < dealerTotal) {
                cli.showResult("--- Dealer wins! ---");
                player.loseBet();
            } else {
                cli.showResult("Push (tie)!");
                player.winBet();
            }
        }
    }

    private void showFarewell() {
        cli.showResult("Thanks for playing!");
    }

    private void showProfitLoss(double startingBalance, double finalBalance) {
        double profit = finalBalance - startingBalance;
        if (profit > 0) {
            cli.showResult(String.format("You made $%.2f profit!", profit));
        } else if (profit < 0) {
            cli.showResult(String.format("You lost $%.2f.", -profit));
        } else {
            cli.showResult("You broke even!");
        }
    }
}