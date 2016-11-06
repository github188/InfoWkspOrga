package com.sgu.infowksporga.jfx.workspace.dlg.mvc.panel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.GCheckBox;
import com.sgu.core.framework.gui.jfx.control.GComboBox;
import com.sgu.core.framework.gui.jfx.control.GLabel;
import com.sgu.core.framework.gui.jfx.control.GTextField;
import com.sgu.core.framework.gui.jfx.control.list.DefaultListCellFactory;
import com.sgu.core.framework.gui.jfx.control.pane.GGridPane;
import com.sgu.core.framework.gui.jfx.screen.AGController;
import com.sgu.core.framework.gui.jfx.screen.AGModel;
import com.sgu.core.framework.gui.jfx.screen.AGScreen;
import com.sgu.core.framework.gui.jfx.screen.AGView;
import com.sgu.core.framework.gui.jfx.util.UtilControl;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.core.framework.pivot.UserInfo;
import com.sgu.infowksporga.business.entity.enumeration.PartageEnum;
import com.sgu.infowksporga.jfx.component.cbb.CbbOwnerItemVo;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.workspace.dlg.mvc.panel.cbb.CbbPartageItemVo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class ViewWebConfigPanelFxml.
 *
 * @see {ViewComponentAttributesGenerator}
 */
@Slf4j
@Setter
@Getter
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = {// Force /n
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.TEXT, value = "Configuration"), // Force /n
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.STYLE_CSS, value = "-fx-text-fill: #11468E"), // Force /n
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.ICON, value = "/icons/configuration.gif"), // Force /n
              //----------
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "baseFolder" + I18NConstant.TEXT, value = "Base des dossiers :"), // Force /n
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "baseFolder" + I18NConstant.TOOLTIP_TEXT, value = "Utilisées par les vues de type Dossier"), // Force /n
              //----------
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "owner" + I18NConstant.TEXT, value = "Owner :"), // Force /n
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "owner" + I18NConstant.TOOLTIP_TEXT, value = "The owner of this element"), // Force /n
              //----------
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "cbbPartage" + I18NConstant.TEXT, value = "Partage :"), // Force /n
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "cbbPartage" + I18NConstant.TOOLTIP_TEXT,
              value = "Permet de déterminer la visibilité du Workspace"), // Force /n
              //----------
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "wksp.position" + I18NConstant.TEXT, value = "Position :"), // Force /n
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "wksp.position" + I18NConstant.TOOLTIP_TEXT,
              value = "Indique la position (ordre de 0 à n) à laquelle apparaitra ce workspace au sein de sont parent (si vide ou = 0, il sera ajouter au parent à l''index 0)"), // Force /n
})
public class ConfigurationPanelFxml extends AGView<AGScreen, AGModel, AGController> implements Initializable {

  /** The Constant ViewWebConfigPanelFxml.PROPERTIES_PREFIX. */
  public static final String PROPERTIES_PREFIX = "dialog.workspace.panel.configuration.";

  //---------------------------------
  //Configuration Panel components
  //---------------------------------

  @FXML
  private TitledPane ttlPnlWorkspaceConfig;

  @FXML
  private AnchorPane ancPnlWorkspaceConfig;

  @FXML
  private GGridPane grdPnlWorkspaceConfig;

  @FXML
  private GCheckBox chkEnable;

  @FXML
  private GCheckBox chkChildrenAllowed;

  @FXML
  private GLabel lblBaseFolder;

  @FXML
  private GLabel lblWkspPosition;

  @FXML
  private GTextField txtBaseFolder;

  @FXML
  private GComboBox<CbbOwnerItemVo> cbbOwner;

  @FXML
  private GLabel lblOwner;

  @FXML
  private GTextField txtWkspPosition;

  @FXML
  private GLabel lblCbbPartage;

  @FXML
  private GComboBox<CbbPartageItemVo> cbbPartage;

  /*------------------------------------------------------*/
  // ==> Controls not defined in FXML file
  /*------------------------------------------------------*/

