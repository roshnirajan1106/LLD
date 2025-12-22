import org.example.game.GameConfig;
import org.example.game.GameFactory;
import org.junit.Test;

public class GameTest {
    GameFactory gameFactory = new GameFactory();

    @Test
    public void timeOutTest(){
        Game game = gameFactory.createGame(3,120);
    }
}