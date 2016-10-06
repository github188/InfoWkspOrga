package com.sgu.infowksporga.jfx.zfacade;

import java.awt.Component;
import java.awt.Container;
import java.util.List;

import javax.swing.ProgressMonitor;
import javax.swing.SwingUtilities;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sgu.core.framework.gui.swing.IMessageComponent;
import com.sgu.core.framework.gui.swing.MessageTypeEnum;
import com.sgu.core.framework.gui.swing.dialog.GMessageBox;
import com.sgu.core.framework.gui.swing.dialog.UtilDlgMessage;
import com.sgu.core.framework.gui.swing.service.ICallback;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.core.framework.i18n.I18nHelperFwk;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.core.framework.pivot.AbstractOut;
import com.sgu.core.framework.pivot.AbstractOut.ReturnCode;
import com.sgu.core.framework.pivot.FlatException;
import com.sgu.core.framework.pivot.Message;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

/**
 * Description :AbstractProjectManagerFacade class<br>
 * 
 * @param <PIVOT_OUTPUT> AbstractOut type
 * @param <BUSINESS_CONTAINER> Container type
 */
public abstract class AbstractBusinessFacade<PIVOT_OUTPUT extends AbstractOut, BUSINESS_CONTAINER extends Container>
                                            implements ICallback<PIVOT_OUTPUT, BUSINESS_CONTAINER> {

  /** The Class Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBusinessFacade.class);

  /**
   * Constructor<br>.
   */
  public AbstractBusinessFacade() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void markAllFieldsAsValid(final BUSINESS_CONTAINER container, final ProgressMonitor monitor) {
    if (container != null) {
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {

          UtilGUI.markAllFieldsAsValid(container);
        }
      });
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PIVOT_OUTPUT checkFieldsValidity(final BUSINESS_CONTAINER container, final ProgressMonitor monitor) {
    // Do nothing by default
    return null;
  }

  /**
   * Manage all return message.
   *
   * @param pivotOut the pivot out
   * @param screen the screen
   * @return true, if has blocking errors
   */
  @Override
  public StringBuilder manageAllReturnMessages(final PIVOT_OUTPUT pivotOut, final BUSINESS_CONTAINER screen,
  final ProgressMonitor monitor) {
    final StringBuilder report = new StringBuilder();
    StringBuilder result = manageReturnBusinessInformations(pivotOut, screen, monitor);
    if (result != null) {
      report.append(I18nHelperApp.getMessage("FwkPluginCoreUIMessages.DIALOG_CONTROL_SEPARATOR")).append("\n");
      report.append("==> ").append(I18nHelperApp.getMessage("FwkPluginCoreUIMessages.DIALOG_CONTROL_INFORMATION")).append("\n");
      report.append(result.toString()).append("\n");
    }

    result = manageReturnBusinessWarnings(pivotOut, screen, monitor);
    if (result != null) {
      if (pivotOut.isAskUserConfirmWarning()) {
        // Ask user a confirmation to update business entities
        final int response = UtilDlgMessage.question("FwkPluginCoreUIMessages.DIALOG_MESSAGE_CONFIRM_TITLE", result.toString(),
                                                     GMessageBox.YES | GMessageBox.NO, 500, 200);
        if (response == GMessageBox.YES) {
          pivotOut.setUserValidateWarning(true);
        }
        else {
          pivotOut.setUserValidateWarning(false);
        }
      }
      report.append(I18nHelperApp.getMessage("FwkPluginCoreUIMessages.DIALOG_CONTROL_SEPARATOR")).append("\n");
      report.append("==> ").append(I18nHelperApp.getMessage("FwkPluginCoreUIMessages.DIALOG_CONTROL_WARNING")).append("\n");
      report.append(result.toString()).append("\n");
    }

    result = manageReturnBusinessErrors(pivotOut, screen, monitor);
    if (result != null) {
      report.append(I18nHelperApp.getMessage("FwkPluginCoreUIMessages.DIALOG_CONTROL_SEPARATOR")).append("\n");
      report.append("==> ").append(I18nHelperApp.getMessage("FwkPluginCoreUIMessages.DIALOG_CONTROL_ERROR")).append("\n");
      report.append(result.toString()).append("\n");
    }

    result = manageReturnBusinessException(pivotOut, screen, monitor);
    if (result != null) {
      report.append(I18nHelperApp.getMessage("FwkPluginCoreUIMessages.DIALOG_CONTROL_SEPARATOR")).append("\n");
      report.append("==> ").append(I18nHelperApp.getMessage("FwkPluginCoreUIMessages.DIALOG_CONTROL_EXCEPTION")).append("\n");
      report.append(result.toString()).append("\n");
    }

    if (report.length() == 0) {
      return null;
    }

    return report;
  }

  /**
   * Manage return messages.
   *
   * @param code the code
   * @param messages the messages
   * @param container the container
   * @return the string builder
   */
  protected StringBuilder buildMessagesAndMarkFields(final ReturnCode code, final List<Message> messages,
  final BUSINESS_CONTAINER container, final ProgressMonitor monitor) {
    final StringBuilder messageBuilder = new StringBuilder("");
    for (final Message message : messages) {
      final Object[] params = message.getParams().toArray();
      for (int i = 0; i < params.length; i++) {
        if (params[i] != null) {
          params[i] = I18nHelperApp.getMessage(params[i].toString() + I18NConstant.TEXT).replaceAll(" :", "").replaceAll(":", "");
        }
      }
      final String i18nMessage = I18nHelperFwk.getMessage(message.getCode(), params);
      messageBuilder.append(i18nMessage).append("\n");

      // Mark Field
      markFieldWithTheMessage(code, container, message, i18nMessage, monitor);
    }

    return messageBuilder;
  }

  /**
   * Description : markFieldWithTheMessage method <br>.
   *
   * @param code the code
   * @param container the container
   * @param message the message
   * @param i18nMessage the i18n message
   */
  protected void markFieldWithTheMessage(final ReturnCode code, final BUSINESS_CONTAINER container, final Message message,
  final String i18nMessage, final ProgressMonitor monitor) {

    if ((message.getField() != null) && (container != null)) {

      final Component field = UtilGUI.getComponentByName(container, message.getField());

      if ((field != null) && (field instanceof IMessageComponent)) {
        IMessageComponent fieldMsg = (IMessageComponent) field;

        switch (code) {
          case BUSINESS_ERROR:
            SwingUtilities.invokeLater(new Runnable() {
              @Override
              public void run() {
                fieldMsg.showErrorMessage(i18nMessage);
              }
            });
            doAfterBusinessError(container, message, monitor);
            break;
          case BUSINESS_WARNING:
            SwingUtilities.invokeLater(new Runnable() {
              @Override
              public void run() {
                fieldMsg.showOverlayMessage(i18nMessage, MessageTypeEnum.WARNING);
              }
            });
            doAfterBusinessWarning(container, message, monitor);
            break;
          case BUSINESS_INFORMATION:
            SwingUtilities.invokeLater(new Runnable() {
              @Override
              public void run() {
                fieldMsg.showOverlayMessage(i18nMessage, MessageTypeEnum.INFO);
              }
            });
            doAfterBusinessInformation(container, message, monitor);
            break;
          case TECHNICAL_EXCEPTION:
            SwingUtilities.invokeLater(new Runnable() {
              @Override
              public void run() {
                fieldMsg.showErrorMessage(i18nMessage);
              }
            });
            break;
          default:
            break;
        }
      }
      else {
        LOGGER.error("Field named '{}' has no correspondance in '{}' composite", message.getField(), container.toString());
      }
    }
  }

  /**
   * Do after business error.
   *
   * @param container the container
   * @param message the message
   */
  protected void doAfterBusinessError(final BUSINESS_CONTAINER container, final Message message, final ProgressMonitor monitor) {
    // Do nothing by default

  }

  /**
   * Do after business warning.
   *
   * @param container the container
   * @param message the message
   */
  protected void doAfterBusinessWarning(final BUSINESS_CONTAINER container, final Message message, final ProgressMonitor monitor) {
    // Do nothing by default

  }

  /**
   * Do after business information.
   *
   * @param container the container
   * @param message the message
   */
  protected void doAfterBusinessInformation(final BUSINESS_CONTAINER container, final Message message, final ProgressMonitor monitor) {
    // Do nothing by default

  }

  /**
   * Description : manage Not Ok ReturnCode method <br>
   * FUNCTIONAL_ERROR or TECHNICAL_ERROR.
   *
   * @param out AbstractOut type
   * @param container Container type
   * @return true, if manage return code error
   */
  protected StringBuilder manageReturnBusinessErrors(final PIVOT_OUTPUT out, final BUSINESS_CONTAINER container,
  final ProgressMonitor monitor) {
    StringBuilder messages = null;
    if (out != null && !CollectionUtils.isEmpty(out.getErrors())) {
      messages = buildMessagesAndMarkFields(ReturnCode.BUSINESS_ERROR, out.getErrors(), container, monitor);
      doAfterAllBusinessErrors(out, container, messages, monitor);
    }

    return messages;

  }

  /**
   * Description : manageReturnCodeException method <br>.
   *
   * @param out the out
   * @param container the container
   * @return the string builder
   */
  protected StringBuilder manageReturnBusinessException(final PIVOT_OUTPUT out, final BUSINESS_CONTAINER container,
  final ProgressMonitor monitor) {
    final StringBuilder exceptionBuilder = new StringBuilder();
    if (out != null && out.getException() != null) {
      exceptionBuilder.append("<HTML>");
      final FlatException flatException = out.getException();
      exceptionBuilder.append(flatException.getStacktrace()).append("\n\n");

      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          markFieldWithTheMessage(ReturnCode.TECHNICAL_EXCEPTION, container, flatException, exceptionBuilder.toString(), monitor);
        }
      });

      doAfterBusinessException(out, container, exceptionBuilder, monitor);
    }

    if (UtilString.isBlank(exceptionBuilder.toString())) {
      return null;
    }
    return exceptionBuilder;
  }

  /**
   * Description : manage Functional Message ReturnCode method <br>.
   *
   * @param out AbstractOut type
   * @param container Container type
   * @return true, if manage return code information
   */
  protected StringBuilder manageReturnBusinessInformations(final PIVOT_OUTPUT out, final BUSINESS_CONTAINER container,
  final ProgressMonitor monitor) {
    StringBuilder messages = null;
    // Not blocking message
    if (out != null && !CollectionUtils.isEmpty(out.getInformations())) {
      messages = buildMessagesAndMarkFields(ReturnCode.BUSINESS_INFORMATION, out.getInformations(), container, monitor);
      doAfterAllBusinessInformations(out, container, messages, monitor);
    }

    return messages;
  }

  /**
   * Manage return business warnings.
   *
   * @param out the out
   * @param container the container
   * @return the string builder
   */
  protected StringBuilder manageReturnBusinessWarnings(final PIVOT_OUTPUT out, final BUSINESS_CONTAINER container,
  final ProgressMonitor monitor) {
    StringBuilder messages = null;
    // Not blocking message
    if (out != null && !CollectionUtils.isEmpty(out.getWarnings())) {
      messages = buildMessagesAndMarkFields(ReturnCode.BUSINESS_WARNING, out.getWarnings(), container, monitor);
      doAfterAllBusinessWarnings(out, container, messages, monitor);
    }

    return messages;
  }

  /**
   * Do after all business informations.
   *
   * @param out the out
   * @param container the container
   * @param messages the messages
   */
  protected void doAfterAllBusinessInformations(final PIVOT_OUTPUT out, final BUSINESS_CONTAINER container, final StringBuilder messages,
  final ProgressMonitor monitor) {
    // Do nothing by default

  }

  /**
   * Do after all business warnings.
   *
   * @param out the out
   * @param container the container
   * @param messages the messages
   */
  protected void doAfterAllBusinessWarnings(final PIVOT_OUTPUT out, final BUSINESS_CONTAINER container, final StringBuilder messages,
  final ProgressMonitor monitor) {
    // Do nothing by default
  }

  /**
   * Do after all business errors.
   *
   * @param out the out
   * @param container the container
   * @param messages the messages
   */
  protected void doAfterAllBusinessErrors(final PIVOT_OUTPUT out, final BUSINESS_CONTAINER container, final StringBuilder messages,
  final ProgressMonitor monitor) {
    // Do nothing by default

  }

  /**
   * Do after business exception.
   *
   * @param out the out
   * @param container the container
   * @param exceptionBuilder the exception builder
   */
  protected void doAfterBusinessException(final PIVOT_OUTPUT out, final BUSINESS_CONTAINER container, final StringBuilder exceptionBuilder,
  final ProgressMonitor monitor) {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void doAfterTechnicalException(final PIVOT_OUTPUT result, final BUSINESS_CONTAINER container, final Throwable e,
  final ProgressMonitor monitor) {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ProgressMonitor getProgressMonitor(final BUSINESS_CONTAINER container) {
    return null;// For standard use - It display progress bar in status bar
  }

  @Override
  public void finalizeService(PIVOT_OUTPUT result, BUSINESS_CONTAINER container, ProgressMonitor monitor) {
    // TODO Auto-generated method stub

  }

  @Override
  public void doBeforeServiceCall(BUSINESS_CONTAINER container, ProgressMonitor monitor) {
    // TODO Auto-generated method stub

  }

  @Override
  public Component getGlasspanesFor(BUSINESS_CONTAINER container) {
    // TODO Auto-generated method stub
    return null;
  }

}
