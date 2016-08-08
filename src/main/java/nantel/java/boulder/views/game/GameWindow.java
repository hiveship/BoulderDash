package nantel.java.boulder.views.game;

import nantel.java.annotations.Nullable;
import nantel.java.boulder.controllers.ApplicationController;
import nantel.java.boulder.controllers.GameController;
import nantel.java.boulder.views.SoundManager;
import nantel.java.boulder.views.ViewUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class GameWindow extends JFrame implements Observer
{
	private static final long serialVersionUID = -6379469855411724606L;

	private final GamePanel gamePanel;
	private final InformationPanel informationPanel;
	private final GameController controller;

	public GameController getController()
	{
		return this.controller;
	}

	/**
	 * @return the current instance of GameWindow
	 */
	public GameWindow getCurrentFrame()
	{
		return this;
	}

	public GameWindow(final GameController controller)
	{
		super("Boulder Dash Game");

		this.controller = controller;
		this.controller.registerObserver(getCurrentFrame());
		this.gamePanel = new GamePanel(controller);
		this.informationPanel = new InformationPanel(controller.getDiamondsToWin(), controller.getDiamondsCollectedCount());

		/*
		 * Les positions sont changés par rapport au dessin du sujet pour avoir
		 * un affichage plus ergonomique des informations.
		 */
		getContentPane().add(informationPanel, BorderLayout.PAGE_START);
		getContentPane().add(gamePanel, BorderLayout.CENTER);

		setJMenuBar(createMenu());
		ViewUtilities.setGeneralParameters(getCurrentFrame());
		SoundManager.play(Sounds.START_GAME);

		new Thread(controller::startGame).start();
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
				                "<html><center><h1><strong>Boulder Dash&nbsp;</strong></h1></center>"
				                        + "<hr /><ul><li>Pour d&eacute;placer le personnage (Rockford), utilisez les fl&egrave;ches de votre clavier.</li><li>Vous ne devez pas vous faire tuer par un adversaire</li><li>Vous devez ramasser un nombre suffisant de diamants pour d&eacute;bloquer la sortie du niveau</li><li>Vous ne devez pas vous faire &eacute;craser par un rocher ou un diamant en chute.</li></ul><hr /><p>Bon courage !</p><p>&nbsp;</p></html>"));

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

		JMenuItem pauseOrResume = new JMenuItem(new AbstractAction("Pause / Reprendre") {

			private static final long serialVersionUID = -1482495548134179276L;

			@Override
			public void actionPerformed(final @Nullable ActionEvent ae)
			{
				getController().pauseOrResume();
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

		menuBar.add(menu);
		menuBar.add(help);
		menu.add(mainWindow);
		menu.addSeparator();
		menu.add(pauseOrResume);
		menu.addSeparator();
		menu.add(exit);
		return menuBar;
	}

	public InformationPanel getInformationPanel()
	{
		return informationPanel;
	}

	public GamePanel getGamePanel()
	{
		return gamePanel;
	}

	@Override
	public void update(@Nullable final Observable o, @Nullable final Object arg)
	{
		this.informationPanel.setDiamondsCollected(controller.getDiamondsCollectedCount()); // TODO Set uniquement si ça a changé
		this.informationPanel.setDiamondsToWin(controller.getDiamondsToWin()); // TODO Set uniquement si ça a changé
		this.gamePanel.repaint(); // TODO Repaint que la ou les zones ayant besoin
		if ( controller.isFinish() ) {
			this.gamePanel.removeKeyListener(this.gamePanel.getRockfordDeplacementKeyListener()); // The game is finish, don't allow rockford to moove.
			if ( controller.isWin() ) {
				SoundManager.play(Sounds.FINISH_WIN);
			} else {
				SoundManager.play(Sounds.FINISH_LOOSE);
			}
			JOptionPane.showMessageDialog(getCurrentFrame(), "Partie terminée ! Retounez au menu principal pour une nouvelle partie de ce niveau ou d'un autre.");
		}

	}

}
