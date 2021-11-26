package com.example.project;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category_table")
public class Category {


    @PrimaryKey(autoGenerate = true)
    private int id;

    private int lockImageId;

    private String name, lesson1, lesson2, lesson3, lesson4;


    private int lock;

    public Category(int lockImageId, String name, String lesson1, String lesson2, String lesson3, String lesson4, int lock) {
        this.lockImageId = lockImageId;
        this.name = name;
        this.lesson1 = lesson1;
        this.lesson2 = lesson2;
        this.lesson3 = lesson3;
        this.lesson4 = lesson4;
        this.lock = lock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLockImageId() {
        return lockImageId;
    }

    public void setLockImageId(int lockImageId) {
        this.lockImageId = lockImageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLesson1() {
        return lesson1;
    }

    public void setLesson1(String lesson1) {
        this.lesson1 = lesson1;
    }

    public String getLesson2() {
        return lesson2;
    }

    public void setLesson2(String lesson2) {
        this.lesson2 = lesson2;
    }

    public String getLesson3() {
        return lesson3;
    }

    public void setLesson3(String lesson3) {
        this.lesson3 = lesson3;
    }

    public String getLesson4() {
        return lesson4;
    }

    public void setLesson4(String lesson4) {
        this.lesson4 = lesson4;
    }

    public int getLock() {
        return lock;
    }

    public void setLock(int lock) {
        this.lock = lock;
    }
}