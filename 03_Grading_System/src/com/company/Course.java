package com.company;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Adward on 14/11/30.
 */
public class Course {
    public Course(){}
    public void searchCourse(FileSupport fs){
        Scanner sc = new Scanner(System.in);
        String cname = sc.nextLine();
        int cnt=0, total=0;
        Iterator<Map.Entry<String,HashMap<String,Integer>>> it = fs.students.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,HashMap<String,Integer>> entry = it.next();
            if (entry.getValue().containsKey(cname)) {
                System.out.println(entry.getKey()+", "+entry.getValue().get(cname));
                cnt++;
                total += entry.getValue().get(cname);
            }
        }
        if (cnt==0){
            System.out.println("No such course!");
        }
        else{
            System.out.println("Total: "+total+"\nAvg: "+ (total/cnt));
        }
    }

    public static void main(String []args) throws IOException {
        FileSupport fs = new FileSupport();
        Course stu = new Course();
        int cnt = fs.readIn("/Users/Adward/Desktop/file");
        fs.construct(cnt);

        stu.searchCourse(fs);
        fs.writeBack("/Users/Adward/Desktop/file");
    }
}
