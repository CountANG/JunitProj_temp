package org.apache.commons.csv;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CSVFormatTest {
    private List<Student> students = new ArrayList<>();
    private final String FILE_NAME = "student.csv";
    private CSVFormat format;

    @Before
    public void setUp() throws Exception {
        final String[] FILE_HEADER = {"ID", "Name", "Gender", "Major"};

        Student stuANG = new Student("1", "ANG", "男", "SE");
        Student stuGOOD = new Student("2", "GOOD", "男", "CS");
        students.add(stuANG);
        students.add(stuGOOD);

        final char NEW_LINE_SEPARATOR = '\n';

        format = CSVFormat.DEFAULT.withHeader(FILE_HEADER);
    }



    @After
    public void tearDown() throws Exception {
            try(Writer out = new FileWriter(FILE_NAME);
                CSVPrinter printer = new CSVPrinter(out, format)) {
                for (Student student : students) {
                    List<String> records = new ArrayList<>();
                    records.add(student.getID());
                    records.add(student.getName());
                    records.add(student.getGender());
                    records.add(student.getMajor());
                    printer.printRecord(records);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 这是从上面写入的文件中读出数据的代码
            try(Reader in = new FileReader(FILE_NAME)) {
                Iterable<CSVRecord> records = format.parse(in);
                String strID;
                String strName;
                for (CSVRecord record : records) {
                    strID = record.get("ID");
                    strName = record.get("Name");
                    System.out.println(strID + " " + strName);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    @Test
    public void getHeaderComments() {
        assertNull(format.getHeaderComments());

    }
    @Test
    public void isEscapeCharacterSet() {
        assertFalse(format.getTrailingDelimiter());
    }
    @Test
    public void getTrim() {
        assertFalse(format.getTrim());
    }
}