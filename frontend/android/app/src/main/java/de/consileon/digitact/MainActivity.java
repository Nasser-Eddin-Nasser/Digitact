package de.consileon.digitact;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.getcapacitor.BridgeActivity;
import com.getcapacitor.Plugin;

import java.util.ArrayList;

public class MainActivity extends BridgeActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Initializes the Bridge
    this.init(savedInstanceState, new ArrayList<Class<? extends Plugin>>() {{
      // Additional plugins you've installed go here
      // Ex: add(TotallyAwesomePlugin.class);
    }});
  }

  @Override
  public void onStart() {
    super.onStart();

    handleDisabledOrEnabledDarkMode();
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);

    handleDisabledOrEnabledDarkMode();
  }

  /**
   * If Dark Mode is currently turned on, set it for our WebView.
   * If not, disable Dark Mode for the WebView.
   */
  private void handleDisabledOrEnabledDarkMode() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
      return;
    }

    WebView webView = this.bridge.getWebView();
    WebSettings webSettings = webView.getSettings();

    if ((getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_YES) == 0) {
      webSettings.setForceDark(WebSettings.FORCE_DARK_OFF);
      return;
    }

    webSettings.setForceDark(WebSettings.FORCE_DARK_ON);
  }
}
