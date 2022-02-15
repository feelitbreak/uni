import java.beans.*;
import java.io.*;
import java.util.*;
import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

abstract class AbstractCarPark {
    protected static final int n1 = 7;
    protected static final int n2 = 20;
    protected static final int n3 = 3;
    protected static final int n4 = 2;
    protected static final int n5 = 17;
    protected static final int n6 = 10;
    protected Map <String, Driver> carParkMap;
    public AbstractCarPark() {
        carParkMap = new TreeMap<>();
    }
    public void outXML(String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        XMLEncoder xe = new XMLEncoder(fos);
        xe.setPersistenceDelegate(Driver.class,
                new DefaultPersistenceDelegate(
                        new String[]{
                                "surname",
                                "category",
                                "experience",
                                "street",
                                "birthDate"
                        }) );
        xe.writeObject(carParkMap);
        xe.close();
        fos.close();
    }
}
public class CarPark extends AbstractCarPark {
    public void inputBin (String fileName) throws IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        try {
            carParkMap = (TreeMap<String, Driver>) ois.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public void inputText (String fileName) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
        byte[] byteAr;
        String key;
        String name;
        String cat;
        int exp;
        String str;
        String birth;
        while (true) {
            byteAr = new byte[n1];
            if (raf.read(byteAr) == -1) {
                break;
            }
            key = new String(byteAr).trim();
            raf.skipBytes(1);

            byteAr = new byte[n2];
            raf.read(byteAr);
            name = new String(byteAr).trim();
            raf.skipBytes(1);

            byteAr = new byte[n3];
            raf.read(byteAr);
            cat = new String(byteAr).trim();
            raf.skipBytes(1);

            byteAr = new byte[n4];
            raf.read(byteAr);
            exp = Integer.parseInt(new String(byteAr).trim());
            raf.skipBytes(1);

            byteAr = new byte[n5];
            raf.read(byteAr);
            str = new String(byteAr).trim();
            raf.skipBytes(1);

            byteAr = new byte[n6];
            raf.read(byteAr);
            birth = new String(byteAr).trim();
            raf.skipBytes(1);

            Driver driver = new Driver(name, cat, exp, str, birth);
            carParkMap.put(key, driver);
        }
        raf.close();
    }
    public void inputXML(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        XMLDecoder dcr = new XMLDecoder(fis);
        carParkMap = (TreeMap<String, Driver>) dcr.readObject();
        dcr.close();
        fis.close();
    }
    public void show() {
        carParkMap.forEach((x, y) -> {
                    System.out.print(x + " ");
                    y.show();
                }
        );
    }
    public void addElement(String[] info) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("inText.txt", "rw");
        raf.seek(260);
        raf.writeBytes(info[0]);
        for (int i = n1 - info[0].length(); i > 0; i--) {
            raf.writeByte(' ');
        }
        raf.writeByte(' ');
        raf.writeBytes(info[1]);
        for (int i = n2 - info[1].length(); i > 0; i--) {
            raf.writeByte(' ');
        }
        raf.writeByte(' ');
        raf.writeBytes(info[2]);
        for (int i = n3 - info[2].length(); i > 0; i--) {
            raf.writeByte(' ');
        }
        raf.writeByte(' ');
        raf.writeBytes(info[3]);
        for (int i = n4 - info[3].length(); i > 0; i--) {
            raf.writeByte(' ');
        }
        raf.writeByte(' ');
        raf.writeBytes(info[4]);
        for (int i = n5 - info[4].length(); i > 0; i--) {
            raf.writeByte(' ');
        }
        raf.writeByte(' ');
        raf.writeBytes(info[5]);
        for (int i = n6 - info[5].length(); i > 0; i--) {
            raf.writeByte(' ');
        }
        raf.writeByte('\n');
        raf.close();
    }
    public void outBin(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(carParkMap);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void outXML(String fileName) throws IOException {
        XStream xs = new XStream(new DomDriver());
        xs.alias("driver", Driver.class);
        FileOutputStream fos = new FileOutputStream(fileName);
        xs.toXML(carParkMap, fos);
        fos.close();
    }
    public void outDOM(String fileName) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = df.newDocumentBuilder();
        Document doc = db.newDocument();
        Element rootElement = doc.createElement("carParkMap");
        doc.appendChild(rootElement);
        carParkMap.forEach((x, y) -> {
            Element el = doc.createElement("element");
            rootElement.appendChild(el);

            Element string = doc.createElement("string");
            string.appendChild(doc.createTextNode(x));
            el.appendChild(string);

            Element driver = doc.createElement("driver");
            el.appendChild(driver);

            Element drSur = doc.createElement("surname");
            drSur.appendChild(doc.createTextNode(y.getSurname()));
            driver.appendChild(drSur);

            Element drCat = doc.createElement("category");
            drCat.appendChild(doc.createTextNode(y.getCategory()));
            driver.appendChild(drCat);

            Element drExp = doc.createElement("experience");
            drExp.appendChild(doc.createTextNode(String.valueOf(y.getExperience())));
            driver.appendChild(drExp);

            Element drStreet = doc.createElement("street");
            drStreet.appendChild(doc.createTextNode(y.getStreet()));
            driver.appendChild(drStreet);

            Element drBD = doc.createElement("birthDate");
            drBD.appendChild(doc.createTextNode(y.getBirthDate()));
            driver.appendChild(drBD);
        });
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = null;
        try {
            t = tf.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(fileName));
        assert t != null;
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty(OutputKeys.STANDALONE, "yes");
        t.transform(source, result);
    }
}