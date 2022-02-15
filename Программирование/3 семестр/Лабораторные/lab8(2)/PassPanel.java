import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;

public class PassPanel extends JPanel {
    private final JTextField field1;
    private final JTextField field2;
    private boolean wrong = false;
    public PassPanel() {
        JLabel l1;
        JLabel l2;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        l1 = new JLabel("Логин:");
        field1 = new JTextField("", 10);
        l2 = new JLabel("Пароль:");
        field2 = new JTextField("", 10);
        JButton b;
        b = new JButton("ОК");
        b.addActionListener(z -> {
            if (processSignIn()) {
                SwingUtilities.getWindowAncestor(this).dispose();
                JFrame mainFrame = new JFrame();
                mainFrame.setSize(450, 700);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                MainPanel mainPanel = new MainPanel();
                mainFrame.add(mainPanel);
                mainFrame.setVisible(true);
            }
            else {
                wrong = true;
                repaint();
            }
        });
        JPanel grid1 = new JPanel(new GridLayout(1, 2, 1, 0));
        JPanel grid2 = new JPanel(new GridLayout(1, 2, 1, 0));
        grid1.add(l1);
        grid1.add(field1);
        grid2.add(l2);
        grid2.add(field2);
        add(grid1);
        add(grid2);
        add(b);

        CarPark cp = new CarPark();
        try {
            cp.inputText("inText.txt");
            cp.cipher("inText.txt");
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException | SignatureException | NoSuchProviderException e) {
            System.out.println(e.getMessage());
        }
    }
    private boolean processSignIn() {
        try {
            Passwords passwords = new Passwords();
            passwords.input("inPasswords.txt");
            String login = field1.getText();
            String pass = field2.getText();
            if (passwords.verify(login, pass)) {
                return true;
            } else {
                passwords.register(login, pass);
                return false;
            }
        } catch (NoSuchAlgorithmException | IOException ex) {
            return false;
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (wrong) {
            g.drawString("Вы были зарегистрированы.", 5, 100);
        }
    }
}
