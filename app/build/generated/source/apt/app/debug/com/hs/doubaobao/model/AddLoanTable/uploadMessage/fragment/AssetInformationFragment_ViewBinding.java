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

public class AssetInformationFragment_ViewBinding<T extends AssetInformationFragment> implements Unbinder {
  protected T target;

  @UiThread
  public AssetInformationFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.mOwnAddressEdit = Utils.findRequiredViewAsType(source, R.id.lender_own_address_edit, "field 'mOwnAddressEdit'", TextView.class);
    target.mOwnHouseAddress = Utils.findRequiredViewAsType(source, R.id.lender_own_house_address, "field 'mOwnHouseAddress'", LinearLayout.class);
    target.mOwnStreetEdit = Utils.findRequiredViewAsType(source, R.id.lender_own_street_edit, "field 'mOwnStreetEdit'", EditText.class);
    target.mOwnHouseAreaEdit = Utils.findRequiredViewAsType(source, R.id.lender_own_house_area_edit, "field 'mOwnHouseAreaEdit'", EditText.class);
    target.mOwnHousePropertyEdit = Utils.findRequiredViewAsType(source, R.id.lender_own_house_property_edit, "field 'mOwnHousePropertyEdit'", TextView.class);
    target.mOwnHouseProperty = Utils.findRequiredViewAsType(source, R.id.lender_own_house_property, "field 'mOwnHouseProperty'", LinearLayout.class);
    target.mMonthlyIncomeEdit = Utils.findRequiredViewAsType(source, R.id.lender_monthly_income_edit, "field 'mMonthlyIncomeEdit'", EditText.class);
    target.mBuildPriceEdit = Utils.findRequiredViewAsType(source, R.id.lender_buildPrice_edit, "field 'mBuildPriceEdit'", EditText.class);
    target.mBuildPrice = Utils.findRequiredViewAsType(source, R.id.lender_buildPrice, "field 'mBuildPrice'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mOwnAddressEdit = null;
    target.mOwnHouseAddress = null;
    target.mOwnStreetEdit = null;
    target.mOwnHouseAreaEdit = null;
    target.mOwnHousePropertyEdit = null;
    target.mOwnHouseProperty = null;
    target.mMonthlyIncomeEdit = null;
    target.mBuildPriceEdit = null;
    target.mBuildPrice = null;

    this.target = null;
  }
}
