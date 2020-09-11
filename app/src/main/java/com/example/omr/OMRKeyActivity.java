package com.example.omr;


import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class OMRKeyActivity extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {

    private int[] circleIds = new int[]{R.mipmap.ic_omr_circle_a, R.mipmap.ic_omr_circle_b, R.mipmap.ic_omr_circle_c, R.mipmap.ic_omr_circle_d};

    private int[] correctAnswers;


    private int noOfQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreen f = new FullScreen(this);
        setContentView(R.layout.activity_o_m_r_key);

        noOfQuestions = getIntent().getIntExtra("noOfQuestions", 20);
        createAnswerKey(noOfQuestions);
        loadCorrectAnswers(noOfQuestions);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

        int id = compoundButton.getId();
        CheckBox checkBox;

        for (int i = (id / 4) * 4; i < (id / 4) * 4 + 4; i++) {
            checkBox = findViewById(i);
            if (checkBox.isChecked() && i != id) {
                checkBox.setButtonDrawable(circleIds[i % 4]);
                checkBox.setChecked(false);
                break;
            }
        }

        if (checked) {
            compoundButton.setButtonDrawable(R.mipmap.ic_omr_black_circle);
            compoundButton.setChecked(true);
        } else {
            compoundButton.setButtonDrawable(circleIds[id % 4]);
            compoundButton.setChecked(false);
        }
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        storeCorrectAnswers(noOfQuestions);
//    }


    public void createAnswerKey(int noOfQuestions) {

        TableLayout tableLayout = findViewById(R.id.tableLayout);

        for (int i = 0; i < noOfQuestions; i++) {

            TextView textView = new TextView(this);
            TableRow tableRow;
            tableRow = new TableRow(this);
            tableRow.setPadding(0,12,0,0);

            if (i < 9) {
                textView.setText(("\t" + (i + 1) + ")\t\t"));
            } else {
                textView.setText(((i + 1) + ")" + "\t\t"));
            }

            textView.setTextSize(20);
            textView.setPadding(20, 0, 15, 0);
            textView.setTextColor(getResources().getColor(R.color.dark));
//textView.setTypeface(typeface);
             tableRow.addView(textView);


            for (int j = 0; j < 4; j++) {

                CheckBox checkBox = new CheckBox(this);
                checkBox.setId(j + (i * 4));
                checkBox.setButtonDrawable(circleIds[j]);
                checkBox.setPadding(25, 12,55 , 30);
                checkBox.setOnCheckedChangeListener(this);
                tableRow.addView(checkBox);
            }
            tableLayout.addView(tableRow);
        }
    }

    public void loadCorrectAnswers(final int noOfQuestions) {
//
//        final String[] strCorrectAnswers = {""};
//        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class, "omr").build();
//        final OMRKey omrKey = new OMRKey();
//        omrKey.setOmrkeyid(noOfQuestions);
//        omrKey.setStrCorrectAnswers(strCorrectAnswers[0]);

//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... voids) {
//                if(db.omrKeyDao().findById(noOfQuestions) != null)
//                    strCorrectAnswers[0] = db.omrKeyDao().findById(noOfQuestions).getStrCorrectAnswers();
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                super.onPostExecute(aVoid);
//
//                int[] answers;
//                int correctAnswer;
//                CheckBox checkBox;
//
//                answers = AnswersUtils.strtointAnswers(strCorrectAnswers[0]);
//
//                if(answers != null){
//                    for(int i=0; i< answers.length; i++){
//                        correctAnswer = answers[i];
//                        if(correctAnswer != 0){
//                            checkBox = findViewById((i*5) + (correctAnswer - 1));
//                            checkBox.setChecked(true);
//                        }
//                    }
//                }
//            }
//        }.execute();


//    public void storeCorrectAnswers(int noOfQuestions){
//        correctAnswers = new int[noOfQuestions];
//        int cnt = -1;
//        CheckBox checkBox;
//        for(int i=0; i < noOfQuestions * 5; i++){
//            checkBox = findViewById(i);
//
//            if(i%5 == 0)
//                cnt++;
//
//            if(checkBox.isChecked()){
//                correctAnswers[cnt] = (i % 5) + 1;
//            }
//        }

//        String strCorrectAnswers = AnswersUtils.inttostrAnswers(correctAnswers);

//        if(strCorrectAnswers != null){
//            final AppDatabase db = Room.databaseBuilder(getApplicationContext(),
//                    AppDatabase.class, "omr").build();
//            final OMRKey omrKey = new OMRKey();
//            omrKey.setOmrkeyid(noOfQuestions);
//            omrKey.setStrCorrectAnswers(strCorrectAnswers);

//            new AsyncTask() {
//                @Override
//                protected Void doInBackground(Void... voids) {
//                    db.omrKeyDao().insertOMRKey(omrKey);
//                    return null;
//                }
//            }.execute();
//            Toast.makeText(this,"Answers saved",Toast.LENGTH_LONG).show();
    }
}

