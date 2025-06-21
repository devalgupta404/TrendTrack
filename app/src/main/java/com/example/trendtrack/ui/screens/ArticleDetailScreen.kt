package com.example.trendtrack.ui.screens

import android.graphics.Color
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.trendtrack.data.model.Article
import com.example.trendtrack.ui.components.LoadingAnimation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(
    article: Article,
    onBackClick: () -> Unit,
    onShareClick: (String) -> Unit
){
    val context = LocalContext.current
    val webView = remember { WebView(context) }
    val primaryColor = MaterialTheme.colorScheme.primary.toArgb()
    LaunchedEffect(Unit) {
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            setSupportZoom(true)
            builtInZoomControls = true
            displayZoomControls = false
            loadWithOverviewMode = true
            useWideViewPort = true
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                view?.evaluateJavascript("""
                    (function() {
                        // Remove any existing theme styles
                        var styleElements = document.getElementsByTagName('style');
                        for(var i = styleElements.length - 1; i >= 0; i--) {
                            if(styleElements[i].textContent.includes('color-scheme') || 
                               styleElements[i].textContent.includes('prefers-color-scheme')) {
                                styleElements[i].remove();
                            }
                        }
                        
                        // Remove theme-related meta tags
                        var metaElements = document.getElementsByTagName('meta');
                        for(var i = metaElements.length - 1; i >= 0; i--) {
                            if(metaElements[i].getAttribute('name') === 'color-scheme' ||
                               metaElements[i].getAttribute('name') === 'theme-color') {
                                metaElements[i].remove();
                            }
                        }
                        
                        // Remove theme-related attributes
                        document.documentElement.removeAttribute('data-theme');
                        document.documentElement.removeAttribute('data-color-scheme');
                        document.body.removeAttribute('data-theme');
                        document.body.removeAttribute('data-color-scheme');
                        
                        // Remove any theme-related classes
                        document.documentElement.classList.remove('dark', 'light');
                        document.body.classList.remove('dark', 'light');
                    })();
                """.trimIndent(), null)
            }
        }
        
        webView.loadUrl(article.url)
    }
    DisposableEffect(Unit) {
        onDispose {
            webView.destroy()
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Article") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { onShareClick(article.url) }) {
                        Icon(Icons.Default.Share, contentDescription = "Share")
                    }
                }
            )
        }
    ){ paddingValues ->
        AndroidView(
            factory ={webView},
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
} 