import com.sonicscholar.simpleBoardGame.Board;
import com.sonicscholar.simpleBoardGame.DefaultBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultBoardTest {

    private static final int defaultWidth = 3;
    private static final int defaultHeight = 4;
    /**
     * get a default 3 x 4 test board, where 3 is width, 4 is height
     * @return a com.sonicscholar.SimpleBoardGame.DefaultBoard object as a com.sonicscholar.SimpleBoardGame.Board interface
     */
    static Board getDefaultTestBoard() {
        return new DefaultBoard(defaultWidth, defaultHeight);
    }

    @Test
    void getWidth() {
        int width = 3;
        Board board = getDefaultTestBoard();
        int actual = board.getWidth();
        assertEquals(width, actual);
    }

    @Test
    void getHeight() {
        int height = 4;
        Board board = getDefaultTestBoard();
        int actual = board.getHeight();
        assertEquals(height, actual);
    }

    //mark every position on the board, then reset it.
    //make sure every space is unoccupied after reset
    @Test
    void resetBoard() {
        Board board = getDefaultTestBoard();
        for (int row=0; row < defaultHeight; row++){
            for (int col=0; col < defaultWidth; col++){
                board.markPosition(row, col, 'X');
            }
        }

        board.resetBoard();
        for (int row=0; row < defaultHeight; row++){
            for (int col=0; col < defaultWidth; col++){
                boolean isEmpty = board.isEmpty(row, col);
                assertTrue(isEmpty);
            }
        }
    }

    @Test
    void markPosition() {
        //mark 2 positions with X and O in opposite corners and check their values
        Board board = getDefaultTestBoard();
        char marker = 'X';
        board.markPosition(0,0, marker);
        marker = 'O';
        board.markPosition(defaultHeight -1, defaultWidth -1, marker);

        assertEquals('X', board.getMarkerAtPosition(0,0));
        assertEquals('O', board.getMarkerAtPosition(defaultHeight-1, defaultWidth-1));
    }

    @Test
    void markPositionWithDefaultMarkerThrowsIllegalArgumentException(){
        Board board = getDefaultTestBoard();
        char emptyMarker = DefaultBoard._defaultMarker;
        String expectedErrorMessage = "Cannot manually mark a position using" +
                " the default empty marker. Use clearPosition() instead.";
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> board.markPosition(0,0,emptyMarker));

        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void clearMarker() {
        Board board = getDefaultTestBoard();
        char marker = 'X';
        board.markPosition(0,0, marker);
        board.clearMarker(0,0);
        assertTrue(board.isEmpty(0,0));
    }

    @Test
    void isEmpty() {
        Board board = getDefaultTestBoard();
        //check positions are empty
        assertTrue(board.isEmpty(0,0));
        assertTrue(board.isEmpty(defaultHeight-1, defaultWidth-1));

        //mark these two positions
        char marker = 'X';
        board.markPosition(0,0, marker);
        board.markPosition(defaultHeight -1, defaultWidth -1, marker);

        //check that they are now occupied
        assertFalse(board.isEmpty(0,0));
        assertFalse(board.isEmpty(defaultHeight-1, defaultWidth-1));
    }

    @Test
    public void test1x1BoardToString()
    {
        Board board = new DefaultBoard(1, 1);
        String expected =
                "===" + "\r\n" +
                "| |" + "\r\n" +
                "===" + "\r\n";

        String actual = board.toString();
        assertEquals(expected, actual);

        board.markPosition(0,0, 'X');

        expected =
                "===" + "\r\n" +
                "|X|" + "\r\n" +
                "===" + "\r\n";
        actual = board.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void test2x2BoardToString()
    {
        Board board = new DefaultBoard(2, 2);
        String expected =
                "=====" + "\r\n" +
                "| | |" + "\r\n" +
                "-----" + "\r\n" +
                "| | |" + "\r\n" +
                "=====" + "\r\n";

        String actual = board.toString();
        assertEquals(expected, actual);

        board.markPosition(0, 0, 'X');
        board.markPosition(1,1, 'X');

        expected =
                "=====" + "\r\n" +
                "|X| |" + "\r\n" +
                "-----" + "\r\n" +
                "| |X|" + "\r\n" +
                "=====" + "\r\n";
        actual = board.toString();
        assertEquals(expected, actual);
    }
}