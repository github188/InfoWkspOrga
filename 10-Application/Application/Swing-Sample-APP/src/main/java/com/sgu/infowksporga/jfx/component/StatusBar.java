package com.sgu.infowksporga.jfx.component;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

import org.joda.time.DateTime;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.GButton;
import com.sgu.core.framework.gui.swing.GLabel;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.gui.swing.separator.GSeparator;
import com.sgu.core.framework.gui.swing.util.UtilNotificationMsg;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.menu.action.statusbar.ForceGarbageCollectorAction;

import net.miginfocom.swing.MigLayout;

/**
 * Description : StatusBar class<br>
 */
public class StatusBar extends GPanel {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -5353801350011403634L;

  /**
   * current File Edition Mode icon
   */
  private GLabel lblCurrentFileEditionMode;

  /**
   * Message to the user
   */
  private GLabel lblMessage;

  /**
   * JProgress bar to indicate treatment location
   */
  private JProgressBar progressBar;

  /**
   * Constructor<br>
   */
  public StatusBar() {
    super();

    buildPanel();
  }

  /**
   * Description : buildPanel method <br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "status.bar.transparent.icon", value = "/icons/transparent-16-16.png"), // Force \n
                @I18nProperty(key = "status.bar.transparent.text", value = " "), // Force \n
                @I18nProperty(key = "status.bar.information.tooltip",
                value = "Cliquer sur le bouton droit de la souris pour copier le texte affiché dans le clipboard"), // Force \n
                @I18nProperty(key = "status.bar.information.text", value = " "), // Force \n
                @I18nProperty(key = "status.bar.information.icon", value = "/icons/information.png"), // Force \n
                @I18nProperty(key = "status.bar.force.garbage.collector.tooltip", value = "Force le garbage collector"), // Force \n
                @I18nProperty(key = "status.bar.force.garbage.collector.icon", value = "/icons/garbage-collector.png"), // Force \n
                @I18nProperty(key = "status.bar.information.copy.to.clipboard",
                value = "Le contenu de message a été copié dans le presse papier."), // Force \n
                @I18nProperty(key = "status.bar.used.memory.vs.max.memory.tooltip", value = "Used Memory / Max Memory"), // Force \n
  })
  private void buildPanel() {
    setLayout(new MigLayout("insets 2 2 2 2", "[][][fill, grow][][]"));
    setBorder(new MatteBorder(1, 0, 0, 0, new Color(182, 188, 204)));
    setBackground(new Color(235, 235, 235));

    lblCurrentFileEditionMode = new GLabel();
    lblCurrentFileEditionMode.setBundleConfiguration("status.bar.transparent", I18nHelperApp.getI18nHelper());
    add(lblCurrentFileEditionMode);
    add(new GSeparator(GSeparator.VERTICAL), "growy");

    final ForceGarbageCollectorAction forceGarbageCollectorAction = new ForceGarbageCollectorAction();
    final GButton forceGarbageCollectorButton = forceGarbageCollectorAction.createButton(false, true);

    lblMessage = new GLabel();
    lblMessage.setBundleConfiguration("status.bar.information", I18nHelperApp.getI18nHelper());
    lblMessage.setHorizontalAlignment(SwingConstants.LEFT);
    // Allow copy of the status bar message in clipboard
    lblMessage.addMouseListener(new MouseAdapter() {

      /**
       * {@inheritDoc}
       */
      @Override
      public void mousePressed(final MouseEvent e) {
        if (e.getClickCount() == 1 && SwingUtilities.isRightMouseButton(e)) {
          final StringSelection stringSelection = new StringSelection(lblMessage.getText());
          final Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
          clpbrd.setContents(stringSelection, null);

          UtilNotificationMsg.displayMessage("status.bar.information.copy.to.clipboard");
        }
      }

    });

    add(lblMessage, "growx");

    add(new GSeparator(GSeparator.VERTICAL), "growy");

    progressBar = new JProgressBar(0, 100);
    progressBar.setVisible(false);
    progressBar.setStringPainted(true);

    add(progressBar, "width 150");

    final GLabel lblDateHour = new GLabel();
    final GLabel lblRuntimeMemory = new GLabel();
    lblRuntimeMemory.setBundleConfiguration("status.bar.used.memory.vs.max.memory", I18nHelperApp.getI18nHelper());

    final int mb = 1024 * 1024;
    // Getting the runtime reference from system
    final Runtime runtime = Runtime.getRuntime();

    final TimerTask timerTask = new TimerTask() {

      @Override
      public void run() {
        // Update Memory information
        final long usedMemory = (runtime.maxMemory() - runtime.freeMemory()) / mb;
        final long maxMemory = runtime.maxMemory() / mb;
        lblRuntimeMemory.setText(usedMemory + " / " + maxMemory);

        // Update Hour information
        lblDateHour.setText(new DateTime().toString(I18NConstant.getDateHourMinuteSecondFormat()));
      }
    };

    final Timer timer = new Timer("MyTimer");// create a new Timer
    timer.scheduleAtFixedRate(timerTask, 30, 1000);// this line starts the timer at the same time its executed

    final GPanel gcHourPanel = new GPanel(new MigLayout("insets 0 0 0 0", "[fill, grow][right][right][right]"));
    gcHourPanel.setBackground(new Color(235, 235, 235));
    gcHourPanel.add(new GLabel(""));
    gcHourPanel.add(new GSeparator(GSeparator.VERTICAL), "growy");

    gcHourPanel.add(forceGarbageCollectorButton, "width 22!");
    gcHourPanel.add(lblRuntimeMemory);
    gcHourPanel.add(new GSeparator(GSeparator.VERTICAL), "growy");
    gcHourPanel.add(lblDateHour);
    add(gcHourPanel, "");

  }

  /**
   * @see #lblCurrentFileEditionMode
   * @return the lblCurrentFileEditionMode : See field description
   */
  public GLabel getLblCurrentFileEditionMode() {
    return lblCurrentFileEditionMode;
  }

  /**
   * @see #progressBar
   * @return the progressBar : See field description
   */
  public JProgressBar getProgressBar() {
    return progressBar;
  }

  /**
   * Description : reinitProgressBar method <br>
   */
  public void reinitProgressBar() {
    progressBar.setValue(0);
    progressBar.setToolTipText(null);
  }

  /**
   * @see #lblMessage
   * @return the lblMessage : See field description
   */
  public GLabel getLblMessage() {
    return lblMessage;
  }

}
