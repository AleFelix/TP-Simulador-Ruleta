package juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class StopButton extends JButton {

	private static final long serialVersionUID = 1L;

	public StopButton(String string) {
		super(string);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D antiAlias = (Graphics2D) g;
		antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.RED);
		g.fillRect(5, 5, getWidth() - 10, getHeight() - 10);

	}

	public Dimension getPreferredSize() {
		return new Dimension(getWidth(), getHeight());
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	public Dimension getMaximumSize() {
		return getPreferredSize();
	}
}
