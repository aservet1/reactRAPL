
package httpRaplServer;

import java.util.Date;
import jRAPL.*;

/** Parse url path, call a JraplJob (get energy stats, get energy lapse over milliseconds, etc) 
 * if necessary, and then package up a response and header into a a String array {header, response}
 * which is then send to the appropriate channels in the calling HttpRAPL server class.
*/
class Router {
	/** Returns header and body of GET request on 'path' */
    static String[] route(String path) {
        String header, body;
        boolean success;
		
		// if-else ladder of routing
		if (path.equals("/"))
		{
			body = Pages.WELCOME;
			success = true;
		}
		else if (path.equals("/energy/stats"))
		{
			body = Utils.toJSON(JraplJobs.energySnapshot());
			success = true;
		}
		else if (path.startsWith("/energy/diff/")) 
		{
			String suffix = path.substring("/energy/diff/".length());
			try {
				if (suffix.startsWith("list:")) { // '/energy/diff/list:{duration},{samplingRate}' //@TODO: get legit URL query, not list:%d,%d
					suffix = suffix.substring("list:".length());
					String[] params = suffix.split(",");
					
					String[] energyDiffJSONs = Utils.diffListToJsonList(
						JraplJobs.energyDiffList(
							Integer.parseInt(params[0]),
							Integer.parseInt(params[1])
						)
					);
					body = String.format("[%s]", String.join(",", energyDiffJSONs));
					success = true;
				} else { // will overall be of the form '/energy/diff/{milliseconds}'	
					int milliseconds = Integer.parseInt(suffix);
					body = Utils.toJSON(JraplJobs.energyDiff(milliseconds));
					success = true;
				}

			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
				body = Pages.NOT_FOUND;
				success = false;
			}
		}
	    else {
            body = Pages.NOT_FOUND;
            success = false;
        }
 
		// Create header based off of collected info
		header = String.join("\n" , 
				"HTTP/1.1 " + ((success) ? "200 OK" : "404 NOT FOUND"),
    		    "Server: HttpRAPL Server for Energy Requests : 1.0",
    	    	"Date: " + new Date(),
        		// "Content-type: " + "text/html", // @TODO not sure if you can just blanket claim that it's "text/html"
				"Content-length: " + body.getBytes().length,
				"Access-Control-Allow-Origin: *"
			);

        return new String[]{header, body};
    }    
}
