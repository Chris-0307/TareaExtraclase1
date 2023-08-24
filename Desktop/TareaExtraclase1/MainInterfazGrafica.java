import java.awt.BorderLayout;

import javax.swing.*;

public class MainInterfazGrafica extends JFrame {
    MainInterfazGrafica(){
        
        JTextField texto_enviar = new JTextField();
        JTextArea texto_chat = new JTextArea();
        JButton boton_enviar = new JButton("Enviar");
        
        

        JPanel panel_principal = new JPanel();
        panel_principal.setLayout(new BorderLayout());
        panel_principal.add(texto_chat, BorderLayout.NORTH);
        panel_principal.add(texto_enviar, BorderLayout.CENTER);
        panel_principal.add(boton_enviar, BorderLayout.SOUTH);

        add(panel_principal);

        setTitle("Chat");
        setSize(400,900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        }
        public static void main(String[] args) {
            MainInterfazGrafica Chat1 = new MainInterfazGrafica();
    }
}

