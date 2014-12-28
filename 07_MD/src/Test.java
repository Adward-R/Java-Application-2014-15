import org.tautua.markdownpapers.Markdown;
import org.tautua.markdownpapers.parser.ParseException;

import java.io.*;

/**
 * Created by Adward on 14/12/28.
 */
public class Test {
    public static void main(String[] args){
        Reader in = null;
        try {
            in = new FileReader("in.md");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Writer out = null;
        try {
            out = new FileWriter("out.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Markdown md = new Markdown();
        try {
            md.transform(in, out);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
