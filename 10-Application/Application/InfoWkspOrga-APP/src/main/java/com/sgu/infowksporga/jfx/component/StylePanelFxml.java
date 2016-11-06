package com.sgu.infowksporga.jfx.component;

import java.net.URL;
import java.util.ResourceBundle;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.GCheckBox;
import com.sgu.core.framework.gui.jfx.control.GLabel;
import com.sgu.core.framework.gui.jfx.control.GTextField;
import com.sgu.core.framework.gui.jfx.control.pane.GGridPane;
import com.sgu.core.framework.gui.jfx.screen.AGController;
import com.sgu.core.framework.gui.jfx.screen.AGModel;
import com.sgu.core.framework.gui.jfx.screen.AGScreen;
import com.sgu.core.framework.gui.jfx.screen.AGView;
import com.sgu.core.framework.gui.jfx.util.UtilControl;
import com.sgu.core.framework.gui.jfx.util.UtilGUI;
import com.sgu.core.framework.gui.jfx.util.UtilStyle;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.jfx.perspective.tree.vo.WorkspaceItemVo;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.view.web.dlg.mvc.WebViewDlgModel;
import com.sgu.infowksporga.jfx.workspace.dlg.mvc.WorkspaceDlgModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class StylePanelFxml.
 *
 * @see {ViewComponentAttributesGenerator}
 */
@Slf4j
@Setter
@Getter
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.TEXT, value = "Style"), // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.STYLE_CSS, value = "-fx-text-fill: #11468E"), // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.ICON, value = "/icons/color_wheel.png"), // Force /n
              //----------
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "textStyle" + I18NConstant.TEXT, value = "Style :"), // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "textStyle" + I18NConstant.TOOLTIP_TEXT, value = "Liste des styles à appliquer au libellé"), // Force /n
              //----------
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "bold" + I18NConstant.TEXT, value = "Gras"), // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "bold" + I18NConstant.TOOLTIP_TEXT, value = "Gras"), // Force /n
              //----------
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "italic" + I18NConstant.TEXT, value = "Italique"), // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "italic" + I18NConstant.TOOLTIP_TEXT, value = "Italique"), // Force /n
              //----------
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "underline" + I18NConstant.TEXT, value = "Souligné"), // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "underline" + I18NConstant.TOOLTIP_TEXT, value = "Souligné"), // Force /n
              //----------
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "strike" + I18NConstant.TEXT, value = "Barré"), // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "strike" + I18NConstant.TOOLTIP_TEXT, value = "Barré"), // Force /n
              //----------
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "color" + I18NConstant.TEXT, value = "Couleur du text :"), // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "color" + I18NConstant.TOOLTIP_TEXT, value = "Couleur du text"), // Force /n
              //----------
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "bgColor" + I18NConstant.TEXT, value = "Couleur du fond :"), // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "bgColor" + I18NConstant.TOOLTIP_TEXT, value = "Couleur du fond"), // Force /n
              //----------
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "icon" + I18NConstant.TEXT, value = "Icône :"), // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "icon" + I18NConstant.TOOLTIP_TEXT, value = "Icône ajouté à côté du libellé"), // Force /n
              //----------
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "iconRenderer" + I18NConstant.TEXT, value = ""), // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "iconRenderer" + I18NConstant.TOOLTIP_TEXT, value = "Affiche l'icône si celui-ci est retrouvé"), // Force /n

})
public class StylePanelFxml extends AGView<AGScreen, AGModel, AGController> implements Initializable {

  /** The Constant PROPERTIES_PREFIX. */
  public static final String PROPERTIES_PREFIX = "panel.style.";

  //---------------------------------
  //Style  Panel components
  //---------------------------------

  @FXML
  private TitledPane ttlPnlStyle;

  @FXML
  private AnchorPane ancPnlStyle;

  @FXML
  private GGridPane grdPnlStyle;

  @FXML
  private GCheckBox chkBold;

  @FXML
  private GCheckBox chkItalic;

  @FXML
  private GCheckBox chkStrike;

  @FXML
  private GCheckBox chkUnderline;

  @FXML
  private GLabel lblColor;

  @FXML
  private GLabel lblBgColor;

  @FXML
  private Text txtStyleRenderer;

  @FXML
  private GLabel lblIcon;

  @FXML
  private GTextField txtIcon;

  @FXML
  private GLabel lblIconRenderer;

  @FXML
  private GLabel lblTextStyle;

  @FXML
  private ColorPicker cpkColor;

  @FXML
  private ColorPicker cpkBgColor;

  /*------------------------------------------------------*/
  // ==> Controls not defined in FXML file
  /*------------------------------------------------------*/

  /**
   * The Constructor.
   */
  public StylePanelFxml() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void createUI() {
    super.createUI();

  }

  /** {@inheritDoc} */
  @Override
  public void fillUI() {
    super.fillUI();

  }

  /** {@inheritDoc} */
  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    super.initialize(location, resources);

