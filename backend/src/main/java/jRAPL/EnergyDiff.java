
package jRAPL;

import java.time.Duration;

public final class EnergyDiff extends EnergySample
{
	private Duration elapsedTime = null; //time between the two EnergyStamps

	public EnergyDiff(int socket, double[] enerForSocket, Duration elapsedTime) {
		super(socket, enerForSocket);
		this.elapsedTime = elapsedTime;
	}

	public EnergyDiff(int socket, double[] enerForSocket) {
		super(socket, enerForSocket);
		this.elapsedTime = null;
	}

	public Duration getElapsedTime() {
		return this.elapsedTime;
	}

	public static EnergyDiff average(EnergyDiff[] diffs) {
		int socket = diffs[0].getSocket();
		double[] avgs = new double[diffs[0].getNumberOfEnergyReadings()]; // it'll be initialized to all zeroes, right?
		long elapsedtimeAvg = 0; // nanoseconds
		int n = diffs.length;
		for (int i = 0; i < n; i++) {
			EnergyDiff current = diffs[i];
			assert current.getSocket() == socket;
			double[] energies = current.getEnergyArray();
			for (int j = 0; j < energies.length; j++)
				avgs[j] += energies[j];	
			elapsedtimeAvg += current.getElapsedTime().getNano(); // nanoseconds
		}
		for (int j = 0; j < avgs.length; j++ )
			avgs[j] /= n;
		elapsedtimeAvg /= n;
		return new EnergyDiff(diffs[0].getSocket(), avgs, Duration.ofNanos(elapsedtimeAvg));
	}

	@Override
	public String dump() {
		return String.join(
			",",
			super.dump(),
			(this.elapsedTime == null)
			? ("null")
			: (Long.toString(this.elapsedTime.toNanos()))
		);
	}

	@Override
	public String toJSON() {
		return "{ " + arrayToJSONContent() + " \"elapsedTime\": " 
				+ Long.toString(elapsedTime.getNano()/1000) + "}";
	}

	public static EnergyDiff between(EnergyStats before, EnergyStats after) {
		assert after.socket == before.socket;
		assert after.ener.length == before.ener.length;		

		double[] enerDiff = new double[before.ener.length];
		for (int i = 0; i < after.ener.length; i++) {
			enerDiff[i] = after.ener[i] - before.ener[i];
			if (enerDiff[i] < 0) enerDiff[i] += ArchSpec.RAPL_WRAPAROUND;
		}

		Duration elapsedTime = Duration.between(before.getTimeStamp(), after.getTimeStamp());

		return new EnergyDiff (before.socket, enerDiff, elapsedTime );
	}

	@Override
	public String toString() {
		return String.join(
			", ",
			super.toString(),
			"Duration (microseconds): " + this.elapsedTime.toNanos()/1000
		);	
	}

	public static void main(String[] args) throws InterruptedException {
		SyncEnergyMonitor em = new SyncEnergyMonitor();
		em.init();
		EnergyStats x = em.getObjectSample(1);
		Thread.sleep(100);
		EnergyStats y = em.getObjectSample(1);
		EnergyDiff z = EnergyDiff.between(x,y);
		System.out.println(z);
		System.out.println(z.toJSON());

		em.dealloc();
	}	

}





