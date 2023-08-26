import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;

public class Server extends JFrame implements Runnable{
    public static void main(String[] args) {
        new Server();
    } Server() {
    
/**
 * @param thread
 */
        Thread thread = new Thread(this);
        thread.start();

    }
    @Override
    public void run() {

        try {
            /** 
             * 
             * @param serversocket
             * @param nombre_usuario_1
             * @param recibo_total
            */
        ServerSocket serversocket = new ServerSocket(21564);
        String nombre_usuario_1, mensaje1; 
        Envio_total recibo_total;

        
            while(true){
                /**
                 * @param mensaje1
                 * @param socket2
                 * @param info_final
                 * @param socket_envio_final
                 */
            Socket socket2 = serversocket.accept();
            ObjectInputStream info_recibida = new ObjectInputStream(socket2.getInputStream());
            recibo_total = (Envio_total) info_recibida.readObject();

            nombre_usuario_1 = recibo_total.get_nombre_usuario1();
            mensaje1 = recibo_total.get_mensaje_1();

            Socket socket_envio_final = new Socket("127.0.0.1",21564);
            
            ObjectOutputStream info_final = new ObjectOutputStream(socket_envio_final.getOutputStream());

            info_final.writeObject(recibo_total);

            info_final.close();
            
            socket_envio_final.close();

            socket2.close();
        }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }
  
}



