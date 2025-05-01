package BlackJack;

import java.util.List;
import java.util.Scanner;

public class CLI {
    private final Scanner scanner = new Scanner(System.in);

    public void printWelcome() {
        System.out.println("=== Welcome to Blackjack ===");
    }

    public double promptBet(Player player) {
        double bet;
        do {
            System.out.printf("Your balance: $%.2f. Enter your bet: ", player.getBalance());
            bet = scanner.nextDouble();
        } while (!player.canAfford(bet) || bet <= 0);
        return bet;
    }

    public String promptMove() {
        System.out.print("Hit or Stand? (hit/stand): ");
        return scanner.next().trim().toLowerCase();
    }

    public void showHands(Player player, Dealer dealer, boolean revealDealer) {
        System.out.print("Dealer: ");
        if (revealDealer) {
            List<Card> dCards = dealer.getHand().getCards();
            dCards.forEach(c -> System.out.print("[" + c + "] "));
            System.out.println("  Total: " + dealer.getHand().getTotal());
        } else {
            System.out.print("[" + dealer.showFirstCard() + "] [hidden]");
            System.out.println();
        }

        System.out.print("Player: ");
        player.getHand().getCards()
              .forEach(c -> System.out.print("[" + c + "] "));
        System.out.println("  Total: " + player.getHand().getTotal());
    }

    public void showResult(String result) {
        System.out.println("==> " + result);
    }

    public void printBalance(Player player) {
        System.out.printf("Balance now: $%.2f%n", player.getBalance());
    }

    public boolean askToContinue() {
        System.out.print("Play again? (y/n): ");
        String ans = scanner.next().trim().toLowerCase();
        return ans.startsWith("y");
    }
}