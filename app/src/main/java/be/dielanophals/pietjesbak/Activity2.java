package be.dielanophals.pietjesbak;

        import android.content.DialogInterface;
        import android.content.Intent;
        import android.support.constraint.ConstraintLayout;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.FrameLayout;
        import android.widget.ImageView;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import org.w3c.dom.Text;

        import java.util.Random;

public class Activity2 extends AppCompatActivity {

    public static final Random random = new Random();
    private Button rollDices;
    private ImageView imageView1, imageView2, imageView3, pintje1, pintje2, pintje3, pintje4, pintje5;

    Integer throwCounter = 0;
    Boolean turnPlayer1 = true;
    Integer player1Turns;
    Integer round = 1;

    int value1, value2, value3;

    Integer player1RoundPoints;
    Integer player2RoundPoints;
    String player1TotalPoints;
    String player2TotalPoints;
    int player1Pintjes = 5;
    int player2Pintjes = 5;
    int minpintjes = 0;

    int winsPlayer1 = 0;
    int winsPlayer2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = getIntent();
        final String string_player1 = intent.getStringExtra(MainActivity.EXTRA_TEXT1);
        final String string_player2 = intent.getStringExtra(MainActivity.EXTRA_TEXT2);

        TextView textView1 = (TextView) findViewById(R.id.player1);
        TextView textView2 = (TextView) findViewById(R.id.player2);
        final TextView visual_player = (TextView) findViewById(R.id.visual_player);

        textView1.setText(string_player1);
        textView2.setText(string_player2);
        visual_player.setText(string_player1);

        rollDices  = (Button) findViewById(R.id.rollDices);
        imageView1 = (ImageView) findViewById(R.id.dice1);
        imageView2 = (ImageView) findViewById(R.id.dice2);
        imageView3 = (ImageView) findViewById(R.id.dice3);

        final Button endTurn  = (Button) findViewById(R.id.endTurn);

