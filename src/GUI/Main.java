package GUI;

import javax.swing.*;
import java.sql.SQLException;


public class Main {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
                MainPage o = new MainPage();
                JFrame f = new JFrame(o.getClass().getSimpleName());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.setResizable(false);
                f.setTitle("Работа с кадрами");

                f.setContentPane(o.getUI());
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setLocationRelativeTo(null);
                f.setVisible(true);



}



}

