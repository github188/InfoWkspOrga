package com.sgu.infowksporga.jfx.view.adlg.config.mvc;

import java.util.Date;

import com.sgu.core.framework.gui.jfx.screen.AGModel;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class AViewDlgModel.
 */
@Slf4j
@Getter
public abstract class AViewDlgModel<V extends AViewDlgViewFxml, C extends AViewDlgController> extends AGModel<V, C> {

  //----------------------------------------------------------------------
  // List of the property used to bind screen components with this Model
  // Bindings are initialized by the controller @see{controller.bindComponentsWithPojo()}
  //----------------------------------------------------------------------

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
   * Map model to viewEntity.
   *
   * @return the view
   */
  public View mapModelToViewEntity() {
    final View viewEntity = new View();

    //----------------------------
    // IdentityCard panel Binding
    //----------------------------
    viewEntity.setId(idProperty.getValue());
    viewEntity.setName(nameProperty.getValue());

    final AViewDlgViewFxml viewDlg = view();
    viewEntity.setDescription(viewDlg.getPnlIdentityCardController().getHtmlEdtDescription().getHtmlText());

    viewEntity.setCategory(categoryProperty.getValue());
    viewEntity.setTags(tagsProperty.getValue());
    viewEntity.setCmmiPractices(cmmiPracticesProperty.getValue());

    //----------------------------
    // configuration panel Binding
    //----------------------------
    mapConfigurationModelToView(viewEntity);

    //----------------------------
    // style Binding
    //----------------------------
    viewEntity.setBold(boldProperty.getValue());
    viewEntity.setItalic(italicProperty.getValue());
    viewEntity.setStrike(strikeProperty.getValue());
    viewEntity.setUnderline(underlineProperty.getValue());
    viewEntity.setColor(colorProperty.getValue());
    viewEntity.setBgColor(bgColorProperty.getValue());
    viewEntity.setIcon(iconProperty.getValue());

    //----------------------------
    // horodate Binding
    //----------------------------
    viewEntity.setCreatedBy(createdByProperty.getValue());
    viewEntity.setLastModifiedBy(lastModifiedByProperty.getValue());

    viewEntity.setCreatedDate(createdDateProperty.getValue());
    viewEntity.setLastModifiedDate(lastModifiedDateProperty.getValue());

    return viewEntity;
  }

  /**
   * Map view to model.
   *
   * @return the view
   */
  public void mapViewEntityToModel(final View viewEntity) {
    //----------------------------
    // IdentityCard panel Binding
    //----------------------------
    idProperty.setValue(viewEntity.getId());
    nameProperty.setValue(I18nHelperApp.getMessage(viewEntity.getName()));
    final AViewDlgViewFxml viewDlg = view();
    viewDlg.getPnlIdentityCardController().getHtmlEdtDescription().setHtmlText(I18nHelperApp.getMessage(viewEntity.getDescription())); // Because binding not worked for HTML Editor
    categoryProperty.setValue(viewEntity.getCategory());
    tagsProperty.setValue(viewEntity.getTags());
    cmmiPracticesProperty.setValue(viewEntity.getCmmiPractices());

    //-----------------------------
    // configuration panel Binding
    //-----------------------------
    mapConfigurationViewToModel(viewEntity);

    //----------------------------
    // style Binding
    //----------------------------
    boldProperty.setValue(viewEntity.isBold());
    italicProperty.setValue(viewEntity.isItalic());
    strikeProperty.setValue(viewEntity.isStrike());
    underlineProperty.setValue(viewEntity.isUnderline());
    colorProperty.setValue(viewEntity.getColor());
    bgColorProperty.setValue(viewEntity.getBgColor());
    iconProperty.setValue(I18nHelperApp.getMessage(viewEntity.getIcon()));

    //----------------------------
    // horodate Binding
    //----------------------------
    createdByProperty.setValue(viewEntity.getCreatedBy());
    lastModifiedByProperty.setValue(viewEntity.getLastModifiedBy());

    createdDateProperty.setValue(viewEntity.getCreatedDate());
    lastModifiedDateProperty.setValue(viewEntity.getLastModifiedDate());
  }

  /**
   * Map configuration view to model.
   *
   * @param view the view
   */
  public abstract void mapConfigurationViewToModel(final View viewEntity);

  /**
   * Map configuration model to view.
   */
  public abstract void mapConfigurationModelToView(final View viewEntity);

}
