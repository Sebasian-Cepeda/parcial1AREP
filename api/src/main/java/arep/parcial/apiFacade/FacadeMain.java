package arep.parcial.apiFacade;

import java.io.IOException;

public class FacadeMain {
    public static void main(String[] args){
        //HttpServer httpServer = new HttpServer();
    
        try {
            HttpServer.startServer();
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
    
    
    
}
