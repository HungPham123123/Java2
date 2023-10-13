package Lab03.part3Ex1;

import java.io.*;

public class TextFileEncryptor {

    public void readFile () {
        try {
            File files = new File("texts.txt");
            FileInputStream filesInput = new FileInputStream(files);
            BufferedInputStream bufferedInput = new BufferedInputStream(filesInput);
            int i;
            while ((i = bufferedInput.read()) != -1) {
                System.out.print((char) (i + 3));
            }
            System.out.println("");
            filesInput.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void writeData(String str) {
        byte[] b = str.getBytes();
        try {
            FileOutputStream filesOutput = new FileOutputStream("texts.txt");
            filesOutput.write(b);
            filesOutput.flush();
            filesOutput.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TextFileEncryptor t = new TextFileEncryptor();
        t.readFile();
        t.writeData("Hello World");
    }

}

