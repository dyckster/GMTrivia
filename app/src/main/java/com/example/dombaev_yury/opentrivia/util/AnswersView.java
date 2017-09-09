package com.example.dombaev_yury.opentrivia.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dyckster.opentrivia.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AnswersView extends LinearLayout {
    public AnswersView(Context context) {
        super(context);
        init();
    }

    public AnswersView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnswersView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AnswersView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private List<AnswerButton> buttons = new ArrayList<>();
    private OnAnswerClickListener onAnswerClickListener;

    private void init() {
        setOrientation(VERTICAL);
        setShowDividers(SHOW_DIVIDER_MIDDLE);
        setDividerDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider_transparent));
    }


    public void setAnswers(@NonNull List<String> answers, @NonNull OnAnswerClickListener onAnswerClickListener) {
        this.onAnswerClickListener = onAnswerClickListener;
        Collections.shuffle(answers);
        for (String answer : answers) {
            AnswerButton answerButton = getAnswerButton(answer);
            buttons.add(answerButton);
            this.addView(answerButton);
        }
    }

    private AnswerButton getAnswerButton(String answer) {
        LinearLayout.LayoutParams params =
                new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
        AnswerButton button = new AnswerButton(getContext(), null, R.attr.answerButtonStyle);
        button.setAnswer(answer);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AnswerButton b = (AnswerButton) view;
                boolean answerIsTrue = onAnswerClickListener.onAnswerClicked(b.getAnswer());
                if (answerIsTrue) {
                    b.animateButtonCorrect();
                } else {
                    b.animateButtonInCorrect();
                }
            }
        });
        button.setLayoutParams(params);
        return button;
    }

    public interface OnAnswerClickListener {
        /**
         * @return true if answer is correct, false otherwise
         */
        boolean onAnswerClicked(String answer);
    }
}
