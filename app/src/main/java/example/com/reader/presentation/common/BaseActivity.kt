package example.com.reader.presentation.common

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity
import example.com.reader.R
import timber.log.Timber


abstract class BaseActivity : DaggerAppCompatActivity(), MvpView {

    var toast: Toast? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent != null && intent.extras != null) {
            val argumentsBundle = intent.extras
            if (savedInstanceState != null) {
                argumentsBundle.putAll(savedInstanceState)
            }
            extractArguments(argumentsBundle)
        }
    }

    protected open fun extractArguments(bundle: Bundle) {
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setContentView(layoutResID: Int) {
        val contentRoot = findViewById<ViewGroup>(android.R.id.content)
        val contentView = layoutInflater.inflate(layoutResID, contentRoot, false)
        setContentView(contentView)
    }

    override fun showMessage(messageResId: Int) {
        showMessage(resources.getString(messageResId))
    }

    override fun showMessage(message: String?) {
        toast?.cancel()
        toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        val tv: TextView? = toast?.view?.findViewById<TextView>(android.R.id.message)
        tv?.gravity = Gravity.CENTER
        toast?.show()
    }

    fun safeStartActivity(intent: Intent) {
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Timber.e(e)
            showDialog(R.string.error_title_default, R.string.default_error)
        }
    }

    fun dialogBuilder(): AlertDialog.Builder = AlertDialog.Builder(this)

    fun showDialog(@StringRes titleId: Int, @StringRes descId: Int) {
        dialogBuilder().setTitle(titleId)
                .setMessage(descId)
                .setPositiveButton(android.R.string.ok, null)
                .show()
    }

    fun showDialog(title: String?, desc: String) {
        dialogBuilder().setTitle(title)
                .setMessage(desc)
                .setPositiveButton(android.R.string.ok, null)
                .show()
    }


    fun openAppSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
    }

}