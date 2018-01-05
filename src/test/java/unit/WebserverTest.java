import Webserver.Webserver;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import static org.junit.Assert.*;


public class WebserverTest {

    @Test public void testOpensSocketOnPort() {
        String publicDirectory = new File("../fixtures/homepage").getAbsolutePath();
        Webserver server = new Webserver(publicDirectory);
        assertTrue(available(8080));
        server.listen(8080);
        assertFalse(available(8080));

    }

    private static boolean available(int port) {
        try (Socket ignored = new Socket("localhost", port)) {
            return false;
        } catch (IOException ignored) {
            return true;
        }
    }
}