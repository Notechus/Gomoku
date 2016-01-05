package gra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class GameBoard extends JPanel
{
	private static final int COLUMNS = 19;
	private static final int ROWS = 19;
	private Color backgroundColor;

	private List<Stone> player;
	private List<Stone> computer;

	private Color playerColor;
	private Color computerColor;

	private Game gameInstance;

	private boolean computerRound = false;

	public GameBoard()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex)
		{
		}
		player = new ArrayList<>();
		computer = new ArrayList<>();
		gameInstance = new Game(1);
		setLayout(new GridLayout(19, 19));
		initBoard();
		addMouseListener(mouseCallback);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (int i = 0; i < player.size(); i++)
		{
			Stone s = player.get(i);
			drawStone(g, s.center.x, s.center.y, s.radius, s.color);
		}
		for (int i = 0; i < computer.size(); i++)
		{
			Stone s = computer.get(i);
			drawStone(g, s.center.x, s.center.y, s.radius, s.color);
		}

	}

	private void initBoard()
	{

		GridBagConstraints gbc = new GridBagConstraints();
		for (int i = 0; i < 20; i++)
		{
			gbc.gridx = 0;
			gbc.gridy = i;
			LabelCell lc = null;
			Border border = new MatteBorder(1, 1, 0, 0, Color.GRAY);;
			if (i != 0)
			{
				lc = new LabelCell("" + (i));
				// lc.setBorder(border);
				lc.setSize(new Dimension(100, 100));

			} else
			{
				lc = new LabelCell("");
				// lc.setBorder(border);
				lc.setSize(new Dimension(100, 100));

			}
			add(lc, gbc);
		}

		for (int row = 1; row <= ROWS; row++)
		{
			for (int col = 1; col <= COLUMNS; col++)
			{
				gbc.gridx = col;
				gbc.gridy = row;

				Cell cell = new Cell(row, col);
				Border border = new MatteBorder(1, 1, 0, 0, Color.GRAY);;
				cell.setBorder(border);
				cell.setSize(new Dimension(100, 100));
				add(cell, gbc);
			}
		}
	}

	public static void drawStone(Graphics g, int x, int y, int radius, Color c)
	{
		int d = radius * 2;
		g.setColor(c);
		g.fillOval(x - radius, y - radius, d, d);
	}

	private class Cell extends JPanel
	{
		private Color defaultBackground = new Color(158, 93, 30);
		public int row;
		public int column;

		public Cell(int row, int col)
		{
			this.row = row;
			this.column = col;
			this.setBackground(defaultBackground);
			addMouseListener(new MouseAdapter()
			{
				public void mousePressed(MouseEvent e)
				{
					Graphics2D g = (Graphics2D) getGraphics();
					int x = getWidth() / 2;
					int y = getHeight() / 2;
					int r = getHeight() / 2;
					if (computerRound)
					{
						drawStone(g, x, y, r, computerColor);
						computer.add(new Stone(new Point(x, y), r, computerColor));
					} else
					{
						drawStone(g, x, y, r, playerColor);
						computer.add(new Stone(new Point(x, y), r, playerColor));
					}
					// add it to the set of circles
				}

				@Override
				public void mouseEntered(MouseEvent e)
				{

				}

				@Override
				public void mouseExited(MouseEvent e)
				{

				}
			});
		}
	}

	private class LabelCell extends JPanel
	{
		private String text;
		private JLabel label;

		public LabelCell(String txt)
		{
			text = txt;
			setLayout(new BorderLayout());
			label = new JLabel(text);
			add(label, BorderLayout.CENTER);
		}
	}

	/**
	 * Handler eventów myszy
	 */
	private MouseListener mouseCallback = new MouseAdapter()
	{

		public void mousePressed(MouseEvent ev)
		{

		}

		public void mouseReleased(MouseEvent e)
		{

		}

		public void mouseEntered(MouseEvent e)
		{
			// setBackground(Color.BLUE);
		}

		public void mouseExited(MouseEvent e)
		{
			// setBackground(Color.WHITE);
			// repaint();
		}
	};

	/**
	 * Handler klawiatury
	 */
	private KeyListener keyCallback = new KeyAdapter()
	{
		public void keyReleased(KeyEvent ev)
		{
			switch (ev.getKeyCode())
			{
				case KeyEvent.VK_F:
					remove(1);
					break;
				case KeyEvent.VK_L:
					remove(-1);
					break;
				case KeyEvent.VK_BACK_SPACE:
					remove(0);
					break;
			};
		}
	};

}