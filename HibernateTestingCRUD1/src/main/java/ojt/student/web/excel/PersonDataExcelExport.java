package ojt.student.web.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import ojt.student.persistence.entity.Person;

/**
 * <h2> PersonDataExcelExport Class</h2>
 * <p>
 * Process for Displaying PersonDataExcelExport
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
public class PersonDataExcelExport extends AbstractXlsxView{

    /**
     * <h2> buildExcelDocument </h2>
     * <p>
     * 
     * </p>
     * 
     * @param model
     * @param workbook
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        response.addHeader("Content-Disposition", "attachment;fileName=PersonData.xlsx");

        // read data provided by controller
        @SuppressWarnings("unchecked")
        List<Person> list = (List<Person>) model.get("list1");

        // create one sheet 
        Sheet sheet = workbook.createSheet("Person");

        // create row0 as a header
        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("ID");
        row0.createCell(1).setCellValue("NAME");
        row0.createCell(2).setCellValue("COUNTRY");

        // create row1 onwards from List<T>
        int rowNum = 1;
        for(Person spec : list) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(spec.getId());
            row.createCell(1).setCellValue(spec.getName());
            row.createCell(2).setCellValue(spec.getCountry());
            
        }
     }

        
    }