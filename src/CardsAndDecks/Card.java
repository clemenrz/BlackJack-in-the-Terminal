package CardsAndDecks;

/**
 * Card class
 *
 * @author Rzez, Marcelo, Uzair, Hannah April 2022
 */

public class Card {
    
    CardSuits suit;
    CardValues value;
    
    public Card(CardValues value, CardSuits suit){
        this.value = value;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "The card is a " + this.value + " of " + this.suit;
    }

    public CardValues getValue() {
        return this.value;
    }
    
}
