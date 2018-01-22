// Generated code from Butter Knife. Do not modify!
package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hs.doubaobao.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoanEvaluationActivity_ViewBinding<T extends LoanEvaluationActivity> implements Unbinder {
  protected T target;

  private View view2131689669;

  @UiThread
  public LoanEvaluationActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.evaluation_standard, "field 'mStandard' and method 'onViewClicked'");
    target.mStandard = Utils.castView(view, R.id.evaluation_standard, "field 'mStandard'", Button.class);
    view2131689669 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mStandard = null;

    view2131689669.setOnClickListener(null);
    view2131689669 = null;

    this.target = null;
  }
}
