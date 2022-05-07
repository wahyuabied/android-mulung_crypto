package com.wahyuabid.mulungcrypto.browser

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.wahyuabid.mulungcrypto.R
import com.wahyuabid.mulungcrypto.ext.forceOrientation
import com.wahyuabid.mulungcrypto.ext.setOnSingleClickListener
import com.wahyuabid.mulungcrypto.view.ProgressDialog
import kotlinx.android.synthetic.main.activity_browser.*
import android.content.DialogInterface
import android.graphics.Bitmap

import android.widget.Toast

import android.webkit.WebView
import androidx.core.view.isInvisible
import com.wahyuabid.mulungcrypto.ext.invisible
import com.wahyuabid.mulungcrypto.ext.visible


class BrowserActivity : AppCompatActivity() {
    companion object{
        const val URL = "URL"
        fun getIntent(context: Context, url:String): Intent {
            return Intent(context,BrowserActivity::class.java).apply {
                putExtra(URL, url)
            }
        }
    }

    var url: String = ""
    var loading: ProgressDialog ?= null

    fun initData(){
        loading = ProgressDialog(this)
        url = intent.getStringExtra(URL).orEmpty()
        tv_url.setText(url)
        loading?.showLoading(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)
        forceOrientation()
        initData()

        iv_share.setOnSingleClickListener {
            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,tv_url.text.toString())
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))
        }

        iv_back.setOnSingleClickListener {
            onBackPressed()
        }

        iv_reload.setOnSingleClickListener {
            wv_browser.reload()
        }

        iv_stop.setOnSingleClickListener {
            wv_browser.stopLoading()
        }

        wv_browser.setWebViewClient(object : WebViewClient() {
            override fun onLoadResource(view: WebView?, url: String?) {
                super.onLoadResource(view, url)
                url?.let {
                    if(it.contains("tg:resolve?domain")){
                           
                    }
                }
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                tv_url.setText(url)
                onSetLoading(true)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                onSetLoading(false)
                cl_empty.invisible()
                wv_browser.visible()
            }

            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                onSetLoading(false)
                cl_empty.visible()
                wv_browser.invisible()
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                onSetLoading(true)
            }

            override fun onPageCommitVisible(view: WebView?, url: String?) {
                super.onPageCommitVisible(view, url)
                onSetLoading(false)
            }
        })

        wv_browser.loadUrl(url)
    }

    override fun onBackPressed() {
        if(wv_browser.canGoBack()){
            wv_browser.goBack().also {
                tv_url.text = wv_browser.originalUrl
            }
        }else{
            super.onBackPressed()
        }
    }

    private fun onSetLoading(value: Boolean){
        if(value){
            iv_stop.visible()
            iv_reload.invisible()
            loading?.showLoading(true)
        }else{
            iv_reload.visible()
            iv_stop.invisible()
            loading?.showLoading(false)
        }
    }
}
