package com.example.android.quizapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.example.android.quizapp.R.string.question4_answer_toast_msg;
import static com.example.android.quizapp.R.string.score_button_toast_msg1;
import static com.example.android.quizapp.R.string.score_button_toast_msg2;

public class MainActivity extends AppCompatActivity {

    int testScore = 0;//to record score
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked (used for both questions 1 & 3, using radio buttons)
        switch (view.getId()) {
            case R.id.question1_yes_radio_button:
                if (checked)
                Toast.makeText(this, getString(R.string.question1_wrong_answer_toast_msg), Toast.LENGTH_LONG).show();
                break;
            case R.id.question1_no_radio_button:
                if (checked)
                    testScore = testScore + 1;
                    Toast.makeText(this, getString(R.string.question1_right_answer_toast_msg), Toast.LENGTH_LONG).show();
                break;
            case R.id.question3_yes_radio_button:
                if (checked)
                    testScore = testScore + 1;
                Toast.makeText(this, getString(R.string.question3_right_answer_toast_msg), Toast.LENGTH_LONG).show();
                break;
            case R.id.question3_no_radio_button:
                if (checked)
                    Toast.makeText(this, getString(R.string.question3_wrong_answer_toast_msg), Toast.LENGTH_LONG).show();
                break;
        }
    }

    //used for question 2
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        if ((view.getId() == R.id.question2_checkbox_option1) && (checked)) {
            ImageView thisImageView = (ImageView) findViewById(R.id.detecting_land);
            thisImageView.setImageResource(R.drawable.my_detector);
            Toast.makeText(this, getString(R.string.question2_right_answer_toast_msg1), Toast.LENGTH_LONG).show();
            testScore = testScore + 1;
        }
        if ((view.getId() == R.id.question2_checkbox_option2) && (checked)) {
            ImageView thisImageView = (ImageView) findViewById(R.id.detecting_land);
            thisImageView.setImageResource(R.drawable.my_detector);
            Toast.makeText(this, getString(R.string.question2_right_answer_toast_msg2), Toast.LENGTH_LONG).show();
            testScore = testScore + 1;
        }
        if ((view.getId() == R.id.question2_checkbox_option3) && (checked)) {
            ImageView thisImageView = (ImageView) findViewById(R.id.detecting_land);
            thisImageView.setImageResource(R.drawable.my_detector);
            Toast.makeText(this, getString(R.string.question2_right_answer_toast_msg3), Toast.LENGTH_LONG).show();
            testScore = testScore + 1;
        }
    }

    public void processQuestionTextInput(View view) {
        //catch question 4 text input as String
        EditText question4Reply = (EditText) findViewById(R.id.question_four_input);
        String question4Answer = question4Reply.getText().toString();
        //check String for correct answer
        if (question4Answer.equals("no")) {
            testScore = testScore + 1;
        } else if (question4Answer == "NO") {
            testScore = testScore + 1;
        } else if (question4Answer == "No") {
            testScore = testScore + 1;
        } else {
        }
        //same advice regardless of input
        Toast.makeText(this, getString(question4_answer_toast_msg), Toast.LENGTH_LONG).show();
    }

    //totals score using instance variable testScore
    public void onScoreButtonClicked(View view) {
        ImageView thisImageView = (ImageView) findViewById(R.id.detecting_land);
        thisImageView.setImageResource(R.drawable.silver_shilling);
        //stops scoring above max
        if (testScore >6){
            testScore=6;
        }
        String converted_int = "" + testScore;
        EditText customerName = (EditText) findViewById(R.id.name_input);
        String custName = customerName.getText().toString();
        //concluding message to customer, with score
        String finalCustomerText = custName + ", " + getString(score_button_toast_msg1) + " " + converted_int + " " + getString(score_button_toast_msg2);
        Toast.makeText(this, finalCustomerText, Toast.LENGTH_LONG).show();
    }

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

