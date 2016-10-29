package com.sgu.infowksporga.jfx.workspace.dlg.mvc;

import com.sgu.core.framework.gui.jfx.binding.ColorStringConverter;
import com.sgu.core.framework.gui.jfx.binding.ColorStringConverter.ColorToStringMode;
import com.sgu.core.framework.gui.jfx.screen.AGController;
import com.sgu.infowksporga.jfx.util.UtilAppUI;

import javafx.beans.binding.Bindings;
import javafx.util.converter.NumberStringConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The class <strong>WorkspacePanelController</strong>.
 */

@Slf4j
@Setter
@Getter
public final class WorkspaceDlgController extends AGController<WorkspaceDlgModel, WorkspaceDlgViewFxml> {

  /**
   * The Constructor.
   */
  public WorkspaceDlgController() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void createListeners() {
    super.createListeners();

    //---------------------------------------------------
    // initialize Actions
    // set empty String for toolbar buttons
    //---------------------------------------------------
    view().getPnlStyleController().getChkBold().selectedProperty().addListener((observable, oldValue, newValue) -> {
      view().getPnlStyleController().applyBoldOnTxtStyleRenderer(newValue);
    });

    view().getPnlStyleController().getChkItalic().selectedProperty().addListener((observable, oldValue, newValue) -> {
      view().getPnlStyleController().applyItalicOnTxtStyleRenderer(newValue);
    });

    view().getPnlStyleController().getChkStrike().selectedProperty().addListener((observable, oldValue, newValue) -> {
      view().getPnlStyleController().applyStrikeOnTxtStyleRenderer(newValue);
    });

    view().getPnlStyleController().getChkUnderline().selectedProperty().addListener((observable, oldValue, newValue) -> {
      view().getPnlStyleController().applyUnderlineOnTxtStyleRenderer(newValue);
    });

    // To change the text color
    view().getPnlStyleController().getCpkColor().valueProperty().addListener((observable, oldValue, newValue) -> {
      view().getPnlStyleController().applyColorOnTxtStyleRenderer(newValue);
    });

    view().getPnlStyleController().getCpkBgColor().valueProperty().addListener((observable, oldValue, newValue) -> {
      view().getPnlStyleController().applyBgColorOnTxtStyleRenderer(newValue);
    });

    // Update of Icon for pre-visualisation
    view().getPnlStyleController().getTxtIcon().focusedProperty().addListener((observable, oldValue, newValue) -> {
      // On focus lost
      if (newValue == false) {
        view().getPnlStyleController().applyTxtIconUrlOnlblIconRenderer();
      }
    });

  }

  /** {@inheritDoc} */
  @Override
  public void bindComponentsWithPojo() {
    super.bindComponentsWithPojo();

    initIdendificationCardPanel();
    initReferencePanelBindings();
    initConfigurationPanelBindings();
    initStylePanelBindings();
    initHorodatePanelBindings();

  }

  /**
   * Inits the idendification card panel.
   */
  private void initIdendificationCardPanel() {
    view().getPnlIdentityCardController().getLblIdValue().textProperty().bindBidirectional(model().getIdProperty());
    view().getPnlIdentityCardController().getTxtName().textProperty().bindBidirectional(model().getNameProperty());
    view().getPnlIdentityCardController().getTxtCategory().textProperty().bindBidirectional(model().getCategoryProperty());
    view().getPnlIdentityCardController().getTxtTags().textProperty().bindBidirectional(model().getTagsProperty());

    //HtmlEdtDescription :  It is not possible since HTMLEditor doesn't extend TextInputControl and doesn't inherit the textProperty().
    // Use setHtmlText(String) and getHtmlText() directly
    //view().getPnlIdentityCardController().getTxtHtmDescription().textProperty().bindBidirectional(model().getDescriptionProperty());

  }

  /**
   * Inits the configuration panel bindings.
   */
  private void initConfigurationPanelBindings() {
    Bindings.bindBidirectional(view().getPnlConfigurationController().getTxtWkspPosition().textProperty(), model().getWkspPositionProperty(),
                               new NumberStringConverter());

    view().getPnlConfigurationController().getChkEnable().selectedProperty().bindBidirectional(model().getEnableProperty());
    view().getPnlConfigurationController().getChkChildrenAllowed().selectedProperty().bindBidirectional(model().getChildrenAllowedProperty());

    view().getPnlConfigurationController().getTxtBaseFolder().textProperty().bindBidirectional(model().getBaseFolderProperty());
    view().getPnlConfigurationController().getCbbOwner().valueProperty().bindBidirectional(model().getOwnerProperty());
    view().getPnlConfigurationController().getCbbPartage().valueProperty().bindBidirectional(model().getPartageProperty());
  }

  /**
   * Inits the reference panel bindings.
   */
  @SuppressWarnings("unchecked")
  private void initReferencePanelBindings() {
    view().getPnlReferenceController().getCbbParent().valueProperty().bindBidirectional(model().getParentProperty());
    view().getPnlReferenceController().getCbbMaster().valueProperty().bindBidirectional(model().getMasterProperty());
    view().getPnlReferenceController().getCbbProject().valueProperty().bindBidirectional(model().getProjectProperty());
    view().getPnlReferenceController().getTxtCustomer().textProperty().bindBidirectional(model().getCustomerProperty());
  }

  /**
   * Inits the style panel bindings.
   */
  private void initStylePanelBindings() {
    // First bind component between them to display result of selected style option
    view().getPnlStyleController().getChkBold().selectedProperty().bindBidirectional(model().getBoldProperty());
    view().getPnlStyleController().getChkItalic().selectedProperty().bindBidirectional(model().getItalicProperty());
    view().getPnlStyleController().getChkStrike().selectedProperty().bindBidirectional(model().getStrikeProperty());
    view().getPnlStyleController().getChkUnderline().selectedProperty().bindBidirectional(model().getUnderlineProperty());
    view().getPnlStyleController().getTxtIcon().textProperty().bindBidirectional(model().getIconProperty());

    Bindings.bindBidirectional(model().getColorProperty(), view().getPnlStyleController().getCpkColor().valueProperty(),
                               new ColorStringConverter(ColorToStringMode.HTML));
    Bindings.bindBidirectional(model().getBgColorProperty(), view().getPnlStyleController().getCpkBgColor().valueProperty(),
                               new ColorStringConverter(ColorToStringMode.HTML));

  }

  /**
   * Inits the horodate panel bindings.
   */
  private void initHorodatePanelBindings() {
    view().getPnlHorodateController().getLblCreatedByValue().textProperty().bindBidirectional(model().getCreatedByProperty());
    view().getPnlHorodateController().getLblLastModifiedByValue().textProperty().bindBidirectional(model().getLastModifiedByProperty());

    view().getPnlHorodateController().getLblCreatedDateValue().textProperty()
          .bind(Bindings.createStringBinding(() -> UtilAppUI.formatDate(model().getCreatedDateProperty()), model().getCreatedDateProperty()));

    view().getPnlHorodateController().getLblLastModifiedDateValue().textProperty()
          .bind(Bindings.createStringBinding(() -> UtilAppUI.formatDate(model().getLastModifiedDateProperty()), model().getLastModifiedDateProperty()));

  }

}
