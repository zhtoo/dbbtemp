// Generated code from Butter Knife. Do not modify!
package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hs.doubaobao.R;
import com.hs.doubaobao.view.ArcProgressView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FilloutLenderInformationActivity_ViewBinding<T extends FilloutLenderInformationActivity> implements Unbinder {
  protected T target;

  private View view2131689648;

  private View view2131689637;

  private View view2131689640;

  private View view2131689643;

  @UiThread
  public FilloutLenderInformationActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mBasicProgress = Utils.findRequiredViewAsType(source, R.id.fillout_basic_info_progress, "field 'mBasicProgress'", ArcProgressView.class);
    target.mLiveProgress = Utils.findRequiredViewAsType(source, R.id.fillout_live_info_progress, "field 'mLiveProgress'", ArcProgressView.class);
    target.mAssetProgress = Utils.findRequiredViewAsType(source, R.id.fillout_asset_info_progress, "field 'mAssetProgress'", ArcProgressView.class);
    view = Utils.findRequiredView(source, R.id.fillout_lender_info_save, "field 'filloutSave' and method 'onViewClicked'");
    target.filloutSave = Utils.castView(view, R.id.fillout_lender_info_save, "field 'filloutSave'", Button.class);
    view2131689648 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.fillout_basic_info_tab, "field 'mBasicInfoTab' and method 'onViewClicked'");
    target.mBasicInfoTab = Utils.castView(view, R.id.fillout_basic_info_tab, "field 'mBasicInfoTab'", FrameLayout.class);
    view2131689637 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.fillout_live_info_tab, "field 'mtLiveInfoTab' and method 'onViewClicked'");
    target.mtLiveInfoTab = Utils.castView(view, R.id.fillout_live_info_tab, "field 'mtLiveInfoTab'", FrameLayout.class);
    view2131689640 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.fillout_asset_info_tab, "field 'mAssetInfoTab' and method 'onViewClicked'");
    target.mAssetInfoTab = Utils.castView(view, R.id.fillout_asset_info_tab, "field 'mAssetInfoTab'", FrameLayout.class);
    view2131689643 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mTabLayout = Utils.findRequiredViewAsType(source, R.id.lender_info_tablayout, "field 'mTabLayout'", TabLayout.class);
    target.mViewpager = Utils.findRequiredViewAsType(source, R.id.lender_info_viewpager, "field 'mViewpager'", ViewPager.class);
    target.mBasicInfoIcon = Utils.findRequiredViewAsType(source, R.id.fillout_basic_info_icon, "field 'mBasicInfoIcon'", ImageView.class);
    target.mLiveInfoIcon = Utils.findRequiredViewAsType(source, R.id.fillout_live_info_icon, "field 'mLiveInfoIcon'", ImageView.class);
    target.mAssetInfoIcon = Utils.findRequiredViewAsType(source, R.id.fillout_asset_info_icon, "field 'mAssetInfoIcon'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mBasicProgress = null;
    target.mLiveProgress = null;
    target.mAssetProgress = null;
    target.filloutSave = null;
    target.mBasicInfoTab = null;
    target.mtLiveInfoTab = null;
    target.mAssetInfoTab = null;
    target.mTabLayout = null;
    target.mViewpager = null;
    target.mBasicInfoIcon = null;
    target.mLiveInfoIcon = null;
    target.mAssetInfoIcon = null;

    view2131689648.setOnClickListener(null);
    view2131689648 = null;
    view2131689637.setOnClickListener(null);
    view2131689637 = null;
    view2131689640.setOnClickListener(null);
    view2131689640 = null;
    view2131689643.setOnClickListener(null);
    view2131689643 = null;

    this.target = null;
  }
}
