package com.company;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Adward on 14/11/30.
 */
public class Student {
    public Student(){}
    public void searchStudent(FileSupport fs){
        Scanner sc = new Scanner(System.in);
        String sname = sc.nextLine();
        if (!fs.students.containsKey(sname)){
            System.out.println("No such student!");
        }
        else{
            Iterator<Map.Entry<String,Integer>> it = fs.students.get(sname).entrySet().iterator();
            int total=0;
            while (it.hasNext()){
                Map.Entry<String,Integer> entry = it.next();
                System.out.println(entry.getKey()+", "+entry.getValue().toString());
                total += entry.getValue();
            }
            System.out.println("Total: "+ total + "\nAvg: "+ (total/fs.students.get(sname).size()));
        }
    }

    public static void main(String []args) throws IOException {
        FileSupport fs = new FileSupport();
        Student stu = new Student();
        int cnt = fs.readIn("/Users/Adward/Desktop/file");
        fs.construct(cnt);

        stu.searchStudent(fs);
        fs.writeBack("/Users/Adward/Desktop/file");
    }
}
