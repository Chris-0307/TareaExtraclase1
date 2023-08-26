import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.*;

public class Chat1 extends JFrame implements Runnable{
    private JTextArea texto_chat;
    private JTextField texto_enviar;
    private JTextField nombre_usuario1;

    public static void main(String[] args) {
            Chat1 chat_1 = new Chat1();
            Chat1 chat_2 = new Chat1();
    }
    Chat1(){
        /**
         * Parametros de la interfaz gráfica
         * @author Christian Navarro
         * @param texto_enviar
         * @param nombre_usuario1
         * @param boton_enviar
         * @param texto_chat
         * @param tnu_1
         */

        texto_enviar = new JTextField();
        texto_chat = new JTextArea();
        nombre_usuario1 = new JTextField();
        JButton boton_enviar = new JButton("Enviar");
        JLabel tnu1 = new JLabel("Nombre de usuario:");

        nombre_usuario1.setBounds(20, 590, 100, 30);
        boton_enviar.setBounds(150, 575, 70, 35);
        texto_enviar.setBounds(20, 525, 345, 30);
        tnu1.setBounds(10, 560, 150, 30);

        /**
         * @param barra
         */

        JScrollPane barra = new JScrollPane(texto_chat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        barra.setBounds(20, 20, 345, 500);

        add(nombre_usuario1);
        add(boton_enviar);
        add(texto_enviar);
        add(barra);
        add(tnu1);

        texto_chat.setEditable(false);
        setLayout(null);
        setTitle("Chat 1");
        setSize(400,700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        Enviar_texto envio = new Enviar_texto();
        boton_enviar.addActionListener(envio);

        /**
         * @param thread
         */
        Thread thread = new Thread(this);
        thread.start();

    }

    private class Enviar_texto implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            /**
             * @param socket1
             * @param info
             * @param info_envio
             */
            texto_chat.append("\n" + texto_enviar.getText());

            try{
            Socket socket1 = new Socket("127.0.0.1",21564);
            
            Envio_total info = new Envio_total();
            info.set_nombre_usuario1(nombre_usuario1.getText());
            info.set_mensaje_1(texto_enviar.getText());

            ObjectOutputStream info_envio = new ObjectOutputStream(socket1.getOutputStream());
            info_envio.writeObject(info);
            socket1.close();
            
            /**
             * @throws UnknownHostException
             * @throws IOException
             * @param ev
             */
            } catch (UnknownHostException ev){
                ev.printStackTrace();
            } catch (IOException ev) {
                System.out.println(ev.getMessage());
            }

        }

    }
    
        @Override
        public void run(){
            try{
                /**
                 * @param servidor_chat
                 * @param envio_recibido
                 * @param info_entrada
                 */
                ServerSocket servidor_chat = new ServerSocket(21564);

                Socket chat;
                Envio_total envio_recibido;

                while(true){
                    chat = servidor_chat.accept();
                    ObjectInputStream info_entrada = new ObjectInputStream(chat.getInputStream());
                    envio_recibido = (Envio_total) info_entrada.readObject();
                    texto_chat.append("\n" + envio_recibido.get_nombre_usuario1() + ": " + envio_recibido.get_mensaje_1());
                }
            /**
             * @param e
             */
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
}       


class Envio_total implements Serializable{

    private String nombre_usuario1, mensaje_1;
    /** 
     * @return nombre_usuario1
     * @return mensaje_1
     * 
    */
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
}