package example.com.reader.presentation.common.loading

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import example.com.reader.presentation.common.mvp.BaseMvpActivity

abstract class BaseLoadingActivity<V : BaseLoadingView, P : BaseLoadingPresenter<V>> : BaseMvpActivity<V, P>(), BaseLoadingView {

    private var loadingContainerId = android.R.id.content
    private var fragmentManager: FragmentManager = supportFragmentManager
    private var loadingFragment: Fragment = LoadingFragment()

    override fun showLoading() {
        if (!loadingFragment.isAdded) {
            fragmentManager
                    .beginTransaction()
                    .add(loadingContainerId, loadingFragment)
                    .commit()
        }
    }

    override fun hideLoading() {
        fragmentManager
                .beginTransaction()
                .remove(loadingFragment)
                .commitAllowingStateLoss()
    }

}