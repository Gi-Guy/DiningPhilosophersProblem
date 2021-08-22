
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * @author Guy Gips, Year: 2017b, Maman15 Q2
 * This Program is the philosophers game, This class only creating Array lists and calling to other Panels only
 *
 */
public class MainGui extends JFrame {

	private static Table table;
	private static ArrayList<Stick> sticks;
	private static ArrayList<Philosopher> philosophers;
	private static ExecutorService e;
	public static void main(String[] args) {
		//define sticks
		sticks = new ArrayList<Stick>();
		for(int i=0; i<5; i++)
			sticks.add(new Stick(i+1));
		//define table
		table = new Table(sticks);
		//define philosophers
				e = Executors.newFixedThreadPool(5);
				philosophers = new ArrayList<Philosopher>();
				for(int i=0; i<5; i++){//Active Threads
					philosophers.add(new Philosopher(i+1, table));
					e.execute(philosophers.get(i));
				}
				e.shutdown();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui frame = new MainGui();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGui() {
		initComponets();
		
	}
	/**This method responsible for all initializing component and adding them to JFrame panel*/
	private void initComponets(){
		setTitle("Philosophers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 500, 600);
		PanelStatus panelStatus = new PanelStatus(philosophers);
		
		//JPanel panel = new JPanel();
		TableJpanel tableJpanel = new TableJpanel(philosophers);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(tableJpanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
						.addComponent(panelStatus, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelStatus, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tableJpanel, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
	}
}
