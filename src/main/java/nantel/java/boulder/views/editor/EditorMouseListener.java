package nantel.java.boulder.views.editor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

import nantel.java.annotations.Nullable;
import nantel.java.boulder.controllers.LevelEditorController;
import nantel.java.boulder.models.exceptions.NotImplementedException;

/**
 * This listener allows to add elements in the editor panel.<br/>
 * MouseListener -> one lick add only one element. <br/>
 * MouseMotionListener -> allows to add many elements of the same type by
 * keeping pressed the button of the mouse.
 */
public class EditorMouseListener implements MouseListener, MouseMotionListener
{
	private final LevelEditorController controller;
	private ActionPanel actionPanel;

	public ActionPanel getActionPanel()
	{
		return this.actionPanel;
	}

	public LevelEditorController getController()
	{
		return controller;
	}

	public void setActionPanel(final ActionPanel actionPanel)
	{
		this.actionPanel = actionPanel;
	}

	public EditorMouseListener(final LevelEditorController controller, final ActionPanel actionPanel)
	{
		this.controller = controller;
		this.actionPanel = actionPanel; // We are in the view, so we can not use tne Entity interface (model).
	}

	@Override
	public void mouseClicked(final @Nullable MouseEvent event)
	{
		assert event != null;
		try {
			getController().setContentAt(event.getY() / LevelEditorController.getSprizeUnitSize(), event.getX() / LevelEditorController.getSprizeUnitSize(), getActionPanel().getSelectedElement());
		} catch ( NotImplementedException e ) {
			JOptionPane.showMessageDialog(getActionPanel(), "Cet élément n'est pas disponible pour le moment", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void mouseDragged(final @Nullable MouseEvent event)
	{
		assert event != null;
		mouseClicked(event);
	}

	@Override
	public void mousePressed(final @Nullable MouseEvent e)
	{
		// We don't care about this event.
	}

	@Override
	public void mouseReleased(final @Nullable MouseEvent e)
	{
		// We don't care about this event.
	}

	@Override
	public void mouseEntered(final @Nullable MouseEvent e)
	{
		// We don't care about this event.
	}

	@Override
	public void mouseExited(final @Nullable MouseEvent e)
	{
		// We don't care about this event.
	}

	@Override
	public void mouseMoved(final @Nullable MouseEvent e)
	{
		// We don't care about this event.
	}
}
