import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

/**
 * @author Guy Gips
 * This Panel used for update the philosophers current status, what are they doing around the big black table.
 *
 */
public class TableJpanel extends JPanel {

		private ArrayList<Philosopher> philosophers;
		
		private int tableRADIUS = 100;
		private int philosopherRADIUS = 20;
		private int xCenter, yCenter;
	public TableJpanel(ArrayList<Philosopher> philosophers) {
		this.philosophers = philosophers;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.xCenter = getWidth();
		this.yCenter = getHeight();
		Graphics2D g2 = (Graphics2D) g;
		//Draw table
		g2.setColor(Color.BLACK);
		g2.fillOval(xCenter/2-tableRADIUS, yCenter/2-tableRADIUS, tableRADIUS*2, tableRADIUS*2);
		
		//Philosophers
		//Top of the table - philosopher 1
		g2.setColor(getColor(philosophers.get(0)));
		g2.fillOval(xCenter/2-philosopherRADIUS, yCenter/2-philosopherRADIUS*8+14, philosopherRADIUS*2, philosopherRADIUS*2);
		repaint();
		
		//Right side of the tabel - philosopher 2 & 3
		//philosopher 2
		g2.setColor(getColor(philosophers.get(1)));
		g2.fillOval(xCenter/2+philosopherRADIUS*5, yCenter/2-philosopherRADIUS*3, philosopherRADIUS*2, philosopherRADIUS*2);
		repaint();
		//philosopher 3
		g2.setColor(getColor(philosophers.get(2)));
		g2.fillOval(xCenter/2+philosopherRADIUS*3, yCenter/2+philosopherRADIUS*4, philosopherRADIUS*2, philosopherRADIUS*2);
		repaint();
		
		//Left side of the table - philosopher 4 & 5
		//philosopher 4
		g2.setColor(getColor(philosophers.get(3)));
		g2.fillOval(xCenter/2-philosopherRADIUS*5, yCenter/2+philosopherRADIUS*4, philosopherRADIUS*2, philosopherRADIUS*2);
		repaint();
		//philosopher 5
		g2.setColor(getColor(philosophers.get(4)));
		g2.fillOval(xCenter/2-philosopherRADIUS*7, yCenter/2-philosopherRADIUS*3, philosopherRADIUS*2, philosopherRADIUS*2);
		repaint();
		
		//Names:
		g2.setFont(new Font("Tahoma", 1, 30));
		g2.setColor(Color.RED);
		g2.drawString("Table", xCenter/2-40, yCenter/2);
		g2.setColor(Color.BLUE);
		g2.setFont(new Font("Tahoma", 1, 13));
		g2.drawString("Philosopher 1", xCenter/2-philosopherRADIUS, yCenter/2-philosopherRADIUS*8);
		g2.drawString("Philosopher 2", xCenter/2+philosopherRADIUS*5,yCenter/2-philosopherRADIUS*3-5);
		g2.drawString("Philosopher 3", xCenter/2+philosopherRADIUS*3,yCenter/2+philosopherRADIUS*6+13);
		g2.drawString("Philosopher 4", xCenter/2-philosopherRADIUS*5, yCenter/2+philosopherRADIUS*6+13);
		g2.drawString("Philosopher 5", xCenter/2-philosopherRADIUS*7-18, yCenter/2-philosopherRADIUS*3-13);
	}
	/**
	 * This method update the current color of each philosopher
	 * @param ph
	 * @return Color of the current status.
	 */
	private synchronized Color getColor(Philosopher ph){
		//Is philosopher is eating?
		if(ph.isEating())
			return Color.BLUE;
		//Is philosopher is thinking?
		else if(ph.isThinking())
			return Color.RED;
		else if(!ph.isActive() && !ph.isEating() && !ph.isThinking())
			return Color.BLACK;
		//at this point philosopher is waiting
		else
			return Color.WHITE;
	}
}
