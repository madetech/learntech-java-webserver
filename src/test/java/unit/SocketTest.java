import org.junit.Test;

import webserver.Socket;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SocketTest {
    @Test
    public void testCallbackCalled() throws IOException, InterruptedException {
        final String[] requestBody = {null};

        Socket socket = new Socket((InputStream inputStream, OutputStream outputStream) -> {
            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder requestBodyBuilder = new StringBuilder();
            String line;
            try {
                line = input.readLine();

                while (!line.equals("")) {
                    requestBodyBuilder.append(line);
                    line = input.readLine();
                }

                requestBody[0] = requestBodyBuilder.toString();
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

            return null;
        });

        socket.listen(9090);

        URL url = new URL("http://localhost:9090");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();

        assertThat(requestBody[0], containsString("GET / HTTP/1.1"));
    }
}