    UtilControl.applyBundleConfigToLabeledControl(PROPERTIES_PREFIX + "title", ttlPnlStyle);

    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "color", lblColor);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "bgColor", lblBgColor);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "icon", lblIcon);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "iconRenderer", lblIconRenderer);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "textStyle", lblTextStyle);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "bold", chkBold);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "italic", chkItalic);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "underline", chkUnderline);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "strike", chkStrike);

  }

  /**
   * Apply bold on txt style renderer.
   * Use by controller to bind chkbox with TxtStyleRenderer
   *
   * @param bool the bool or remove it
   */
  public void applyBoldOnTxtStyleRenderer(final boolean addStyle) {
    applyStyleOnTxtStyleRenderer(UtilStyle.BOLD_FX_CSS, addStyle);
  }

  /**
   * Apply italic on txt style renderer.
   *
   * @param addStyle the add style or remove it
   */
  public void applyItalicOnTxtStyleRenderer(final boolean addStyle) {
    applyStyleOnTxtStyleRenderer(UtilStyle.ITALIC_FX_CSS, addStyle);
  }

  /**
   * Apply strike on txt style renderer.
   *
   * @param addStyle the add style
   */
  public void applyStrikeOnTxtStyleRenderer(final boolean addStyle) {
    applyStyleOnTxtStyleRenderer(UtilStyle.STRIKE_FX_CSS, addStyle);
  }

  /**
   * Apply underline on txt style renderer.
   *
   * @param addStyle the add style or remove it
   */
  public void applyUnderlineOnTxtStyleRenderer(final boolean addStyle) {
    applyStyleOnTxtStyleRenderer(UtilStyle.UNDERLINE_FX_CSS, addStyle);
  }

  /**
   * Apply style on txt style renderer.
   *
   * @param style the style
   * @param addStyle If true add the style to the TxtStyleRenderer, If false it remove the style from TxtStyleRenderer
   */
  public void applyStyleOnTxtStyleRenderer(final String style, final boolean addStyle) {
    String txtNewStyle = txtStyleRenderer.getStyle();
    if (addStyle) { // Add Style
      txtNewStyle += style;
    }
    else { // Remove style
      txtNewStyle = UtilString.replaceString(txtNewStyle, style, "");
    }

    txtStyleRenderer.setStyle(txtNewStyle);
  }

  /**
   * Apply color on txt style renderer.
   *
   * @param htmlColorCode the html color code rgb format
   */
  public void applyColorOnTxtStyleRenderer(final Color colorCode) {
    txtStyleRenderer.setFill(colorCode);
  }

  /**
   * Apply bg color on txt style renderer.
   *
   * @param colorCode the color code
   */
  public void applyBgColorOnTxtStyleRenderer(final Color colorCode) {
    final String bgColorStyle = UtilStyle.getStyleForBackgroundColor(UtilStyle.getHTMLColorAsString(colorCode));

    applyStyleOnTxtStyleRenderer(bgColorStyle, true);
  }

  /**
   * Apply txt icon url onlbl icon renderer.
   */
  public void applyTxtIconUrlOnlblIconRenderer() {
    lblIconRenderer.setGraphic(UtilGUI.getImageView(txtIcon.getText()));
  }

  /**
   * Apply common initialization.
   */
  private void applyCommonInitialization() {
    // TODO Auto-generated method stub
    // Fill the color of the workspace
    final WorkspaceItemVo workspaceItemVo = GUISessionProxy.getCurrentWorkspace().getWorkspaceItemVo();
    final String workspaceNameColor = workspaceItemVo.getWorkspace().getColor();

    if (model() instanceof WorkspaceDlgModel) {
      ((WorkspaceDlgModel) model()).getColorProperty().setValue(workspaceNameColor);
    }
    else if (model() instanceof WebViewDlgModel) {
      ((WebViewDlgModel) model()).getColorProperty().setValue(workspaceNameColor);
    }

    final String icon = workspaceItemVo.getWorkspace().getIcon();
    if (UtilString.isNotBlank(icon)) {
      lblIconRenderer.setGraphic(UtilGUI.getImageView(icon));
    }
    else {
      lblIconRenderer.setGraphic(null);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void applyDisplayModeConsult() {
    super.applyDisplayModeConsult();
    applyCommonInitialization();
  }

  /** {@inheritDoc} */
  @Override
  public void applyDisplayModeDelete() {
    super.applyDisplayModeDelete();
    applyCommonInitialization();
  }

  /** {@inheritDoc} */
  @Override
  public void applyDisplayModeUpdate() {
    super.applyDisplayModeUpdate();
    applyCommonInitialization();

  }

  /** {@inheritDoc} */
  @Override
  public void applyDisplayModeCopy() {
    super.applyDisplayModeCopy();
    applyCommonInitialization();
  }

  /** {@inheritDoc} */
  @Override
  public void applyDisplayModeCreate() {
    super.applyDisplayModeCreate();

    // set to the default color
    cpkColor.setValue(Color.BLACK);
    // set to the default color
    cpkBgColor.setValue(null);
  }

}
