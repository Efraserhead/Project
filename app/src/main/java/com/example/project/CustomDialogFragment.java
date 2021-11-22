package com.example.project;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;

public class CustomDialogFragment extends androidx.fragment.app.DialogFragment {

    private DialogListener dialogListener;

    public interface DialogListener {
        void getButtonAction(boolean buttonAction);
    }


    public CustomDialogFragment() {
    }

    public static CustomDialogFragment newInstance(String title, String message) {
        CustomDialogFragment dialogFragment = new CustomDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("message", message);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        String message = getArguments().getString("message");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogListener.getButtonAction(false);
                dialog.dismiss();
            }
        });
        alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogListener.getButtonAction(true);
                dialog.dismiss();
            }
        });
        return alertDialogBuilder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dialogListener = (DialogListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dialogListener = null;
    }
}
