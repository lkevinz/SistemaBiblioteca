package util;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.util.List;

public class JTextFieldAutoCompleter {
    public JTextFieldAutoCompleter(JTextField textField, List<String> items) {
        // Se crea un modelo para el JComboBox con las opciones
        JComboBox<String> comboBox = new JComboBox<>(items.toArray(new String[0]));
        comboBox.setFocusable(false);
        comboBox.setBorder(null);
        comboBox.setSelectedIndex(-1);

        // Agregar el comboBox como “popup” de sugerencias
        textField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String text = textField.getText();
                comboBox.removeAllItems();
                for (String item : items) {
                    if (item.toLowerCase().contains(text.toLowerCase())) {
                        comboBox.addItem(item);
                    }
                }
                if (comboBox.getItemCount() > 0) {
                    comboBox.setPopupVisible(true);
                } else {
                    comboBox.setPopupVisible(false);
                }
            }
        });

        comboBox.addActionListener((ActionEvent e) -> {
            if (comboBox.getSelectedItem() != null) {
                textField.setText(comboBox.getSelectedItem().toString());
                comboBox.setPopupVisible(false);
            }
        });
        
        // Se agrega el comboBox al mismo contenedor del textField (opcional, según diseño)
        JLayeredPane layeredPane = textField.getRootPane().getLayeredPane();
        layeredPane.add(comboBox, JLayeredPane.POPUP_LAYER);
        
        // Posicionar el comboBox justo debajo del textField
        textField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                comboBox.setPopupVisible(false);
            }
        });
    }
}
