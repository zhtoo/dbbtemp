// Generated code from Butter Knife. Do not modify!
package com.hs.doubaobao.model.AddLoanTable.uploadMessage.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hs.doubaobao.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BasicInformationFragment_ViewBinding<T extends BasicInformationFragment> implements Unbinder {
  protected T target;

  private View view2131689772;

  private View view2131689773;

  private View view2131689774;

  private View view2131689775;

  private View view2131689776;

  private View view2131689778;

  private View view2131689779;

  private View view2131689777;

  private View view2131689708;

  private View view2131689780;

  private View view2131689782;

  private View view2131689783;

  @UiThread
  public BasicInformationFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.lender_name, "field 'mName' and method 'onViewClicked'");
    target.mName = Utils.castView(view, R.id.lender_name, "field 'mName'", TextView.class);
    view2131689772 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.lender_name_edit, "field 'mNameEdit' and method 'onViewClicked'");
    target.mNameEdit = Utils.castView(view, R.id.lender_name_edit, "field 'mNameEdit'", EditText.class);
    view2131689773 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.lender_id_card, "field 'mIdCard' and method 'onViewClicked'");
    target.mIdCard = Utils.castView(view, R.id.lender_id_card, "field 'mIdCard'", TextView.class);
    view2131689774 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.lender_id_card_edit, "field 'mIdCardEdit' and method 'onViewClicked'");
    target.mIdCardEdit = Utils.castView(view, R.id.lender_id_card_edit, "field 'mIdCardEdit'", EditText.class);
    view2131689775 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.lender_sex, "field 'mSex' and method 'onViewClicked'");
    target.mSex = Utils.castView(view, R.id.lender_sex, "field 'mSex'", TextView.class);
    view2131689776 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.lender_sex_male, "field 'mSexMale' and method 'onViewClicked'");
    target.mSexMale = Utils.castView(view, R.id.lender_sex_male, "field 'mSexMale'", RadioButton.class);
    view2131689778 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.lender_sex_female, "field 'mSexFemale' and method 'onViewClicked'");
    target.mSexFemale = Utils.castView(view, R.id.lender_sex_female, "field 'mSexFemale'", RadioButton.class);
    view2131689779 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.lender_sex_group, "field 'mSexGroup' and method 'onViewClicked'");
    target.mSexGroup = Utils.castView(view, R.id.lender_sex_group, "field 'mSexGroup'", RadioGroup.class);
    view2131689777 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.lender_marital_status, "field 'mMaritalStatus' and method 'onViewClicked'");
    target.mMaritalStatus = Utils.castView(view, R.id.lender_marital_status, "field 'mMaritalStatus'", TextView.class);
    view2131689708 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.lender_marital_status_edit, "field 'mMaritalStatusEdit' and method 'onViewClicked'");
    target.mMaritalStatusEdit = Utils.castView(view, R.id.lender_marital_status_edit, "field 'mMaritalStatusEdit'", LinearLayout.class);
    view2131689780 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.lender_domicile, "field 'mDomicile' and method 'onViewClicked'");
    target.mDomicile = Utils.castView(view, R.id.lender_domicile, "field 'mDomicile'", TextView.class);
    view2131689782 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.lender_domicile_edit, "field 'mDomicileEdit' and method 'onViewClicked'");
    target.mDomicileEdit = Utils.castView(view, R.id.lender_domicile_edit, "field 'mDomicileEdit'", EditText.class);
    view2131689783 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mMaritalStatusText = Utils.findRequiredViewAsType(source, R.id.lender_marital_status_text, "field 'mMaritalStatusText'", TextView.class);
    target.mPhone = Utils.findRequiredViewAsType(source, R.id.lender_phone, "field 'mPhone'", TextView.class);
    target.mPhoneEdit = Utils.findRequiredViewAsType(source, R.id.lender_phone_edit, "field 'mPhoneEdit'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mName = null;
    target.mNameEdit = null;
    target.mIdCard = null;
    target.mIdCardEdit = null;
    target.mSex = null;
    target.mSexMale = null;
    target.mSexFemale = null;
    target.mSexGroup = null;
    target.mMaritalStatus = null;
    target.mMaritalStatusEdit = null;
    target.mDomicile = null;
    target.mDomicileEdit = null;
    target.mMaritalStatusText = null;
    target.mPhone = null;
    target.mPhoneEdit = null;

    view2131689772.setOnClickListener(null);
    view2131689772 = null;
    view2131689773.setOnClickListener(null);
    view2131689773 = null;
    view2131689774.setOnClickListener(null);
    view2131689774 = null;
    view2131689775.setOnClickListener(null);
    view2131689775 = null;
    view2131689776.setOnClickListener(null);
    view2131689776 = null;
    view2131689778.setOnClickListener(null);
    view2131689778 = null;
    view2131689779.setOnClickListener(null);
    view2131689779 = null;
    view2131689777.setOnClickListener(null);
    view2131689777 = null;
    view2131689708.setOnClickListener(null);
    view2131689708 = null;
    view2131689780.setOnClickListener(null);
    view2131689780 = null;
    view2131689782.setOnClickListener(null);
    view2131689782 = null;
    view2131689783.setOnClickListener(null);
    view2131689783 = null;

    this.target = null;
  }
}
