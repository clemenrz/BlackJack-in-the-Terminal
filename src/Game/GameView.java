package Game;

import CardsAndDecks.Card;
import CardsAndDecks.Deck;
import Player.Dealer;
import Player.Player;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameView {

    private String gameName = "Generic Blackjack 1v1";
    private Boolean running;
    private int bet = 0;
    private char actionOption;
    private Dealer dealer = new Dealer();
    private Player player = new Player("Player1");
    Deck deck = new Deck();

    Scanner sc = new Scanner(System.in);

    //TODO FILL THE GAME COMMANDS HERE
    public void play() {

        deck.populateDeck();
        dealer.getDeckReady();

        helloAndName();
        ruleExplanation();
        gameBegins();
        offerBetting();
        cardDealing();
        performAction();

    }//Play ends    

    public void declareWinner() {

    }//Declare winner ends

    public void declareLooser() {

    }// Declare looser ends

    /**
     * *
     * getter and Setters
     *
     * @return
     */
    public Boolean getRunning() {
        return running;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * *
     * Getter and setters end
     */
    /**
     * Game voice lines interactions and methods
     */
    public void helloAndName() {
        System.out.println("Greetings player, my name is Dealy, and I will be your dealer of cards");
        System.out.print("What's your name?\nMy name is: ");
        String playerName = sc.nextLine();
        player.setName(playerName);
        System.out.println("Nice to meet you " + player.getName());
    }

    public void ruleExplanation() {
        System.out.println("Would you like me to explain to you the rules of the game?\n(Y)ES    or    (N)O");
        String decision = sc.nextLine();
        if (decision.charAt(0) == 'Y' || decision.charAt(0) == 'y') {
            try {
                System.out.println(rulesText());
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String rulesText() {

        return "1. The main goal of BlackJack is to beat the dealer's cards with your own.\n"
                + "2. To beat the dealer, you must not go over 21 and either outscore the dealer or have him bust.\n"
                + "3. At the start of the round the dealer will give out a card to the every player including themselves.\n"
                + "4. From this point the player can hit or stand(Once per each round).\n"
                + "5. Choosing 'Hit', will add another card to get closer to 21,\n"
                + " choosing 'Stand', will make you not draw a card\n"
                + "6. Each Card has it's own value, the numbered cards have face value while suited cards are worth 10.";
    }

    public void gameBegins() {
        System.out.println("\nBefore we begin, here is the initial balance for you to start betting:");
        System.out.println(player.getName() + " received " + player.getMoney());
        System.out.println("\nLet the game begin!\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void offerBetting() {
        System.out.println("How much would you like to bet this round");
        for (;;) { //The funky "for"
            System.out.print("Bet amount: ");
            try {
                bet = sc.nextInt();
                if (bet <= 0 || bet > player.getMoney()) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Why would you even try to bet negative money!");
                sc.nextLine();
                continue;
            }
        }
    }

    public void cardDealing() {
        System.out.println("\nAlright, here are your cards");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Code to add a random card to hand
        player.addToHand(dealer.giveRandomCard());
        player.addToHand(dealer.giveRandomCard());
        System.out.println(player.getHand().toString());
    }

    public void performAction() {
        System.out.println("Would you like to (H)IT or (S)TAND?");
        while (true) {
            String continueAction = sc.next();
            actionOption = Character.toUpperCase(continueAction.charAt(0));

            switch (actionOption) {
                case 'H':
                    this.player.hit(this.dealer.giveRandomCard());
                    System.out.println(player.getName() + " hits!");
                    break;

                case 'S':
                    this.player.stand();
                    break;

                default:
                    System.out.println("Thats not a valid input");
                    continue;
            }
            this.dealer.evaluateMove();
        }
    }
    //TODO
    //Insert the action methods from player above.
}
