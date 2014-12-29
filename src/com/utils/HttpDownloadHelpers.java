
package com.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * @author Li
 *
 */
/**
 * @author Li
 *
 */
public class HttpDownloadHelpers {

	private URL url = null;

	public String download(String urlStr) {
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;

		try {
			//����һ��URL����
			url = new URL(urlStr);
			//����һ��Http����
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection();
			//ʹ��IO����ȡ����
			buffer = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));
			while ((line = buffer.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buffer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	

	public InputStream getInputStreamFromUrl(String urlStr)
			throws MalformedURLException, IOException {
		url = new URL(urlStr);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		InputStream inputStream = urlConn.getInputStream();
		return inputStream;
	}
}