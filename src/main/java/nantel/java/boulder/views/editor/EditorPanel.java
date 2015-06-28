package nantel.java.boulder.views.editor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import nantel.java.annotations.Nullable;
import nantel.java.boulder.controllers.GameController;
import nantel.java.boulder.controllers.LevelEditorController;

public class EditorPanel extends JPanel implements Observer
{
	private static final long serialVersionUID = -2616522539975158457L;

	private final LevelEditorController controller;

	public LevelEditorController getController()
	{
		return this.controller;
	}

	private final ActionPanel actionPanel;

	public EditorPanel(final LevelEditorController controller, final ActionPanel actionPanel)
	{
		this.controller = controller;
		this.actionPanel = actionPanel;
		setPreferredSize(new Dimension(controller.getColumns() * 16, controller.getRows() * 16));
		setFocusable(true);
		getController().registerObserver(this);

		EditorMouseListener mouseListener = new EditorMouseListener(controller, getActionPanel());
		// Need to add the listener twice. See http://stackoverflow.com/questions/5577619/why-are-mousedragged-events-not-received-when-using-mouseadapter
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
	}

	@Override
	public void paintComponent(@Nullable final Graphics graphicsContext)
	{
		assert graphicsContext != null;
		int defaultSpriteSize = GameController.getSpriteSize();

		for ( int y = 0; y < getController().getRows(); y++ ) {
			for ( int x = 0; x < getController().getColumns(); x++ ) {
				// We need to swap the 'x' and 'y' coords in order to display the playfield in the right order.
				graphicsContext.drawImage(getController().getImageAt(y, x), x * defaultSpriteSize, y * defaultSpriteSize, this);
			}
		}
	}

	@Override
	public void update(final @Nullable Observable o, final @Nullable Object arg)
	{
		repaint();
	}

	public ActionPanel getActionPanel()
	{
		return actionPanel;
	}
}
