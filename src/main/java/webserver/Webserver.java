/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Webserver {
    private String path;
    public ServerSocket socket;

    public Webserver(String path) {
        this.path = path;
    }

    public void listen(Integer port)  {
        new Thread(() -> {
            try {
                ServerSocket socket = new ServerSocket(port);
                socket.setReuseAddress(true);
                Socket clientSocket = socket.accept();
                OutputStream outputStream = clientSocket.getOutputStream();
                InputStream inputStream = clientSocket.getInputStream();
                BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
                BufferedWriter output = new BufferedWriter(new OutputStreamWriter(outputStream));


                String line = input.readLine();
                while(!line.equals("")) {
                    line = input.readLine();
                }

                output.write("HTTP/1.1 200 OK\n" +
                        "Date: Fri, 26 Jan 2018 14:16:02 GMT\n" +
                        "Content-Type: text/html; charset=ISO-8859-1\n" +
                        "Content-Length: 30\n" +
                        "\n" +
                        "<article>Hello World</article>"
                );

                output.flush();
                output.close();
            }

            catch (IOException e) {
                System.out.println(e);
            }
        }).start();
    }
}

//socket.on(function (input, output) {
//    request = parseInput(input)
//        handler(request, response)
//        })
//
//parse(httpRequestBody) => { headers: { "Date": "" }, body: "" }
//format({ status: 200, body: "" }) => httpResponseBody
//
//
//
//
//webserver = new Webserver()
//
//webserver.get('/test.html', function (request, response) {
//    response.send(file.open('test.html'))
//        })
//
//webserver.listen(8080)