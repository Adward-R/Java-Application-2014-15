package com.company;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Adward on 14/12/2.
 */
public class Importing {
    public Importing(){}
    public int importFile(FileSupport fs){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please specify name of the file which is to be imported:");
        String newfname,str="";
        while (true) {
            newfname = sc.nextLine();
            try {
                File file = new File("file.csv");
                File newfile = new File(newfname+".csv");
                //FileInputStream f = new FileInputStream(file);
                //FileInputStream newf = new FileInputStream(newfile);

                //str += f.read(new byte[((int) file.length())]);
                //str += newf.read(new byte[((int)newfile.length())]);
/*
                for (int i = 0; i < f.available(); i++) {
                    str += f.read();
                }
                for (int i = 0; i < newf.available(); i++) {
                    str += (char) newf.read();
                }*/
                BufferedReader fr = new BufferedReader(new FileReader(file));
                String tmp;
                while ((tmp = fr.readLine())!=null){
                    str += tmp+"\n";
                }
                BufferedReader nfr = new BufferedReader(new FileReader(newfile));
                while ((tmp = nfr.readLine())!=null){
                    str += tmp+"\n";
                }

                break;
            } catch (IOException io_err) {
                System.out.println("File not found, key in another:");
                str = "";
            }
        }

        BufferedWriter fo = null;
        try {
            fo = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("file.csv")));
            fo.write(str);
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int cnt=0;
        try {
            cnt = fs.readIn("file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Imported successfully.");
        return cnt;
    }

    public static void main(String[] args){
        FileSupport fs = new FileSupport();
        Importing imp = new Importing();
        imp.importFile(fs);
        try {
            fs.writeBack("file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
