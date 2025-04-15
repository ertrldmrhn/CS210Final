
class GameEngine {
    private Deck deck;
    private Player player;
    private Dealer dealer;
    private CLI cli;

    public GameEngine() {
        this.deck = new Deck();
        this.player = new Player();
        this.dealer = new Dealer();
        this.cli = new CLI();
    }
    public void startGame(){
        
    }
}