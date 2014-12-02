package com.company;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Adward on 14/12/2.
 * "file.csv" must be placed under project's main directory as a database,
 * and new files for importing should be in the same directory;
 * "file.csv" should end with an empty line
 */
public class Main {
    public static void main(String[] args){

        FileSupport fs = new FileSupport();
        Input in = new Input();
        Importing imp = new Importing();
        Student stu = new Student();
        Course cour = new Course();
        System.out.println("Welcome to the Grading System");

        Scanner sc = new Scanner(System.in);
        int cnt=0;/*
        String fname;

        System.out.println("Please specify a file name (will be automatically regarded as *.csv)");
        while (true){
            fname = sc.nextLine();
            try {
                cnt = fs.readIn(fname);
                break;
            }
            catch (IOException io_err){
                System.out.println("File not found, key in another:");
            }
        }*/
        try {
            cnt = fs.readIn("file");
        } catch (IOException e) {
            System.out.println("Database not found!");
            return;
        }
        fs.construct(cnt);
        //fs.records.clear();//to be modified to a func
        int oper=0;
        while (true){
            System.out.println("Please specify an operation as following:");
            System.out.println("1: Input; 2: Import; 3: Student Stat; 4: Course Stat; 0: exit");
            try {
                oper = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException num_e){
                System.out.println("Input cannot be parsed.");
                continue;
            }
            switch (oper){
                case 1: in.input(fs);break;
                case 2: {
                    fs.records.clear();
                    cnt = imp.importFile(fs);
                    fs.students.clear();
                    fs.construct(cnt);
                    break;
                }
                case 3: stu.searchStudent(fs);break;
                case 4: cour.searchCourse(fs);break;
                case 0: {
                    try {
                        fs.writeBack("file");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thanks for using!");
                    return;
                }
                default: System.out.println("Input cannot be parsed.");
            }
        }

    }
}
