package com.example.project;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class HomeActivityTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(HomeActivity.class);



    @Before
    public void setUp() throws Exception {
        ActivityScenario scenario = rule.getScenario();
    }

    @Test
    public void signUpButton() {

    }

    @Test
    public void loadUserDetails() {
    }

    @Test
    public void enterProblems() {
    }

    @Test
    public void loadCategories() {
    }

    @Test
    public void populateDb() {
    }
}