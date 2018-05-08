package hagenahbsw.info.unterkunft

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class UnterkunftDetailActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_URL = "url"

        fun newIntent(context: Context, unterkunft: Unterkunft): Intent {
            val detailIntent = Intent(context, UnterkunftDetailActivity::class.java)

            detailIntent.putExtra(EXTRA_TITLE, unterkunft.name)
            detailIntent.putExtra(EXTRA_URL, unterkunft.link_href)

            return detailIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unterkunft_detail)

        val title = intent.extras.getString(EXTRA_TITLE)
        val url = intent.extras.getString(EXTRA_URL)
        setTitle(title)
        webView = findViewById(R.id.detail_web_view)
        webView.loadUrl(url)
    }
}
