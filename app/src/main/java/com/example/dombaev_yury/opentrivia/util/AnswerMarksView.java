package com.example.dombaev_yury.opentrivia.util;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dyckster.opentrivia.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class AnswerMarksView extends LinearLayout {
    public AnswerMarksView(Context context) {
        super(context);
        init();
    }

    public AnswerMarksView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnswerMarksView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AnswerMarksView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @IntDef({DotResource.NOT_PASSED, DotResource.PASSED_CORRECT, DotResource.PASSED_INCORRECT, DotResource.SELECTED})
    @Retention(RetentionPolicy.CLASS)
    public @interface DotResource {
        int NOT_PASSED = R.drawable.vp_item_not_selected;
        int PASSED_CORRECT = R.drawable.vp_item_passed_correct;
        int PASSED_INCORRECT = R.drawable.vp_item_passed_incorrect;
        int SELECTED = R.drawable.vp_item_selected;
    }

    private List<ImageView> answerMarks = new ArrayList<>();

    private void init() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        setShowDividers(SHOW_DIVIDER_MIDDLE);
        setDividerPadding(16);
        setDividerDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider_transparent_small));
    }

    public void setAnswersCount(int count) {
        for (int i = 0; i < count; i++) {
            ImageView imageView = getImageView();
            answerMarks.add(imageView);
            addView(imageView);
        }
    }

    public void updateNewPosition(int newPosition) {
        ImageView imageView = answerMarks.get(newPosition);
        imageView.setImageResource(DotResource.SELECTED);

    }

    public void setAnswerCorrect(int position) {
        ImageView imageView = answerMarks.get(position);
        imageView.setImageResource(DotResource.PASSED_CORRECT);
    }

    public void setAnswerIncorrect(int position) {
        ImageView imageView = answerMarks.get(position);
        imageView.setImageResource(DotResource.PASSED_INCORRECT);

    }

    public Point getPositionPoint(int position) {
        ImageView imageView = answerMarks.get(position);
        return new Point((int) imageView.getX(), (int) imageView.getY());
    }

    public void clear() {
        answerMarks.clear();
        removeAllViews();
    }

    private ImageView getImageView() {
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = (int) getContext().getResources().getDimension(R.dimen.answer_mark_margin);
        params.setMargins(margin, 0, margin, 0);
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(DotResource.NOT_PASSED);
        imageView.setLayoutParams(params);

        imageView.setAdjustViewBounds(true);
        return imageView;
    }


}
