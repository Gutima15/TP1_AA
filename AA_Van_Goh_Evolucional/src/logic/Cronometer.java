package logic;

import graphics.MainFrame;

public class Cronometer extends Thread{
	
	private MainFrame mainframe;
	private boolean run;
	private int mins;
	private int secs;
	public Cronometer(MainFrame mainFrame) {
		this.mainframe = mainFrame;
		run = false;
		mins = 0;
		secs = 0;
	}
	public void run() {
		run = true;
		while(run==true) {
			if(secs==60) {
				secs=0;
				mins++;
			}
			mainframe.updateCronometer(mins, secs);
			secs++;
			try{
				Thread.sleep(1000);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void noRun() {
		run = false;
	}

}
