package com.example.project;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ScheduleReceiver extends BroadcastReceiver {
    int scheduledProblems;
    Date today;
    Repository repository;

    @Override
    public void onReceive(Context context, Intent intent) {
        Application devMind = (Application) context.getApplicationContext();
        repository = new Repository(devMind);
        today = Calendar.getInstance().getTime();
        new RetrieveScheduleAsyncTask(this).execute();
        if (!(scheduledProblems == 0)) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "schedule")
                    .setContentTitle("Today's Schedule")
                    .setContentText("you have " + scheduledProblems + " code problems to solve ")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(101, builder.build());
        }

    }


    private static class RetrieveScheduleAsyncTask extends AsyncTask<Void, Void, Void> {
        WeakReference<ScheduleReceiver> scheduleReceiverWeakReference;
        int scheduledProblems;

        public RetrieveScheduleAsyncTask(ScheduleReceiver scheduleReceiver) {
            scheduleReceiverWeakReference = new WeakReference<ScheduleReceiver>(scheduleReceiver);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            ScheduleReceiver scheduleReceiver = scheduleReceiverWeakReference.get();
            List<Problem> problems = scheduleReceiver.repository.getUnlockedProblems();
            for (Problem problem : problems) {
                if (scheduleReceiver.today.after(problem.getNextAvailable()) || scheduleReceiver.today.equals(problem.getNextAvailable())) {
                    scheduledProblems++;
                    scheduleReceiver.scheduledProblems = scheduledProblems;
                }
            }
            return null;
        }
    }
}
