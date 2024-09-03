/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.loginapp;

/**
 *
 * @author melis
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class LoginApp {
    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
    }
}

class LoginFrame extends JFrame {
    private JTextField nameField, lastnameField, emailField, ageField;
    private JPasswordField passwordField;
    private JComboBox<String> generoField;
    private JButton loginButton;
    private JLabel messageLabel;

    public LoginFrame() {
        setTitle("Formulario de Registro");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(60, 63, 65));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BorderLayout(30, 30));

        // Etiqueta de título
        JLabel titleLabel = new JLabel("Registrar usuario", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(187, 225, 250));

        // Panel para los campos de entrada 
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        

        // Campos de entrada con etiquetas
        addInputField(inputPanel, gbc, "Nombre:", nameField = new JTextField(20));
        addInputField(inputPanel, gbc, "Apellido:", lastnameField = new JTextField(20));
        addInputField(inputPanel, gbc, "Correo Electrónico:", emailField = new JTextField(20));
        addInputField(inputPanel, gbc, "Contraseña:", passwordField = new JPasswordField(20));
        addInputField(inputPanel, gbc, "Edad:", ageField = new JTextField(20));

        // ComboBox para seleccionar el género
        JLabel generoLabel = new JLabel("Sexo:");
        generoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        generoLabel.setForeground(new Color(187, 225, 250));
        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(generoLabel, gbc);

        String[] opcionesGenero = {"Seleccione", "Femenino", "Masculino"};
        generoField = new JComboBox<>(opcionesGenero);
        generoField.setFont(new Font("Arial", Font.PLAIN, 14));
        generoField.setBackground(new Color(43, 43, 43));
        generoField.setForeground(Color.WHITE);
        generoField.setBorder(BorderFactory.createLineBorder(new Color(87, 96, 111)));
        gbc.gridx = 1;
        inputPanel.add(generoField, gbc);

        // Panel para el botón de login
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        loginButton = new JButton("Enviar");
        customizeButton(loginButton);
        loginButton.addActionListener(new LoginButtonListener());
        buttonPanel.add(loginButton);

        // Etiqueta para mensajes
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
        add(messageLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    // Método para agregar un campo de entrada con su etiqueta al panel
    private void addInputField(JPanel panel, GridBagConstraints gbc, String labelText, JTextField textField) {
    JLabel label = new JLabel(labelText);
    label.setFont(new Font("Arial", Font.PLAIN, 14));
    label.setForeground(new Color(187, 225, 250));

    // Establecer la configuración para la etiqueta
    gbc.gridx = 0; // Columna 0
    gbc.gridy++; // Incrementa la fila para la etiqueta
    gbc.insets = new Insets(5, 5, 5, 5); // Agregar margen alrededor del componente
    gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda
    gbc.fill = GridBagConstraints.NONE; // No expandir la etiqueta
    panel.add(label, gbc); // Agregar etiqueta al panel

    // Establecer la configuración para el campo de texto
    textField.setFont(new Font("Arial", Font.PLAIN, 14));
    textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(87, 96, 111)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
    textField.setBackground(new Color(43, 43, 43));
    textField.setForeground(Color.WHITE);
    textField.setCaretColor(Color.WHITE);

    gbc.gridx = 1; // Columna 1 para el campo de texto
    gbc.fill = GridBagConstraints.HORIZONTAL; // El campo de texto debe expandirse horizontalmente
    gbc.weightx = 1.0; // Permitir que el campo de texto ocupe el espacio disponible en X
    panel.add(textField, gbc); // Agregar campo de texto al panel
}

    // Método para personalizar el botón
    private void customizeButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(87, 96, 111));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createLineBorder(new Color(60, 63, 65), 1, true));
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText().trim();
            String lastname = lastnameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String ageText = ageField.getText().trim();
            String genero = (String) generoField.getSelectedItem();

            // Validaciones
            if (name.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty() || ageText.isEmpty() || genero.equals("Seleccione")) {
                messageLabel.setText("Todos los campos son obligatorios.");
                return;
            }

            if (name.length() < 3 || name.length() > 20) {
                messageLabel.setText("El nombre debe tener entre 3 y 20 caracteres.");
            } else if (lastname.isEmpty()) {
                messageLabel.setText("El apellido no puede estar vacío.");
            } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                messageLabel.setText("El correo debe ser un correo electrónico válido.");
            } else if (password.length() < 8) {
                messageLabel.setText("La contraseña debe tener al menos 8 caracteres.");
            } else if (!ageText.matches("\\d+")) {
                messageLabel.setText("La edad debe ser un número.");
            } else if (Integer.parseInt(ageText) < 18) {
                messageLabel.setText("La edad debe ser mayor o igual a 18.");
            } else {
                messageLabel.setForeground(Color.GREEN);
                messageLabel.setText("¡Formulario enviado correctamente!");
            }
        }
    }
}