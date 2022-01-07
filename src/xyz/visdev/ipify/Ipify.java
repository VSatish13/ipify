package xyz.visdev.ipify;

import android.app.*;
import android.content.*;

import org.json.*;

import java.io.*;
import java.net.*;

import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.*;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.runtime.errors.*;
import com.google.appinventor.components.runtime.util.*;

public class Ipify extends AndroidNonvisibleComponent {

  private final Context context;
  private final Activity activity;
  public String ip = "";

  public Ipify(ComponentContainer container) {
    super(container.$form());
    context = container.$context();
    activity = container.$context();
  }

  @SimpleFunction(description = "Returns the sum of the given list of integers.")
  public String GetIpv6() {
    final String link = "https://api64.ipify.org/?format=json";
    AsynchUtil.runAsynchronously(new Runnable() {
      @Override
      public void run() {
        try {
          URL url = new URL(link);
          HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
          httpURLConnection.setRequestMethod("GET");
          BufferedReader bufferedReader = null;
          if (httpURLConnection.getResponseCode() == 200) {
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
          } else {
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
          }
          StringBuilder stringBuilder = new StringBuilder();
          String str;
          while ((str = bufferedReader.readLine()) != null) stringBuilder.append(str);
          bufferedReader.close();
          final String data = stringBuilder.toString();
          activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
              try {
                JSONObject jsonObj = new JSONObject(data);
                ip = jsonObj.getString("ip");
                } catch (JSONException e) {
                  ip = e.toString();
                }
            }});
        } catch (MalformedURLException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
    return ip;
  }

  
}
