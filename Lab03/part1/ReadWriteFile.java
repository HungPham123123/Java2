package Lab03.part1;

import java.io.*;

public class ReadWriteFile {

    public void readData() {
        try {
            File f = new File("test.txt");
            FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            int i;
            while ((i = bis.read()) != -1) {
                System.out.println((char) i);
            }
            System.out.println("");
            fis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void writeData(String str) {
        //chuyen chuoi thanh byte
        byte[] b = str.getBytes();
        try {
            FileOutputStream fos = new FileOutputStream("test.txt");
            fos.write(b);
            fos.flush();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ReadWriteFile d = new ReadWriteFile();
        d.readData();
        d.writeData("hom nay hoc java.io");
    }

}
