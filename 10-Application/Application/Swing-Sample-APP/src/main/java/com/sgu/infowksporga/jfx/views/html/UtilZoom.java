package com.sgu.infowksporga.jfx.views.html;

import java.awt.Point;

import javax.swing.SwingUtilities;
import javax.swing.text.Document;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.textfield.GNumericField;
import com.sgu.core.framework.gui.swing.textfield.formatter.NumericalFormatter;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

/**
 * Description : UtilZoom class<br>
 */
public final class UtilZoom {

  /**
   * ZOOM MAXIMUM
   */
  public static final double ZOOM_MAX = 4.0; // 400 %

  /**
   * Constructor<br>
   */
  private UtilZoom() {
    super();
  }

  /**
   * Description : zoomIn method <br>
   */
  public static void zoomIn(final HtmlView htmlView, final double scaleToAdd) {
    final Document doc = htmlView.getHtmlContent().getDocument();
    Double zoom = (Double) doc.getProperty("ZOOM_FACTOR");
    zoom += scaleToAdd;
    if (zoom <= ZOOM_MAX) {
      final int pourcentage = (int) (zoom * 100);
      htmlView.getTxtZoom().setText("" + pourcentage);
      doc.putProperty("ZOOM_FACTOR", zoom);

      refreshView(htmlView);
    }
  }

  /**
   * Description : zoomOut method <br>
   */
  public static void zoomOut(final HtmlView htmlView, final double scaleToRemove) {
    final Document doc = htmlView.getHtmlContent().getDocument();
    Double zoom = (Double) doc.getProperty("ZOOM_FACTOR");
    zoom -= scaleToRemove;
    if (zoom > 0) {
      final int pourcentage = (int) (zoom * 100);
      htmlView.getTxtZoom().setText("" + pourcentage);
      doc.putProperty("ZOOM_FACTOR", zoom);

      refreshView(htmlView);
    }
  }

  /**
   * Description : zoomTo method <br>
   *
   * @param htmlView
   * @param scale in % --> 100 ; 150; 200; 50 ; ...
   */
  public static void zoomTo(final HtmlView htmlView, int zoomInPercent) {
    if (zoomInPercent == 0) {
      zoomInPercent = 100;
    }
    final double scale = (zoomInPercent * 1.0) / 100;

    htmlView.getTxtZoom().setValue(zoomInPercent);

    final Document doc = htmlView.getHtmlContent().getDocument();
    doc.putProperty("ZOOM_FACTOR", scale);

    refreshView(htmlView);
  }

  /**
   * Description : refreshView method <br>
   *
   * @param htmlView
   */
  public static void refreshView(final HtmlView htmlView) {
    //refresh view
    final Point viewPosition = htmlView.getHtmlContentScrollPane().getViewport().getViewPosition(); // Keep scroll position
    htmlView.getHtmlContent().setText(htmlView.getHtmlContent().getText());

    // Force scroll to top of the editor
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        htmlView.getHtmlContentScrollPane().getViewport().setViewPosition(new Point(0, 0));
      }
    });
  }

  /**
   * Description : getZoomTextField method <br>
   * Used in configuration html view title bar and in configuration Dialog
   *
   * @return the configured Numerical field for zoom
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "html.view.txt.scale.text", value = ""), // Force \n
                @I18nProperty(key = "html.view.txt.scale.tooltip", value = "Valeur possible entre 1 et 400 %"), // Force \n
  })
  public static GNumericField getZoomTextField() {
    final GNumericField txtScale = new GNumericField();

    txtScale.setText(I18nHelperApp.getMessage("html.view.txt.scale.text"));
    txtScale.setToolTipText("html.view.txt.scale.tooltip");
    txtScale.setColumns(3);
    txtScale.setNumericFieldType(GNumericField.INTEGER_FIELD);

    final NumericalFormatter numFormatter = new NumericalFormatter();
    numFormatter.setNumericFormatter(I18NConstant.getNumericFormat());
    txtScale.getNumericDocument().setFormatter(numFormatter);

    /*
     * txtScale.getNumericDocument().getValidCharacters().remove(new Character('.'));
     * txtScale.getNumericDocument().getValidCharacters().remove(new Character('-'));
     */

    txtScale.getNumericDocument().getRange().setMin(1);
    txtScale.getNumericDocument().getRange().setMax((int) UtilZoom.ZOOM_MAX * 100);

    return txtScale;
  }
}
