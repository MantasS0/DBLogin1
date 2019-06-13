package com.company;

import javax.swing.*;

public class App {
    private JPanel panel1;
    private JButton labasPrisijungesButton;

    public App(){
        labasPrisijungesButton.setText("Labas prisijunges: " + User.currentUser.getName());
    }


    public JPanel getPanel1() {
        return panel1;
    }
}
