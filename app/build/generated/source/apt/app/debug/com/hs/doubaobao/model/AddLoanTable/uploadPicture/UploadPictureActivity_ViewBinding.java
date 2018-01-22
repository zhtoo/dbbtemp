// Generated code from Butter Knife. Do not modify!
package com.hs.doubaobao.model.AddLoanTable.uploadPicture;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hs.doubaobao.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UploadPictureActivity_ViewBinding<T extends UploadPictureActivity> implements Unbinder {
  protected T target;

  @UiThread
  public UploadPictureActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.tablayout = Utils.findRequiredViewAsType(source, R.id.upload_picture_tablayout, "field 'tablayout'", TabLayout.class);
    target.mViewpager = Utils.findRequiredViewAsType(source, R.id.upload_picture_viewpager, "field 'mViewpager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tablayout = null;
    target.mViewpager = null;

    this.target = null;
  }
}
