
public class DemoGame extends Game {

	public DemoGame() {
		GameState welcome = new WelcomeState();
		GameState play = new PlayState();
		GameState end = new EndGameState();
		stateMachine.installState("Play", play);
		stateMachine.installState("Welcome", welcome);
		stateMachine.installState("End", end);
		stateMachine.setStartState(welcome);
	}
	
	public static void main( String[] args ) {
	    Game app = new DemoGame();
	    app.setTitle( "A Simple Game" );
	    app.setVisible( true );
	    app.run();
	    System.exit( 0 );
	  }
}
