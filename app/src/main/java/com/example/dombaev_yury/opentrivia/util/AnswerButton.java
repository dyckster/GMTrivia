package com.example.dombaev_yury.opentrivia.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.dyckster.opentrivia.R;

public class AnswerButton extends android.support.v7.widget.AppCompatButton {
    public AnswerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnswerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AnswerButton(Context context) {
        super(context);
    }

    private String answer;

    public void setAnswer(String answer) {
        this.answer = answer;
        setText(answer);
    }

    public String getAnswer() {
        return answer;
    }

    public void animateButtonCorrect() {
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.answerCorrect));
// TODO: 05.09.17
    }

    public void animateButtonInCorrect() {
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.answerIncorrect));
        // TODO: 05.09.17
    }
}
