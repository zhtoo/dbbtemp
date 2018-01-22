// Generated code from Butter Knife. Do not modify!
package com.hs.doubaobao.model.AddLoanTable.uploadPicture;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hs.doubaobao.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UploadPictureFragment_ViewBinding<T extends UploadPictureFragment> implements Unbinder {
  protected T target;

  @UiThread
  public UploadPictureFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.mRecycler = Utils.findRequiredViewAsType(source, R.id.upload_picture_recycler, "field 'mRecycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mRecycler = null;

    this.target = null;
  }
}
