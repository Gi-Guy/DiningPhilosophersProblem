import java.util.ArrayList;

/**
 * Each philosopher has an index, so then each left stick, of each philosopher, will be with value of the philosopher itself.
 * for example: philosopher index 1 -> left stick value 1, right stick value will be philosopher 5 left stick value
 * philosopher index 2 -> left stick value 2, right stick value 1*/
public class Table {
	ArrayList<Stick> sticks;
	
	public Table(ArrayList<Stick> sticks){
		this.sticks = sticks;
	}
	
	/**
	 * This Method will return the stick in index 'stickIndex'.
	 * If stick is busy, method will wait, otherwise will return an free stick.
	 * @param stickIndex
	 * @return Stick object of free stick
	 */
	private synchronized Stick getStick(int stickIndex){
		//will return only free stick, if stick is busy then wait
		while(!sticks.get(stickIndex).isFree()){
			//stick is busy, wait
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//stick isn't busy
		sticks.get(stickIndex).setBusy();
		return sticks.get(stickIndex);
	}
	
	/**
	 * This method will return the first lowest value stick of each philosopher
	 * @param philosopherIndex
	 * @return Stick - lowest value Stick
	 */
	public synchronized Stick getFirstStick(int philosopherIndex){
		//will try to return the lowest stick value, if philosopherIndex == 1, will try to return stick 1 instead.
		if(philosopherIndex == 1)
			return this.getStick(0);
		else
			return this.getStick(philosopherIndex-2);
		
	}
	/**
	 * This method will return the second highest value stick of each philosopher
	 * @param philosopherIndex
	 * @return Stick - highest value Stick
	 */
	public synchronized Stick getSecondStick(int philosopherIndex){
		//if philosopherIndex==1 then his second stick will be philosopherIndex == 5
		if(philosopherIndex==1)
			return this.getStick(4);
		else
			return this.getStick(philosopherIndex-1);
	}
	/**
	 * Use this method to return two sticks back to the table, This method will use notifyall() to call other thread that waiting. 
	 * @param firstStick
	 * @param secondStick
	 */
	public synchronized void returnSticks(Stick firstStick, Stick secondStick){
		sticks.get(sticks.indexOf(firstStick)).setFree();
		sticks.get(sticks.indexOf(secondStick)).setFree();
		this.notifyAll();
	}
}
