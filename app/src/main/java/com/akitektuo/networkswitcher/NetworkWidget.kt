package com.akitektuo.networkswitcher

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.Toast

/**
 * Implementation of App Widget functionality.
 */
class NetworkWidget : AppWidgetProvider() {

    companion object {
        private const val CLICK = "click"
    }

    private var is4GActive = true

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }


    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {

        val views = RemoteViews(context.packageName, R.layout.network_widget)
        views.setInt(R.id.layoutNetwork, "setBackgroundResource", R.drawable.background_green)
        views.setTextViewText(R.id.textNetwork, "2G")
        views.setOnClickPendingIntent(R.id.layoutNetwork, getPendingIntent(context, CLICK))

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    private fun changeTo4G(views: RemoteViews) {
        views.setInt(R.id.layoutNetwork, "setBackgroundResource", R.drawable.background_red)
        views.setTextViewText(R.id.textNetwork, "4G")
    }

    private fun changeTo2G(views: RemoteViews) {
        views.setInt(R.id.layoutNetwork, "setBackgroundResource", R.drawable.background_green)
        views.setTextViewText(R.id.textNetwork, "2G")
    }

    private fun getPendingIntent(context: Context, action: String): PendingIntent {
        val intent = Intent(context, javaClass)
        intent.action = action
        return PendingIntent.getBroadcast(context, 0, intent, 0)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (CLICK == intent.action) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val views = RemoteViews(context.packageName, R.layout.network_widget)
            val component = ComponentName(context, NetworkWidget::class.java)

//            if (is4GActive) {
//                changeTo2G(views)
//            } else {
                changeTo4G(views)
//            }
//            is4GActive = !is4GActive
            Toast.makeText(context, "Clicked and 4G is active: $is4GActive", Toast.LENGTH_SHORT).show()
            appWidgetManager.updateAppWidget(component, views)
        }
    }
}

