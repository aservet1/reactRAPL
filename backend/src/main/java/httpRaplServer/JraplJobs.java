package httpRaplServer;

import jRAPL.SyncEnergyMonitor;

import java.time.Duration;

import jRAPL.EnergyDiff;
import jRAPL.EnergyStats;

final class JraplJobs {
	
	private static SyncEnergyMonitor energyMonitor = HttpRAPL.energyMonitor;
	//static {
	//	energyMonitor = new SyncEnergyMonitor();
	//}
	
	protected static EnergyDiff energyDiff(int msec) {
		EnergyStats before = energyMonitor.getObjectSample(1);
		Utils.sleep_print(msec);
		EnergyStats after = energyMonitor.getObjectSample(1);
		return EnergyDiff.between(before, after);
	}
	
	protected static EnergyStats energySnapshot() {
		return energyMonitor.getObjectSample(1);
	}

	protected static EnergyDiff[] energyDiffList(int duration, int interval)
	{
		int seconds = (int)(duration / interval);
		EnergyDiff[] diffs = new EnergyDiff[seconds];
		EnergyStats before = energyMonitor.getObjectSample(1);
		EnergyStats after;
		for(int s = 1; s <= seconds; s++) {
			try{
				Thread.sleep(interval);
			} catch (InterruptedException ex) {
				System.out.println("!!");
				ex.printStackTrace();
				return null;
			}
			after = energyMonitor.getObjectSample(1);
			diffs[s-1] = EnergyDiff.between(before,after);
			before = after;
		}
		return diffs;
	}
	public static void main(String args[]) {
		energyMonitor.init();
		
		EnergyDiff[] diffs = energyDiffList(5000,1000);
		for (EnergyDiff d : diffs)
			System.out.println(d.toJSON());
		
		System.out.println("------------------------------");
		
		EnergyDiff avg = EnergyDiff.average(diffs);
		System.out.println(avg.toJSON());
		
		energyMonitor.dealloc();
	}
}
/** Jobs I would like to see implemented:
 *	- Over the course of the next duration (in milliseconds), give average joules consumed per second
 */