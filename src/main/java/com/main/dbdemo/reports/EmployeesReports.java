package com.main.dbdemo.reports;


import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.main.dbdemo.database.MySqlDatabase;
import com.main.dbdemo.entities.EmployeesModel;
import com.main.dbdemo.services.EmployeesServices;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static com.itextpdf.kernel.pdf.PdfName.Document;
import static com.itextpdf.text.FontFactory.HELVETICA;

@Service
public class EmployeesReports {

    @Autowired
    EmployeesServices employeesServices;

    static Connection connection = null;
    static {
        try {
            connection = MySqlDatabase.connect();
            System.out.println("connection established");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void employeeListInExcel(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/vnd.ms-excel");
        String decompositionParameter = "attachment;filename=employeeList.xls";
        resp.setHeader("Content-disposition", decompositionParameter);
        resp.setHeader("Content-Type", "application/octet-stream");

        try{
            //HttpSession session = req.getSession();
            WritableWorkbook workBook = Workbook.createWorkbook(resp.getOutputStream());
            WritableSheet sheet = workBook.createSheet("Employees List", 0);
            WritableSheet sheet1 = workBook.createSheet("Employees List 1", 1);

            WritableFont font = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD);
            WritableCellFormat cellFormat = new WritableCellFormat(font);
            cellFormat.setBackground(jxl.format.Colour.GRAY_25);
            cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);

            WritableFont headerfont = new WritableFont(WritableFont.ARIAL, 20, WritableFont.BOLD);
            WritableCellFormat headerFormat = new WritableCellFormat(headerfont);
            headerFormat.setAlignment(jxl.format.Alignment.CENTRE);

            WritableFont valuefont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
            WritableCellFormat valueformat = new WritableCellFormat(valuefont);
            valueformat.setAlignment(Alignment.LEFT);

            setHeaderValue(2, 2, "Demo Name", headerFormat, sheet);
            setHeaderValue(2, 3, "Demo Address", valueformat, sheet);

            int rowno = 6;
            int columnno = 0;
            int columnSize = 10;

            setHeaderValue(columnno, rowno, "Sr. No.", cellFormat, sheet);
            sheet.setColumnView(columnno, (int) columnSize + 5);
            columnno++;

            setHeaderValue(columnno, rowno, "Name", cellFormat, sheet);
            sheet.setColumnView(columnno, (int) columnSize + 5);
            columnno++;

            setHeaderValue(columnno, rowno, "Email", cellFormat, sheet);
            sheet.setColumnView(columnno, (int) columnSize + 5);
            columnno++;

            setHeaderValue(columnno, rowno, "Phone", cellFormat, sheet);
            sheet.setColumnView(columnno, (int) columnSize + 5);
            columnno++;

            rowno++;

            Map<String, Object> employeeData = employeesServices.getEmployeeData(null);
            List<EmployeesModel> employee_array = (List<EmployeesModel>)employeeData.get("employee_array");
            Long srno = 1l;
            for (EmployeesModel obj : employee_array){
                Long empid = obj.getEmpid();
                String empname = obj.getEmpname();
                String empemail = obj.getEmpemail();
                String empphoneno = obj.getEmpphoneno();

                columnno = 0;

                setHeaderValue(columnno, rowno, ""+srno, valueformat, sheet);
                columnno++;

                setHeaderValue(columnno, rowno, ""+empname, valueformat, sheet);
                columnno++;

                setHeaderValue(columnno, rowno, ""+empemail, valueformat, sheet);
                columnno++;

                setHeaderValue(columnno, rowno, ""+empphoneno, valueformat, sheet);
                columnno++;

                srno++;
                rowno++;
            }
            workBook.write();
            workBook.close();
        } catch (Exception e){
            new RuntimeException(e);
        }
    }

    public void employeeListInPDF(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Document document = new Document(PageSize.A4, 0, 0, 0, 0);
        //conn = DataSource.getConnection(req.getSession());
        resp.setContentType("application/pdf");
        int tablepercentage = 95;

        PdfWriter writer = PdfWriter.getInstance(document, resp.getOutputStream());
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        //setBackgroundimage(cb, conn, false);

        PdfPTable table = new PdfPTable(4);
//        PdfPCell cell1 = new PdfPCell();
//        cell1.setColspan(4);
//        cell1.setBorder(1);
//        cell1.setMinimumHeight(60);
        int fontsize = 8;

        Paragraph paragraph = new Paragraph("Demo Name", FontFactory.getFont(HELVETICA, fontsize + 2, Font.BOLD));
        Paragraph sno = new Paragraph("S.No.", FontFactory.getFont(HELVETICA, fontsize + 2, Font.BOLD));
        Paragraph name = new Paragraph("Name", FontFactory.getFont(HELVETICA, fontsize + 2, Font.BOLD));
        Paragraph email = new Paragraph("Email", FontFactory.getFont(HELVETICA, fontsize + 2, Font.BOLD));
        Paragraph phoneno = new Paragraph("Phone", FontFactory.getFont(HELVETICA, fontsize + 2, Font.BOLD));

        setValueInCell(paragraph,table,4,true,20,false,true);

        setValueInCell(sno,table,1,true,20,false,true);
        setValueInCell(name,table,1,true,20,false,true);
        setValueInCell(email,table,1,true,20,false,true);
        setValueInCell(phoneno,table,1,true,20,false,true);

        Map<String, Object> employeeData = employeesServices.getEmployeeData(null);
        List<EmployeesModel> employee_array = (List<EmployeesModel>) employeeData.get("employee_array");
        Long srno =1l;
        for (EmployeesModel obj : employee_array){
            String empname = obj.getEmpname();
            String empphoneno = obj.getEmpphoneno();
            String empemail = obj.getEmpemail();

            Paragraph srno_p = new Paragraph(""+srno, FontFactory.getFont(HELVETICA, fontsize + 2, Font.BOLD));
            Paragraph empname_p = new Paragraph(""+empname, FontFactory.getFont(HELVETICA, fontsize + 2, Font.BOLD));
            Paragraph empemail_p = new Paragraph(""+empemail, FontFactory.getFont(HELVETICA, fontsize + 2, Font.BOLD));
            Paragraph empphoneno_p = new Paragraph(""+empphoneno, FontFactory.getFont(HELVETICA, fontsize + 2, Font.BOLD));

            setValueInCell(srno_p,table,1,true,20,false,true);
            setValueInCell(empname_p,table,1,true,20,false,true);
            setValueInCell(empemail_p,table,1,true,20,false,true);
            setValueInCell(empphoneno_p,table,1,true,20,false,true);

            srno++;
        }


        document.add(table);
        document.close();

    }

    public void setHeaderValue(int column, int rowNo, String value, WritableCellFormat cellFormat, WritableSheet sheet) throws WriteException {
        Label label = new Label(column, rowNo, value, cellFormat);
        sheet.addCell(label);

    }

    private void setValueInCell(Paragraph paragraph, PdfPTable studentPersonalTable, int colspan, boolean isboarder, Integer height, boolean isright, boolean iscenter) {


        PdfPCell studentPersonalCell = new PdfPCell(paragraph);
        if (!isboarder) {
            studentPersonalCell.setBorder(0);
        }
        studentPersonalCell.setColspan(colspan);
        if (height != null) {
            studentPersonalCell.setFixedHeight(height);
        }
        if (isright) {
            studentPersonalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        } else if (iscenter) {
            studentPersonalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        }
        studentPersonalTable.addCell(studentPersonalCell);
    }






}
