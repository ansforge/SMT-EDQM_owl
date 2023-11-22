package fr.gouv.esante.pml.smt.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.gouv.esante.pml.smt.model.DomainList;
import fr.gouv.esante.pml.smt.model.TermsList;

public class GetDomainDataFromAPI {

	String data = null;

	public DomainList getDomainList() throws Exception {

		String uri = "https://standardterms.edqm.eu/standardterms/api/v1/domains";

		data = getURI(uri);
		System.out.println(data);
		ObjectMapper om = new ObjectMapper();
		DomainList root = om.readValue(data, DomainList.class);
		return root;
	}

	private String getURI(final String uri) throws Exception {

		// System.out.println("Getting URI...");
//		System.setProperty("http.proxyHost", "grpxy-vip.grita.fr");
//		System.setProperty("http.proxyPort", "3128");
//		System.setProperty("https.proxyHost", "grpxy-vip.grita.fr");
//		System.setProperty("https.proxyPort", "3128");
		String Login = "Ali.GOCHATH.EXT@esante.gouv.fr";
		String YourSecretAccessKey = "74qvhYRz";
		String HOST = "standardterms.edqm.eu";
		String ur = "/standardterms/api/v1/domains";
		String reqDate = getServerTime();
		String StringToSign = "GET" + "&" + ur + "&" + HOST + "&" + reqDate;
		String Signature = hashSignature(YourSecretAccessKey, StringToSign);
		String X_STAPI_KEY = Login + "|" + Signature;
		System.out.println(X_STAPI_KEY);
		System.out.println(StringToSign);

		final URL url = new URL(uri);
		final HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		// HTTP header fields to set
		con.setRequestProperty("Date", reqDate);
		con.setRequestProperty("X-STAPI-KEY", X_STAPI_KEY);

		final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String inputLine;
		final StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}

	public String getServerTime() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		return dateFormat.format(calendar.getTime());
	}

	public String hashSignature(String secret, String message) throws NoSuchAlgorithmException, InvalidKeyException {
		Mac sha256_HMAC = Mac.getInstance("HmacSHA512");
		SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA512");
		sha256_HMAC.init(secret_key);

		String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
		return hash.substring(hash.length() - 22);
	}

}
