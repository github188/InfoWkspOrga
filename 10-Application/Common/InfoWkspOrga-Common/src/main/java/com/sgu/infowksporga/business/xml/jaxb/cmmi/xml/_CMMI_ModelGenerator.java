package com.sgu.infowksporga.business.xml.jaxb.cmmi.xml;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sgu.core.framework.exception.TechnicalException;

// TODO: Auto-generated Javadoc
/**
 * Description : _CMMI_ModelGenerator class<br>.
 */
public class _CMMI_ModelGenerator {

  /** The Class Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(_CMMI_ModelGenerator.class);

  /** The buffer. */
  private static StringBuilder buffer = new StringBuilder();

  /** The reader. */
  private static BufferedReader reader;

  /**
   * The main method.
   *
   * @param args the args
   */
  public static void main(String[] args) {
    HashMap<String, Category> categories = new HashMap(5);

    Process process = null;
    Goal goal = null;
    String[] rows;

    readNextXWorkbookCsvLines(500000);

    String filePart = buffer.toString();
    rows = filePart.split("£££");

    for (int i = 0; i < rows.length; i++) {

      String[] cmmiLine = rows[i].split(";");
      if (isNewPractice(cmmiLine)) {
        System.out.println("      Practice    " + cmmiLine[7]);
        Practice practice = new Practice();
        practice.setCode(cmmiLine[7]);
        Label lbl = new Label();
        lbl.setEn(cmmiLine[8]);
        lbl.setFr(cmmiLine[14]);
        practice.setLabel(lbl);

        Description lngText = new Description();
        lngText.setEn(cmmiLine[9]);
        lngText.setFr(cmmiLine[15]);
        practice.setDescription(lngText);
        DirectQuestion dq = new DirectQuestion();
        dq.setEn(cmmiLine[10]);
        dq.setFr(cmmiLine[16]);
        practice.setDirectQuestion(dq);
        IndirectQuestion iq = new IndirectQuestion();
        iq.setEn(cmmiLine[11]);
        iq.setFr(cmmiLine[17]);
        practice.setIndirectQuestion(iq);

        goal.getPractice().add(practice);
      }
      else if (isNewGoal(cmmiLine)) {
        System.out.println("    Goal    " + cmmiLine[6]);
        goal = new Goal();
        goal.setCode(cmmiLine[6]);
        Label lbl = new Label();
        lbl.setEn(cmmiLine[7]);
        lbl.setFr(cmmiLine[13]);
        goal.setLabel(lbl);

        Description lngText = new Description();
        lngText.setEn(cmmiLine[8]);
        lngText.setFr(cmmiLine[14]);
        goal.setDescription(lngText);

        process.getGoal().add(goal);
      }
      else if (isNewProcess(cmmiLine)) {
        process = new Process();
        process.setCode(cmmiLine[5]);
        Label lbl = new Label();
        lbl.setEn(cmmiLine[6]);
        lbl.setFr(cmmiLine[12]);
        process.setLabel(lbl);

        Description lngText = new Description();
        lngText.setEn(cmmiLine[8]);
        lngText.setFr(cmmiLine[14]);
        process.setDescription(lngText);

        process.setStaged(cmmiLine[4]);

        String categoryName = cmmiLine[3];
        Category category = (Category) categories.get(categoryName);
        if (category == null) {
          category = new Category();
          category.setName(categoryName);
          categories.put(categoryName, category);
          System.out.println("Category    " + categoryName);
        }
        List<Process> processLst = category.getProcess();
        processLst.add(process);
        System.out.println("  Process    " + process.getCode());
      }
    }
    //***************************************************************
    //saveCmmiModel(categories);
    //***************************************************************
  }

  /**
   * Save cmmi model.
   *
   * @param categories the categories
   */
  private static void saveCmmiModel(HashMap<String, Category> categories) {
    Cmmi cmmi = new Cmmi();
    cmmi.getCategory().addAll(categories.values());
    cmmi.setCode("CMMI-DEV-V1.3");
    cmmi.setFor("Capability Maturity Model Integration� for Development");
    cmmi.setVersion(new BigDecimal("1.3"));
    try {
      JAXBContext jaxbCtx = JAXBContext.newInstance(cmmi.getClass().getPackage().getName());
      Marshaller marshaller = jaxbCtx.createMarshaller();
      marshaller.setProperty("jaxb.encoding", "UTF-8");
      marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
      marshaller.marshal(cmmi, System.out);
      OutputStream os = new FileOutputStream("Cmmi-Model-test.xml");
      marshaller.marshal(cmmi, os);
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger("global").log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Checks if is new process.
   *
   * @param cmmiLine the cmmi line
   * @return true, if checks if is new process
   */
  private static boolean isNewProcess(String[] cmmiLine) {
    if (cmmiLine[7].equals("...")) {
      return true;
    }
    return false;
  }

  /**
   * Checks if is new goal.
   *
   * @param cmmiLine the cmmi line
   * @return true, if checks if is new goal
   */
  private static boolean isNewGoal(String[] cmmiLine) {
    if ((cmmiLine[5].equals("...")) && (!cmmiLine[6].equals("...")) && (!cmmiLine[7].equals("..."))) {
      return true;
    }
    return false;
  }

  /**
   * Checks if is new practice.
   *
   * @param cmmiLine the cmmi line
   * @return true, if checks if is new practice
   */
  private static boolean isNewPractice(String[] cmmiLine) {
    if (((cmmiLine[5].equals("...")) && (cmmiLine[6].equals("...")) && (cmmiLine[7].contains("-SP"))) || (cmmiLine[7].contains("-GP"))) {
      return true;
    }
    return false;
  }

  /**
   * Read next x workbook csv lines.
   *
   * @param nbLineToRead the nb line to read
   * @return the int
   */
  private static int readNextXWorkbookCsvLines(int nbLineToRead) {
    int nbReadedLines = 0;
    try {
      if (reader == null) {
        String dbWorkbookCsvFile = "G:/Projects/200-InfoWkspOrga-RCP/Common/InfoWkspOrga-Common/plugins/InfoWkspOrga-Common/"
                                   + "src/main/resources/forCmmiReferentialGeneration/CMMI-DEV-v1.3.csv";
        //Charset.forName("iso-8859-1")
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(dbWorkbookCsvFile), Charset.forName("UTF-8")));
      }
      String strLine = "";

      nbReadedLines = 0;
      do {
        buffer.append(strLine).append("\n");
        nbReadedLines++;
        if ((strLine = reader.readLine()) == null) {
          break;
        }
      } while (nbReadedLines <= nbLineToRead);
    } catch (Exception e) {
      throw new TechnicalException(e);
    }
    return nbReadedLines;
  }

}
