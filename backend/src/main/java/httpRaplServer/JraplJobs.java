package httpRaplServer;

import jRAPL.SyncEnergyMonitor;

import java.time.Duration;
import jRAPL.EnergyDiff;
import jRAPL.EnergyStats;

final class JraplJobs {
	
	private static SyncEnergyMonitor energyMonitor = HttpRAPL.energyMonitor;
	
	protected static EnergyDiff energyDiff(int msec) {
		EnergyStats before = energyMonitor.getSample();
		Utils.sleep_print(msec);
		EnergyStats after = energyMonitor.getSample();
		return EnergyDiff.between(before, after);
	}
	
	protected static EnergyStats energySnapshot() {
		return energyMonitor.getSample();
	}

	protected static EnergyDiff[] energyDiffList(int duration, int interval)
	{
		int seconds = (int)(duration / interval);
		EnergyDiff[] diffs = new EnergyDiff[seconds];
		EnergyStats before = energyMonitor.getSample();
		EnergyStats after;
		for(int s = 1; s <= seconds; s++) {
			try{
				Thread.sleep(interval);
			} catch (InterruptedException ex) {
				System.out.println("!!");
				ex.printStackTrace();
				return null;
			}
			after = energyMonitor.getSample();
			diffs[s-1] = EnergyDiff.between(before,after);
			before = after;
		}
		return diffs;
	}
	public static void main(String args[]) {
		// energyMonitor.init();
		
		// EnergyDiff[] diffs = energyDiffList(5000,1000);
		// for (EnergyDiff d : diffs)
		// 	System.out.println(Utils.toJSON(d));
		
		// System.out.println("------------------------------");
		
		// EnergyDiff avg = EnergyDiff.average(diffs);
		// System.out.println(Utils.toJSON(avg));
		
		// energyMonitor.dealloc();
	}
}
/** Jobs I would like to see implemented:
 *	- Over the course of the next duration (in milliseconds), give average joules consumed per second
 */
