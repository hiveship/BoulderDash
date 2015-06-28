package nantel.java.boulder.views.editor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import nantel.java.annotations.Nullable;

public class ActionPanel extends JPanel
{
	private static final long serialVersionUID = 7911305299177162361L;
	@Nullable
	private GameElement selectedElement; // Nullable because by default, there is nothing selected

	public ActionPanel()
	{
		setLayout(new GridLayout(17, 1));
		createAndAddEntitiesButtons();
	}

	private void createAndAddEntitiesButtons()
	{
		JButton buttonRockford = new JButton(new ImageIcon(ActionPanel.class.getResource("/views/entities/rockford.gif").getPath()));
		buttonRockford.setText("Rockford");
		buttonRockford.addActionListener(new GameElementSelectionListener(GameElement.ROCKFORD));

		JButton buttonExit = new JButton(new ImageIcon(ActionPanel.class.getResource("/views/entities/exit.gif")));
		buttonExit.setText("Exit");
		buttonExit.addActionListener(new GameElementSelectionListener(GameElement.EXIT));

		JButton buttonDiamond = new JButton(new ImageIcon(ActionPanel.class.getResource("/views/entities/diamond.gif")));
		buttonDiamond.setText("Diamond");
		buttonDiamond.addActionListener(new GameElementSelectionListener(GameElement.DIAMOND));

		JButton buttonBoulder = new JButton(new ImageIcon(ActionPanel.class.getResource("/views/entities/boulder.gif")));
		buttonBoulder.setText("Boulder");
		buttonBoulder.addActionListener(new GameElementSelectionListener(GameElement.BOULDER));

		JButton buttonDirt = new JButton(new ImageIcon(ActionPanel.class.getResource("/views/entities/dirt.gif")));
		buttonDirt.setText("Dirt");
		buttonDirt.addActionListener(new GameElementSelectionListener(GameElement.DIRT));

		JButton buttonEmptySpace = new JButton(new ImageIcon(ActionPanel.class.getResource("/views/entities/emptyspace.gif")));
		buttonEmptySpace.setText("Empty Space");
		buttonEmptySpace.addActionListener(new GameElementSelectionListener(GameElement.EMPTY_SPACE));

		JButton buttonButterfly = new JButton(new ImageIcon(ActionPanel.class.getResource("/views/entities/butterfly.gif")));
		buttonButterfly.setText("Butterfly");
		buttonButterfly.addActionListener(new GameElementSelectionListener(GameElement.BUTTERFLY));

		JButton buttonFirefly = new JButton(new ImageIcon(ActionPanel.class.getResource("/views/entities/firefly.gif")));
		buttonFirefly.setText("Firefly");
		buttonFirefly.addActionListener(new GameElementSelectionListener(GameElement.FIREFLY));

		JButton buttonAmoeba = new JButton(new ImageIcon(ActionPanel.class.getResource("/views/entities/Amoeba.gif")));
		buttonAmoeba.setText("Amoeba");
		buttonAmoeba.addActionListener(new GameElementSelectionListener(GameElement.AMOEBA));

		JButton buttonBrickWall = new JButton(new ImageIcon(ActionPanel.class.getResource("/views/entities/brickwall.gif")));
		buttonBrickWall.setText("Brick Wall");
		buttonBrickWall.addActionListener(new GameElementSelectionListener(GameElement.BRICK_WALL));

		JButton buttonExpandingWall = new JButton(new ImageIcon(ActionPanel.class.getResource("/views/entities/expandingwall.gif")));
		buttonExpandingWall.setText("Expanding Wall");
		buttonExpandingWall.addActionListener(new GameElementSelectionListener(GameElement.EXPANDING_WALL));

		JButton buttonMagicWall = new JButton(new ImageIcon(ActionPanel.class.getResource("/views/entities/magicwall.gif")));
		buttonMagicWall.setText("Magic Wall");
		buttonMagicWall.addActionListener(new GameElementSelectionListener(GameElement.MAGIC_WALL));

		JButton buttonSteelWall = new JButton(new ImageIcon(ActionPanel.class.getResource("/views/entities/steelwall.gif")));
		buttonSteelWall.setText("Steel Wall");
		buttonSteelWall.addActionListener(new GameElementSelectionListener(GameElement.STEEL_WALL));

		add(buttonRockford);
		add(buttonExit);
		add(buttonBoulder);
		add(buttonDiamond);
		add(buttonBrickWall);
		add(buttonSteelWall);
		add(buttonMagicWall);
		add(buttonExpandingWall);
		add(buttonDirt);
		add(buttonEmptySpace);
		add(buttonAmoeba);
		add(buttonFirefly);
		add(buttonButterfly);
	}

	/**
	 * Save the selected element (button) in order to add it in the play field
	 * (editor panel). To select and element, click on it's button.
	 * 
	 * @param selectedElement
	 */
	public void setSelectedElement(final GameElement selectedElement)
	{
		this.selectedElement = selectedElement;
	}

	/**
	 * Get the element selected or null if nothing is selected.
	 * 
	 * @return
	 */
	@Nullable
	public GameElement getSelectedElement()
	{
		return selectedElement;
	}

	private final class GameElementSelectionListener implements ActionListener
	{
		private GameElement element;

		public GameElementSelectionListener(final GameElement element)
		{
			this.element = element;
		}

		@Override
		public void actionPerformed(@Nullable final ActionEvent e)
		{
			setSelectedElement(getElement());
		}

		public GameElement getElement()
		{
			return element;
		}

	}
}
