/**
 * Created by Adward on 14/11/2.
 */

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static int calculate(char op,int num1,int num2) {
        int result=0;
        switch (op){
            case '+' : result = (num1+num2);break;
            case '-' : result = (num1-num2);break;
            case '*' : result = (num1*num2);break;
            case '/' : result = (num1/num2);break;
            case '%' : result = (num1%num2);break;
        }
        return result;
    }
    //To test if the priority of op1 is NO LESS THAN op2
    public static boolean priorerThan(char op1,char op2) {
        if (op1=='*'||op1=='/')
            return true;
        else if (op1=='%') {
            if (op2=='*'||op2=='/')
                return false;
            else
                return true;
        }
        else {
            if (op2=='+'||op2=='-')
                return true;
            else
                return false;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str;
        while (!((str = scan.nextLine()).equals("exit"))) {
            Stack<Character> sk1= new Stack<Character>();
            Stack<Integer> sk2= new Stack<Integer>();
            Character ch;
            int digit=0,cnt=-1;
            for (int i=str.length();i>0;i--) {
                ch = str.charAt(i-1);
                if (ch.isWhitespace(ch)) {
                    ;
                }
                else if (ch.isDigit(ch)) {
                    cnt++;
                    digit += Math.pow(10,cnt) * (ch - '0'); //implicit type converted
                    if (i==1) {
                        sk2.push(digit);
                        digit=0;
                        cnt=-1;
                    } //Make sure there is no digit left in the buffer
                }
                else if (ch==')') {
                    sk1.push(')');
                }
                else if (ch=='('||ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='%') {
                    if (cnt!=-1) {
                        sk2.push(digit);
                        digit=0;
                        cnt=-1;
                    }
                    if (ch=='(') {
                        char tmp;
                        while ((tmp=sk1.pop())!=')') {
                            int num1=sk2.pop();
                            int num2=sk2.pop();
                            sk2.push(calculate(tmp,num1,num2));
                        }
                    }
                    else {
                        boolean bo = sk1.empty()||sk1.peek()==')'||priorerThan(ch,sk1.peek());
                        while (bo==false) {
                            int num1=sk2.pop();
                            int num2=sk2.pop();
                            sk2.push(calculate(sk1.pop(),num1,num2));
                            bo = sk1.empty()||sk1.peek()==')'||priorerThan(ch,sk1.peek());
                        }
                        sk1.push(ch);
                    }
                }
                else {
                    System.out.println("Illegal input, please try again...");
                    break;
                }
            }
            while (!sk1.empty()) {
                int num1=sk2.pop();
                int num2=sk2.pop();
                sk2.push(calculate(sk1.pop(),num1,num2));
            }
            System.out.println(sk2.peek());
        }
    }
}
