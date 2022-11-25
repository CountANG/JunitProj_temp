package org.apache.commons.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteDemo {
    private static final char NEW_LINE_SEPARATOR = '\n';

    /**写入csv文件
     * @param headers 列头
     * @param data 数据内容
     * @param filePath 创建的csv文件路径
     * @throws IOException **/
    public static void writeCsv(String[] headers, List<String[]> data, String filePath) throws IOException{

        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        //创建FileWriter对象
        FileWriter fileWriter=new FileWriter(filePath);

        //创建CSVPrinter对象
        CSVPrinter printer=new CSVPrinter(fileWriter,formator);

        //写入列头数据
        printer.printRecord(headers);

        if(null!=data){
            //循环写入数据
            for(String[] lineData:data){

                printer.printRecord(lineData);

            }
        }

        System.out.println("CSV文件创建成功,文件路径:"+filePath);

    }

    public static void main(String[] args) throws IOException{
        String[] headers = new String[]{"name", "age", "birthday"};
        List<String[]> data = new ArrayList<String[]>();
        data.add(new String[]{"Tom", "15", "2002-8-26"});
        data.add(new String[]{"Good", "15", "2002-8-26"});
        data.add(new String[]{"Tim", "15", "2002-8-26"});
        String csvPath = "/Users/countang/IdeaProjects/JunitProj/src/test/java/org/apache/commons/csv/file.csv";
        writeCsv(headers, data, csvPath);
    }
}
