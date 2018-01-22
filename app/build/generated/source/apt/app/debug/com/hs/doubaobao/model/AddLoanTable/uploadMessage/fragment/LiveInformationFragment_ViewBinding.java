// Generated code from Butter Knife. Do not modify!
package com.hs.doubaobao.model.AddLoanTable.uploadMessage.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hs.doubaobao.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LiveInformationFragment_ViewBinding<T extends LiveInformationFragment> implements Unbinder {
  protected T target;

  @UiThread
  public LiveInformationFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.mLiveAddress = Utils.findRequiredViewAsType(source, R.id.lender_live_address, "field 'mLiveAddress'", LinearLayout.class);
    target.mLiveAddressEdit = Utils.findRequiredViewAsType(source, R.id.lender_live_address_edit, "field 'mLiveAddressEdit'", TextView.class);
    target.mLiveStreet = Utils.findRequiredViewAsType(source, R.id.lender_live_street, "field 'mLiveStreet'", TextView.class);
    target.mLiveStreetEdit = Utils.findRequiredViewAsType(source, R.id.lender_live_street_edit, "field 'mLiveStreetEdit'", EditText.class);
    target.mSupportNumber = Utils.findRequiredViewAsType(source, R.id.lender_support_number, "field 'mSupportNumber'", TextView.class);
    target.mSupportNumberEdit = Utils.findRequiredViewAsType(source, R.id.lender_support_number_edit, "field 'mSupportNumberEdit'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mLiveAddress = null;
    target.mLiveAddressEdit = null;
    target.mLiveStreet = null;
    target.mLiveStreetEdit = null;
    target.mSupportNumber = null;
    target.mSupportNumberEdit = null;

    this.target = null;
  }
}
