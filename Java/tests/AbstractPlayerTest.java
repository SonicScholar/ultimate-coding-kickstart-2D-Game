import com.sonicscholar.simpleBoardGame.*;
import com.sonicscholar.simpleBoardGame.mock.MockPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractPlayerTest {

    @Test
    void getName() {
        Player p = new MockPlayer("Jimmy", 'X', null);
        assertEquals("Jimmy", p.getName());
    }

    @Test
    void getMarker() {
        Player p = new MockPlayer("Jimmy", 'X', null);
        assertEquals('X', p.getMarker());
    }

    @Test
    void getActionOnFullBoardIsNull(){
        Board board = new DefaultBoard(1,1);
        Player p = new MockPlayer("Hank", 'X', board);
        board.markPosition(0,0,'O');
        GameAction action = p.requestAction();
        assertNull(action);
    }
}