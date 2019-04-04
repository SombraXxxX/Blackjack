import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class Blackjack {
	static Scanner in = new Scanner(System.in); 
	public static void main(String args[]) {
		System.out.println("Let's play Blackjack!"); 
		System.out.println("What is your name?");
		String name = in.nextLine(); 
		
		Deck playingDeck = new Deck(); 
		playingDeck.createFullDeck(); 
		playingDeck.shuffle(); 
		
		Deck playerDeck = new Deck();
		
		Deck dealerDeck = new Deck(); 
		
		//TODO: Add chips and replace money with them
		int playerChips = 100; 
		
		while (playerChips > 0) {
			System.out.println("You have " + playerChips + " chips. How much will you bet?");
			double playerBet = in.nextDouble(); 
			if (playerBet > playerChips) {
				System.out.println("You cannot bet anymore than you have."); 
				break; 
			}
			else if (playerBet == playerChips) {
				System.out.println("Thanks for playing!"); 
				System.exit(0);
			}
			
			boolean endRound = false; 
			
			playerDeck.draw(playingDeck);
			playerDeck.draw(playingDeck);
			
			dealerDeck.draw(playingDeck);
			dealerDeck.draw(playingDeck);
			
			while (true) {
				System.out.println("Your hand: ");
				System.out.println(playerDeck.toString()); 
				System.out.println("Your hand is valued at: " + playerDeck.cardsValue());
				
				System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString() + " and [Hidden]");
				
				System.out.println("Would you like to (1) Hit or (2) Stand?");
				int response = in.nextInt();
				
				if (response == 1) {
					playerDeck.draw(playingDeck);
					System.out.println("You drew a " + playerDeck.getCard(playerDeck.deckSize() - 1).toString());
					
					if (playerDeck.cardsValue() > 21) {
						System.out.println("Bust. Valued at " + playerDeck.cardsValue()); 
						playerChips -= playerBet;
						endRound = true; 
					}
				}
				
				if (response == 2) {
					break;
				}
			}
			
			System.out.println("Dealer's Cards: " + dealerDeck.toString()); 
			
			if ((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false) {
				System.out.println("Dealer beats you!"); 
				playerChips -= playerBet;
				endRound = true; 
			}
			
			while ((dealerDeck.cardsValue() < 17) && endRound == false) {
				dealerDeck.draw(playingDeck);
				System.out.println("Dealer Draws " + dealerDeck.getCard(dealerDeck.deckSize() - 1).toString());
			}
			
			System.out.println("Dealer's Hand is valued at " + dealerDeck.cardsValue()); 
			
			if ((dealerDeck.cardsValue() > 21) && endRound == false) {
				System.out.println("Dealer busts! You win!"); 
				playerChips += playerBet;
				endRound = true; 
			}
			if ((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false) {
				System.out.println("Push");
				endRound = true; 
			}
			
			if ((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false) {
				System.out.println("You win the hand!");
				playerChips += playerBet;
				endRound = true; 
			}
			else if (endRound == false) {
				System.out.println("You lost"); 
				playerChips -= playerBet;
				endRound = true; 
			}
			
			playerDeck.moveAllToDeck(playingDeck);
			dealerDeck.moveAllToDeck(playingDeck); 
			System.out.println("End of hand"); 
		}
		System.out.println("No more money left."); 
	}
}
