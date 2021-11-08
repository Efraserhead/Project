package com.example.project;

public class LessonData {

    private int stringId;
    private int imageId;
    private LessonData[] thisLesson;

    public static final LessonData[] variables = {
            new LessonData(R.string.lesson1_1,R.drawable.brain_2),
            new LessonData(R.string.lesson1_2, R.drawable.brain_2),
            new LessonData(R.string.lesson1_3,R.drawable.brain_2),
            new LessonData(R.string.lesson1_4,R.drawable.brain_2),
            new LessonData(R.string.lesson1_5,R.drawable.brain_2),
            new LessonData(R.string.lesson1_6,R.drawable.brain_2),
            new LessonData(R.string.lesson1_7,R.drawable.brain_2),
            new LessonData(R.string.lesson1_8,R.drawable.brain_2),
            new LessonData(R.string.lesson1_9,R.drawable.brain_2),
            new LessonData(R.string.lesson1_10,R.drawable.brain_2),
            new LessonData(R.string.lesson1_11,R.drawable.brain_2),
            new LessonData(R.string.lesson1_12,R.drawable.brain_2),
            new LessonData(R.string.lesson1_13,R.drawable.brain_2),
            new LessonData(R.string.lesson1_14,R.drawable.brain_2),
            new LessonData(R.string.lesson1_15,R.drawable.brain_2),
            new LessonData(R.string.lesson1_16,R.drawable.brain_2)};

    public LessonData(){}

    public LessonData[] getThisLesson() {
        return thisLesson;
    }

    public void setThisLesson(LessonData[] thisLesson) {
        this.thisLesson = thisLesson;
    }

    public LessonData(int stringId, int imageId) {
        this.stringId = stringId;
        this.imageId = imageId;
    }

    public int getStringId() {
        return stringId;
    }

    public void setStringId(int stringId) {
        this.stringId = stringId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
