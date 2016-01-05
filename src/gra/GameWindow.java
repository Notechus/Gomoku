package gra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GameWindow extends JFrame
{
	private GameBoard board;
	private static JLabel score;

	/**
	 * Create the frame.
	 */
	public GameWindow()
	{
		/*
		 * try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) { }
		 */

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Gomoku");
		setSize(1000, 850);
		setMinimumSize(new Dimension(1000, 850));
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(0, 0));
		score = new JLabel("Wynik: ");
		score.setBorder(new EmptyBorder(10, 30, 0, 0));
		score.setFont(new Font("Calibri", Font.ITALIC, 14));
		add(score, BorderLayout.NORTH);

		board = new GameBoard();
		board.setBorder(new LineBorder(Color.BLACK));
		board.setBounds(10, 10, 10, 10);
		add(board, BorderLayout.CENTER);
		createMenu();
		pack();
		this.setVisible(true);
	}

	public void createMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnGra = new JMenu("Gra");
		menuBar.add(mnGra);

		JMenuItem nowa = new JMenuItem("Nowa");
		nowa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		nowa.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				board.restart();
			}
		});
		mnGra.add(nowa);
		mnGra.addSeparator();

		JMenuItem koniec = new JMenuItem("Koniec");
		koniec.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
		koniec.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		mnGra.add(koniec);

		JMenu ustawienia = new JMenu("Ustawienia");
		menuBar.add(ustawienia);

		JCheckBoxMenuItem oznaczeniePol = new JCheckBoxMenuItem("Oznaczenie pól");
		oznaczeniePol.setSelected(true);
		oznaczeniePol.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (oznaczeniePol.isSelected()) board.enumerateBoard();
				else
					board.denumerateBoard();
			}
		});
		ustawienia.add(oznaczeniePol);

		JCheckBoxMenuItem skresleniePol = new JCheckBoxMenuItem("Skreślenie pól");
		skresleniePol.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (skresleniePol.isSelected())
				{
					board.skresl = true;
					System.out.println("Skreslono");
				} else
					board.skresl = false;
			}
		});
		ustawienia.add(skresleniePol);

		JMenu mnZaczyna = new JMenu("Zaczyna");
		ustawienia.add(mnZaczyna);

		JRadioButtonMenuItem radiobtnProgram = new JRadioButtonMenuItem("Program");
		radiobtnProgram.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				board.playerColor = Color.WHITE;
				board.computerColor = Color.BLACK;
				board.computerRound = true;
			}
		});
		mnZaczyna.add(radiobtnProgram);

		JRadioButtonMenuItem radiobtnUzytkownik = new JRadioButtonMenuItem("Uzytkownik");
		radiobtnUzytkownik.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				board.playerColor = Color.BLACK;
				board.computerColor = Color.WHITE;
				board.computerRound = false;
			}
		});
		mnZaczyna.add(radiobtnUzytkownik);

		ButtonGroup startingGroup = new ButtonGroup();
		startingGroup.add(radiobtnProgram);
		startingGroup.add(radiobtnUzytkownik);
		radiobtnUzytkownik.setSelected(true);

		JMenu mnPomoc = new JMenu("Pomoc");
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(mnPomoc);

		JMenuItem oGrze = new JMenuItem("O grze");
		String gameMsg = "Gra Gomoku jest rozgrywana przez dwóch graczy na planszy o wymiarach 19x19.\n "
				+ "Gracze zajmują pola na przemian, dążąc do objęcia pięciu pól w jednej linii.\n "
				+ "Do zajęcia pola używa się pionów (jeden z graczy uzywa pionów czarnych, a drugi białych).\n "
				+ "Pole może być zajęte tylko przez jednego gracza i nie zmienia swego właściciela do końca gry.\n "
				+ "Grę rozpoczyna grający czarnymi pionami. Celem każdego z graczy jest ułożenie na planszy dokładnie\n "
				+ "pięciu (nie więcej) pionów swojego koloru w ciągłej linii - poziomej, pionowej lub ukośnej.\n "
				+ "Pierwszy gracz, który tego dokona zostaje zwycięzcą. Jeśli nie uda się to nikomu ( plansza\n "
				+ "zostanie zapełniona) to jest remis.";
		oGrze.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(GameWindow.this, gameMsg);

			}
		});
		mnPomoc.add(oGrze);

		String appMsg = "Gomoku \nAutor: Sebastian Paulus \nWersja: 0.3 alpha \nData wydania: 09.12.2015";

		JMenuItem oAplikacji = new JMenuItem("O aplikacji");
		oAplikacji.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(GameWindow.this, appMsg);
			}
		});
		mnPomoc.add(oAplikacji);
	}

	public static void updateResult(String txt)
	{
		score.setText("Wynik: " + txt);
	}
}
