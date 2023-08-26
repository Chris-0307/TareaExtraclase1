import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;

public class Chat2 extends JFrame implements Runnable{
    private JTextField texto_enviar2;
    private JTextArea texto_chat2;
    public static void main(String[] args) {
        Chat2 chat_2 = new Chat2();

    } Chat2() {
    
        texto_enviar2 = new JTextField();
        texto_chat2 = new JTextArea();
        JButton boton_enviar2 = new JButton("Enviar");

        boton_enviar2.setBounds(150, 575, 70, 35);
        texto_enviar2.setBounds(20, 525, 345, 30);

        JScrollPane barra2 = new JScrollPane(texto_chat2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        barra2.setBounds(20, 20, 345, 500);

        add(boton_enviar2);
        add(texto_enviar2);
        add(barra2);

        texto_chat2.setEditable(false);
        setLayout(null);
        setTitle("Chat 2");
        setSize(400, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        Thread thread = new Thread(this);
        thread.start();

    }
    @Override
    public void run() {

        try {
        ServerSocket serversocket = new ServerSocket(21564);   
        
            while(true){
            Socket socket2 = serversocket.accept();
            DataInputStream recibo = new DataInputStream(socket2.getInputStream());

            String msj = recibo.readUTF();
            texto_chat2.append("\n" + "Chat 1: " + msj);
            socket2.close();
        }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
  
}



