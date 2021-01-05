package httpRaplServer;

import jRAPL.SyncEnergyMonitor;
import jRAPL.EnergyDiff;
import jRAPL.EnergyStats;

class JraplJobs {
	
	private static SyncEnergyMonitor energyMonitor = HttpRAPL.energyMonitor;
	
	protected static EnergyDiff energyLapse(int msec) {
		EnergyStats before = energyMonitor.getObjectSample(1);
		Utils.sleep_print(msec);
		EnergyStats after = energyMonitor.getObjectSample(1);
		return EnergyDiff.between(before, after);
	}
	
	protected static EnergyStats energySnapshot() {
		return energyMonitor.getObjectSample(1);
	}
}