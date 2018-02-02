import org.junit.Test;

import static org.junit.Assert.*;

import webserver.Socket;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SocketTest {
    @Test public void testCallbackCalled() throws IOException, InterruptedException {
        StringBuilder requestBody = new StringBuilder();

        Socket socket = new Socket((InputStream inputStream, OutputStream outputStream) -> {
            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));

            String line = null;
            try {
                line = input.readLine();
                while(!line.equals("")) {
                    requestBody.append(line);
                    line = input.readLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedWriter response = new BufferedWriter(new OutputStreamWriter(outputStream));

            try {
                response.write("THIS IS YOUR RESPONSE");
                response.flush();
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            synchronized (requestBody) {
                requestBody.notify();
            }

            return null;
        });

        socket.listen(9090);

        URL url = new URL("http://localhost:9090");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();

        synchronized (requestBody) {
            requestBody.wait();
            assertThat(requestBody.toString(), containsString("GET / HTTP/1.1"));
        }
    }
}
