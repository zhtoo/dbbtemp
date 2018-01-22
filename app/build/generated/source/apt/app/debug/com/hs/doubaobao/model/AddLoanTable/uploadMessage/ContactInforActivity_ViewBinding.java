// Generated code from Butter Knife. Do not modify!
package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hs.doubaobao.R;
import com.hs.doubaobao.view.ExpandableView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContactInforActivity_ViewBinding<T extends ContactInforActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ContactInforActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.expandableView1 = Utils.findRequiredViewAsType(source, R.id.expandable_view1, "field 'expandableView1'", ExpandableView.class);
    target.expandableView2 = Utils.findRequiredViewAsType(source, R.id.expandable_view2, "field 'expandableView2'", ExpandableView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.expandableView1 = null;
    target.expandableView2 = null;

    this.target = null;
  }
}
