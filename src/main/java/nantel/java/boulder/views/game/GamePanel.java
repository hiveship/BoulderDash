package nantel.java.boulder.views.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import nantel.java.annotations.Nullable;
import nantel.java.boulder.controllers.GameController;
import nantel.java.boulder.models.Direction;
import nantel.java.boulder.models.exceptions.IllegalDeplacementException;

public class GamePanel extends JPanel
{
	private static final long serialVersionUID = -7359130493342043178L;
	private final GameController controller;
	private final KeyListener rockfordDeplacementKeyListener;

	public GameController getController()
	{
		return this.controller;
	}

	public KeyListener getRockfordDeplacementKeyListener()
	{
		return this.rockfordDeplacementKeyListener;
	}

	public GamePanel(final GameController controller)
	{
		this.controller = controller;
		setPreferredSize(new Dimension(controller.getColumns() * 16, controller.getRows() * 16));
		setFocusable(true); // Necessary to allow to final user to interact with the KeyListener
		this.rockfordDeplacementKeyListener = new RockfordDeplacementKeyListener();
		addKeyListener(getRockfordDeplacementKeyListener());
	}

	@Override
	public void paintComponent(@Nullable final Graphics graphicsContext)
	{
		assert graphicsContext != null;
		int defaultSpriteSize = GameController.getSpriteSize();

		for ( int y = 0; y < controller.getRows(); y++ ) {
			for ( int x = 0; x < controller.getColumns(); x++ ) {
				// We need to swap the 'x' and 'y' coords in order to display the playfield in the right order.
				graphicsContext.drawImage(controller.getImageAt(y, x), x * defaultSpriteSize, y * defaultSpriteSize, this);
			}
		}
	}

	private final class RockfordDeplacementKeyListener implements KeyListener
	{
		@Override
		public void keyPressed(final @Nullable KeyEvent event)
		{
			assert event != null;
			switch ( event.getKeyCode() ) {
				case KeyEvent.VK_UP :
					mooveOrBeep(Direction.UP);
					break;
				case KeyEvent.VK_DOWN :
					mooveOrBeep(Direction.DOWN);
					break;
				case KeyEvent.VK_LEFT :
					mooveOrBeep(Direction.LEFT);
					break;
				case KeyEvent.VK_RIGHT :
					mooveOrBeep(Direction.RIGHT);
					break;
				default :
					// We don't care about any other key.
					break;
			}
		}

		private void mooveOrBeep(final Direction direction)
		{
			try {
				getController().mooveRockford(direction);
			} catch ( IllegalDeplacementException e ) {
				getToolkit().beep(); // Just a beep that inform the player that his deplacement was not accepted.
			}
		}

		@Override
		public void keyTyped(final @Nullable KeyEvent event)
		{
			// We don't care about this event
		}

		@Override
		public void keyReleased(final @Nullable KeyEvent event)
		{
			// We don't care about this event
		}

	}

}
