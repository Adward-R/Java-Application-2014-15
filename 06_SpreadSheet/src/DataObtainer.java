import java.io.*;
import java.util.Vector;

/**
 * Created by Adward on 14/12/23.
 */

/*
RFC 4180是一个将CSV正式化的努力。它定义了互联网媒体类型“text/csv”，并且采用它的规则的CSV文件将会具有广泛的可移植性。它有如下要求：

以（CR/LF）字符结束的DOS风格的行（最后一行可选）。
一条可选的表头记录（没有可靠的方式来检测它是否存在，所以导入时必须谨慎）。
每条记录“应当”包含同样数量的逗号分隔字段。
任何字段都可以被包裹（用双引号）。
包含换行符、双引号和/或逗号的字段应当被包裹。（否则，文件很可能不能被正确处理）。
字段中的一个（双）引号字符必须被表示为两个（双）引号字符。

本parser要求最后一行为空行（即以\n结束），且分隔符“,”前后不建议有空格
 */

public class DataObtainer {
    private Vector<Vector<String>> tableData;
    private int n;
    //private Vector<String> head;

    public DataObtainer(String filePath) throws IOException {
        tableData = new Vector<Vector<String>>();
        fileParser(filePath);
        //head = new Vector<String>();
        //for (int i=0;i<10;i++)
        //    head.add(String.valueOf(i));
    }

    public void fileParser(String filePath) throws FileNotFoundException,IOException {
        tableData.clear();
        InputStream fReader = new FileInputStream(filePath);
        //BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        int fSize = fReader.available();
        int cnt=0;
        String blockItem="";
        boolean isInQuotes=false;
        boolean isNDetermined=false;
        Vector<String> record = new Vector<String>();
        n = 0;
        while (cnt < fSize){
            Character ch = (char) fReader.read();
            cnt++;
            if (isInQuotes){
                if (ch=='"'){
                    Character _ch = (char) fReader.read();
                    cnt++;
                    if (_ch=='"'){
                        blockItem += '"';
                    }
                    else {
                        record.add(blockItem);
                        isInQuotes = false;
                        blockItem = "";
                        if (_ch=='\n'){
                            if (!isNDetermined) {
                                n = record.size();
                                isNDetermined = true;
                            }
                            tableData.add(new Vector<String>(record));
                            record.clear();
                        }
                    }
                }
                else {
                    blockItem += ch;
                }
            }
            else {
                if (ch==','){
                    //System.out.println("~"+blockItem+"~");
                    record.add(blockItem);
                    blockItem = "";
                }
                else if (ch=='\n'){
                    record.add(blockItem);
                    blockItem = "";
                    if (!isNDetermined) {
                        n = record.size();
                        isNDetermined = true;
                    }
                    tableData.add(new Vector<String>(record));
                    record.clear();
                }
                else if (ch=='"'){
                    isInQuotes = true;
                }
                else {
                    blockItem += ch;
                }
            }
        }
        fReader.close();
        //head.clear();
        //for (int i=0;i<n;i++)
        //    head.add(String.valueOf(i));
    }

    public Object[][] getTableData(){
        Object obj[][] = new Object[tableData.size()][n];
        for (int i=0;i<tableData.size();i++){
            Vector<String> tmp = tableData.elementAt(i);
            for (int j=0;j<n;j++){
                obj[i][j] = tmp.elementAt(j);
            }
        }
        return obj;
    }
    public int getColumnNum(){
        return n;
    }
    public int getRowNum(){
        return tableData.size();
    }

    public void fileSaver(String filePath) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(filePath)));
        fileWriter.write("");

        for (int i=0;i<tableData.size();i++){
            for (int j=0;j<n-1;j++){
                String str = tableData.elementAt(i).elementAt(j);
                if (str.contains("\n")||str.contains("/")||str.contains("\"")||str.contains(",")){
                    fileWriter.append("\""+str.replaceAll("\"","\"\"")+"\",");
                }
                else{
                    fileWriter.append(str+",");
                }
            }
            String str = tableData.elementAt(i).elementAt(n-1);
            if (str.contains("\n")||str.contains("/")||str.contains("\"")||str.contains(",")){
                fileWriter.append("\""+str.replaceAll("\"","\"\"")+"\"\n");
            }
            else{
                fileWriter.append(str+"\n");
            }
        }
        fileWriter.close();
    }

    public void setBlock(int i,int j,Object value){
        tableData.elementAt(i).setElementAt((String) value,j);
    }
    public String getBlockValue(int row,int col){
        if (row>=getRowNum()||col>=getColumnNum())
            return "_EXCEED";
        else
            return tableData.elementAt(row).elementAt(col);
    }
    /*public String getHeadName(int col){
        return head.elementAt(col);
    }*/
}
