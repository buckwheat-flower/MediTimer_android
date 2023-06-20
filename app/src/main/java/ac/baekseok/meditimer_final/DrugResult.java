package ac.baekseok.meditimer_final;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DrugResult extends MainActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_search);

        WebView webView = (WebView)findViewById(R.id.webView);
        String result = getIntent().getStringExtra("scan");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://m.search.naver.com/search.naver?sm=mtp_sly.hst&where=m&query=" + result);
    }
}
