import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main {
    private JFrame frame;
    private JTextField textField;

    double first;
    double second;
    double result;
    String operation;
    String answer;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Main window = new Main();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Main() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Scientific Calculator");
        frame.setUndecorated(true); // Remove window decorations
        frame.setBackground(new Color(0, 0, 0, 0)); // Set frame background to fully transparent
        frame.setBounds(0, 0, 580, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Create a transparent JPanel
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Optional: draw a semi-transparent background for the panel
                g.setColor(new Color(195, 199, 244, 200)); // Semi-transparent color
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setOpaque(false); // Make the panel transparent
        panel.setLayout(null);
        frame.setContentPane(panel);

        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Open Sans", Font.PLAIN, 16));
        btnExit.setBounds(430, 650, 100, 40); // Set the position and size of the button
        btnExit.addActionListener(e -> {
            System.exit(0); // Exit the application
        });
        panel.add(btnExit);

        textField = new JTextField();
        textField.setFont(new Font("Open Sans", Font.BOLD, 18));
        textField.setBounds(50, 30, 460, 60);
        panel.add(textField);
        textField.setColumns(10);

        // Add a label to display the operation
        JLabel label1 = new JLabel();
        label1.setFont(new Font("Open Sans", Font.BOLD, 15));
        label1.setBounds(400, 10, 100, 20);
        label1.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.getContentPane().add(label1);

        JLabel label2 = new JLabel();
        label2.setFont(new Font("Open Sans", Font.BOLD, 13));
        label2.setBounds(520, 45, 40, 20);
        frame.getContentPane().add(label2);


        /* Square Root Group */
        JButton btnSqrt = new JButton("√");
        btnSqrt.setFont(new Font("Open Sans", Font.PLAIN, 16));
        btnSqrt.setBounds(50, 110, 70, 40);
        btnSqrt.addActionListener(e -> {
            try {
                // Parse the number from the text field
                double number = Double.parseDouble(textField.getText());

                // Handle negative input
                if (number < 0) {
                    textField.setText("Invalid Input"); // Display error
                } else {
                    // Compute square root
                    double result = Math.sqrt(number);
                    textField.setText(String.format("%.2f", result)); // Display result
                    label2.setText("√");
                }
            } catch (NumberFormatException ex) {
                textField.setText("Invalid Input"); // Display error for invalid input
            }
        });
        frame.getContentPane().add(btnSqrt);

// Implementing Cube Root
        JButton btnCubeRoot = new JButton("∛");
        btnCubeRoot.setFont(new Font("Open Sans", Font.PLAIN, 16));
        btnCubeRoot.setBounds(127, 110, 70, 40);
        btnCubeRoot.addActionListener(e -> {
            try {
                // Parse the input
                double number = Double.parseDouble(textField.getText());

                // Calculate the cube root
                double result = Math.cbrt(number);

                // Display the formatted result
                textField.setText(String.format("%.2f", result));
            } catch (NumberFormatException ex) {
                // Handle invalid input
                textField.setText("Invalid Input");
            }
        });
        frame.getContentPane().add(btnCubeRoot);

// Implementing n-th Root
        JButton btnNthRoot = new JButton("n√");
        btnNthRoot.setFont(new Font("Open Sans", Font.PLAIN, 16));
        btnNthRoot.setBounds(204, 110, 70, 40);
        btnNthRoot.addActionListener(e -> {
            try {
                // Prompt user for n
                String input = JOptionPane.showInputDialog("Enter n for n-th root:");
                if (input != null && !input.isEmpty()) {
                    // Parse n and validate it
                    double n = Double.parseDouble(input);
                    if (n == 0) {
                        textField.setText("Invalid Input (n cannot be 0)");
                        return;
                    }

                    // Parse the base from the text field
                    double base = Double.parseDouble(textField.getText());

                    // Handle negative base with even roots
                    if (base < 0 && n % 2 == 0) {
                        textField.setText("Invalid Input (negative base with even root)");
                        return;
                    }

                    // Calculate the n-th root
                    double result = Math.pow(base, 1.0 / n);

                    // Display the formatted result
                    textField.setText(String.format("%.2f", result));
                    label2.setText( n + "√");
                }
            } catch (NumberFormatException ex) {
                // Handle invalid inputs
                textField.setText("Invalid Input");
            }
        });
        frame.getContentPane().add(btnNthRoot);

        /* Exponents */
        JButton btnX2 = new JButton("x²");
        btnX2.setFont(new Font("Open Sans", Font.PLAIN, 16));
        btnX2.setBounds(281, 110, 70, 40);
        btnX2.addActionListener(e -> {
            try {
                // Parse the input
                double number = Double.parseDouble(textField.getText());

                // Calculate the cube root
                double result = Math.cbrt(number);

                // Display the formatted result
                textField.setText(String.format("%.2f", result));
                label2.setText("x²");
            } catch (NumberFormatException ex) {
                // Handle invalid input
                textField.setText("Invalid Input");
            }
        });
        frame.getContentPane().add(btnX2);

        JButton btnX3 = new JButton("X^3");
        btnX3.setFont(new Font("Open Sans", Font.PLAIN, 16));
        btnX3.setBounds(358, 110, 70, 40);
        btnX3.addActionListener(e -> {
            double a = Double.parseDouble(textField.getText());
            a = a * a * a;
            textField.setText(String.valueOf(a));
            label2.setText("X^3");
        });
        frame.getContentPane().add(btnX3);

        JButton btnXy = new JButton("X^Y");
        btnXy.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnXy.setBounds(435, 110, 70, 40);
        btnXy.addActionListener(e -> {
            try {
                first = Double.parseDouble(textField.getText());
                textField.setText(""); // Clear text field for the second number
                label1.setText(String.valueOf(first)); // Display the first number
                label2.setText("X^Y"); // Display the operation
            } catch (NumberFormatException ex) {
                textField.setText("Invalid Input");
            }
        });
        frame.getContentPane().add(btnXy);


        /* Logarithm */
        JButton btnLog = new JButton("Log");
        btnLog.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnLog.setBounds(50, 155, 70, 40);
        btnLog.addActionListener(e -> {
            double a = Math.log(Double.parseDouble(textField.getText()));
            textField.setText(String.valueOf(a));
            label2.setText("Log");
        });
        frame.getContentPane().add(btnLog);

        JButton btnEx = new JButton("e^x");
        btnEx.setFont(new Font("Open Sans", Font.PLAIN, 16));
        btnEx.setBounds(127, 155, 70, 40);
        btnEx.addActionListener(e -> {
            double a = Math.exp(Double.parseDouble(textField.getText()));
            textField.setText(String.valueOf(a));
            label2.setText("e^x");
        });
        frame.getContentPane().add(btnEx);

        /* Factorial */
        JButton btnFactorial = new JButton("n!");
        btnFactorial.setFont(new Font("Open Sans", Font.PLAIN, 16));
        btnFactorial.setBounds(204, 155, 70, 40);
        btnFactorial.addActionListener(e -> {
            int a = Integer.parseInt(textField.getText());
            if (a < 0) {
                textField.setText("Error");
            } else {
                double fact = 1;
                for (int i = 1; i <= a; i++) {
                    fact *= i;
                }
                textField.setText(String.valueOf(fact));
                label2.setText(a + "!");
            }
        });
        frame.getContentPane().add(btnFactorial);


        /* Trigonometric functions */
        JButton btnSin = new JButton("Sin");
        btnSin.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnSin.setBounds(281, 155, 70, 40);
        btnSin.addActionListener(e -> {
            double a = Math.sin(Math.toRadians(Double.parseDouble(textField.getText())));
            textField.setText(String.valueOf(a));
            label2.setText("Sin");
        });
        frame.getContentPane().add(btnSin);

        JButton btnCos = new JButton("Cos");
        btnCos.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnCos.setBounds(358, 155, 70, 40);
        btnCos.addActionListener(e -> {
            double a = Math.cos(Math.toRadians(Double.parseDouble(textField.getText())));
            textField.setText(String.valueOf(a));
            label2.setText("Cos");
        });
        frame.getContentPane().add(btnCos);

        JButton btnTan = new JButton("Tan");
        btnTan.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnTan.setBounds(435, 155, 70, 40);
        btnTan.addActionListener(e -> {
            double a = Math.tan(Math.toRadians(Double.parseDouble(textField.getText())));
            textField.setText(String.valueOf(a));
            label2.setText("Tan");
        });
        frame.getContentPane().add(btnTan);

        /* Reciprocal */
        JButton btnReciprocal = new JButton("1/x");
        btnReciprocal.setFont(new Font("Open Sans", Font.PLAIN, 16));
        btnReciprocal.setBounds(50, 200, 70, 40);
        btnReciprocal.addActionListener(e -> {
            double a = 1 / (Double.parseDouble(textField.getText()));
            textField.setText(String.valueOf(a));
            label2.setText("1/x");
        });
        frame.getContentPane().add(btnReciprocal);

        JButton btnPercentage = new JButton("%");
        btnPercentage.setFont(new Font("Open Sans", Font.PLAIN, 16));
        btnPercentage.setBounds(127, 200, 70, 40);
        btnPercentage.addActionListener(e -> {
            first = Double.parseDouble(textField.getText());
            textField.setText(""); // Clear text field for the second number
            label1.setText(String.valueOf(first)); // Display the first number
            label2.setText("%"); // Display the operation
        });
        frame.getContentPane().add(btnPercentage);


        /* Pi */
        JButton btnPi = new JButton("π");
        btnPi.setFont(new Font("Open Sans", Font.PLAIN, 16));
        btnPi.setBounds(204, 200, 70, 40);
        btnPi.addActionListener(e -> {
            textField.setText(textField.getText() + Math.PI);
            label2.setText("π");
        });
        frame.getContentPane().add(btnPi);

        /* Inverse Trigonometric Functions */
        JButton btnSinh = new JButton("Sinh");
        btnSinh.setFont(new Font("Open Sans", Font.PLAIN, 14));
        btnSinh.setBounds(281, 200, 70, 40);
        btnSinh.addActionListener(e -> {
            double a = Math.sinh(Double.parseDouble(textField.getText()));
            textField.setText(String.valueOf(a));
            label2.setText("Sinh");
        });
        frame.getContentPane().add(btnSinh);

        JButton btnCosh = new JButton("Cosh");
        btnCosh.setFont(new Font("Open Sans", Font.PLAIN, 12));
        btnCosh.setBounds(358, 200, 70, 40);
        btnCosh.addActionListener (e -> {
            double a = Math.cosh(Double.parseDouble(textField.getText()));
            textField.setText(String.valueOf(a));
            label2.setText("Cosh");
        });
        frame.getContentPane().add(btnCosh);

        JButton btnTanh = new JButton("Tanh");
        btnTanh.setFont(new Font("Open Sans", Font.PLAIN, 12));
        btnTanh.setBounds(435, 200, 70, 40);
        btnTanh.addActionListener(e -> {
            double a = Math.tanh(Double.parseDouble(textField.getText()));
            textField.setText(String.valueOf(a));
            label2.setText("Tanh");
        });
        frame.getContentPane().add(btnTanh);

        //Operators
        JButton btnNewButton_4_2 = new JButton("+");
        btnNewButton_4_2.setEnabled(true);
        btnNewButton_4_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label1.setText(textField.getText());
                label2.setText("+");
                textField.setText("");
            }
        });
        btnNewButton_4_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
        btnNewButton_4_2.setBounds(335, 370, 85, 70);
        frame.getContentPane().add(btnNewButton_4_2);

        JButton btnNewButton_4_3 = new JButton("-");
        btnNewButton_4_3.setEnabled(true);
        btnNewButton_4_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                first=Double.parseDouble(textField.getText());
                label1.setText(textField.getText());
                label2.setText("-");
                textField.setText("");
            }
        });
        btnNewButton_4_3.setFont(new Font("Open Sans", Font.PLAIN, 18));
        btnNewButton_4_3.setBounds(430, 370, 85, 70);
        btnNewButton_4_3.setBounds(430, 370, 85, 70);
        frame.getContentPane().add(btnNewButton_4_3);

        JButton btnNewButton_4_4 = new JButton("×");
        btnNewButton_4_4.setEnabled(true);
        btnNewButton_4_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                first=Double.parseDouble(textField.getText());
                label1.setText(textField.getText());
                label2.setText("×");
                textField.setText("");
            }
        });
        btnNewButton_4_4.setFont(new Font("Open Sans", Font.PLAIN, 18));
        btnNewButton_4_4.setBounds(335, 460, 85, 70);
        frame.getContentPane().add(btnNewButton_4_4);

        JButton btnNewButton_4_5 = new JButton("÷");
        btnNewButton_4_5.setEnabled(true);
        btnNewButton_4_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                first=Double.parseDouble(textField.getText());
                label1.setText(textField.getText());
                label2.setText("÷");
                textField.setText("");
            }
        });
        btnNewButton_4_5.setFont(new Font("Open Sans", Font.PLAIN, 18));
        btnNewButton_4_5.setBounds(430, 460, 85, 70);
        frame.getContentPane().add(btnNewButton_4_5);


        /*Numbers*/

        JButton btnNewButton_1_5 = new JButton("1");
        btnNewButton_1_5.setEnabled(true);
        btnNewButton_1_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_1_5.getText();
                textField.setText(number);

            }
        });
        btnNewButton_1_5.setFont(new Font("Open Sans", Font.PLAIN, 18));
        btnNewButton_1_5.setBounds(48, 460, 85, 70);
        frame.getContentPane().add(btnNewButton_1_5);

        JButton btnNewButton_2_5 = new JButton("2");
        btnNewButton_2_5.setEnabled(true);
        btnNewButton_2_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_2_5.getText();
                textField.setText(number);
            }
        });
        btnNewButton_2_5.setFont(new Font("Open Sans", Font.PLAIN, 18));
        btnNewButton_2_5.setBounds(145, 460, 85, 70);
        frame.getContentPane().add(btnNewButton_2_5);

        JButton btnNewButton_3_5 = new JButton("3");
        btnNewButton_3_5.setEnabled(true);
        btnNewButton_3_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_3_5.getText();
                textField.setText(number);
            }
        });
        btnNewButton_3_5.setFont(new Font("Open Sans", Font.PLAIN, 18));
        btnNewButton_3_5.setBounds(240, 460, 85, 70);
        frame.getContentPane().add(btnNewButton_3_5);

        JButton btnNewButton_1_4 = new JButton("4");
        btnNewButton_1_4.setEnabled(true);
        btnNewButton_1_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_1_4.getText();
                textField.setText(number);
            }
        });
        btnNewButton_1_4.setFont(new Font("Open Sans", Font.PLAIN, 18));
        btnNewButton_1_4.setBounds(48, 370, 85, 70);
        frame.getContentPane().add(btnNewButton_1_4);

        JButton btnNewButton_2_4 = new JButton("5");
        btnNewButton_2_4.setEnabled(true);
        btnNewButton_2_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_2_4.getText();
                textField.setText(number);
            }
        });
        btnNewButton_2_4.setFont(new Font("Open Sans", Font.PLAIN, 18));
        btnNewButton_2_4.setBounds(145, 370, 85, 70);
        frame.getContentPane().add(btnNewButton_2_4);

        JButton btnNewButton_3_4 = new JButton("6");
        btnNewButton_3_4.setEnabled(true);
        btnNewButton_3_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_3_4.getText();
                textField.setText(number);
            }
        });
        btnNewButton_3_4.setFont(new Font("Open Sans", Font.PLAIN, 18));
        btnNewButton_3_4.setBounds(240, 370, 85, 70);
        frame.getContentPane().add(btnNewButton_3_4);

        JButton btnNewButton_1_3 = new JButton("7");
        btnNewButton_1_3.setEnabled(true);
        btnNewButton_1_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_1_3.getText();
                textField.setText(number);
            }
        });
        btnNewButton_1_3.setFont(new Font("Open Sans", Font.PLAIN, 16));
        btnNewButton_1_3.setBounds(48, 280, 85, 70);
        frame.getContentPane().add(btnNewButton_1_3);

        JButton btnNewButton_2_3 = new JButton("8");
        btnNewButton_2_3.setEnabled(true);
        btnNewButton_2_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_2_3.getText();
                textField.setText(number);
            }
        });
        btnNewButton_2_3.setFont(new Font("Open Sans", Font.PLAIN, 16));
        btnNewButton_2_3.setBounds(145, 280, 85, 70);
        frame.getContentPane().add(btnNewButton_2_3);

        JButton btnNewButton_3_3 = new JButton("9");
        btnNewButton_3_3.setEnabled(true);
        btnNewButton_3_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_3_3.getText();
                textField.setText(number);
            }
        });
        btnNewButton_3_3.setFont(new Font("Open Sans", Font.PLAIN, 18));
        btnNewButton_3_3.setBounds(240, 280, 85, 70);
        frame.getContentPane().add(btnNewButton_3_3);

        JButton btnNewButton_1_6 = new JButton("0");
        btnNewButton_1_6.setEnabled(true);
        btnNewButton_1_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_1_6.getText();
                textField.setText(number);
            }
        });
        btnNewButton_1_6.setFont(new Font("Open Sans", Font.PLAIN, 18));
        btnNewButton_1_6.setBounds(145, 550, 85, 70);
        frame.getContentPane().add(btnNewButton_1_6);



        JButton btnNewButton_2_2 = new JButton("C");
        btnNewButton_2_2.setEnabled(true);
        btnNewButton_2_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText(null);
                label1.setText(null);
                label2.setText(null);
            }
        });
        btnNewButton_2_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
        btnNewButton_2_2.setBounds(430, 280, 85, 70);
        frame.getContentPane().add(btnNewButton_2_2);

        JButton btnNewButton_3_2 = new JButton("Back");
        btnNewButton_3_2.setEnabled(true);
        btnNewButton_3_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String backSpace=null;
                if(textField.getText().length()>0) {
                    StringBuilder str= new StringBuilder(textField.getText());
                    str.deleteCharAt(textField.getText().length()-1);
                    backSpace= str.toString();
                    textField.setText(backSpace);
                }
            }
        });
        btnNewButton_3_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
        btnNewButton_3_2.setBounds(335, 280, 85, 70);
        frame.getContentPane().add(btnNewButton_3_2);

        JButton btnNewButton_10 = new JButton("+/-");
        btnNewButton_10.setEnabled(true);
        btnNewButton_10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double a= Double.parseDouble(String.valueOf(textField.getText()));
                a=a*(-1);
                textField.setText(String.valueOf(a));
            }
        });
        btnNewButton_10.setFont(new Font("Open Sans", Font.PLAIN, 18));
        btnNewButton_10.setBounds(48, 550, 85, 70);
        frame.getContentPane().add(btnNewButton_10);


        JButton btnNewButton_3_6 = new JButton(".");
        btnNewButton_3_6.setEnabled(true);
        btnNewButton_3_6.setFont(new Font("Open Sans", Font.PLAIN, 18));
        btnNewButton_3_6.setBounds(240, 550, 85, 70);
        frame.getContentPane().add(btnNewButton_3_6);

        JButton btnNewButton_4_6 = new JButton("=");
        btnNewButton_4_6.setEnabled(true);
        btnNewButton_4_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double first = Double.parseDouble(label1.getText());
                    double second = Double.parseDouble(textField.getText());
                    String operation = label2.getText();

                    if (operation.equals("+")) {
                        textField.setText(String.format("%.2f", first + second));
                        label1.setText("");
                        label2.setText("=");
                    } else if (operation.equals("-")) {
                        textField.setText(String.format("%.2f", first - second));
                        label1.setText("");
                        label2.setText("=");
                    } else if (operation.equals("×")) {
                        textField.setText(String.format("%.2f", first * second));
                        label1.setText("");
                        label2.setText("=");
                    } else if (operation.equals("÷")) {
                        textField.setText(String.format("%.2f", first / second));
                        label1.setText("");
                        label2.setText("=");
                    } else if (operation.equals("%")) {
                        double result = (first / second) * 100;  // Calculate percentage
                        textField.setText(String.format("%.2f%%", result));  // Append '%' to the result
                        label1.setText("");  // Clear label1
                        label2.setText("=");  // Set operation to "="
                    } else if (operation.equals("X^Y")) {
                        double result = Math.pow(first, second);
                        textField.setText(String.format("%.2f", result));
                        label1.setText("");
                        label2.setText("=");
                    }
                } catch (NumberFormatException ex) {
                    textField.setText("Invalid Input");
                }
            }
        });

        btnNewButton_4_6.setFont(new Font("Open Sans", Font.PLAIN, 18));
        btnNewButton_4_6.setBounds(335, 550, 178, 70);
        frame.getContentPane().add(btnNewButton_4_6);
    }
}
