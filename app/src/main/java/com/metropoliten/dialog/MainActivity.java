package com.metropoliten.dialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;
import android.net.Uri;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    CharSequence[] items = {"FIT", "Fakultet za menadžment", "Fakultet digitalnih umetnosti"};
    boolean[] itemsChecked = new boolean[items.length];
    ProgressDialog progressDialog;

    /**
     * Poziva se prvim definisanjem aktivnosti.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClick(View v) {
        showDialog(0);
    }
    public void onClick2(View v) {
//---prikazuje dijalog---
        final ProgressDialog dialog = ProgressDialog.show(
                this, "Nešto se dešava.", "Sačekajte...", true);
        new Thread(new Runnable(){
            public void run() {
                try {
//---simulacija da nešto radi---
                    Thread.sleep(5000);
//---odjavljuje dijalog---
                    dialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void onClick3(View v) {
        showDialog(1);
        progressDialog.setProgress(0);
        new Thread(new Runnable(){
            public void run(){
                for (int i=1; i<=15; i++) {
                    try {
//---simulacija da nešto radi---
                        Thread.sleep(1000);
//---osvežavanje dijaloga---
                        progressDialog.incrementProgressBy((int)(100/15));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                progressDialog.dismiss();
            }
        }).start();
    }


/////////////////////////////////////////////////////////

    @Override
    protected Dialog onCreateDialog(final int id) {
        switch (id) {
            case 0:
                return new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("Dijalog sa malo teksta...")
                        .setPositiveButton("OK",


                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int
                                            whichButton)
                                    {


                                        Toast.makeText(getBaseContext(),
                                                "OK je kliknut!",
                                                Toast.LENGTH_SHORT).show();


                                    }
                                }
                        )
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int
                                            whichButton)
                                    {
                                        Toast.makeText(getBaseContext(),
                                                "Cancel je kliknut!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                        )

                        .setMultiChoiceItems(items, itemsChecked,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which, boolean
                                                                isChecked) {
                                        if (items.equals("FIT")) {
                                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.metropolitan.ac.rs/osnovne-studije/fakultet-informacionih-tehnologija/")));

                                        }else if (items.equals("Fakultet za menadžment")){
                                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.metropolitan.ac.rs/osnovne-studije/fakultet-za-menadzment")));

                                        }else if (items.equals("Fakultet digitalnih umetnosti")){
                                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.metropolitan.ac.rs/fakultet-digitalnih-umetnosti-2")));
                                        }

                                        }
                                }
                        ).create();
            case 1:
                progressDialog = new ProgressDialog(this);
                progressDialog.setIcon(R.mipmap.ic_launcher);
                progressDialog.setTitle("Preuzimanje datoteka...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton)
                            {
                                Toast.makeText(getBaseContext(),
                                        "OK je kliknut!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton)
                            {
                                Toast.makeText(getBaseContext(),
                                        "Cancel je kliknut!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                return progressDialog;
        }
        return null;
    }
}

