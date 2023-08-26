import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

public class Chat1 extends JFrame {
    private JTextArea texto_chat;
    private JTextField texto_enviar;
    private JTextField ip_1;
    private JTextField nombre_usuario1;
    Chat1(){
        
        /*Declaraciones de variables en las interfaces graficas:
         * 1. Caja de texto con lo que quieres escribir en el chat.
         * 2. El chat
         * 3. Botón para enviar el mensaje.
         */

        texto_enviar = new JTextField();
        texto_chat = new JTextArea();
        nombre_usuario1 = new JTextField();
        ip_1 = new JTextField();
        JButton boton_enviar = new JButton("Enviar");
        JLabel tnu1 = new JLabel("Nombre de usuario:");
        JLabel tip = new JLabel("IP:");

        /*Tamaño de:
         * 1. Botón
         * 2. Caja de texto para escribir
         */

        nombre_usuario1.setBounds(20, 590, 100, 30);
        ip_1.setBounds(250, 570, 100, 30);
        boton_enviar.setBounds(150, 575, 70, 35);
        texto_enviar.setBounds(20, 525, 345, 30);
        tnu1.setBounds(10, 560, 150, 30);
        tip.setBounds(295, 545, 100, 30);

        /*Barra deslizadora para ver los mensajes del chat.
         * Tamaño del chat + barra deslizadora
         */

        JScrollPane barra = new JScrollPane(texto_chat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        barra.setBounds(20, 20, 345, 500);

        /*Añadir elementos gráficos de todo el chat.
         * Prohibir edición manual de texto al chat.
         * Configuración de: estilo, título, tamaño, apagado, visibilidad.
         */

        add(nombre_usuario1);
        add(boton_enviar);
        add(texto_enviar);
        add(barra);
        add(tnu1);
        add(tip);
        add(ip_1);

        texto_chat.setEditable(false);
        setLayout(null);
        setTitle("Chat 1");
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
            
            Envio_total info = new Envio_total();
            info.set_nombre_usuario1(nombre_usuario1.getText());
            info.set_mensaje_1(texto_enviar.getText());

            ObjectOutputStream info_envio = new ObjectOutputStream(socket1.getOutputStream());
            info_envio.writeObject(info);
            socket1.close();

            /*DataOutputStream output = new DataOutputStream(socket1.getOutputStream());
            output.writeUTF(texto_enviar.getText());
            output.close();*/

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

class Envio_total implements Serializable{

    private String nombre_usuario1, mensaje_1;

    public String get_nombre_usuario1(){
        return nombre_usuario1;
    }

    public void set_nombre_usuario1(String nombre_usuario1){
        this.nombre_usuario1 = nombre_usuario1;
    }

    public String get_mensaje_1(){
        return mensaje_1;
    }
    public void set_mensaje_1(String mensaje_1){
        this.mensaje_1 = mensaje_1;
    }
}