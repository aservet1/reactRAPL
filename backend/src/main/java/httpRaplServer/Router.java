
package httpRaplServer;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import java.util.Date;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import jRAPL.*;

/** Parse url path, call a JraplJob (get energy stats, get energy lapse over milliseconds, etc) 
 * if necessary, and then package up a response and header into a a String array {header, response}
 * which is then send to the appropriate channels in the calling HttpRAPL server class.
*/
class Router {
	/** Returns {header, body} of GET request on 'path' */
    static String[] route(String fileRequested) {

        String header, body;
        boolean success;
		
		// 'fillerPrefix' is used to prevent a malformed URL exception, since my server doesn't provide
		// the scheme, authority, user info, port, or host. and it's not relevant to the method of this
		// Router class, which is to look through the path and query to pick which jRAPL request to satisfy
		// I dont intend to use url.get() any info provided by this String, just the stuff from the
		// path and query.
		String fillerPrefix = "https://localhost:1111";
		URL url = null;
		try{
			url = new URL(fillerPrefix + fileRequested);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
			//@TODO some sort of error message / status for bad url? or just 404 not found?
		}
		
		HashMap<String,String> queryMap = queryToMap(url.getQuery());

		switch (url.getPath()) {
			case "/":
				body = Pages.WELCOME;
				success = true;
				break;
			case "/energy/stats":
				body = Utils.toJSON (
					JraplJobs.energySnapshot()
				);
				success = true;
				break;
			case "/energy/diff/sample":
				int milliseconds = Integer.parseInt(queryMap.get("duration"));
				body = Utils.toJSON (
					JraplJobs.energyDiff(milliseconds)
				);
				success = true;
				break;
			case "/energy/diff/list": //@TODO return an error JSON or something if the query params are invalid
				int samplingRate = Integer.parseInt(queryMap.get("sampling_rate"));
				int duration = Integer.parseInt(queryMap.get("duration"));
				body = String.format("[%s]",
					String.join(",", Utils.diffListToJsonList (
						JraplJobs.energyDiffList(duration, samplingRate)
					)));
				success = true;
				break;
			default:
		    	body = Pages.NOT_FOUND;
            	success = false;		
		}

		// Create header based off of collected info
		header = String.join("\n" , 
			"HTTP/1.1 " + ((success) ? "200 OK" : "404 NOT FOUND"), //@TODO some day get more robust error codes
			"Server: HttpRAPL Server for Energy Requests : 1.0",
			"Date: " + new Date(),
			// "Content-type: " + "text/html", // @TODO not sure if you can just blanket claim that it's "text/html"
			"Content-length: " + body.getBytes().length,
			"Access-Control-Allow-Origin: *"
		);

        return new String[]{header, body};
	}

	// I got this code of the internet but I'm pretty sure it works dandily. I haven't fully confirmed and vetted its usefulness, though
	// Assumes query delimiter is '&'. Consider adding functionality for other delimiters, if need be (just have the split take a regex with the | things)
	private static HashMap<String, String> queryToMap(String source) {// throws Exception {
		if (source == null) return null;
		HashMap<String, String> data = new HashMap<String, String>();
		final String[] arrParameters = source.split("&");
		for (final String tempParameterString : arrParameters) {
			final String[] arrTempParameter = tempParameterString .split("=");
			if (arrTempParameter.length >= 2) {
				final String parameterKey = arrTempParameter[0];
				final String parameterValue = arrTempParameter[1];
				data.put(parameterKey, parameterValue);
			} else {
				final String parameterKey = arrTempParameter[0];
				data.put(parameterKey, "");
			}
		}
		return data;
	}
}
