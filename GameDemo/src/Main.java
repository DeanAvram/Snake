public class Main extends Game {

	public Main() {
		GameState welcome = new WelcomeState();
		GameState play = new PlayState();
		GameState end = new EndGameState();
		stateMachine.installState("Play", play);
		stateMachine.installState("Welcome", welcome);
		stateMachine.installState("End", end);
		stateMachine.setStartState(welcome);
	}
	
	public static void main( String[] args ) {
	    Game app = new Main();
	    app.setTitle( "Snake" );
	    app.setVisible( true );
	    app.run();
	    System.exit( 0 );
	  }
}
