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
        frame.setBounds(0, 0, 560, 680);
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
        panel.setBorder(BorderFactory.createLineBorder(new Color(250, 231, 251, 255), 2));
        frame.setContentPane(panel);

        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Serif", Font.PLAIN, 16));
        btnExit.setBackground(new Color(231, 231, 251));
        btnExit.setBounds(410, 600, 100, 40); // Set the position and size of the button
        btnExit.addActionListener(e -> {
            System.exit(0); // Exit the application
        });
        panel.add(btnExit);

        textField = new JTextField();
        textField.setFont(new Font("Serif", Font.BOLD, 18));
        textField.setBounds(50, 50, 460, 60);
        panel.add(textField);
        textField.setColumns(10);

        // Add a label to display the operation
        JLabel label1 = new JLabel();
        label1.setFont(new Font("Serif", Font.BOLD, 15));
        label1.setBounds(400, 25, 100, 20);
        label1.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.getContentPane().add(label1);

        JLabel label2 = new JLabel();
        label2.setFont(new Font("Serif", Font.BOLD, 13));
        label2.setBounds(520, 65, 40, 20);
        frame.getContentPane().add(label2);

        JLabel label3 = new JLabel("Scientific Calculator");
        label3.setFont(new Font("Open Sans", Font.BOLD, 25));
        label3.setBounds(50, 605, 300, 40); // Adjusted width to fit the text
        label3.setHorizontalAlignment(SwingConstants.LEFT);
        label3.setVerticalAlignment(SwingConstants.TOP);
        frame.getContentPane().add(label3);



        /* Square Root Group */
        JButton btnSqrt = new JButton("√");
        btnSqrt.setFont(new Font("Serif", Font.PLAIN, 16));
        btnSqrt.setBackground(new Color(231, 231, 251));
        btnSqrt.setBounds(50, 125, 70, 40);
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
        btnCubeRoot.setFont(new Font("Serif", Font.PLAIN, 16));
        btnCubeRoot.setBackground(new Color(231, 231, 251));
        btnCubeRoot.setBounds(128, 125, 70, 40);
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
        btnNthRoot.setFont(new Font("Serif", Font.PLAIN, 16));
        btnNthRoot.setBackground(new Color(231, 231, 251));
        btnNthRoot.setBounds(206, 125, 70, 40);
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
        btnX2.setFont(new Font("Serif", Font.PLAIN, 16));
        btnX2.setBackground(new Color(231, 231, 251));
        btnX2.setBounds(284, 125, 70, 40);
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
        btnX3.setFont(new Font("Serif", Font.PLAIN, 16));
        btnX3.setBackground(new Color(231, 231, 251));
        btnX3.setBounds(362, 125, 70, 40);
        btnX3.addActionListener(e -> {
            double a = Double.parseDouble(textField.getText());
            a = a * a * a;
            textField.setText(String.valueOf(a));
            label2.setText("X^3");
        });
        frame.getContentPane().add(btnX3);

        JButton btnXy = new JButton("X^Y");
        btnXy.setFont(new Font("Serif", Font.PLAIN, 14));
        btnXy.setBackground(new Color(231, 231, 251));
        btnXy.setBounds(440, 125, 70, 40);
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
        btnLog.setFont(new Font("Serif", Font.PLAIN, 14));
        btnLog.setBackground(new Color(231, 231, 251));
        btnLog.setBounds(50, 170, 70, 40);
        btnLog.addActionListener(e -> {
            double a = Math.log(Double.parseDouble(textField.getText()));
            textField.setText(String.valueOf(a));
            label2.setText("Log");
        });
        frame.getContentPane().add(btnLog);

        JButton btnEx = new JButton("e^x");
        btnEx.setFont(new Font("Serif", Font.PLAIN, 16));
        btnEx.setBackground(new Color(231, 231, 251));
        btnEx.setBounds(128, 170, 70, 40);
        btnEx.addActionListener(e -> {
            double a = Math.exp(Double.parseDouble(textField.getText()));
            textField.setText(String.valueOf(a));
            label2.setText("e^x");
        });
        frame.getContentPane().add(btnEx);

        /* Factorial */
        JButton btnFactorial = new JButton("n!");
        btnFactorial.setFont(new Font("Serif", Font.PLAIN, 16));
        btnFactorial.setBackground(new Color(231, 231, 251));
        btnFactorial.setBounds(206, 170, 70, 40);
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
        btnSin.setFont(new Font("Serif", Font.PLAIN, 14));
        btnSin.setBackground(new Color(231, 231, 251));
        btnSin.setBounds(284, 170, 70, 40);
        btnSin.addActionListener(e -> {
            double a = Math.sin(Math.toRadians(Double.parseDouble(textField.getText())));
            textField.setText(String.valueOf(a));
            label2.setText("Sin");
        });
        frame.getContentPane().add(btnSin);

        JButton btnCos = new JButton("Cos");
        btnCos.setFont(new Font("Serif", Font.PLAIN, 14));
        btnCos.setBackground(new Color(231, 231, 251));
        btnCos.setBounds(362, 170, 70, 40);
        btnCos.addActionListener(e -> {
            double a = Math.cos(Math.toRadians(Double.parseDouble(textField.getText())));
            textField.setText(String.valueOf(a));
            label2.setText("Cos");
        });
        frame.getContentPane().add(btnCos);

        JButton btnTan = new JButton("Tan");
        btnTan.setFont(new Font("Serif", Font.PLAIN, 14));
        btnTan.setBackground(new Color(231, 231, 251));
        btnTan.setBounds(440, 170, 70, 40);
        btnTan.addActionListener(e -> {
            double a = Math.tan(Math.toRadians(Double.parseDouble(textField.getText())));
            textField.setText(String.valueOf(a));
            label2.setText("Tan");
        });
        frame.getContentPane().add(btnTan);

        /* Reciprocal */
        JButton btnReciprocal = new JButton("1/x");
        btnReciprocal.setFont(new Font("Serif", Font.PLAIN, 16));
        btnReciprocal.setBackground(new Color(231, 231, 251));
        btnReciprocal.setBounds(50, 215, 70, 40);
        btnReciprocal.addActionListener(e -> {
            double a = 1 / (Double.parseDouble(textField.getText()));
            textField.setText(String.valueOf(a));
            label2.setText("1/x");
        });
        frame.getContentPane().add(btnReciprocal);

    //Percentage
        JButton btnPercentage = new JButton("%");
        btnPercentage.setFont(new Font("Serif", Font.PLAIN, 16));
        btnPercentage.setBackground(new Color(231, 231, 251));
        btnPercentage.setBounds(128, 215, 70, 40);
        btnPercentage.addActionListener(e -> {
            first = Double.parseDouble(textField.getText());
            textField.setText(""); // Clear text field for the second number
            label1.setText(String.valueOf(first)); // Display the first number
            label2.setText("%"); // Display the operation
        });
        frame.getContentPane().add(btnPercentage);


        /* Pi */
        JButton btnPi = new JButton("π");
        btnPi.setFont(new Font("Serif", Font.PLAIN, 16));
        btnPi.setBackground(new Color(231, 231, 251));
        btnPi.setBounds(206, 215, 70, 40);
        btnPi.addActionListener(e -> {
            textField.setText(textField.getText() + Math.PI);
            label2.setText("π");
        });
        frame.getContentPane().add(btnPi);

        /* Inverse Trigonometric Functions */
        JButton btnSinh = new JButton("Sinh");
        btnSinh.setFont(new Font("Serif", Font.PLAIN, 14));
        btnSinh.setBackground(new Color(231, 231, 251));
        btnSinh.setBounds(284, 215, 70, 40);
        btnSinh.addActionListener(e -> {
            double a = Math.sinh(Double.parseDouble(textField.getText()));
            textField.setText(String.valueOf(a));
            label2.setText("Sinh");
        });
        frame.getContentPane().add(btnSinh);

        JButton btnCosh = new JButton("Cosh");
        btnCosh.setFont(new Font("Serif", Font.PLAIN, 12));
        btnCosh.setBackground(new Color(231, 231, 251));
        btnCosh.setBounds(362, 215, 70, 40);
        btnCosh.addActionListener (e -> {
            double a = Math.cosh(Double.parseDouble(textField.getText()));
            textField.setText(String.valueOf(a));
            label2.setText("Cosh");
        });
        frame.getContentPane().add(btnCosh);

        JButton btnTanh = new JButton("Tanh");
        btnTanh.setFont(new Font("Serif", Font.PLAIN, 12));
        btnTanh.setBackground(new Color(231, 231, 251));
        btnTanh.setBounds(440, 215, 70, 40);
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
        btnNewButton_4_2.setFont(new Font("Serif", Font.BOLD, 16));
        btnNewButton_4_2.setBackground(new Color(231, 231, 251));
        btnNewButton_4_2.setBounds(332, 360, 84, 70);
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
        btnNewButton_4_3.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_4_3.setBackground(new Color(231, 231, 251));
        btnNewButton_4_3.setBounds(426, 360, 84, 70);
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
        btnNewButton_4_4.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_4_4.setBackground(new Color(231, 231, 251));
        btnNewButton_4_4.setBounds(332, 440, 84, 70);
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
        btnNewButton_4_5.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_4_5.setBackground(new Color(231, 231, 251));
        btnNewButton_4_5.setBounds(426, 440, 84, 70);
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
        btnNewButton_1_5.setFont(new Font("Serif", Font.PLAIN, 18));
        btnNewButton_1_5.setBackground(new Color(231, 231, 251));
        btnNewButton_1_5.setBounds(50, 440, 84, 70);
        frame.getContentPane().add(btnNewButton_1_5);

        JButton btnNewButton_2_5 = new JButton("2");
        btnNewButton_2_5.setEnabled(true);
        btnNewButton_2_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_2_5.getText();
                textField.setText(number);
            }
        });
        btnNewButton_2_5.setFont(new Font("Serif", Font.PLAIN, 18));
        btnNewButton_2_5.setBackground(new Color(231, 231, 251));
        btnNewButton_2_5.setBounds(144, 440, 84, 70);
        frame.getContentPane().add(btnNewButton_2_5);

        JButton btnNewButton_3_5 = new JButton("3");
        btnNewButton_3_5.setEnabled(true);
        btnNewButton_3_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_3_5.getText();
                textField.setText(number);
            }
        });
        btnNewButton_3_5.setFont(new Font("Serif", Font.PLAIN, 18));
        btnNewButton_3_5.setBackground(new Color(231, 231, 251));
        btnNewButton_3_5.setBounds(238, 440, 84, 70);
        frame.getContentPane().add(btnNewButton_3_5);

        JButton btnNewButton_1_4 = new JButton("4");
        btnNewButton_1_4.setEnabled(true);
        btnNewButton_1_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_1_4.getText();
                textField.setText(number);
            }
        });
        btnNewButton_1_4.setFont(new Font("Serif", Font.PLAIN, 18));
        btnNewButton_1_4.setBackground(new Color(231, 231, 251));
        btnNewButton_1_4.setBounds(50, 360, 84, 70);
        frame.getContentPane().add(btnNewButton_1_4);

        JButton btnNewButton_2_4 = new JButton("5");
        btnNewButton_2_4.setEnabled(true);
        btnNewButton_2_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_2_4.getText();
                textField.setText(number);
            }
        });
        btnNewButton_2_4.setFont(new Font("Serif", Font.PLAIN, 18));
        btnNewButton_2_4.setBackground(new Color(231, 231, 251));
        btnNewButton_2_4.setBounds(144, 360, 84, 70);
        frame.getContentPane().add(btnNewButton_2_4);

        JButton btnNewButton_3_4 = new JButton("6");
        btnNewButton_3_4.setEnabled(true);
        btnNewButton_3_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_3_4.getText();
                textField.setText(number);
            }
        });
        btnNewButton_3_4.setFont(new Font("Serif", Font.PLAIN, 18));
        btnNewButton_3_4.setBackground(new Color(231, 231, 251));
        btnNewButton_3_4.setBounds(238, 360, 84, 70);
        frame.getContentPane().add(btnNewButton_3_4);

        JButton btnNewButton_1_3 = new JButton("7");
        btnNewButton_1_3.setEnabled(true);
        btnNewButton_1_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_1_3.getText();
                textField.setText(number);
            }
        });
        btnNewButton_1_3.setFont(new Font("Serif", Font.PLAIN, 16));
        btnNewButton_1_3.setBackground(new Color(231, 231, 251));
        btnNewButton_1_3.setBounds(50, 280, 84, 70);
        frame.getContentPane().add(btnNewButton_1_3);

        JButton btnNewButton_2_3 = new JButton("8");
        btnNewButton_2_3.setEnabled(true);
        btnNewButton_2_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_2_3.getText();
                textField.setText(number);
            }
        });
        btnNewButton_2_3.setFont(new Font("Serif", Font.PLAIN, 16));
        btnNewButton_2_3.setBackground(new Color(231, 231, 251));
        btnNewButton_2_3.setBounds(144, 280, 84, 70);
        frame.getContentPane().add(btnNewButton_2_3);

        JButton btnNewButton_3_3 = new JButton("9");
        btnNewButton_3_3.setEnabled(true);
        btnNewButton_3_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_3_3.getText();
                textField.setText(number);
            }
        });
        btnNewButton_3_3.setFont(new Font("Serif", Font.PLAIN, 18));
        btnNewButton_3_3.setBackground(new Color(231, 231, 251));
        btnNewButton_3_3.setBounds(238, 280, 84, 70);
        frame.getContentPane().add(btnNewButton_3_3);

        JButton btnNewButton_1_6 = new JButton("0");
        btnNewButton_1_6.setEnabled(true);
        btnNewButton_1_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText()+btnNewButton_1_6.getText();
                textField.setText(number);
            }
        });
        btnNewButton_1_6.setFont(new Font("Serif", Font.PLAIN, 18));
        btnNewButton_1_6.setBackground(new Color(231, 231, 251));
        btnNewButton_1_6.setBounds(144, 520, 84, 70);
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
        btnNewButton_2_2.setFont(new Font("Serif", Font.PLAIN, 16));
        btnNewButton_2_2.setBackground(new Color(231, 231, 251));
        btnNewButton_2_2.setBounds(426, 280, 84, 70);
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
        btnNewButton_3_2.setFont(new Font("Serif", Font.PLAIN, 16));
        btnNewButton_3_2.setBackground(new Color(231, 231, 251));
        btnNewButton_3_2.setBounds(332, 280, 84, 70);
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
        btnNewButton_10.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_10.setBackground(new Color(231, 231, 251));
        btnNewButton_10.setBounds(50, 520, 84, 70);
        frame.getContentPane().add(btnNewButton_10);


        JButton btnNewButton_3_6 = new JButton(".");
        btnNewButton_3_6.setEnabled(true);
        btnNewButton_3_6.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_3_6.setBackground(new Color(231, 231, 251));
        btnNewButton_3_6.setBounds(238, 520, 84, 70);
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

        btnNewButton_4_6.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_4_6.setBackground(new Color(231, 231, 251));
        btnNewButton_4_6.setBounds(332, 520, 178, 70);
        frame.getContentPane().add(btnNewButton_4_6);
    }
}
