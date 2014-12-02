package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 * Created by Adward on 14/11/30.
 */
public class FileSupport {

    public Vector<String> records;
    public HashMap<String,HashMap<String,Integer>> students;

    public FileSupport(){
        records = new Vector<String>(3);
        students = new HashMap<String, HashMap<String, Integer>>();
    }

    public int readIn(String fname) throws IOException {

        InputStream f = new FileInputStream(fname+".csv");
        int fsize = f.available();
        int cnt = 0;
        String str = "";
        for (int i=0;i<fsize;i++){
            Character ch = (char)f.read();
            if (ch==','){
                records.add(str);
                cnt++;
                f.read();
                str = "";
            }
            else if (ch=='\n'){ //modify to handle EOF
                records.add(str);
                cnt++;
                str = "";
            }
            else {
                str += ch;
            }
        }
        f.close();
        return cnt;
    }

    public void construct(int cnt){
        for (int i=0;i<cnt;i+=3){
            HashMap<String,Integer> courses = new HashMap<String, Integer>();
            courses.put(records.elementAt(i+1),Integer.parseInt(records.elementAt(i+2)));
            if (students.containsKey(records.elementAt(i))){
                students.get(records.elementAt(i)).put(records.elementAt(i+1),Integer.parseInt(records.elementAt(i+2)));
            }
            else{
                students.put(records.elementAt(i),new HashMap<String, Integer>(courses));
            }
            courses.clear();
        }
    }

    public void writeBack(String fname) throws IOException{
        //OutputStream f = new FileOutputStream(fname+".csv");
        BufferedWriter f = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fname+".csv")));
        f.write("");
        Iterator<Map.Entry<String,HashMap<String,Integer>>> stu_it = students.entrySet().iterator();
        while (stu_it.hasNext()){
            Map.Entry<String,HashMap<String,Integer>> entry = stu_it.next();
            String stu_name = entry.getKey();
            Iterator<Map.Entry<String,Integer>> cour_it = entry.getValue().entrySet().iterator();
            while (cour_it.hasNext()){
                Map.Entry<String,Integer> _entry = cour_it.next();
                //f.write(stu_name);
                f.append(stu_name+", "+_entry.getKey()+", "+_entry.getValue().toString()+"\n");
            }
        }
        f.close();
    }

}
