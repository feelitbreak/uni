import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame passFrame = new JFrame();
        passFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        passFrame.setSize(260, 150);
        PassPanel passPanel = new PassPanel();
        passFrame.add(passPanel);
        passFrame.setVisible(true);
    }
}
