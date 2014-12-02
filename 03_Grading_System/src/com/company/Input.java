package com.company;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Adward on 14/11/30.
 */
public class Input {

    public Input(){}
    public void input(FileSupport fs){
        System.out.println("Key in [name], [course], [score] in a line and finish with \"end\"");
        Scanner sc = new Scanner(System.in);
        while (true){
            String[] record = sc.nextLine().split(", ");
            if (record[0].equals("end")){
                System.out.println("Records inserted successfully.");
                break;
            }
            if (record.length!=3){
                System.out.println("Illegal input!");
            }
            else{
                if (fs.students.containsKey(record[0])){
                    fs.students.get(record[0]).put(record[1],Integer.parseInt(record[2]));
                }
                else{
                    HashMap<String,Integer> courses = new HashMap<String, Integer>();
                    courses.put(record[1],Integer.parseInt(record[2]));
                    fs.students.put(record[0],courses);
                }
            }
        }
    }
    public static void main(String []args) throws IOException {
        FileSupport fs = new FileSupport();
        Input in = new Input();
        int cnt = fs.readIn("file");
        fs.construct(cnt);
        //System.out.println(fs.students.get("ppp").get("ppp").toString());
        in.input(fs);
        fs.writeBack("file");
    }
}
