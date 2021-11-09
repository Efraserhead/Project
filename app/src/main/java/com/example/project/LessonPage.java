package com.example.project;

public class LessonPage {

    private int stringId;
    private int imageId;


    public LessonPage(int stringId, int imageId) {
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