        final CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
        final CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
        final CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkbox3);

        endTurn.setVisibility(View.INVISIBLE);
        checkBox1.setVisibility(View.INVISIBLE);
        checkBox2.setVisibility(View.INVISIBLE);
        checkBox3.setVisibility(View.INVISIBLE);

        final TextView textViewThrowCounter = (TextView) findViewById(R.id.textViewThrowCounter);
        final TextView textViewround = (TextView) findViewById(R.id.round);

        final TextView scorePlayer1 = (TextView) findViewById(R.id.total_score_player1);
        final TextView scorePlayer2 = (TextView) findViewById(R.id.total_score_player2);
        final TextView roundsWinPlayer1 = (TextView) findViewById(R.id.rounds_win_player1);
        final TextView roundsWinPlayer2 = (TextView) findViewById(R.id.rounds_win_player2);

        final TextView results = (TextView) findViewById(R.id.results);

        pintje1 = (ImageView) findViewById(R.id.pintje1);
        pintje2 = (ImageView) findViewById(R.id.pintje2);
        pintje3 = (ImageView) findViewById(R.id.pintje3);
        pintje4 = (ImageView) findViewById(R.id.pintje4);
        pintje5 = (ImageView) findViewById(R.id.pintje5);

        final FrameLayout Frame = (FrameLayout) findViewById(R.id.pop_up);

        final TextView pop_up_text = (TextView) findViewById(R.id.pop_up_text);

        rollDices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBox1.isChecked()) {
                    value1 = randomDiceValue();
                    int res1 = getResources().getIdentifier("dice" + value1, "drawable", getPackageName());
                    imageView1.setImageResource(res1);
                }
                if (!checkBox2.isChecked()) {
                    value2 = randomDiceValue();
                    int res2 = getResources().getIdentifier("dice" + value2, "drawable", getPackageName());
                    imageView2.setImageResource(res2);
                }
                if (!checkBox3.isChecked()) {
                    value3 = randomDiceValue();
                    int res3 = getResources().getIdentifier("dice" + value3, "drawable", getPackageName());
                    imageView3.setImageResource(res3);
                }

                results.setVisibility(View.INVISIBLE);

                endTurn.setVisibility(View.VISIBLE);
                checkBox1.setVisibility(View.VISIBLE);
                checkBox2.setVisibility(View.VISIBLE);
                checkBox3.setVisibility(View.VISIBLE);
                throwCounter++;
                if(turnPlayer1 == true){
                    textViewThrowCounter.setText(throwCounter + "/3");
                    if(throwCounter <= 1){
                        endTurn.setVisibility(View.VISIBLE);
                    }else if (throwCounter == 3){
                        checkBox1.setVisibility(View.INVISIBLE);
                        checkBox2.setVisibility(View.INVISIBLE);
                        checkBox3.setVisibility(View.INVISIBLE);
                        rollDices.setVisibility(View.INVISIBLE);
                    }
                }else{
                    textViewThrowCounter.setText(throwCounter + "/" + player1Turns);
                    if (throwCounter == player1Turns){
                        checkBox1.setVisibility(View.INVISIBLE);
                        checkBox2.setVisibility(View.INVISIBLE);
                        checkBox3.setVisibility(View.INVISIBLE);
                        rollDices.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        endTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value1 == 1){
                    value1 = 100;
                }else if(value1 == 2){
                    value1 = 2;
                }else if(value1 == 3){
                    value1 = 3;
                }else if(value1 == 4){
                    value1 = 4;
                }else if(value1 == 5){
                    value1 = 5;
                }else if(value1 == 6){
                    value1 = 60;
                }

                if(value2 == 1){
                    value2 = 100;
                }else if(value2 == 2){
                    value2 = 2;
                }else if(value2 == 3){
                    value2 = 3;
                }else if(value2 == 4){
                    value2 = 4;
                }else if(value2 == 5){
                    value2 = 5;
                }else if(value2 == 6){
                    value2 = 60;
                }

                if(value3 == 1){
                    value3 = 100;
                }else if(value3 == 2){
                    value3 = 2;
                }else if(value3 == 3){
                    value3 = 3;
                }else if(value3 == 4){
                    value3 = 4;
                }else if(value3 == 5){
                    value3 = 5;
                }else if(value3 == 6){
                    value3 = 60;
                }

                if(value1 == 60 && value2 == 5 && value3 == 4 || value1 == 60 && value2 == 4 && value3 == 5 || value1 == 5 && value2 == 60 && value3 == 4 || value1 == 5 && value2 == 4 && value3 == 60 || value1 == 4 && value2 == 60 && value3 == 5 || value1 == 4 && value2 == 5 && value3 == 60){
                    if(turnPlayer1 == true){
                        player1RoundPoints = 700;
                    }else{
                        player2RoundPoints = 700;
                    }
                    minpintjes = 3;
                }else if(value1 == value2 && value1 == value3){
                    switch(value1){
                        case 2:
                            if(turnPlayer1 == true){
                                player1RoundPoints = 270;
                            }else{
                                player2RoundPoints = 270;
                            }
                        break;

                        case 3:
                            if(turnPlayer1 == true){
                                player1RoundPoints = 333;
                            }else{
                                player2RoundPoints = 333;
                            }
                        break;

                        case 4:
                            if(turnPlayer1 == true){
                                player1RoundPoints = 444;
                            }else{
                                player2RoundPoints = 444;
                            }
                        break;

                        case 5:
                            if(turnPlayer1 == true){
                                player1RoundPoints = 555;
                            }else{
                                player2RoundPoints = 555;
                            }
                        break;

                        case 6:
                            if(turnPlayer1 == true){
                                player1RoundPoints = 666;
                            }else{
                                player2RoundPoints = 666;
                            }
                            break;
                    }
                    minpintjes = 2;
                }else{
                    if(turnPlayer1 == true){
                        player1RoundPoints = value1 + value2 + value3;
                    }else{
                        player2RoundPoints = value1 + value2 + value3;
                    }
                    minpintjes = 2;
                }

                if(value1 == 100 && value2 == 100 && value3 == 100){
                    //speler gewonnen
                    results.setVisibility(View.VISIBLE);
                    if(turnPlayer1 == true){
                        results.setText(string_player1 + " wint het spel!");
                    }else{
                        results.setText(string_player2 + " wint het spel!");
                    }
                }else{
                    if(turnPlayer1 == true){
                        turnPlayer1 = false;
                        player1Turns = throwCounter;
                        textViewThrowCounter.setText("0/" + player1Turns);
                        visual_player.setText(string_player2);

                        player1TotalPoints = value1 + " " + value2 + " " + value3;
                        scorePlayer1.setText("" + player1TotalPoints);
                    }else{
                        //Ronde gedaan
                        turnPlayer1 = true;
                        visual_player.setText(string_player1);
                        textViewThrowCounter.setText("0/3");
                        round++;
                        textViewround.setText("Round " + round);

                        player2TotalPoints = value1 + " " + value2 + " " + value3;
                        scorePlayer2.setText("" + player2TotalPoints);

                        results.setVisibility(View.VISIBLE);
                        String winner;
                        if(player1RoundPoints > player2RoundPoints){
                            winner = string_player1;
                            winsPlayer1++;
                            roundsWinPlayer1.setText("" + winsPlayer1);

                            player1Pintjes = player1Pintjes - minpintjes;
                        }else if(player1RoundPoints.equals(player2RoundPoints)){
                            winner = "niemand";
                        }else{
                            winner = string_player2;
                            winsPlayer2++;
                            roundsWinPlayer2.setText("" + winsPlayer2);

                            player2Pintjes = player2Pintjes - minpintjes;
                        }
                        System.out.println(minpintjes);
                        results.setText(winner + " wint deze ronde!");
                        totalPintjes();
                        if(player1Pintjes <= 0){
                            pop_up_text.setText(winner + " wint het spel!");
                            Frame.setVisibility(View.VISIBLE);
                            rollDices.setVisibility(View.INVISIBLE);
                        }else if(player2Pintjes <= 0){
                            pop_up_text.setText(winner + " wint het spel!");
                            Frame.setVisibility(View.VISIBLE);
                            rollDices.setVisibility(View.INVISIBLE);
                        }
                    }
                    endTurn.setVisibility(View.INVISIBLE);
                    rollDices.setVisibility(View.VISIBLE);

                    if(checkBox1.isChecked()) {
                        checkBox1.toggle();
                    }
                    if(checkBox2.isChecked()) {
                        checkBox2.toggle();
                    }
                    if(checkBox3.isChecked()) {
                        checkBox3.toggle();
                    }
                    checkBox1.setVisibility(View.INVISIBLE);
                    checkBox2.setVisibility(View.INVISIBLE);
                    checkBox3.setVisibility(View.INVISIBLE);

                    throwCounter = 0;

                    int image = getResources().getIdentifier("dicestart", "drawable", getPackageName());
                    imageView1.setImageResource(image);
                    imageView2.setImageResource(image);
                    imageView3.setImageResource(image);
                }
            }
        });

        Button quit_button = (Button) findViewById(R.id.quit_button);
        quit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity2.this);

                builder.setCancelable(true);
                builder.setTitle("Opgepast!");
                builder.setMessage("Weet u zeker dat u dit spel wilt stoppen?");

                builder.setNegativeButton("Annuleer", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                    dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    openMainActivity();
                    }
                });
                builder.show();
            }
        });
    }
    public static int randomDiceValue(){
        return random.nextInt(6) + 1;
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void totalPintjes(){
        if(player1RoundPoints > player2RoundPoints){
            if(player1Pintjes == 4){
                pintje5.setVisibility(View.INVISIBLE);
                pintje4.setVisibility(View.VISIBLE);
                pintje3.setVisibility(View.VISIBLE);
                pintje2.setVisibility(View.VISIBLE);
                pintje1.setVisibility(View.VISIBLE);
            }else if(player1Pintjes == 3){
                pintje5.setVisibility(View.INVISIBLE);
                pintje4.setVisibility(View.INVISIBLE);
                pintje3.setVisibility(View.VISIBLE);
                pintje2.setVisibility(View.VISIBLE);
                pintje1.setVisibility(View.VISIBLE);

            }else if(player1Pintjes == 2){
                pintje5.setVisibility(View.INVISIBLE);
                pintje4.setVisibility(View.INVISIBLE);
                pintje3.setVisibility(View.INVISIBLE);
                pintje2.setVisibility(View.VISIBLE);
                pintje1.setVisibility(View.VISIBLE);
            }else if(player1Pintjes == 1){
                pintje5.setVisibility(View.INVISIBLE);
                pintje4.setVisibility(View.INVISIBLE);
                pintje3.setVisibility(View.INVISIBLE);
                pintje2.setVisibility(View.INVISIBLE);
                pintje1.setVisibility(View.VISIBLE);
            }else if(player1Pintjes <= 0){
                pintje5.setVisibility(View.INVISIBLE);
                pintje4.setVisibility(View.INVISIBLE);
                pintje3.setVisibility(View.INVISIBLE);
                pintje2.setVisibility(View.INVISIBLE);
                pintje1.setVisibility(View.INVISIBLE);
            }
        }else if(player1RoundPoints < player2RoundPoints){
            if(player2Pintjes == 4){
                pintje5.setVisibility(View.INVISIBLE);
                pintje4.setVisibility(View.VISIBLE);
                pintje3.setVisibility(View.VISIBLE);
                pintje2.setVisibility(View.VISIBLE);
                pintje1.setVisibility(View.VISIBLE);
            }else if(player2Pintjes == 3){
                pintje5.setVisibility(View.INVISIBLE);
                pintje4.setVisibility(View.INVISIBLE);
                pintje3.setVisibility(View.VISIBLE);
                pintje2.setVisibility(View.VISIBLE);
                pintje1.setVisibility(View.VISIBLE);

            }else if(player2Pintjes == 2){
                pintje5.setVisibility(View.INVISIBLE);
                pintje4.setVisibility(View.INVISIBLE);
                pintje3.setVisibility(View.INVISIBLE);
                pintje2.setVisibility(View.VISIBLE);
                pintje1.setVisibility(View.VISIBLE);
            }else if(player2Pintjes == 1){
                pintje5.setVisibility(View.INVISIBLE);
                pintje4.setVisibility(View.INVISIBLE);
                pintje3.setVisibility(View.INVISIBLE);
                pintje2.setVisibility(View.INVISIBLE);
                pintje1.setVisibility(View.VISIBLE);
            }else if(player2Pintjes <= 0){
                pintje5.setVisibility(View.INVISIBLE);
                pintje4.setVisibility(View.INVISIBLE);
                pintje3.setVisibility(View.INVISIBLE);
                pintje2.setVisibility(View.INVISIBLE);
                pintje1.setVisibility(View.INVISIBLE);
            }
        }
    }
}
