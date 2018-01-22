// Generated code from Butter Knife. Do not modify!
package com.hs.doubaobao.model.department;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hs.doubaobao.R;
import com.hs.doubaobao.view.main.Indicator;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DepartmentStatisticsActivity_ViewBinding<T extends DepartmentStatisticsActivity> implements Unbinder {
  protected T target;

  @UiThread
  public DepartmentStatisticsActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mStoresName = Utils.findRequiredViewAsType(source, R.id.ds_stores_name, "field 'mStoresName'", TextView.class);
    target.mViewpager = Utils.findRequiredViewAsType(source, R.id.ds_viewpager, "field 'mViewpager'", ViewPager.class);
    target.mIndicator = Utils.findRequiredViewAsType(source, R.id.indicator, "field 'mIndicator'", Indicator.class);
    target.mPtr = Utils.findRequiredViewAsType(source, R.id.ds_ptr, "field 'mPtr'", PtrClassicFrameLayout.class);
    target.mTodayApply = Utils.findRequiredViewAsType(source, R.id.department_today_apply, "field 'mTodayApply'", RadioButton.class);
    target.mTotalLending = Utils.findRequiredViewAsType(source, R.id.department_total_lending, "field 'mTotalLending'", RadioButton.class);
    target.mLoanFailure = Utils.findRequiredViewAsType(source, R.id.department_loan_failure, "field 'mLoanFailure'", RadioButton.class);
    target.mTotalLoans = Utils.findRequiredViewAsType(source, R.id.department_total_loans, "field 'mTotalLoans'", RadioButton.class);
    target.mShowTextLeft = Utils.findRequiredViewAsType(source, R.id.ds_show_text_left, "field 'mShowTextLeft'", TextView.class);
    target.mShowNumberLeft = Utils.findRequiredViewAsType(source, R.id.ds_show_number_left, "field 'mShowNumberLeft'", TextView.class);
    target.mShowTextRight = Utils.findRequiredViewAsType(source, R.id.ds_show_text_right, "field 'mShowTextRight'", TextView.class);
    target.mShowNumberRight = Utils.findRequiredViewAsType(source, R.id.ds_show_number_right, "field 'mShowNumberRight'", TextView.class);
    target.mRadioGroup = Utils.findRequiredViewAsType(source, R.id.ds_radio_group, "field 'mRadioGroup'", RadioGroup.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mStoresName = null;
    target.mViewpager = null;
    target.mIndicator = null;
    target.mPtr = null;
    target.mTodayApply = null;
    target.mTotalLending = null;
    target.mLoanFailure = null;
    target.mTotalLoans = null;
    target.mShowTextLeft = null;
    target.mShowNumberLeft = null;
    target.mShowTextRight = null;
    target.mShowNumberRight = null;
    target.mRadioGroup = null;

    this.target = null;
  }
}
