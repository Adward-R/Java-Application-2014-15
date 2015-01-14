import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] wordlist = {"update", "license", "get", "install","open", "run", "uninstall",
                "crash", "java", "version", "support", "windows", "mac", "osx", "linux"};
        String[] answerlist01 = {"Please search in the HELP menu.","Please check on http://dodgysoft.com."};
        String[] answerlist02 = {"Please search in the HELP menu to find out if it still works.",
                "The license is still available when installing again."};
        String[] answerlist03 = {"Please check on http://dodgysoft.com.","Our product is available on eBay."};
        String[] answerlist04 = {"Which platform (OS) are you installing on?","Check if you are running Windows."};
        String[] answerlist05 = {"Try uninstalling completedly before install again.",
                "You may have selected the improper file type.",
                "Check on official website to see if your OS version is compatible with the soft version."};
        String[] answerlist06 = {"Try uninstalling completedly before install again.",
                "You may have selected the improper file type.",
                "Check on official website to see if your OS version is compatible with the soft version."};
        String[] answerlist07 = {"Please use the original uninstalling tool.",
                "The license is still available when installing again."};
        String[] answerlist08 = {"Try uninstalling completedly before install again.",
                "You may have selected the improper file type.",
                "Check on official website to see if your OS version is compatible with the soft version."};
        String[] answerlist09 = {"Java EE is not needed to run our product."};
        String[] answerlist10 = {"Please check http://dodgysoft.com for latest information."};
        String[] answerlist11 = {"Our product supporting service is still available."};
        String[] answerlist12 = {"Windows platform is not supported.",
                "Try running our product in a Linux virtual machine"};
        String[] answerlist13 = {"Mac OSX is the ideal running environment."};
        String[] answerlist14 = {"Mac OSX is the ideal running environment."};
        String[] answerlist15 = {"Ubuntu and OpenSUSE is supported.",
                "Only 13+ version Ubuntu can run our lastest version."};

        HashMap<String,String[]> m = new HashMap<String,String[]>();
        m.put("update",answerlist01);
        m.put("license",answerlist02);
        m.put("get",answerlist03);
        m.put("install",answerlist04);
        m.put("open",answerlist05);
        m.put("run",answerlist06);
        m.put("uninstall",answerlist07);
        m.put("crash",answerlist08);
        m.put("java",answerlist09);
        m.put("version",answerlist10);
        m.put("support",answerlist11);
        m.put("windows",answerlist12);
        m.put("mac",answerlist13);
        m.put("osx",answerlist14);
        m.put("linux",answerlist15);

        Scanner scan = new Scanner(System.in);
        int cnt=0;
        String word;
        while (!(word=scan.next().replaceAll("\\u003F","").toLowerCase()).equals("exit")) {
            for (String w : wordlist) {
                if (w.equals(word)) {
                    cnt++;
                    int len = m.get(word).length;
                    System.out.println(m.get(word)[(int)(Math.random()*10)%len]);
                }
            }
            if (cnt==0) {
                System.out.println("Please specify your problem.");
            }
        }
    }
}
