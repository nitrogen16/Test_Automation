import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.*;

public class BookTest {

    private JAXBContext context;

    @BeforeClass
    private void init() throws JAXBException {
        this.context = JAXBContext.newInstance(Libraries.class);
    }

    @Test
    public void marshal() throws JAXBException {
        List<Library> libraries = new ArrayList<Library>();
        libraries.add(new Library(generateBooksList()));
        libraries.add(new Library(generateBooksList()));
        libraries.add(new Library(generateBooksList()));
        
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setListener(new Marshaller.Listener() {
            private int counter = 1;
            public void beforeMarshal(Object object) {
                if(object instanceof Library) {
                    ((Library) object).setId(counter++);
                }
            }
        });
        marshaller.marshal(new Libraries(libraries), new File("JAXB.xml"));
    }

    @Test(description = "unmarshal xml created", dependsOnMethods = "marshal")
    public void unmarshal() throws JAXBException {
        Unmarshaller unmarshaller = this.context.createUnmarshaller();
        Object unmarshalled = unmarshaller.unmarshal(new File("JAXB.xml"));
        System.out.println(unmarshalled.toString());
    }

    @Test(description = "Book to Excel", dependsOnMethods = "unmarshal")
    public void storeBookToExcel() {
        File xmlDocument = new File("JAXB.xml");
        generateExcel(xmlDocument);
    }

    @Test(description = "Excel to XML", dependsOnMethods = "storeBookToExcel")
    public void storeBookFromExcelToXml() {
        File input = new File("XMLtoExcel.xls");
        generateXML(input);
    }

    @Test(description = "create ftl template based on xml created", dependsOnMethods = "storeBookFromExcelToXml")
    public void createFTLTemplate() {
        try{
            List<Library> libraries = new ArrayList<Library>();
            libraries.add(new Library(generateBooksList()));
            libraries.add(new Library(generateBooksList()));
            libraries.add(new Library(generateBooksList()));
            
            Configuration cfg = new Configuration();
            Template template = cfg.getTemplate("ftlmodel.ftl");

            // Create data for template
            Map<String, Object> templateData = new HashMap<String, Object>();
            List<Book> library = Arrays.asList(
                    new Book(),
                    new Book() );
            templateData.put("bookDetails", library);

            Writer file = new FileWriter(new File("XMLfromFTL.xml"));
            template.process(templateData, file);
            file.flush();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateXML(File excelFile) {
        try{
            //Initializing the XML document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element rootElement = document.createElement("Books");
            document.appendChild(rootElement);

            InputStream input = new FileInputStream(excelFile);
            HSSFWorkbook workbook = new HSSFWorkbook(input);
            HSSFSheet spreadsheet = workbook.getSheetAt(0);
            
            for(int i = 1; i < spreadsheet.getLastRowNum() + 1; i++){
                HSSFRow row = spreadsheet.getRow(i);
                
                Element bookElement = document.createElement("Book");
                bookElement.setAttribute("id", row.getCell(0).getStringCellValue());
                rootElement.appendChild(bookElement);
                
                Element bookName = document.createElement("BookName");
                bookName.appendChild(document.createTextNode(row.getCell(1).getStringCellValue()));
                bookElement.appendChild(bookName);

                Element bookAuthor = document.createElement("BookAuthor");
                bookAuthor.appendChild(document.createTextNode(row.getCell(2).getStringCellValue()));
                bookElement.appendChild(bookAuthor);
            }

            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            //Add indentation to output
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("ExcelToXML.xml"));
            transformer.transform(source, result);
            
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException " + e.getMessage());
        } catch (TransformerConfigurationException e) {
            System.out.println("TransformerConfigurationException "+ e.getMessage());
        } catch (TransformerException e) {
            System.out.println("TransformerException " + e.getMessage());
        }
    }

    private void generateExcel(File xmlDocument) {
        try{
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet spreadSheet = wb.createSheet("spreadSheet");
            spreadSheet.setColumnWidth((short) 0, (short) (200 * 25));
            spreadSheet.setColumnWidth((short) 1, (short) (200 * 25));
            spreadSheet.setColumnWidth((short) 2, (short) (200 * 25));
            
            // Parsing XML Document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlDocument);
            NodeList nodeList = document.getElementsByTagName("Book");
            
            // Creating Rows and Cells
            HSSFRow row1 = spreadSheet.createRow(0);
            HSSFCell cell;
            HSSFCell cellID = row1.createCell((short) 0);
            cellID.setCellValue("Book ID");
            HSSFCell cellBookName = row1.createCell((short) 1);
            cellBookName.setCellValue("Book Name");
            HSSFCell cellBookAuthor = row1.createCell((short) 2);
            cellBookAuthor.setCellValue("Book Author");
            
            for(int i = 0; i < nodeList.getLength(); i++){
                HSSFRow row = spreadSheet.createRow(i + 1);
                cell = row.createCell((short) 0);
                cell.setCellValue(nodeList.item(i).getNodeName());
                cell.setCellValue(((Element) (nodeList.item(i))).getAttribute("id"));

                cell = row.createCell((short) 1);
                cell.setCellValue(((Element) (nodeList.item(i)))
                        .getElementsByTagName("BookName").item(0)
                        .getFirstChild().getNodeValue());

                cell = row.createCell((short) 2);
                cell.setCellValue(((Element) (nodeList.item(i)))
                        .getElementsByTagName("BookAuthor").item(0)
                        .getFirstChild().getNodeValue());
            }
            
            // Outputting to Excel spreadsheet
            FileOutputStream output = new FileOutputStream(new File("XMLtoExcel.xls"));
            wb.write(output);
            output.flush();
            output.close();
        }  catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException " + e.getMessage());
        } catch (SAXException e) {
            System.out.println("SAXException " + e.getMessage());
        }
    }

    private List<Book> generateBooksList() {
        List<Book> list = new ArrayList<Book>();
        list.add(new Book());
        list.add(new Book());
        return list;
    }

}
