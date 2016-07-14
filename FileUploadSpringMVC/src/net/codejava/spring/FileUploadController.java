package net.codejava.spring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class FileUploadController {
	
	private String saveDirectory = "/Users/kapil/Desktop/test1/output/Final";
	
	@RequestMapping(method = RequestMethod.POST,value="/uploadFile.do")
	public String handleFileUpload(HttpServletRequest request, 
			@RequestParam CommonsMultipartFile[] fileUpload) throws Exception {
		
		System.out.println("description: " + request.getParameter("description"));
		
		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload){
				
				System.out.println("Saving file: " + aFile.getOriginalFilename());
				
				if (!aFile.getOriginalFilename().equals("")) {
					aFile.transferTo(new File(saveDirectory + aFile.getOriginalFilename()));
				}
			}
		}

		// returns to the view "Result"
		return "Result";
	}
	@RequestMapping(method = RequestMethod.POST,value="/downloadpdf.do")
	public String handlePDFDownload()
	{
		  try
          {
			  	Document document = new Document();
			  	File file=new File("/Users/kapil/Desktop/test1/output/test.pdf");
			  	FileOutputStream fileos=new FileOutputStream(file);
			  	//PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("AddTableExample.pdf"));
			  	PdfWriter writer = PdfWriter.getInstance(document, fileos);
			  	document.open();
		 
		        PdfPTable table = new PdfPTable(4); // 4 columns.
		        table.setWidthPercentage(100); //Width 100%
		        table.setSpacingBefore(10f); //Space before table
		        table.setSpacingAfter(10f); //Space after table
		 
		        //Set Column widths
		        float[] columnWidths = {1f, 1f, 1f, 1f};
		        table.setWidths(columnWidths);
			  	
                 System.out.println("inside download pdf action");
                  //csv file containing data
                  String strFile = "/Users/kapil/Desktop/test1/output/Finaldetails.txt";
                 
                  //create BufferedReader to read csv file
                  BufferedReader br = new BufferedReader( new FileReader(strFile));
                  String strLine = "";
                  StringTokenizer st = null;
                  int lineNumber = 0, tokenNumber = 0;
           
                  
                 /* PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
                  cell1.setBorderColor(BaseColor.BLUE);
                  cell1.setPaddingLeft(10);
                  cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                  cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
           
                 
                  
                  
                  PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
                  cell2.setBorderColor(BaseColor.GREEN);
                  cell2.setPaddingLeft(10);
                  cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                  cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
           
                  PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));
                  cell3.setBorderColor(BaseColor.RED);
                  cell3.setPaddingLeft(10);
                  cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                  cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
           
                  PdfPCell cell4 = new PdfPCell(new Paragraph("Cell 4"));
                  cell4.setBorderColor(BaseColor.BLUE);
                  cell4.setPaddingLeft(10);
                  cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                  cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                  
                  //To avoid having the cell border and the content overlap, if you are having thick cell borders
                  //cell1.setUserBorderPadding(true);
                  //cell2.setUserBorderPadding(true);
                  //cell3.setUserBorderPadding(true);
           
                  table.addCell(cell1);
                  table.addCell(cell2);
                  table.addCell(cell3);
                  table.addCell(cell4);*/
                  
                  //read comma separated file line by line
                  
                  while( (strLine = br.readLine()) != null)
                  {
                          lineNumber++;
                         
                          //break comma separated line using ","
                          st = new StringTokenizer(strLine, ",");
                         System.out.println("before splitting string");
                          while(st.hasMoreTokens())
                          {
                                  //display csv values
                                  tokenNumber++;
                                 /* System.out.println("Line # " + lineNumber +
                                                  ", Token # " + tokenNumber
                                                  + ", Token : "+ st.nextToken());*/
                                  for(int i=0;i<4;i++)
                                  {
                                	  //System.out.println(st.nextToken());
                                	  
                                	  
                                	  PdfPCell cell1 = new PdfPCell(new Paragraph(st.nextToken()));
                                      cell1.setBorderColor(BaseColor.BLUE);
                                      cell1.setPaddingLeft(10);
                                      cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                                      cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                               
                                      table.addCell(cell1);
                                      
                                      
                                      /*PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
                                      cell2.setBorderColor(BaseColor.GREEN);
                                      cell2.setPaddingLeft(10);
                                      cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                                      cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                               
                                      PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));
                                      cell3.setBorderColor(BaseColor.RED);
                                      cell3.setPaddingLeft(10);
                                      cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                                      cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                               
                                      //To avoid having the cell border and the content overlap, if you are having thick cell borders
                                      //cell1.setUserBorderPadding(true);
                                      //cell2.setUserBorderPadding(true);
                                      //cell3.setUserBorderPadding(true);
                               
                                      table.addCell(cell1);
                                      table.addCell(cell2);
                                      table.addCell(cell3);
                                	  */
                                	  
                                	  
                                  }
                                  
                                  System.out.println("######################");
                          }
                         
                          //reset token number
                          tokenNumber = 0;
                         
                  }
                  document.add(table);
                  
                  document.close();
                  writer.close();
                  System.out.println("Pdf created successfully..");
                 
          }
          catch(Exception e)
          {
                  System.out.println("Exception while reading csv file: " + e);  
                  e.printStackTrace();
          }
		return "Result";
	}
}