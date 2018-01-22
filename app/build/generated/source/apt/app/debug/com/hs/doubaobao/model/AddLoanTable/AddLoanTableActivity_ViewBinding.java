// Generated code from Butter Knife. Do not modify!
package com.hs.doubaobao.model.AddLoanTable;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hs.doubaobao.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddLoanTableActivity_ViewBinding<T extends AddLoanTableActivity> implements Unbinder {
  protected T target;

  private View view2131689593;

  private View view2131689594;

  private View view2131689595;

  private View view2131689596;

  private View view2131689597;

  private View view2131689598;

  private View view2131689599;

  private View view2131689600;

  private View view2131689601;

  private View view2131689602;

  private View view2131689603;

  @UiThread
  public AddLoanTableActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.add_lender_information, "field 'addLenderInformation' and method 'onViewClicked'");
    target.addLenderInformation = Utils.castView(view, R.id.add_lender_information, "field 'addLenderInformation'", LinearLayout.class);
    view2131689593 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.add_the_loans, "field 'addTheLoans' and method 'onViewClicked'");
    target.addTheLoans = Utils.castView(view, R.id.add_the_loans, "field 'addTheLoans'", LinearLayout.class);
    view2131689594 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.add_contact_information, "field 'addContactInformation' and method 'onViewClicked'");
    target.addContactInformation = Utils.castView(view, R.id.add_contact_information, "field 'addContactInformation'", LinearLayout.class);
    view2131689595 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.add_investigation_and_opinion, "field 'addInvestigationAndOpinion' and method 'onViewClicked'");
    target.addInvestigationAndOpinion = Utils.castView(view, R.id.add_investigation_and_opinion, "field 'addInvestigationAndOpinion'", LinearLayout.class);
    view2131689596 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.add_lender_information_optional, "field 'addLenderInformationOptional' and method 'onViewClicked'");
    target.addLenderInformationOptional = Utils.castView(view, R.id.add_lender_information_optional, "field 'addLenderInformationOptional'", LinearLayout.class);
    view2131689597 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.add_mutual_lender_information, "field 'addMutualLenderInformation' and method 'onViewClicked'");
    target.addMutualLenderInformation = Utils.castView(view, R.id.add_mutual_lender_information, "field 'addMutualLenderInformation'", LinearLayout.class);
    view2131689598 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.add_car_information, "field 'addCarInformation' and method 'onViewClicked'");
    target.addCarInformation = Utils.castView(view, R.id.add_car_information, "field 'addCarInformation'", LinearLayout.class);
    view2131689599 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.add_loan_evaluation, "field 'addLoanEvaluation' and method 'onViewClicked'");
    target.addLoanEvaluation = Utils.castView(view, R.id.add_loan_evaluation, "field 'addLoanEvaluation'", LinearLayout.class);
    view2131689600 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.add_upload_picture, "field 'addUploadPicture' and method 'onViewClicked'");
    target.addUploadPicture = Utils.castView(view, R.id.add_upload_picture, "field 'addUploadPicture'", LinearLayout.class);
    view2131689601 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.add_upload_vidoe, "field 'addUploadVidoe' and method 'onViewClicked'");
    target.addUploadVidoe = Utils.castView(view, R.id.add_upload_vidoe, "field 'addUploadVidoe'", LinearLayout.class);
    view2131689602 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.add_submit, "field 'mSubmit' and method 'onViewClicked'");
    target.mSubmit = Utils.castView(view, R.id.add_submit, "field 'mSubmit'", Button.class);
    view2131689603 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.addLenderInformation = null;
    target.addTheLoans = null;
    target.addContactInformation = null;
    target.addInvestigationAndOpinion = null;
    target.addLenderInformationOptional = null;
    target.addMutualLenderInformation = null;
    target.addCarInformation = null;
    target.addLoanEvaluation = null;
    target.addUploadPicture = null;
    target.addUploadVidoe = null;
    target.mSubmit = null;

    view2131689593.setOnClickListener(null);
    view2131689593 = null;
    view2131689594.setOnClickListener(null);
    view2131689594 = null;
    view2131689595.setOnClickListener(null);
    view2131689595 = null;
    view2131689596.setOnClickListener(null);
    view2131689596 = null;
    view2131689597.setOnClickListener(null);
    view2131689597 = null;
    view2131689598.setOnClickListener(null);
    view2131689598 = null;
    view2131689599.setOnClickListener(null);
    view2131689599 = null;
    view2131689600.setOnClickListener(null);
    view2131689600 = null;
    view2131689601.setOnClickListener(null);
    view2131689601 = null;
    view2131689602.setOnClickListener(null);
    view2131689602 = null;
    view2131689603.setOnClickListener(null);
    view2131689603 = null;

    this.target = null;
  }
}
