package redmine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class TicketManager {

	private String host;	// ex)192.168.0.2
	private String apiKey;

	public TicketManager(String host, String apiKey) {
		this.host = host;
		this.apiKey = apiKey;
	}

	public void getCsvFile(File file, String urlStr) throws IOException {

		String[] splitUrl = urlStr.split("\\?");
		String openUrl = "";

		splitUrl[0] = "http://" + this.host + splitUrl[0] + "?key=" + this.apiKey + "&";

		for(String str : splitUrl) {
			openUrl += str;
		}

		System.out.println(openUrl);

		URL url = new URL(openUrl);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();

		FileOutputStream out = new FileOutputStream(file, false);
		int b;
		while((b = in.read()) != -1){
		    out.write(b);
		}

		out.close();
		in.close();

	}

}
