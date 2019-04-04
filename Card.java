public class Card implements Comparable<Card>{
	public Suit suit; 
	public Value value;
	
	public Card(Suit suit, Value value) {
		this.suit = suit;
		this.value = value; 
	}
	
	public String toString() {
		return this.suit.toString() + "-" + this.value.toString(); 
	}
	
	public Value getValue() {
		return this.value;
	}
	
	public Suit getSuit() {
		return this.suit; 
	}

	@Override
	public int compareTo(Card arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}