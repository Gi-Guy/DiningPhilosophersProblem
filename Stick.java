
public class Stick {
	private int value;
	private boolean free = true;
	
	public Stick(int value){
		this.value = value;
	}
	/**
	 * Return current object value
	 * @return
	 */
	public int getValue(){
		return this.value;
	}
	/**
	 * Return the current status of current object
	 * @return boolean
	 */
	public boolean isFree(){
		return this.free;
	}
	/**
	 * Set this object as 'Busy'
	 */
	public void setBusy(){
		this.free = false;
	}
	/**
	 * Set this object as 'Free'
	 */
	public void setFree(){
		this.free = true;
	}
}
