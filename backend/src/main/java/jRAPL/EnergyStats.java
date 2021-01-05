package jRAPL;

import java.time.Instant;
import java.time.Duration;

/** High-level representation of jrapl's energy stats. */
public final class EnergyStats extends EnergySample
{
	private Instant timestamp;

	public EnergyStats(int socket) {
		super(socket, EnergyStringParser.toPrimitiveArray(EnergyMonitor.energyStatCheck(socket)));
		this.timestamp = Instant.now();
	}
	
	public EnergyStats(int socket, double[] enerForSocket, Instant ts) {
		super(socket, enerForSocket);
		this.timestamp = ts;
	}

	public EnergyStats(int socket, double[] enerForSocket) {
		super(socket, enerForSocket);
		this.timestamp = Instant.now();
	}

	public String toJSON()
	{
		/// System.out.println("<< timestamp: " + timestamp);
		return "{" + arrayToJSONContent() + "\"timestamp\": "
				+ Long.toString(timestamp.getNano()/1000) + "}";
	}

	public void setTimestamp(Instant ts)
	{
		assert this.timestamp == null;
		this.timestamp = ts;
	}

	public Instant getTimeStamp(){
		return this.timestamp; // should I worry about not copying it?
	}

	@Override
	public String dump() {
		return super.dump() + Long.toString(timestamp.getNano()/1000); //microseconds
	}

	@Override
	public String toString() {
		return super.toString() + (
				(timestamp == null)
					? "null"
					: Long.toString(
						Duration.between(
								Instant.EPOCH,
								timestamp
						).getNano()/1000 //microseconds
					)
				);
	}

	public static void main(String[] args){
		SyncEnergyMonitor em = new SyncEnergyMonitor();
		em.init();
		EnergyStats es = em.getObjectSample(1);
		System.out.println(es);
		System.out.println(es.toJSON());
		em.dealloc();

	}
}

