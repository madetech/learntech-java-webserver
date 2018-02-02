//import webserver.Webserver;
//import org.junit.Test;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.SocketTest;
//import static org.junit.Assert.*;
//
//
//public class WebserverTest {
//
//    @Test public void testOpensSocketOnPort() {
//        String publicDirectory = new File("../fixtures/homepage").getAbsolutePath();
//        Webserver server = new Webserver(publicDirectory);
//        assertTrue(available(8080));
//        server.listen(8080);
//        assertFalse(available(8080));
//
//    }
//
//    private static boolean available(int port) {
//        try (SocketTest ignored = new SocketTest("localhost", port)) {
//            return false;
//        } catch (IOException ignored) {
//            return true;
//        }
//    }
//
//    @Test public void testSendsOutput() {
//        String publicDirectory = new File("../fixtures/homepage").getAbsolutePath();
//        Webserver server = new Webserver(publicDirectory);
//        new Webserver(function (request) {
//            assertWhatever(request)
//        })
//
//        server.listen(8080)
//        send(request)
//    }
//}
