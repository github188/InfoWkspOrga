package com.sgu.infowksporga.jfx.workspace;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.dto.WorkspaceDto;
import com.sgu.infowksporga.jfx.views.AbstractView;
import com.sgu.infowksporga.jfx.views.AbstractViewIdentifier;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerViewIdentifier;
import com.sgu.infowksporga.jfx.views.html.HtmlView;
import com.sgu.infowksporga.jfx.views.html.HtmlViewIdentifier;
import com.sgu.infowksporga.jfx.views.web.WebView;
import com.sgu.infowksporga.jfx.views.web.WebViewIdentifier;

import lombok.Getter;
import lombok.Setter;
import net.infonode.docking.View;
import net.infonode.docking.ViewSerializer;

/**
 * Description : WorkspaceSerializer class<br>
 * This class is used to serialize All the Application workspace View
 */
@Getter
@Setter
public class WorkspaceSerializer implements ViewSerializer {
  /**
   * The logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(WorkspaceSerializer.class);

  /**
   * Store workspace info loaded from database
   */
  private WorkspaceDto workspaceDto;

  /**
   * {@inheritDoc}
   */
  @Override
  public void writeView(final View view, final ObjectOutputStream outputStream) throws IOException {

    // Skip view with configuration code = null
    if (view instanceof AbstractView && ((AbstractView) view).getViewIdentifier() == null
        && ((AbstractView) view).getViewIdentifier().getViewEntityName() == null) {
      return;
    }
    else if (view instanceof FileExplorerView) {
      // Write view object of type Directory Desk View
      saveFileExplorerView(view, outputStream);
    }
    else if (view instanceof WebView) {
      // Write view object of type Web View
      saveWebView(view, outputStream);
    }
    else if (view instanceof HtmlView) {
      // Write view object of type HTML View
      saveHtmlView(view, outputStream);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public View readView(final ObjectInputStream inputStream) throws IOException {
    // First de-serialize the object
    Object viewIdentifier;
    try {
      viewIdentifier = inputStream.readObject();
    } catch (final Exception e) {
      LOGGER.warn("Impossible to LOAD view and it is skipped due to this error:\n{}", UtilString.convertStackTraceToString(e));
      return null;
    }

    // Secondly Apply treatment to recreate the view
    View result = null;

    if (viewIdentifier instanceof WebViewIdentifier) {
      result = loadWebView((WebViewIdentifier) viewIdentifier);
    }
    else if (viewIdentifier instanceof FileExplorerViewIdentifier) {
      result = loadFileExplorerView((FileExplorerViewIdentifier) viewIdentifier);
    }
    else if (viewIdentifier instanceof HtmlViewIdentifier) {
      result = loadHtmlView((HtmlViewIdentifier) viewIdentifier);
    }

    return result;
  }

  /* ------------------------------------------------------------------------------------------------------------------------------------ */
  /* LOADING VIEWS */
  /* ------------------------------------------------------------------------------------------------------------------------------------ */

  /**
   * Load web view.
   *
   * @param workspaceDto the configuration stored by Dockable in database
   * @return the view
   */
  protected View loadWebView(final WebViewIdentifier viewIdentifier) {
    // Ckeck if view already exists otherwise return null
    Object viewEntity = workspaceDto.buildWorkspacesViewsDtoExtract().getWorkspaceViewsByName().get(viewIdentifier.getViewEntityName());
    if (viewEntity == null) {
      logViewLoadErrorMessage(viewIdentifier);
      return null;
    }

    final WebView uiView = new WebView(viewIdentifier);
    return uiView;
  }

  /**
   * Load directory desk view.
   *
   * @param viewIdentifier the view configuration
   * @return the view
   */
  protected View loadFileExplorerView(final FileExplorerViewIdentifier viewIdentifier) {

    // Ckeck if view already exists otherwise return null
    Object viewEntity = workspaceDto.buildWorkspacesViewsDtoExtract().getWorkspaceViewsByName().get(viewIdentifier.getViewEntityName());
    if (viewEntity == null) {
      logViewLoadErrorMessage(viewIdentifier);
      return null;
    }

    final FileExplorerView uiView = new FileExplorerView(viewIdentifier);
    return uiView;
  }

  /**
   * Load Html view.
   *
   * @param viewIdentifier the view configuration
   * @return the view
   */
  protected View loadHtmlView(final HtmlViewIdentifier viewIdentifier) {

    // Ckeck if view already exists otherwise return null
    Object viewEntity = workspaceDto.buildWorkspacesViewsDtoExtract().getWorkspaceViewsByName().get(viewIdentifier.getViewEntityName());
    if (viewEntity == null) {
      logViewLoadErrorMessage(viewIdentifier);
      return null;
    }

    final HtmlView uiView = new HtmlView(viewIdentifier);
    return uiView;
  }

  /* ------------------------------------------------------------------------------------------------------------------------------------ */
  /* SAVING VIEWS */
  /* ------------------------------------------------------------------------------------------------------------------------------------ */
  /**
   * Description : saveWebView method <br>
   *
   * @param view
   * @param outputStream
   */
  protected void saveWebView(final View view, final ObjectOutputStream outputStream) {
    WebView uiView = null;
    try {
      uiView = (WebView) view;
      outputStream.writeObject(uiView.getViewIdentifier());
    } catch (final Exception e) {
      WebViewIdentifier configuration = null;
      if (uiView != null) {
        configuration = (WebViewIdentifier) uiView.getViewIdentifier();
      }
      logViewSavingWarningMessage(e, configuration);
    }
  }

  /**
   * Save file explorer view.
   *
   * @param view the view
   * @param outputStream the output stream
   */
  protected void saveFileExplorerView(final View view, final ObjectOutputStream outputStream) {
    FileExplorerView uiView = null;
    try {
      uiView = (FileExplorerView) view;
      outputStream.writeObject(uiView.getViewIdentifier());
    } catch (final Exception e) {
      FileExplorerViewIdentifier configuration = null;
      if (uiView != null) {
        configuration = uiView.getViewIdentifier();
      }
      logViewSavingWarningMessage(e, configuration);
    }
  }

  /**
   * Save html view.
   *
   * @param view the view
   * @param outputStream the output stream
   */
  protected void saveHtmlView(final View view, final ObjectOutputStream outputStream) {
    HtmlView uiView = null;
    try {
      uiView = (HtmlView) view;
      outputStream.writeObject(uiView.getViewIdentifier());
    } catch (final Exception e) {
      HtmlViewIdentifier configuration = null;
      if (uiView != null) {
        configuration = uiView.getViewIdentifier();
      }
      logViewSavingWarningMessage(e, configuration);
    }
  }

  /**
   * Log view saving warning message.
   *
   * @param e the e
   * @param configuration the configuration
   */
  protected void logViewSavingWarningMessage(final Exception e, final AbstractViewIdentifier configuration) {
    LOGGER.warn("Impossible to SAVE view '{}', view is skipped due to this error:\n{}", configuration,
                UtilString.convertStackTraceToString(e));
  }

  /**
   * Log view load error message.
   *
   * @param configuration the configuration
   */
  protected void logViewLoadErrorMessage(final AbstractViewIdentifier configuration) {
    LOGGER.error("Impossible to READ view '{}', view is skipped !", configuration);
  }

}
