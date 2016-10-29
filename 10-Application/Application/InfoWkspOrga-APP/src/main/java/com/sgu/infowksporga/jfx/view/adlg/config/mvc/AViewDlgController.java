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
public abstract class AViewDlgController<M extends AViewDlgModel, V extends AViewDlgViewFxml> extends AGController<AViewDlgModel, AViewDlgViewFxml> {

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

    // To change the background color of the text
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
    initStylePanelBindings();
    initHorodatePanelBindings();
    initConfigurationPanelBindings();

  }

  /**
   * Inits the idendification card panel.
   */
  protected void initIdendificationCardPanel() {
    Bindings.bindBidirectional(view().getPnlIdentityCardController().getLblIdValue().textProperty(), model().getIdProperty(), new NumberStringConverter());
    view().getPnlIdentityCardController().getTxtName().textProperty().bindBidirectional(model().getNameProperty());
    view().getPnlIdentityCardController().getTxtCategory().textProperty().bindBidirectional(model().getCategoryProperty());
    view().getPnlIdentityCardController().getTxtTags().textProperty().bindBidirectional(model().getTagsProperty());
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
  protected void initHorodatePanelBindings() {
    view().getPnlHorodateController().getLblCreatedByValue().textProperty().bindBidirectional(model().getCreatedByProperty());
    view().getPnlHorodateController().getLblLastModifiedByValue().textProperty().bindBidirectional(model().getLastModifiedByProperty());

    view().getPnlHorodateController().getLblCreatedDateValue().textProperty()
          .bind(Bindings.createStringBinding(() -> UtilAppUI.formatDate(model().getCreatedDateProperty()), model().getCreatedDateProperty()));

    view().getPnlHorodateController().getLblLastModifiedDateValue().textProperty()
          .bind(Bindings.createStringBinding(() -> UtilAppUI.formatDate(model().getLastModifiedDateProperty()), model().getLastModifiedDateProperty()));

  }

}