  /**
   * The Constructor.
   */
  public ConfigurationPanelFxml() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void createUI() {
    super.createUI();

    cbbOwner.setCellFactory(c -> new DefaultListCellFactory<CbbOwnerItemVo>());
    // The button cell is used to render what is shown in the ComboBox 'button' area.
    cbbOwner.setButtonCell(new DefaultListCellFactory<CbbOwnerItemVo>());

    Platform.runLater(() -> {
      cbbPartage.setCellFactory(c -> new DefaultListCellFactory<CbbPartageItemVo>());
      // The button cell is used to render what is shown in the ComboBox 'button' area.
      cbbPartage.setButtonCell(new DefaultListCellFactory<CbbPartageItemVo>());
    });
  }

  /** {@inheritDoc} */
  @Override
  public void fillUI() {
    super.fillUI();

    cbbPartage.getItems().clear();
    final List<PartageEnum> partageEnums = PartageEnum.getEnumAsOrderedList();
    for (int i = 0; i < partageEnums.size(); i++) {
      final CbbPartageItemVo vo = new CbbPartageItemVo(partageEnums.get(i));
      cbbPartage.getItems().add(vo);
    }

    // TODO Load all Users Informations
    cbbOwner.getItems().clear();
    cbbOwner.getItems().add(new CbbOwnerItemVo(new UserInfo("sguisse", "Guisse", "Sébastien", "sebastien.guisse@gfi.fr", "en", "fr_FR")));
    cbbOwner.getItems().add(new CbbOwnerItemVo(new UserInfo("cdelrue", "Delrue", "Cédric", "cedric.delrue@gfi.fr", "fr_FR", "en_GB")));
    cbbOwner.getItems().add(new CbbOwnerItemVo(new UserInfo("hsouala", "Souala", "Haithem", "haithem.souala@gfi.fr", "fr_FR", "ar_MA")));
    cbbOwner.getItems().add(new CbbOwnerItemVo(new UserInfo("jcatrix", "Catrix", "Julien", "julien.catrix@gfi.fr", "fr_FR", "fr_CH")));
    cbbOwner.getItems().add(new CbbOwnerItemVo(new UserInfo("mnezzari", "Nezzari", "Moustapha", "moustapha.nezzari@gfi.fr", "fr_FR", "ar_DZ")));
    cbbOwner.getItems().add(new CbbOwnerItemVo(new UserInfo("mmoiroux", "Moiroux", "Maxime", "maxime.moiroux@gfi.fr", "fr_FR", "es_ES")));
    cbbOwner.getItems().add(new CbbOwnerItemVo(new UserInfo("pcieslar", "Cieslar", "Paul", "paul.cieslar@gfi.fr", "fr_FR", "pt_PT")));
    cbbOwner.getItems().add(new CbbOwnerItemVo(new UserInfo("hduee", "Duee", "Hervé", "herve.duee@gfi.fr", "fr_FR", "it_IT")));
    cbbOwner.getItems().add(new CbbOwnerItemVo(new UserInfo("jcquentin", "Quentin", "Jean-Christophe", "jean-christophe.quentin@gfi.fr", "fr_FR", "hi_IN")));
    cbbOwner.getItems().add(new CbbOwnerItemVo(new UserInfo("pleriche", "Leriche", "Pierre", "pierre.leriche@gfi.fr", "fr_FR", "ar_QA")));

  }

  /** {@inheritDoc} */
  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    super.initialize(location, resources);

    UtilControl.applyBundleConfigToLabeledControl(PROPERTIES_PREFIX + "title", ttlPnlWorkspaceConfig);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "baseFolder", lblBaseFolder);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "cbbPartage", lblCbbPartage);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "owner", lblOwner);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "wksp.position", lblWkspPosition);

  }

  /** {@inheritDoc} */
  @Override
  public void applyDisplayModeCreate() {
    super.applyDisplayModeCreate();

    // By Default in creation select the public mode
    cbbPartage.getSelectionModel().select(new CbbPartageItemVo(PartageEnum.PUBLIC));

    // By default select the connected user
    cbbOwner.getSelectionModel().select(new CbbOwnerItemVo(GUISessionProxy.getGuiSession().getCurrentUser()));

    chkEnable.setSelected(true);
    chkChildrenAllowed.setSelected(true);

  }

  /** {@inheritDoc} */
  @Override
  public void applyDisplayModeUpdate() {
    super.applyDisplayModeUpdate();

    // Get the position of the workspace in his parent

  }

}
