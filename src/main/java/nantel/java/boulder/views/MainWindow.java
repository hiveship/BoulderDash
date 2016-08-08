package nantel.java.boulder.views;

import nantel.java.boulder.controllers.ApplicationController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * Main window of the application, used to navigate between the editor and the
 * game.
 */
public class MainWindow extends JFrame
{
	private static final long serialVersionUID = 7564019879373920333L;

	private final ApplicationController controller;
	private JComboBox<String> levelsComboBox;

	public MainWindow(final ApplicationController controller)
	{
		super("Boulder Dash");
		this.controller = controller;
		setLayout(new GridLayout(4, 1));

		levelsComboBox = new JComboBox<>();
		setComboBoxValues();
		levelsComboBox.setSelectedItem(null);
		levelsComboBox.setPreferredSize(new Dimension(300, 50));

		final JButton playButton = new JButton("Jouer");
		playButton.setEnabled(false);
		playButton.setPreferredSize(new Dimension(100, 50));

		final JButton editButton = new JButton("Editer");
		editButton.setEnabled(false);
		editButton.setPreferredSize(new Dimension(100, 50));

		final JButton deleteButton = new JButton("Supprimer");
		deleteButton.setEnabled(false);
		deleteButton.setPreferredSize(new Dimension(100, 50));

		JButton newButton = new JButton("Créer un niveau");
		newButton.setPreferredSize(new Dimension(200, 50));

		JPanel panel0 = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		levelsComboBox.addActionListener(event -> {
            playButton.setEnabled(true);
            editButton.setEnabled(true);
            deleteButton.setEnabled(true);
        });

		newButton.addActionListener(e -> {
            JTextField rowsCount = new JTextField(5);
            JTextField columnsCount = new JTextField(5);
            JTextField diamondsToWinCount = new JTextField(5);
            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Nombre de lignes: "));
            myPanel.add(rowsCount);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("Nombre de colonnes: "));
            myPanel.add(columnsCount);
            myPanel.add(Box.createHorizontalStrut(15));
            myPanel.add(new JLabel("Nombre de diamants pour gagner: "));
            myPanel.add(diamondsToWinCount);

            int result = JOptionPane.showConfirmDialog(getCurrentFrame(), myPanel, "Saisir les paramètres de configuration", JOptionPane.OK_CANCEL_OPTION);
            if ( result == JOptionPane.OK_OPTION ) {
                try {
                    int rows = Integer.parseInt(rowsCount.getText());
                    int columns = Integer.parseInt(columnsCount.getText());
                    int diamonds = Integer.parseInt(diamondsToWinCount.getText());
                    controller.launchLevelEditor(rows, columns, diamonds);
                    getCurrentFrame().setVisible(false);
                    getCurrentFrame().dispose();
                } catch ( NumberFormatException e1 ) {
                    JOptionPane.showMessageDialog(getCurrentFrame(), "Erreurs dans les valeurs, veuillez saisir des nombres entiers uniquement");
                }
            }
        });

		playButton.addActionListener(e -> {
            String selectedItem = (String) levelsComboBox.getSelectedItem();
            assert selectedItem != null;
            controller.lauchGame(selectedItem);
            getCurrentFrame().dispose();
        });

		editButton.addActionListener(e -> {
            String selectedItem = (String) levelsComboBox.getSelectedItem();
            assert selectedItem != null;
            controller.launchLevelEditor(selectedItem);
            getCurrentFrame().dispose();
        });

		deleteButton.addActionListener(e -> {
            int dialogResult = JOptionPane.showConfirmDialog(getCurrentFrame(), "Voulez vous vraiment supprimer ce niveau ?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
            if ( dialogResult == JOptionPane.OK_OPTION ) {
                String selectedItem = (String) levelsComboBox.getSelectedItem();
                assert selectedItem != null;
                try {
                    getController().deleteLevel(selectedItem);
                    setComboBoxValues();
                } catch ( IOException e1 ) {
                    JOptionPane.showMessageDialog(getCurrentFrame(), "Erreur, impossible de supprimer ce niveau.");
                }
            }

        });

		panel0.add(new JLabel(new ImageIcon(getClass().getResource("/logo.png"))));

		panel1.add(levelsComboBox);
		panel2.add(playButton);
		panel2.add(editButton);
		panel2.add(deleteButton);
		panel3.add(newButton);

		add(panel0);
		add(panel1);
		add(panel2);
		add(panel3);

		ViewUtilities.setGeneralParameters(getCurrentFrame());
	}

	private void setComboBoxValues()
	{
		List<String> names = getController().getAllExistingLevelsName();
		levelsComboBox = new JComboBox<>(names.toArray(new String[names.size()]));
		levelsComboBox.revalidate();
	}

	/**
	 * @return the current instance of MainWindow
	 */
	public MainWindow getCurrentFrame()
	{
		return this;
	}

	public ApplicationController getController()
	{
		return controller;
	}
}
