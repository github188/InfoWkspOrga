package com.sgu.infowksporga.jfx.view.adlg.config.mvc;

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
 * The class <strong>AViewDlgController</strong>.
 */

@Slf4j
@Setter
@Getter
public abstract class AViewDlgController<M extends AViewDlgModel, V extends AViewDlgViewFxml> extends AGController<M, V> {

  /**
   * The Constructor.
   */
  public AViewDlgController() {
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
    final AViewDlgViewFxml viewDlg = view();

    viewDlg.getPnlStyleController().getChkBold().selectedProperty().addListener((observable, oldValue, newValue) -> {
      viewDlg.getPnlStyleController().applyBoldOnTxtStyleRenderer(newValue);
    });

    viewDlg.getPnlStyleController().getChkItalic().selectedProperty().addListener((observable, oldValue, newValue) -> {
      viewDlg.getPnlStyleController().applyItalicOnTxtStyleRenderer(newValue);
    });

    viewDlg.getPnlStyleController().getChkStrike().selectedProperty().addListener((observable, oldValue, newValue) -> {
      viewDlg.getPnlStyleController().applyStrikeOnTxtStyleRenderer(newValue);
    });

    viewDlg.getPnlStyleController().getChkUnderline().selectedProperty().addListener((observable, oldValue, newValue) -> {
      viewDlg.getPnlStyleController().applyUnderlineOnTxtStyleRenderer(newValue);
    });

    // To change the text color
    viewDlg.getPnlStyleController().getCpkColor().valueProperty().addListener((observable, oldValue, newValue) -> {
      viewDlg.getPnlStyleController().applyColorOnTxtStyleRenderer(newValue);
    });

    // To change the background color of the text
    viewDlg.getPnlStyleController().getCpkBgColor().valueProperty().addListener((observable, oldValue, newValue) -> {
      viewDlg.getPnlStyleController().applyBgColorOnTxtStyleRenderer(newValue);
    });

    // Update of Icon for pre-visualisation
    viewDlg.getPnlStyleController().getTxtIcon().focusedProperty().addListener((observable, oldValue, newValue) -> {
      // On focus lost
      if (newValue == false) {
        viewDlg.getPnlStyleController().applyTxtIconUrlOnlblIconRenderer();
      }
    });
    viewDlg.getPnlStyleController().getTxtIcon().textProperty().addListener((observable, oldValue, newValue) -> {
      viewDlg.getPnlStyleController().applyTxtIconUrlOnlblIconRenderer();
    });

  }

  /** {@inheritDoc} */
  @Override
  public void bindComponentsWithPojo() {
    super.bindComponentsWithPojo();

    initIdendificationCardPanel();
    initStylePanelBindings();
    initHorodatePanelBindings();
    initConfigurationPanelBindings();

  }

  /**
   * Inits the idendification card panel.
   */
  protected void initIdendificationCardPanel() {
    final AViewDlgViewFxml viewDlg = view();
    final AViewDlgModel mdl = model();

    Bindings.bindBidirectional(viewDlg.getPnlIdentityCardController().getLblIdValue().textProperty(), mdl.getIdProperty(), new NumberStringConverter());
    viewDlg.getPnlIdentityCardController().getTxtName().textProperty().bindBidirectional(mdl.getNameProperty());
    viewDlg.getPnlIdentityCardController().getTxtCategory().textProperty().bindBidirectional(mdl.getCategoryProperty());
    viewDlg.getPnlIdentityCardController().getTxtTags().textProperty().bindBidirectional(mdl.getTagsProperty());
  }

  /**
   * Inits the configuration panel bindings.
   */
  protected abstract void initConfigurationPanelBindings();

  /**
   * Inits the style panel bindings.
   */
  private void initStylePanelBindings() {
    // First bind component between them to display result of selected style option
    final AViewDlgViewFxml viewDlg = view();
    final AViewDlgModel mdl = model();

    viewDlg.getPnlStyleController().getChkBold().selectedProperty().bindBidirectional(mdl.getBoldProperty());
    viewDlg.getPnlStyleController().getChkItalic().selectedProperty().bindBidirectional(mdl.getItalicProperty());
    viewDlg.getPnlStyleController().getChkStrike().selectedProperty().bindBidirectional(mdl.getStrikeProperty());
    viewDlg.getPnlStyleController().getChkUnderline().selectedProperty().bindBidirectional(mdl.getUnderlineProperty());
    viewDlg.getPnlStyleController().getTxtIcon().textProperty().bindBidirectional(mdl.getIconProperty());

    Bindings.bindBidirectional(mdl.getColorProperty(), viewDlg.getPnlStyleController().getCpkColor().valueProperty(), new ColorStringConverter(ColorToStringMode.HTML));
    Bindings.bindBidirectional(mdl.getBgColorProperty(), viewDlg.getPnlStyleController().getCpkBgColor().valueProperty(),
                               new ColorStringConverter(ColorToStringMode.HTML));

  }

  /**
   * Inits the horodate panel bindings.
   */
  protected void initHorodatePanelBindings() {
    final AViewDlgViewFxml viewDlg = view();
    final AViewDlgModel mdl = model();

    viewDlg.getPnlHorodateController().getLblCreatedByValue().textProperty().bindBidirectional(mdl.getCreatedByProperty());
    viewDlg.getPnlHorodateController().getLblLastModifiedByValue().textProperty().bindBidirectional(mdl.getLastModifiedByProperty());

    viewDlg.getPnlHorodateController().getLblCreatedDateValue().textProperty()
           .bind(Bindings.createStringBinding(() -> UtilAppUI.formatDate(mdl.getCreatedDateProperty()), mdl.getCreatedDateProperty()));

    viewDlg.getPnlHorodateController().getLblLastModifiedDateValue().textProperty()
           .bind(Bindings.createStringBinding(() -> UtilAppUI.formatDate(mdl.getLastModifiedDateProperty()), mdl.getLastModifiedDateProperty()));

  }

}
