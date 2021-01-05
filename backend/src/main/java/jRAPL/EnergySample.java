package jRAPL;

//import java.time.Instant;
//import java.time.Duration;

public abstract class EnergySample
{
	protected final int socket;
	
	protected final double[] ener;
	//protected Instant timestamp;

	// public EnergySample(int socket, double[] enerForSocket)//, Instant timestamp)
	// {
	// 	this.socket = socket;
	// 	this.ener = enerForSocket;
	// 	//this.timestamp = timestamp;
	// }

	public EnergySample(int socket, double[] enerForSocket)
	{
		this.socket = socket;
		this.ener = enerForSocket;
		//this.timestamp = Instant.now();
	}

	protected String arrayToJSONContent()
	{
		String jsn = new String();
		jsn += "\"CORE\": " + getCore() + ","
			+ "\"DRAM\": " + getDram() + ","
			+ "\"GPU\": " + getGpu() + ","
			+ "\"PKG\": " + getPackage() + ",";
		return jsn;
	}
	
	public double[] getEnergyArray() {
		return ener.clone();
	}

	public int getNumberOfEnergyReadings() {
		return ener.length;
	}

	public int getSocket() {
		return this.socket;
	}

	public double getCore() {
		return this.ener[ArchSpec.CORE_ARRAY_INDEX];
	}

	public double getGpu() {
		return this.ener[ArchSpec.GPU_ARRAY_INDEX];
	}

	public double getPackage() {
		return this.ener[ArchSpec.PKG_ARRAY_INDEX];
	}

	public double getDram() {
		return this.ener[ArchSpec.DRAM_ARRAY_INDEX];
	}
	
	public String dump() {		
		String joinedStats = new String();
		int i = 0;
		for (; i < ener.length-1; i++) joinedStats += String.format("%4f", ener[i]) + ",";
		joinedStats += String.format("%4f",ener[i]);

		return String.join(
			",",
			String.format("%d", socket),
			joinedStats//,
			// (timestamp == null)
			// 	? "null"
			// 	: Long.toString(
			// 		Duration.between(
			// 				Instant.EPOCH,
			// 				timestamp
			// 		).toNanos()/1000 //microseconds
			// 	)
		);
	}

	public abstract String toJSON();

	@Override
	public String toString() {
		//System.out.println(Arrays.toString(ener));
		String labeledStats = new String();
		if (ArchSpec.DRAM_ARRAY_INDEX != -1) labeledStats += "DRAM: " + String.format("%.4f", ener[ArchSpec.DRAM_ARRAY_INDEX]) + ", ";
		if (ArchSpec.GPU_ARRAY_INDEX != -1)  labeledStats += "GPU: " + String.format("%.4f", ener[ArchSpec.GPU_ARRAY_INDEX]) + ", ";
		if (ArchSpec.PKG_ARRAY_INDEX != -1)  labeledStats += "Package: " + String.format("%.4f", ener[ArchSpec.PKG_ARRAY_INDEX]) + ", ";
		if (ArchSpec.CORE_ARRAY_INDEX != -1) labeledStats += "Core: " + String.format("%.4f", ener[ArchSpec.CORE_ARRAY_INDEX]) + ", ";

		if (labeledStats.length() == 0) labeledStats = "No power domains supported, ";
		// String timestampString = (timestamp == null) ? ("null")
		// 						: ("Timestamp (usecs since epoch): " 
		// 							+ Duration.between(Instant.EPOCH, timestamp)
		// 							.toNanos()/1000);

		return String.format("Socket: %d, ", socket) + labeledStats /*+ timestampString*/; 
	}


}
