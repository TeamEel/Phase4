package eel.seprphase2.TextInterface;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author David
 */
public class TerminalRendererTest {

    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Test of output method, of class TerminalRenderer.
     */
    @Test
    public void testOutput() {
        TerminalRenderer tr = new TerminalRenderer();
        tr.outputLine("Test message");
        assertEquals("Test message" + System.getProperty("line.separator"), outContent.toString());
    }
}
