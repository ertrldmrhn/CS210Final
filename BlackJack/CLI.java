package BlackJack;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;

public class CLI {
    private Scanner scanner;

    public CLI() {
        scanner = new Scanner(System.in);
    }

    public void printWelcome() {
        System.out.println("-- Deck shuffled --");
        System.out.println("Welcome to Blackjack!");
    }

    public double promptBet(Player player) {
        System.out.println("Your balance: $" + player.getBalance());
        
        double bet = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter your bet: $");
            try {
                bet = scanner.nextDouble();
                
                if (bet <= 0) {
                    System.out.println("Bet must be greater than zero.");
                } else if (bet > player.getBalance()) {
                    System.out.println("You cannot bet more than your current balance.");
                } else {
                    validInput = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }

        return bet;
    }

    public String promptMove() {
        System.out.print("Hit or Stay? (h/s): ");
        String input = scanner.next();
        return input.equalsIgnoreCase("h") ? "hit" : "stay";
    }

    public void showHands(Player player, Dealer dealer, boolean hideDealerSecondCard) {
    	System.out.println("\nYour Hand:");
    	printHand(player.getHand());
    	System.out.println("(Total: " + player.getHand().getTotal() + ")");

        if (hideDealerSecondCard) {
        	System.out.println("\n Dealer's Hand:");
            Card firstCard = dealer.showFirstCard();
            System.out.println(firstCard + " [Hidden]");
        } else {
        	System.out.println("\n Dealer's Hand:");
            System.out.println(dealer.getHand() + " (Total: " + dealer.getHand().getTotal() + ")");
        }
    }
    
    public void printHand(Hand hand) {
    	List<Card> cards = hand.getCards();
        if (cards.isEmpty()) {
            System.out.println("[empty hand]");
            return;
        }

        // Each card will have 5 lines
        StringBuilder[] lines = new StringBuilder[5];
        for (int i = 0; i < 5; i++) {
            lines[i] = new StringBuilder();
        }

        for (Card card : cards) {
            String[] cardVisual = card.getCardVisual();
            for (int i = 0; i < 5; i++) {
                lines[i].append(cardVisual[i]).append(" ");
            }
        }

        for (StringBuilder line : lines) {
            System.out.println(line.toString());
        }
    }

    public void showResult(String result) {
        System.out.println(result);
    }

    public void printBalance(Player player) {
        System.out.println("Balance: $" + player.getBalance());
    }

    public boolean askToContinue() {
        System.out.print("Play another round? (y/n): ");
        String input = scanner.next();
        return input.equalsIgnoreCase("y");
    }
}
