package com.sgu.infowksporga.jfx.util;

import java.io.BufferedReader;
import java.io.FileReader;

import com.sgu.core.framework.util.UtilString;

/**
 * The Class ViewComponentAttributesGenerator.
 */
public class ViewComponentAttributesGenerator {

  /**
   * The Constructor.
   */
  public ViewComponentAttributesGenerator() {
  }

  /**
   * The main method.
   *
   * @param args the args
   */
  public static void main(final String[] args) {
    final String fxmlFile = "G:/Projects/400-InfoWkspOrga/10-Application/Application/InfoWkspOrga-APP/src/main/java/com/sgu/infowksporga/jfx/main/ui/application.fxml";

    try {
      final BufferedReader br = new BufferedReader(new FileReader(fxmlFile));
      String line = "";
      while ((line = br.readLine()) != null) {

        String classAttr = "private ";
        if (line.contains("fx:id")) {
          final String type = UtilString.getFirstStringBetween(line, "<", " fx:id");
          final String label = UtilString.getFirstStringBetween(line, "fx:id=\"", "\" ");

          classAttr += type + " " + label + ";";
          System.out.println("");
          System.out.println("@FXML");
          System.out.println(classAttr);
        }
      }
    } catch (final Exception e) {
      e.printStackTrace();
    }

  }

}
