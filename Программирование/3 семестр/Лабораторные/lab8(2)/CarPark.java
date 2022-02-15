import java.beans.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;
import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

abstract class AbstractCarPark {
    protected static final int N_1 = 7;
    protected static final int N_2 = 20;
    protected static final int N_3 = 3;
    protected static final int N_4 = 2;
    protected static final int N_5 = 17;
    protected static final int N_6 = 10;
    protected static final int KEY_LENGTH = 56;
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
            byteAr = new byte[N_1];
            if (raf.read(byteAr) == -1) {
                break;
            }
            key = new String(byteAr).trim();
            raf.skipBytes(1);

            byteAr = new byte[N_2];
            raf.read(byteAr);
            name = new String(byteAr).trim();
            raf.skipBytes(1);

            byteAr = new byte[N_3];
            raf.read(byteAr);
            cat = new String(byteAr).trim();
            raf.skipBytes(1);

            byteAr = new byte[N_4];
            raf.read(byteAr);
            exp = Integer.parseInt(new String(byteAr).trim());
            raf.skipBytes(1);

            byteAr = new byte[N_5];
            raf.read(byteAr);
            str = new String(byteAr).trim();
            raf.skipBytes(1);

            byteAr = new byte[N_6];
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
        for (int i = N_1 - info[0].length(); i > 0; i--) {
            raf.writeByte(' ');
        }
        raf.writeByte(' ');
        raf.writeBytes(info[1]);
        for (int i = N_2 - info[1].length(); i > 0; i--) {
            raf.writeByte(' ');
        }
        raf.writeByte(' ');
        raf.writeBytes(info[2]);
        for (int i = N_3 - info[2].length(); i > 0; i--) {
            raf.writeByte(' ');
        }
        raf.writeByte(' ');
        raf.writeBytes(info[3]);
        for (int i = N_4 - info[3].length(); i > 0; i--) {
            raf.writeByte(' ');
        }
        raf.writeByte(' ');
        raf.writeBytes(info[4]);
        for (int i = N_5 - info[4].length(); i > 0; i--) {
            raf.writeByte(' ');
        }
        raf.writeByte(' ');
        raf.writeBytes(info[5]);
        for (int i = N_6 - info[5].length(); i > 0; i--) {
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
    public void cipher(String fileInput) throws IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();
        /* Создание объекта класса Signature */
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
        /* Инициализация частным ключом */
        dsa.initSign(priv);
        /* Чтение данных из файла "data". Вызов метода update() */
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileInput));
        byte[] buffer;
        buffer = bis.readAllBytes();
        dsa.update(buffer);
        bis.close();
        /* Генерация подписи */
        byte[] realSig = dsa.sign();
        /* Сохранение подписи в файл "signature" */
        FileOutputStream fos = new FileOutputStream("sign.txt");
        fos.write(realSig);
        fos.close();
        /* Сохранение открытого ключа в файл "pubkey" */
        byte[] key = pub.getEncoded();
        fos = new FileOutputStream("pubKey.txt");
        fos.write(key);
        fos.close();
    }
    public static String[] decipher(String fileInput) throws IOException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        /* Получение encoded public key из файла "pubkey" */
        FileInputStream fis = new FileInputStream("pubKey.txt");
        byte[] encKey = fis.readAllBytes();
        fis.close();
        /* Создание спецификации ключа */
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
        /* Создание объектов Keyfactory и Publickey*/
        KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
        PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
        /* Чтение подписи из файла "signature" */
        fis = new FileInputStream("sign.txt");
        byte[] sigToVerify = fis.readAllBytes();
        fis.close();
        /* Создание объекта класса Signature и инициализация с помощью открытого ключа    */
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initVerify(pubKey);
        /* Чтение данных из файла "data" и вызов метода update() */
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileInput));
        byte[] buffer;
        String data;
        buffer = bis.readAllBytes();
        data = new String(buffer, StandardCharsets.UTF_8);
        sig.update(buffer);
        bis.close();
        StringTokenizer st = new StringTokenizer(data, "\r\n");
        String[] stData = new String[st.countTokens()];
        for (int i = 0; i < stData.length; i++) {
            stData[i] = st.nextToken();
        }
        if(sig.verify(sigToVerify)) {
            return stData;
        }
        else {
            return null;
        }
    }
    public Map<String, Driver> getCarParkMap() {
        return Collections.unmodifiableMap(carParkMap);
    }
}