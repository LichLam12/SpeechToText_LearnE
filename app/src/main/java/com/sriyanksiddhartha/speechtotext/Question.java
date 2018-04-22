package com.sriyanksiddhartha.speechtotext;

/**
 * Created by onlyo on 3/20/2018.
 */

public class Question {
    public int quesImage;
    public String quesContent;

    public Question(String quesContent, int quesImage) {
        this.quesImage = quesImage;
        this.quesContent = quesContent;
    }

    public Question(String quesContent) {
        this.quesContent = quesContent;
    }

}
