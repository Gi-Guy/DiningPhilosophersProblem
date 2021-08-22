import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;



/**
 * @author Guy Gips, Year: 2017b
 * This Panel draw all current program status for user interface.
 *Drawing all status for this project:
		 * RED = Thinking Status.
		 * BLUE = Eating status.
		 * WHITE = Waiting status.
		 * BLACK = Inactive \ Dead Status.
 */
public class PanelStatus extends JPanel {

	private int RADIUS = 5;
	private int xCenter, yCenter;
	private ArrayList<Philosopher> philosophers;
	public PanelStatus(ArrayList<Philosopher> ph){
		this.philosophers=ph;
		start();//Starting JPanel action + PaintComponet
	}
	public void start() {
		this.setSize(100,100);
		//Adding Stop button + listener
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<philosophers.size(); i++)
					philosophers.get(i).setInactive();//Setting all philosopheres as inactive
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnStop)
					.addContainerGap(55, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnStop)
					.addContainerGap(66, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.xCenter = getWidth();
		this.yCenter = getHeight();
		Graphics2D g2 = (Graphics2D) g;
		/**Drawing all status for this project:
		 * RED = Thinking Status.
		 * BLUE = Eating status.
		 * WHITE = Waiting status.
		 * BLACK = Inactive \ Dead Status.*/
		
		g2.setFont(new Font("Tahoma", 1, 12));
		g2.setColor(Color.BLACK);
		g2.drawString("Thinking", xCenter/2-RADIUS*20,  yCenter/20+50);
		g2.drawString("Eating", xCenter/2-RADIUS*7,  yCenter/20+50);
		g2.drawString("Waiting", xCenter/2+RADIUS*7,  yCenter/20+50);
		g2.drawString("Inactive", xCenter/2+RADIUS*20,  yCenter/20+50);
		//Thinking status
		g2.setColor(Color.RED);
		g2.fillOval(xCenter/2-RADIUS*20, yCenter/20, RADIUS*7, RADIUS*7);
		//Eating status
		g2.setColor(Color.BLUE);
		g2.fillOval(xCenter/2-RADIUS*7, yCenter/20, RADIUS*7, RADIUS*7);
		//Waiting status
		g2.setColor(Color.WHITE);
		g2.fillOval(xCenter/2+RADIUS*7, yCenter/20, RADIUS*7, RADIUS*7);
		//inactive status
		g2.setColor(Color.BLACK);
		g2.fillOval(xCenter/2+RADIUS*20, yCenter/20, RADIUS*7, RADIUS*7);
	}
}
