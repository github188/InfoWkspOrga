package com.sgu.infowksporga.jfx.view.adlg.config.mvc;

import java.util.Date;

import com.sgu.core.framework.gui.jfx.screen.AGController;
import com.sgu.core.framework.gui.jfx.screen.AGModel;
import com.sgu.infowksporga.business.entity.View;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class AViewDlgModel.
 */
@Slf4j
@Setter
@Getter
public abstract class AViewDlgModel<V extends AViewDlgViewFxml, C extends AGController> extends AGModel<AViewDlgViewFxml, AViewDlgController> {

  //----------------------------------------------------------------------
  // List of the property used to bind screen components with this Model
  // Bindings are initialized by the controller @see{controller.bindComponentsWithPojo()}
  //----------------------------------------------------------------------
  private View view = new View();

  // IdentityCard panel Binding
  private IntegerProperty idProperty = new SimpleIntegerProperty();
  private StringProperty nameProperty = new SimpleStringProperty();
  private StringProperty descriptionProperty = new SimpleStringProperty();
  private StringProperty categoryProperty = new SimpleStringProperty();
  private StringProperty tagsProperty = new SimpleStringProperty();
  private StringProperty cmmiPracticesProperty = new SimpleStringProperty();

  // Configuration panel Binding

  // Style panel Binding
  private BooleanProperty boldProperty = new SimpleBooleanProperty();
  private BooleanProperty italicProperty = new SimpleBooleanProperty();
  private BooleanProperty strikeProperty = new SimpleBooleanProperty();
  private BooleanProperty underlineProperty = new SimpleBooleanProperty();
  private StringProperty iconProperty = new SimpleStringProperty();
  private StringProperty colorProperty = new SimpleStringProperty();
  private StringProperty bgColorProperty = new SimpleStringProperty();

  // Horodate panel Binding
  private StringProperty createdByProperty = new SimpleStringProperty();
  private ObjectProperty<Date> createdDateProperty = new SimpleObjectProperty<Date>();
  private StringProperty lastModifiedByProperty = new SimpleStringProperty();
  private ObjectProperty<Date> lastModifiedDateProperty = new SimpleObjectProperty<Date>();

  //------------------------------------------------------------
  /**
   * The Constructor.
   */
  public AViewDlgModel() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void fillUI() {
    super.fillUI();

  }

  /**
   * Map model to view.
   *
   * @return the view
   */
  public View mapModelToView() {
    final View view = new View();

    //----------------------------
    // IdentityCard panel Binding
    //----------------------------
    view.setId(idProperty.getValue());
    view.setName(nameProperty.getValue());
    view.setDescription(view().getPnlIdentityCardController().getHtmlEdtDescription().getHtmlText());

    //----------------------------
    // configuration panel Binding
    //----------------------------
    mapConfigurationModelToView(view);

    //----------------------------
    // style Binding
    //----------------------------
    view.setBold(boldProperty.getValue());
    view.setItalic(italicProperty.getValue());
    view.setStrike(strikeProperty.getValue());
    view.setUnderline(underlineProperty.getValue());
    view.setColor(colorProperty.getValue());
    view.setBgColor(bgColorProperty.getValue());
    view.setIcon(iconProperty.getValue());

    //----------------------------
    // horodate Binding
    //----------------------------
    view.setCreatedBy(createdByProperty.getValue());
    view.setLastModifiedBy(lastModifiedByProperty.getValue());

    view.setCreatedDate(createdDateProperty.getValue());
    view.setLastModifiedDate(lastModifiedDateProperty.getValue());

    return view;
  }

  /**
   * Bind configuration panel.
   *
   * @param view the view
   */
  public void mapConfigurationModelToView(final View view) {
    view.setCategory(categoryProperty.getValue());
    view.setTags(tagsProperty.getValue());
    view.setCmmiPractices(cmmiPracticesProperty.getValue());
  }

  /**
   * Map view to model.
   *
   * @return the view
   */
  public void mapViewToModel(final View view) {
    //----------------------------
    // IdentityCard panel Binding
    //----------------------------
    idProperty.setValue(view.getId());
    nameProperty.setValue(view.getName());
    view().getPnlIdentityCardController().getHtmlEdtDescription().setHtmlText(view.getDescription()); // Because binding not worked for HTML Editor

    //-----------------------------
    // configuration panel Binding
    //-----------------------------
    mapConfigurationViewToModel(view);

    //----------------------------
    // style Binding
    //----------------------------
    boldProperty.setValue(view.isBold());
    italicProperty.setValue(view.isItalic());
    strikeProperty.setValue(view.isStrike());
    underlineProperty.setValue(view.isUnderline());
    colorProperty.setValue(view.getColor());
    bgColorProperty.setValue(view.getBgColor());
    iconProperty.setValue(view.getIcon());

    //----------------------------
    // horodate Binding
    //----------------------------
    createdByProperty.setValue(view.getCreatedBy());
    lastModifiedByProperty.setValue(view.getLastModifiedBy());

    createdDateProperty.setValue(view.getCreatedDate());
    lastModifiedDateProperty.setValue(view.getLastModifiedDate());
  }

  /**
   * Map configuration view to model.
   *
   * @param view the view
   */
  public void mapConfigurationViewToModel(final View view) {
    categoryProperty.setValue(view.getCategory());
    tagsProperty.setValue(view.getTags());
    cmmiPracticesProperty.setValue(view.getCmmiPractices());
  }

}
