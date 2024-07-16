package com.tp.filmquiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.tp.filmquiz.pojo.Question;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    // Declaration du context
    private Context context;


    // Declaration de la liste de questions
    final List<Question> questions = new ArrayList<>();

    //Déclaration du compteur de question et de score
    private int questionCounter = 0;
    private int score = 0;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button buttonFalse;
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialisation du context
        context = getApplicationContext();

        // Recuperation des questions
        questions.add(new Question(getString(R.string.question_ai), true));
        questions.add(new Question(getString(R.string.question_taxi_driver), true));
        questions.add(new Question(getString(R.string.question_2001), false));
        questions.add(new Question(getString(R.string.question_reservoir_dogs), true));
        questions.add(new Question(getString(R.string.question_citizen_kane), false));

        //Initialisation des objets

        // Boutons
        // Declaration des buttons
        Button buttonTrue = findViewById(R.id.buttonTrue);
        buttonFalse = findViewById(R.id.buttonFalse);

        // Question
        TextView tvQuestion = findViewById(R.id.question);
        tvQuestion.setText(questions.get(questionCounter).getQuestion());

        // Score
        TextView tvScore = findViewById(R.id.score);
        tvScore.setText("Score : " + score);

        // Gestion du click sur buttonTrue
        buttonTrue.setOnClickListener(v -> {
            if (questions.get(questionCounter).isAnswer()) {
                context = getApplicationContext();
                CharSequence text = "Bonne réponse";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                score++;
                incrementScore(score, tvScore);
            } else {
                context = getApplicationContext();
                CharSequence text = "Mauvaise réponse";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            questionCounter++;
            nextQuestion(questionCounter, questions, tvQuestion, buttonTrue, buttonFalse);
        });

        // Gestion du click sur buttonFalse
        buttonFalse.setOnClickListener(v -> {
            if (questions.get(questionCounter).isAnswer()) {
                context = getApplicationContext();
                CharSequence text = "Mauvaise réponse";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else {
                context = getApplicationContext();
                CharSequence text = "Bonne réponse";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                score++;
                incrementScore(score, tvScore);
            }
            questionCounter++;
            nextQuestion(questionCounter, questions, tvQuestion, buttonTrue, buttonFalse);
        });
    }

    /**
     * Méthode pour passer à la question suivante
     * @param questionCounter : le compteur de question
     * @param questions : la liste de questions
     * @param tvQuestion : le TextView de la question
     * @param buttonTrue : le bouton vrai
     * @param buttonFalse : le bouton faux
     */
    @SuppressLint("SetTextI18n")
    private void nextQuestion(int questionCounter, List<Question> questions, TextView tvQuestion, Button buttonTrue, Button buttonFalse) {
        if (questionCounter < questions.size()) {
            tvQuestion.setText(questions.get(questionCounter).getQuestion());
        } else {
            tvQuestion.setText("Fin du quiz");
            buttonTrue.setVisibility(View.INVISIBLE);
            buttonFalse.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Méthode pour incrémenter le score
     * @param score : le score
     * @param tvScore : le TextView du score
     */
    private void incrementScore(int score, TextView tvScore) {
        tvScore.setText(MessageFormat.format("Score : {0}", score));
    }

}