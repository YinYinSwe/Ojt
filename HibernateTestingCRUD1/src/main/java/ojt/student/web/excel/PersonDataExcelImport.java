package ojt.student.web.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import ojt.student.persistence.entity.Person;

public class PersonDataExcelImport {

   public List<Person> excelImport(MultipartFile file){
       List<Person> listStudent=new ArrayList<>();
       int id=0;
       String name="";
       String country="";
       
       long start=System.currentTimeMillis();
       FileInputStream inputStream;
    
      try {
       
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        System.out.println(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();
        rows.next();
        while(rows.hasNext()) {
          Row nextRow=rows.next();
          Iterator<Cell> cellsInRow = nextRow.iterator();
          while (cellsInRow.hasNext()) {
            Cell currentCell = cellsInRow.next();
            int columnIndex=currentCell.getColumnIndex();
            switch (columnIndex) {
            case 0:
                name=(String)currentCell.getStringCellValue();
                System.out.println(name);
                break;
              
            case 1:
                country=(String)currentCell.getStringCellValue();
                System.out.println(country);
                break;
           default:
               break;
            }
            
          }
          listStudent.add(new Person(name,country));
        }
        workbook.close();
        long end=System.currentTimeMillis();
        
      } catch (Exception e) {
          e.printStackTrace();
      }
      return listStudent;
   }
            
           
}
