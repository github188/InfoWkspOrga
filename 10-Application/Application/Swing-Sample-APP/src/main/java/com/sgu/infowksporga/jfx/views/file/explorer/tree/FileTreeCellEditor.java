package com.sgu.infowksporga.jfx.views.file.explorer.tree;

import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;

import com.sgu.core.framework.gui.swing.textfield.GTextField;
import com.sgu.core.framework.gui.swing.tree.GTree;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.infowksporga.jfx.zfacade.local.RenameFileDirectoryServiceUI;

/**
 * Description : FileTreeCellEditor class<br>
 */
public class FileTreeCellEditor extends DefaultTreeCellEditor {

  /**
   * Constructor<br>
   *
   * @param tree
   * @param renderer
   */
  public FileTreeCellEditor(final JTree tree, final DefaultTreeCellRenderer renderer) {
    super(tree, renderer);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected TreeCellEditor createTreeCellEditor() {
    final Border aBorder = UIManager.getBorder("Tree.editorBorder");
    final GTextField textField = new GTextField();
    textField.setBorder(aBorder);
    textField.getTextFieldDocument().addInvalidCharacters(new char[] { '/', '\\', '>', '<', '|', ':', '*', '?', '"' });
    textField.getTextFieldDocument().setTestOnInvalidCharsActive(true);

    final DefaultCellEditor editor = new DefaultCellEditor(textField) {
      @Override
      public boolean shouldSelectCell(final EventObject event) {
        final boolean retValue = super.shouldSelectCell(event);
        return retValue;
      }
    };

    // One click to edit.
    editor.setClickCountToStart(1);
    return editor;
  }

  /**
   * Returns the value currently being edited.
   * 
   * @return the value currently being edited
   */
  @Override
  public Object getCellEditorValue() {
    final String newFileName = (String) realEditor.getCellEditorValue();

    if (((GTree) tree).isCancelEditing() == false) {
      final RenameFileDirectoryServiceUI facade = SpringBeanHelper.getImplementationByInterface(RenameFileDirectoryServiceUI.class);
      facade.setFileTree((FileTree) tree);
      facade.setNewFileName(newFileName);
      final FileVo fileVo = (FileVo) facade.execute(facade, null);
      return fileVo;
    }

    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean stopCellEditing() {
    final boolean result = super.stopCellEditing();

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void cancelCellEditing() {
    super.cancelCellEditing();
  }

}
