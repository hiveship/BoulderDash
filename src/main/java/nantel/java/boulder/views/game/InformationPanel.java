package nantel.java.boulder.views.game;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Display the game information to the player.
 */
public class InformationPanel extends JPanel
{
	private static final long serialVersionUID = 5155036429573332465L;

	/**
	 * Number of "key-value" information to display.
	 */
	private static final int INFORMATION_COUNT = 2;
	private JLabel diamondsCollectedValue;
	private JLabel diamondsToWinValue;

	public InformationPanel(final int initialDiamondsToWin, final int initialDiamondsCollected)
	{
		setLayout(new GridLayout(2, INFORMATION_COUNT)); // Inverser les deux paramètres pour avoir la structuration proposée dans le sujet.
		JLabel diamondsCollected = new JLabel("Diamands récupérés");
		JLabel diamondsRemaining = new JLabel("Diamands manquants");

		// Default values. Will change during the game.
		diamondsCollectedValue = new JLabel(Integer.toString(initialDiamondsCollected));
		diamondsToWinValue = new JLabel(Integer.toString(initialDiamondsToWin));

		diamondsCollected.setHorizontalAlignment(SwingConstants.CENTER);
		diamondsRemaining.setHorizontalAlignment(SwingConstants.CENTER);
		diamondsCollectedValue.setHorizontalAlignment(SwingConstants.CENTER);
		diamondsToWinValue.setHorizontalAlignment(SwingConstants.CENTER);

		add(diamondsCollected);
		add(diamondsRemaining);
		add(diamondsCollectedValue);
		add(diamondsToWinValue);
	}

	public void setDiamondsToWin(final int diamondsToWin)
	{
		diamondsToWinValue.setText(Integer.toString(diamondsToWin));
	}

	public void setDiamondsCollected(final int diamondsRecovered)
	{
		diamondsCollectedValue.setText(Integer.toString(diamondsRecovered));

	}
}
