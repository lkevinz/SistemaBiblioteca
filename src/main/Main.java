package main;

import util.DatabaseConnection;
import gui.LoginFrame;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> {
        new LoginFrame().setVisible(true);
    });
}

}

