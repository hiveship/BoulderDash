package nantel.java.boulder.views.editor;

import nantel.java.annotations.Nullable;
import nantel.java.boulder.controllers.ApplicationController;
import nantel.java.boulder.controllers.LevelEditorController;
import nantel.java.boulder.views.ViewUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Main frame for the level editor.
 */
public class LevelEditorWindow extends JFrame
{
	private static final long serialVersionUID = -5427690430590032843L;
	private final LevelEditorController controller;

	public LevelEditorWindow(final LevelEditorController controller)
	{
		super("Boulder Dash - Editor");
		this.controller = controller;

		setLayout(new FlowLayout());
		ActionPanel actionPanel = new ActionPanel();
		EditorPanel editorPanel = new EditorPanel(controller, actionPanel);

		getContentPane().add(actionPanel);
		getContentPane().add(editorPanel);

		setJMenuBar(createMenu());
		ViewUtilities.setGeneralParameters(getCurrentFrame());
	}

	public LevelEditorWindow getCurrentFrame()
	{
		return this;
	}

	private JMenuBar createMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem help = new JMenuItem(new AbstractAction("Aide") {

			private static final long serialVersionUID = 6300279031400327907L;

			@Override
			public void actionPerformed(@Nullable final ActionEvent e)
			{
				// HTML for pretty display
                JScrollPane scrollpane = new JScrollPane(
						new JLabel(
								"<html><center><h1><strong>Boulder Dash Level Editor&nbsp;</strong></h1></center>"
										+ "<hr /><ul><li>Selectionnez un élément sur la gauche et ajouter le sur le terrain en clickant</li><li>Vous ne pouvez placer qu'un seul Rockford</li><li>Vous ne pouvez placer qu'une seule sortie</li></ul><hr /><p>Bonne construction et n'oubliez pas de sauvegarder votre niveau une fois terminé (onglet Menu > Sauvegarder)!</p><p>&nbsp;</p></html>"));

				JOptionPane.showMessageDialog(getCurrentFrame(), scrollpane, "Page d'aide", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JMenuItem exit = new JMenuItem(new AbstractAction("Quitter") {

			private static final long serialVersionUID = -1482495548134179276L;

			@Override
			public void actionPerformed(final @Nullable ActionEvent ae)
			{
				System.exit(EXIT_ON_CLOSE);
			}
		});
		JMenuItem mainWindow = new JMenuItem(new AbstractAction("Menu principal") {

			private static final long serialVersionUID = -1482495548134179276L;

			@Override
			public void actionPerformed(final @Nullable ActionEvent ae)
			{
				new ApplicationController().launch();
				getCurrentFrame().dispose();
			}
		});

		JMenuItem save = new JMenuItem(new AbstractAction("Sauvegarder...") {

			private static final long serialVersionUID = -1482495548134179276L;

			@Override
			public void actionPerformed(final @Nullable ActionEvent ae)
			{
				String levelName = JOptionPane.showInputDialog(getCurrentFrame(), "Quel nom pour ce niveau ?", "Sauvegarde de niveau", JOptionPane.QUESTION_MESSAGE);
				try {
					getController().save(levelName);
				} catch ( IOException e1 ) {
					JOptionPane.showMessageDialog(getCurrentFrame(), "Une erreur est survenue, impossible de sauvegarder votre niveau", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		menuBar.add(menu);
		menuBar.add(help);
		menu.add(mainWindow);
		menu.addSeparator();
		menu.add(save);
		menu.addSeparator();
		menu.add(exit);
		return menuBar;
	}

	public LevelEditorController getController()
	{
		return controller;
	}
}
