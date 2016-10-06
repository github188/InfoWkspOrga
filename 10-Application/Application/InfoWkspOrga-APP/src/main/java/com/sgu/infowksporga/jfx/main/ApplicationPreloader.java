package com.sgu.infowksporga.jfx.main;

import com.sgu.core.framework.gui.jfx.util.UtilGUI;
import com.sgu.core.framework.util.UtilBeanReflexion;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

import javafx.animation.ScaleTransition;
import javafx.application.Preloader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;

/**
 * The class <strong>ApplicationPreloader</strong> is the class that receives progress notifications .
 */
@Slf4j
public class ApplicationPreloader extends Preloader {

  /** The preloader Stage. */
  protected Stage preloaderStage;

  /** The Progress Bar. */
  protected ProgressBar progressBar;

  /** The text that will display message received. */
  protected Text messageText;

  /**
   * {@inheritDoc}
   */
  @Override
  public void start(final Stage stage) throws Exception {
    // Store the preloader stage to reuse it later
    this.preloaderStage = stage;

    // Configure the stage
    stage.centerOnScreen();
    stage.initStyle(StageStyle.TRANSPARENT);
    stage.setScene(createPreloaderScene());

    // Let's start the show
    stage.show();
    notifyPreloader(new MessageProgressNotification(0.0, "application.preload.start")); // Initializing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleStateChangeNotification(final StateChangeNotification event) {
    switch (event.getType()) {
      case BEFORE_LOAD:
        ApplicationInitializer.initializeI18nMessageBundle();
        break;
      case BEFORE_INIT:
        break;
      case BEFORE_START:
        hideStage();
        break;
      default:
    }
  }

  /**
   * {@inheritDoc}
   */
  protected Scene createPreloaderScene() {

    final StackPane p = new StackPane();

    final ImageView logo = new ImageView(UtilGUI.getImage("/icons/app/application-logo.png"));
    p.getChildren().add(logo);
    StackPane.setAlignment(logo, Pos.CENTER);

    this.progressBar = new ProgressBar(0.0);
    this.progressBar.setPrefSize(460, 20);
    p.getChildren().add(this.progressBar);
    StackPane.setAlignment(this.progressBar, Pos.BOTTOM_CENTER);
    StackPane.setMargin(this.progressBar, new Insets(30));

    this.messageText = new Text("");
    p.getChildren().add(this.messageText);
    StackPane.setAlignment(this.messageText, Pos.BOTTOM_CENTER);
    StackPane.setMargin(this.messageText, new Insets(10));

    return new Scene(p, 600, 200, Color.TRANSPARENT);
  }

  /**
   * {@inheritDoc}
   */
  protected void hideStage() {
    final Stage stage = this.preloaderStage;

    final ScaleTransition st = new ScaleTransition();
    st.setFromX(1.0);
    st.setToX(0.0);
    st.setDuration(Duration.millis(400));
    st.setNode(stage.getScene().getRoot());
    st.setOnFinished(actionEvent -> stage.hide());
    st.play();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleProgressNotification(final ProgressNotification pn) {

    if (pn instanceof MessageProgressNotification) {
      this.progressBar.setProgress(pn.getProgress());
      this.messageText.setText(I18nHelperApp.getMessage(((MessageProgressNotification) pn).getDetails()));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleApplicationNotification(final PreloaderNotification n) {
    if (n instanceof MessageProgressNotification) {
      handleProgressNotification((MessageProgressNotification) n);
    }
  }

  /**
   * The Class MessageProgressNotification.
   */
  public static class MessageProgressNotification extends ProgressNotification {

    public MessageProgressNotification(final double progress) {
      super(progress);
    }

    public MessageProgressNotification(final double progress, final String details) {
      super(progress);
      setDetails(details);
    }

    public String getDetails() {
      return (String) UtilBeanReflexion.getAttributeValue(this, "details");
    }

    public void setDetails(final String details) {
      UtilBeanReflexion.setAttributeValue(this, "details", details);
    }

  }

}
