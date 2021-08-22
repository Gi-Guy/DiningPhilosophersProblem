import java.util.Random;


/**
 *This Class define the philosopher actions, is he active, eating, thinking and etc.
 *The wait() action is at Table.class only. 	
 *
 */
public class Philosopher extends Thread {
	private int philosopherIndex;
	private Stick firstStick, secondStick;
	private Table currentTable;
	private boolean active = false, eating =false, thinking=false;
	
	public Philosopher(int index, Table table){
		this.philosopherIndex = index;
		this.currentTable = table;
		this.firstStick = null;
		this.secondStick = null;
		this.active = true;
	}
	/**
	 * This method hold philosopher actions while eating
	 */
	private void eating(){
		Random rand = new Random();
		this.eating=true;
		try {
			//this.wait((long)rand.nextInt(1000));
			Thread.sleep((long)rand.nextInt(1500));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.eating = false;//Done eating
	}

	/**
	 * return boolean value if philosopher is eating
	 * @return
	 */
	public synchronized boolean isEating(){
		return eating;
	}
	/**
	 * This method hold philosopher while thinking
	 */
	private void thinking(){
		while(isEating())
			try {
				wait();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		this.thinking = true;
		Random rand = new Random();
		try {
			//this.wait((long) rand.nextInt(1000));
			Thread.sleep((long)rand.nextInt(1500));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.thinking=false;
	}
	/**
	 * This method return the current thinking status of the philosopher
	 * @return boolean
	 */
	public synchronized boolean isThinking(){
		return thinking;
	}
	
	/**
	 * Turn on 'Philosopher' Thread
	 */
	public void setActive(){
		this.active = true;
	}
	/**
	 * Turn off 'Philosopher' Thread
	 */
	public void setInactive(){
		this.active=false;
	}
	/**
	 * Return if the philosopher is alive or dead.
	 * @return
	 */
	public synchronized boolean isActive(){
		return this.active;
	}
	public void run(){
		//running as long philosopher is active, gui can turn it off and kill it.
		while(active){
			//getFirstStick->getSecondStick->eat->release sticks->think
			//Step 1: get sticks, or wait
			this.firstStick = currentTable.getFirstStick(philosopherIndex);
			this.secondStick = currentTable.getSecondStick(philosopherIndex);
			System.out.println(this.getName() + " eating with sticks: " + firstStick.getValue() + "," + secondStick.getValue());
			
			//Step 2: Eating
			System.out.println(this.getName() + " eating."); //Test only
			eating();
			System.out.println(this.getName() + " done eating."); //Test only
			
			//Step 3: release sticks
			System.out.println(this.getName() + " release sticks"); //Test only
			currentTable.returnSticks(firstStick, secondStick);
			firstStick = null;
			secondStick = null;
			
			//Step 4: Thinking
			System.out.println(this.getName() + " start to think."); //Test only
			thinking();
			System.out.println(this.getName() + " done thinking."); //Test only
		}
		System.out.println(this.getName() + " bye bye");
	}
}
