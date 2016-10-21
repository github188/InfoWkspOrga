package com.sgu.infowksporga.jfx.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
    List<List<String>> labelFields = new ArrayList<>();

    System.out.println("//---------------------------------");
    System.out.println("// Identity Card Panel components ");
    System.out.println("//---------------------------------");
    String fxmlFile = "G:\\Projects\\400-InfoWkspOrga\\10-Application\\Application\\InfoWkspOrga-APP\\src\\main\\java\\com\\sgu\\infowksporga\\jfx\\component\\identityCardPanel.fxml";
    List<String> labels = createViewFields(fxmlFile);
    labelFields.add(labels);

    System.out.println("\n\n//---------------------------------");
    System.out.println("// Configuration Panel components ");
    System.out.println("//---------------------------------");
    fxmlFile = "G:\\Projects\\400-InfoWkspOrga\\10-Application\\Application\\InfoWkspOrga-APP\\src\\main\\java\\com\\sgu\\infowksporga\\jfx\\workspace\\dlg\\mvc\\panel\\configurationPanel.fxml";
    labels = createViewFields(fxmlFile);
    labelFields.add(labels);

    System.out.println("\n\n//---------------------------------");
    System.out.println("// Reference Panel components ");
    System.out.println("//---------------------------------");
    fxmlFile = "G:\\Projects\\400-InfoWkspOrga\\10-Application\\Application\\InfoWkspOrga-APP\\src\\main\\java\\com\\sgu\\infowksporga\\jfx\\workspace\\dlg\\mvc\\panel\\referencePanel.fxml";
    labels = createViewFields(fxmlFile);
    labelFields.add(labels);

    System.out.println("\n\n//---------------------------------");
    System.out.println("// Style  Panel components ");
    System.out.println("//---------------------------------");
    fxmlFile = "G:\\Projects\\400-InfoWkspOrga\\10-Application\\Application\\InfoWkspOrga-APP\\src\\main\\java\\com\\sgu\\infowksporga\\jfx\\component\\stylePanel.fxml";
    labels = createViewFields(fxmlFile);
    labelFields.add(labels);

    System.out.println("\n\n//---------------------------------");
    System.out.println("// Horodate Panel components ");
    System.out.println("//---------------------------------");
    fxmlFile = "G:\\Projects\\400-InfoWkspOrga\\10-Application\\Application\\InfoWkspOrga-APP\\src\\main\\java\\com\\sgu\\infowksporga\\jfx\\component\\horodatePanel.fxml";
    labels = createViewFields(fxmlFile);
    labelFields.add(labels);

    System.out.println("\n\n");
    for (List<String> labelLst : labelFields) {
      buildI18NConfig(labelLst);
      System.out.println("\n\n");
    }

    System.out.println("\n\n");
    for (List<String> labelLst : labelFields) {
      applyI18nOnLabels(labelLst);
      System.out.println("\n\n");
    }

  }

  /**
   * Apply i18n on labels.
   *
   * @param labels the labels
   */
  private static void applyI18nOnLabels(List<String> labels) {
    for (int i = 0; i < labels.size(); i++) {
      String labelComponent = labels.get(i);
      String labelI18N = UtilString.replaceFirstString(labelComponent, "lbl", "");
      labelI18N = UtilString.lowerCaseFirstCharacter(labelI18N);
      System.out.println("UtilControl.applyBundleConfigToLabel (PROPERTIES_PREFIX + \"" + labelI18N + "\", " + labelComponent + ");");
    }

  }

  /**
   * Builds the i18 n config.
   *
   * @param labels the labels
   */
  private static void buildI18NConfig(List<String> labels) {
    System.out.println("@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = \"i18n\", fileName = \"application-prez\",");
    System.out.println("properties = { // label create");

    for (int i = 0; i < labels.size(); i++) {
      String label = labels.get(i);
      label = UtilString.replaceFirstString(label, "lbl", "");
      label = UtilString.lowerCaseFirstCharacter(label);

      System.out.println("               @I18nProperty(key = PROPERTIES_PREFIX +\"" + label + "\" + I18NConstant.TEXT, value = \"???\"), // Force /n");
      System.out.println("               @I18nProperty(key = PROPERTIES_PREFIX +\"" + label + "\" + I18NConstant.TOOLTIP_TEXT, value = \"???\"), // Force /n");
    }
    System.out.println("})");

  }

  /**
   * Creates the view fields.
   *
   * @param fxmlFile the fxml file
   * @return the list< string>
   */
  public static List<String> createViewFields(final String fxmlFile) {
    List<String> labels = new ArrayList<>();

    try {
      final BufferedReader br = new BufferedReader(new FileReader(fxmlFile));
      String line = "";
      while ((line = br.readLine()) != null) {

        String classAttr = "private ";
        if (line.contains("fx:id")) {
          final String type = UtilString.getFirstStringBetween(line, "<", " fx:id");
          String label = UtilString.getFirstStringBetween(line, "fx:id=\"", "\" ");
          if (label == null) {
            label = UtilString.getFirstStringBetween(line, "fx:id=\"", "\">");
          }

          classAttr += type + " " + label + ";";
          System.out.println("");
          System.out.println("@FXML");
          System.out.println(classAttr);

          if ((type.contains("label") || type.contains("Label")) && label.endsWith("Value") == false) {
            labels.add(label);
          }
        }
      }
    } catch (final Exception e) {
      e.printStackTrace();
    }

    return labels;
  }

}
