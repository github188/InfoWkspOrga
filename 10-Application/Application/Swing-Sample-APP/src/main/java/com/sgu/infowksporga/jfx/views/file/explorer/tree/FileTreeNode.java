package com.sgu.infowksporga.jfx.views.file.explorer.tree;

import java.io.File;

import com.sgu.core.framework.gui.swing.tree.GTreeNode;
import com.sgu.core.framework.util.Util;

import lombok.extern.slf4j.Slf4j;

/**
 * Description : FileTreeNode class<br>
 */
@Slf4j
public class FileTreeNode extends GTreeNode {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3059603337574216299L;

  /**
   * Attribute used to know if nodes have been already been expanded for the complete tree
   */
  private boolean alreadyExpanded;

  /**
   * Constructor<br>
   */
  public FileTreeNode() {
    super();
  }

  /**
   * Constructor<br>
   *
   * @param file The file information
   */
  public FileTreeNode(final FileVo file) {
    super(file);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {

    return getFileName();

  }

  /**
   * Gets the file name.
   *
   * @return the file name
   */
  public String getFileName() {
    if (getFileObject() != null) {
      return getFileObject().getName();
    }

    return "";
  }

  /**
   * Gets the file path.
   *
   * @return the file path
   */
  public String getFilePath() {
    if (getFileObject() != null) {
      return getFileObject().getPath();
    }

    return "";
  }

  /**
   * @see #file
   * @return the file : See field description
   */
  public File getFileObject() {
    return ((FileVo) userObject).getFile();
  }

  /**
   * Description : getFileExtention method <br>
   *
   * @return the file extention
   */
  public String getFileExtention() {
    if (getFileObject() != null && !getFileObject().isDirectory()) {
      final String path = getFileObject().getPath();
      if (path.lastIndexOf(".") != -1) {
        return path.substring(path.lastIndexOf("."), path.length());
      }
    }

    return "";
  }

  /**
   * @see #alreadyExpanded
   * @return the alreadyExpanded : See field description
   */
  public boolean isAlreadyExpanded() {
    return alreadyExpanded;
  }

  /**
   * @see #alreadyExpanded
   * @param alreadyExpanded : See field description
   */
  public void setAlreadyExpanded(final boolean alreadyExpanded) {
    this.alreadyExpanded = alreadyExpanded;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    if (getFileObject() != null) {
      return getFileObject().hashCode();
    }

    return 0;
  }

  /**
   * Description : countChildren method <br>
   *
   * @return the number of children
   */
  public int countChildren() {
    if (getFileObject().list() != null) {
      return getFileObject().list().length;
    }

    return 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object obj) {
    if (obj != null) {
      return Util.isEqual(this.getFileObject(), ((FileTreeNode) obj).getFileObject());
    }
    else {
      return Util.isEqual(this.getFileObject(), null);
    }
  }

  /**
   * Overridden to make clone public. Returns a shallow copy of this node;
   * the new node has no parent or children and has a reference to the same
   * user object, if any.
   *
   * @return a copy of this node
   */
  @Override
  public Object clone() {
    final FileTreeNode newNode = new FileTreeNode();
    newNode.setUserObject(this.getUserObject());
    newNode.setAlreadyExpanded(alreadyExpanded);
    return newNode;
  }

}
