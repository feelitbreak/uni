import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MainPanel extends JPanel {
    CarPark cp;
    String[] data;
    private boolean clicked = false;
    public MainPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton b;
        JLabel l;
        try {
            data = CarPark.decipher("inText.txt");
            l = new JLabel("Полученные данные из файла:");
            add(l);
            for (String i : data) {
                l = new JLabel(i);
                add(l);
            }
        } catch (NoSuchAlgorithmException | IOException | SignatureException | NoSuchProviderException | InvalidKeyException | InvalidKeySpecException e) {
            System.out.println(e.getMessage());
        }
        b = new JButton("Стаж >2 лет");
        add(b);
        b.addActionListener(e -> {
            clicked = true;
            invalidate();
        });
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (clicked) {
            cp = new CarPark();
            try {
                cp.inputBin("inBin.txt");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            Predicate<Driver> pred = x -> x.getExperience() > 2;
            List<String> list = new ArrayList<>();
            cp.getCarParkMap().forEach((x, y) -> {
                if (pred.test(y)) {
                    list.add(x);
                }
                });
            JLabel l;
            boolean doesStart;
            for (String i : data) {
                doesStart = false;
                for (String j : list) {
                    if (i.startsWith(j)) {
                        doesStart = true;
                    }
                }
                if (doesStart) {
                    l = new JLabel(i);
                    add(l);
                }
            }
            clicked = false;
        }
    }
}
