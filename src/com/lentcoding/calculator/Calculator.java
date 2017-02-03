package com.lentcoding.calculator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

@SuppressWarnings("FieldCanBeLocal")
public class Calculator implements ActionListener {
    private final JFrame frame = new JFrame("Simple Calculator");
    private final JPanel[] row = new JPanel[6];
    private final JButton[] button = new JButton[20];
    private final String[] buttonString = {
            "C", "√", "<-",
            "7", "8", "9",
            "4", "5", "6",
            "1", "2", "3",
            "+/-", "0", ".",
            "*", "/", "+", "-", "="
    };
    private final int[] dimWidths = {300, 45, 100, 90};
    private final int[] dimHeights = {30, 40};
    private final Dimension dimDisplay = new Dimension(dimWidths[0], dimHeights[0]);
    private final Dimension dimRegular = new Dimension(dimWidths[1], dimHeights[1]);
    private final Dimension dimRightColumn = new Dimension(dimWidths[2], dimHeights[1]);
    private final boolean[] function = new boolean[4];
    private final double[] temp = {0, 0};
    // TODO: 2/2/2017 Add a new JTextArea to display equation as it is typed
    private final JTextArea display = new JTextArea(1, 20);
    private final Font font = new Font("Times new Roman", Font.BOLD, 14);
    private final Font displayFont = new Font("Times new Roman", Font.BOLD, 20);
//    String equation = "";
// TODO: 2/2/2017 Format result to remove unnecessary 0s after decimal
//    DecimalFormat format = new DecimalFormat("0.######");

    private Calculator() {
        setDesign();
        frame.setSize(340, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        GridLayout grid = new GridLayout(6, 4);
        frame.setLayout(grid);
        try {
            frame.setIconImage(ImageIO.read(getClass().getResource("/images/calculator.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 4; i++) {
            function[i] = false;
        }

        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
        FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, 1, 1);

        for (int i = 0; i < 6; i++) {
            row[i] = new JPanel();
            row[i].setBackground(new Color(158, 158, 158));
        }

        row[0].setLayout(f1);

        for (int i = 1; i < 6; i++) {
            row[i].setLayout(f2);
        }

        for (int i = 0; i < 20; i++) {
            button[i] = new JButton();
            button[i].setText(buttonString[i]);
            button[i].setFont(font);
            button[i].addActionListener(this);
        }

        display.setFont(displayFont);
        display.setEditable(false);
        display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        display.setPreferredSize(dimDisplay);

        for (int i = 0; i < 15; i++) {
            button[i].setPreferredSize(dimRegular);
        }

        for (int i = 15; i < 20; i++) {
            button[i].setPreferredSize(dimRightColumn);
        }

        row[0].add(display);
        frame.add(row[0]);

        for (int i = 0; i < 3; i++) {
            row[1].add(button[i]);
        }
        row[1].add(button[15]);
        frame.add(row[1]);

        for (int i = 3; i < 6; i++) {
            row[2].add(button[i]);
        }
        row[2].add(button[16]);
        frame.add(row[2]);

        for (int i = 6; i < 9; i++) {
            row[3].add(button[i]);
        }
        row[3].add(button[17]);
        frame.add(row[3]);

        for (int i = 9; i < 12; i++) {
            row[4].add(button[i]);
        }
        row[4].add(button[18]);
        frame.add(row[4]);

        for (int i = 12; i < 15; i++) {
            row[5].add(button[i]);
        }
        row[5].add(button[19]);
        frame.add(row[5]);

        frame.setVisible(true);
    }

    private void setDesign() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "C":
                clear();
                break;
            case "√":
                if (display.getText().length() > 0) {
                    double value = Math.sqrt(Double.parseDouble(display.getText()));
                    display.setText(Double.toString(value));
                }
                break;
            case "<-":
                if (display.getText().length() > 0) {
                    display.setText(display.getText().substring(0, display.getText().length() - 1));
                    break;
                }
                break;
            case "7":
//                if (equation != "") {
//                    display.setText("");
//                    display.append("7");
//                    display.setText(display.getText() + equation);
//                    break;
//                } else {
                display.append("7");
                break;
//                }
            case "8":
                display.append("8");
                break;
            case "9":
                display.append("9");
                break;
            case "4":
                display.append("4");
                break;
            case "5":
                display.append("5");
                break;
            case "6":
                display.append("6");
                break;
            case "1":
                display.append("1");
                break;
            case "2":
                display.append("2");
                break;
            case "3":
                display.append("3");
                break;
            case "+/-":
                getPosNeg();
                break;
            case "0":
                display.append("0");
                break;
            case "*":
                if (display.getText().length() > 0) {
                    temp[0] = Double.parseDouble(display.getText());
                    function[2] = true;
//                equation = " * " + format.format(temp[0]);
//                display.setText(equation);
                    display.setText("");
                    break;
                }
                break;
            case "/":
                if (display.getText().length() > 0) {
                    temp[0] = Double.parseDouble(display.getText());
                    function[3] = true;
//                equation = " / " + format.format(temp[0]);
//                display.setText(equation);
                    display.setText("");
                    break;
                }
                break;
            case "+":
                if (display.getText().length() > 0) {
                    temp[0] = Double.parseDouble(display.getText());
                    function[0] = true;
//                equation = " + " + format.format(temp[0]);
//                display.setText(equation);
                    display.setText("");
                    break;
                }
                break;
            case "-":
                if (display.getText().length() > 0) {
                    temp[0] = Double.parseDouble(display.getText());
                    function[1] = true;
//                equation = " - " + format.format(temp[0]);
//                display.setText(equation);
                    display.setText("");
                    break;
                }
                break;
            case "=":
                getResult();
                break;
        }
    }

    public static void main(String[] args) {
        Calculator c = new Calculator();
    }

    private void clear() {
        try {
            display.setText("");
//            equation = "";

            for (int i = 0; i < 4; i++) {
                function[i] = false;
            }

            for (int i = 0; i < 2; i++) {
                temp[i] = 0;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void getPosNeg() {
        try {
            double value = Double.parseDouble(display.getText());

            if (value != 0) {
                value *= -1;
                display.setText(Double.toString(value));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void getResult() {
        double result = 0;
//        String operation = "";
        temp[1] = Double.parseDouble(display.getText());
        String temp0 = Double.toString(temp[0]), temp1 = Double.toString(temp[1]);

        try {
            if (temp0.contains("-")) {
                String[] temp00 = temp0.split("-", 2);
                temp[0] = (Double.parseDouble(temp00[1]) * -1);
            }
            if (temp1.contains("-")) {
                String[] temp11 = temp1.split("-", 2);
                temp[1] = (Double.parseDouble(temp11[1]) * -1);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        try {
            if (function[2]) {
//                operation = " * ";
                result = temp[0] * temp[1];
            } else if (function[3]) {
//                operation = " / ";
                result = temp[0] / temp[1];
            } else if (function[0]) {
//                operation = " + ";
                result = temp[0] + temp[1];
            } else if (function[1]) {
//                operation = " - ";
                result = temp[0] - temp[1];
            }

//            display.setText(Double.toString(result) + " = " + temp1 + operation + temp0);
            display.setText(Double.toString(result));

            for (int i = 0; i < 4; i++) {
                function[i] = false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
