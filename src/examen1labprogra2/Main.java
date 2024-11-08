package examen1labprogra2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;

public class Main extends JFrame implements ActionListener {

    private ArrayList<RentItem> items;
    private JPanel panel;
    private JLabel title;
    private JButton addItemBtn;
    private JButton enterCodeBtn;
    private JButton executeSubMenuBtn;
    private JButton printBtn;

    public Main() {
        items = new ArrayList<>();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setTitle("Sistema de Renta");
        this.setLocationRelativeTo(null); 
        
        panel = new JPanel();
        panel.setBackground(new Color(0, 204, 204)); 
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); 

        title = new JLabel("MENU");
        title.setFont(new Font("Algerian", Font.BOLD, 50));
        title.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(title, gbc);

        addItemBtn = new JButton("Agregar Item");
        addItemBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        addItemBtn.addActionListener(this);
        gbc.gridy = 1;
        
        panel.add(addItemBtn, gbc);
        enterCodeBtn = new JButton("Consultar Item");
        enterCodeBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        enterCodeBtn.addActionListener(this);
        gbc.gridy = 2;
        panel.add(enterCodeBtn, gbc);

        executeSubMenuBtn = new JButton("Ejecutar SubMenu");
        executeSubMenuBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        executeSubMenuBtn.addActionListener(this);
        gbc.gridy = 3;
        panel.add(executeSubMenuBtn, gbc);

        printBtn = new JButton("Imprimir Todo");
        printBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        printBtn.addActionListener(this);
        gbc.gridy = 4;
        panel.add(printBtn, gbc);

        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == addItemBtn) {
            agregarItem();
        } else if (evt.getSource() == enterCodeBtn) {
            consultarItem();
        } else if (evt.getSource() == executeSubMenuBtn) {
            ejecutarSubmenu();
        } else if (evt.getSource() == printBtn) {
            imprimirTodo();
        }
    }

    private void agregarItem() {
    String tipo = null;

    while (true) {
        tipo = JOptionPane.showInputDialog("Ingrese el tipo de item (Movie o Game):");
        if (tipo == null) return; 

        tipo = tipo.trim().toUpperCase();
        if (tipo.equals("MOVIE") || tipo.equals("GAME")) {
            break; 
        } else {
            JOptionPane.showMessageDialog(null, "Tipo incorrecto. Por favor, ingrese 'MOVIE' o 'GAME'.");
        }
    }

    String codigo = JOptionPane.showInputDialog("Ingrese el codigo del item:");
    if (codigo == null || codigo.isEmpty()) return;

    for (RentItem item : items) {
        if (item.getCodigoItem().equalsIgnoreCase(codigo)) {
            JOptionPane.showMessageDialog(null, "Codigo ya existe. Intente nuevamente.");
            return;
        }
    }

    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del item:");
    if (nombre == null || nombre.isEmpty()) return;

    if (tipo.equals("MOVIE")) {
        try {
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio de renta de la pelicula:"));
            items.add(new Movie(codigo, nombre, precio));
            JOptionPane.showMessageDialog(null, "Pelicula agregada exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Precio no valido.");
        }
    } else if (tipo.equals("GAME")) {
        items.add(new Game(codigo, nombre));
        JOptionPane.showMessageDialog(null, "Juego agregado exitosamente.");
    }
}

    private void consultarItem() {
        String codigo = JOptionPane.showInputDialog("Ingrese el codigo del item:");
        if (codigo == null || codigo.isEmpty()) return;

        codigo = codigo.trim().toUpperCase(); 

        for (RentItem item : items) {
            if (item.getCodigoItem().equalsIgnoreCase(codigo)) {
                JOptionPane.showMessageDialog(null, item.toString());
                int dias;
                try {
                    dias = Integer.parseInt(JOptionPane.showInputDialog("Ingrese los dias de renta:"));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Dias no validos.");
                    return;
                }
                double totalPago = item.pagoRenta(dias);
                JOptionPane.showMessageDialog(null, "Monto a pagar: " + totalPago);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Item No Existe");
    }

        private void ejecutarSubmenu() {
    String codigo = JOptionPane.showInputDialog("Ingrese el codigo del item:");
    if (codigo == null || codigo.isEmpty()) return;

    codigo = codigo.trim().toUpperCase();

    for (RentItem item : items) {
       
        if (item instanceof Game && item.getCodigoItem().equalsIgnoreCase(codigo)) {
            
            JDialog dialog = new JDialog(this, "Submenu de Game", true);
            dialog.setSize(400, 300);
            dialog.setLayout(new GridBagLayout());
            dialog.setLocationRelativeTo(this);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel instructionsLabel = new JLabel("Opciones:");
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            dialog.add(instructionsLabel, gbc);

            String opcionesTexto = "1. Actualizar Fecha de Publicacion\n2. Agregar Especificacion\n3. Ver Especificaciones";

            JTextArea opcionesArea = new JTextArea(opcionesTexto);
            opcionesArea.setEditable(false);
            opcionesArea.setBackground(dialog.getBackground());
            gbc.gridy = 1;
            dialog.add(opcionesArea, gbc);

         
            JLabel optionLabel = new JLabel("Ingrese el número de la opcion:");
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            dialog.add(optionLabel, gbc);

            JTextField optionField = new JTextField(10);
            gbc.gridx = 1;
            dialog.add(optionField, gbc);

            JButton confirmButton = new JButton("Confirmar");
            confirmButton.addActionListener(e -> {
                try {
                    int opcion = Integer.parseInt(optionField.getText());
                    dialog.dispose(); 
                    ((Game) item).ejecutarOpcion(opcion);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Por favor, ingrese un numero valido.");
                }
            });
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            dialog.add(confirmButton, gbc);

            dialog.setVisible(true);
            return;
        }
    }
    JOptionPane.showMessageDialog(null, "Item No Existe o no es de tipo Game.");
}
    private void imprimirTodo() {
        StringBuilder builder = new StringBuilder();
        for (RentItem item : items) {
            builder.append(item.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, builder.toString(), "Todos los Items", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}