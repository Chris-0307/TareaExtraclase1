import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

public class Chat1 extends JFrame {
    private JTextField texto_enviar;
    Chat1(){
        
        /*Declaraciones de variables en las interfaces graficas:
         * 1. Caja de texto con lo que quieres escribir en el chat.
         * 2. El chat
         * 3. Botón para enviar el mensaje.
         */

        texto_enviar = new JTextField();
        JTextArea texto_chat = new JTextArea();
        JButton boton_enviar = new JButton("Enviar");

        /*Tamaño de:
         * 1. Botón
         * 2. Caja de texto para escribir
         */

        boton_enviar.setBounds(150, 575, 70, 35);
        texto_enviar.setBounds(20, 525, 345, 30);

        /*Barra deslizadora para ver los mensajes del chat.
         * Tamaño del chat + barra deslizadora
         */

        JScrollPane barra = new JScrollPane(texto_chat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        barra.setBounds(20, 20, 345, 500);

        /*Añadir elementos gráficos de todo el chat.
         * Prohibir edición manual de texto al chat.
         * Configuración de: estilo, título, tamaño, apagado, visibilidad.
         */

        add(boton_enviar);
        add(texto_enviar);
        add(barra);

        texto_chat.setEditable(false);
        setLayout(null);
        setTitle("Chat");
        setSize(400,700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        Enviar_texto envio = new Enviar_texto();
        boton_enviar.addActionListener(envio);

    }

    private class Enviar_texto implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

            try{
            Socket socket1 = new Socket("127.0.0.1",21564);

            DataOutputStream output = new DataOutputStream(socket1.getOutputStream());
            output.writeUTF(texto_enviar.getText());
            output.close();

            } catch (UnknownHostException ev){
                ev.printStackTrace();
            } catch (IOException ev) {
                System.out.println(ev.getMessage());
            }
        }

    }

        
        public static void main(String[] args) {
            Chat1 chat_1 = new Chat1();
    }       
}

