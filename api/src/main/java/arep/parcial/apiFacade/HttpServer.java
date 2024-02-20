package arep.parcial.apiFacade;

import java.net.*;
import java.nio.file.Path;
import java.io.*;

public class HttpServer {
    public static void startServer() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(36000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 36000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        boolean bandera = true;
        while (bandera) {
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();

            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            
            boolean path = false;
            while ((inputLine = in.readLine()) != null) {
               
                System.out.println("Recibí: " + inputLine);
                if (inputLine.contains("/calculadora ")) {
                    path = true;
                }
                if (!in.ready()) {
                    break;
                }
            }

            if (!path) {

                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "<head>\n"
                        + "<meta charset=\"UTF-8\">\n"
                        + "<title>Title of the document</title>\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "<h1>Mi propio mensaje</h1>\n"
                        + "</body>\n"
                        + "</html>\n";

            } else {
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "<head>\n"
                        + "<meta charset=\"UTF-8\">\n"
                        + "<title>Fachada</title>\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "<h1>Form with GET FACHADA</h1>\n"
                        + "<form action=\"/hello\">\r\n" + //
                        "                        <label for=\"name\">Write the Operation:</label>\r\n" + //
                        "                        <input type=\"text\" id=\"name\" name=\"name\" placeholder=\"Operation\"><br><br>\r\n"
                        + //
                        "                        <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\r\n"
                        + //
                        "                    </form> \r\n" + //
                        "                    <div id=\"getrespmsg\"></div>\r\n" + //
                        "            \r\n" + //
                        "                    <script>\r\n" + //
                        "                        function loadGetMsg() {\r\n" + //
                        "                            let nameVar = document.getElementById(\"name\").value;\r\n" + //
                        "                            const xhttp = new XMLHttpRequest();\r\n" + //
                        "                            xhttp.onload = function() {\r\n" + //
                        "                                document.getElementById(\"getrespmsg\").innerHTML =\r\n" + //
                        "                                this.responseText;\r\n" + //
                        "                            }\r\n" + //
                        "                            xhttp.open(\"GET\", \"/computar?comando=\"+operation\"&val=\"double);\r\n" + //
                        "                            xhttp.send();\r\n" + //
                        "                        }\r\n" + //
                        "                    </script>"
                        + "</body>\n"
                        + "</html>\n";

            }
            out.println(outputLine);
            out.close();
            in.close();
        }

        clientSocket.close();
        serverSocket.close();
    }

}