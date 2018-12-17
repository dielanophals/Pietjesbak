package be.dielanophals.pietjesbak;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.Random;

public class Activity2 extends AppCompatActivity {

    public static final Random random = new Random();
    private Button rollDices;
    private ImageView imageView1, imageView2, imageView3;

    Integer throwCounter = 0;
    Boolean turnPlayer1 = true;
    Integer player1Turns;
    Integer round = 1;

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

        rollDices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBox1.isChecked()) {
                    int value1 = randomDiceValue();
                    int res1 = getResources().getIdentifier("dice" + value1, "drawable", getPackageName());

                    imageView1.setImageResource(res1);
                }
                if (!checkBox2.isChecked()) {
                    int value2 = randomDiceValue();
                    int res2 = getResources().getIdentifier("dice" + value2, "drawable", getPackageName());

                    imageView2.setImageResource(res2);
                }
                if (!checkBox3.isChecked()) {
                    int value3 = randomDiceValue();
                    int res3 = getResources().getIdentifier("dice" + value3, "drawable", getPackageName());
                    imageView3.setImageResource(res3);
                }

                endTurn.setVisibility(View.VISIBLE);

                throwCounter++;
                if(turnPlayer1 == true){
                    textViewThrowCounter.setText(throwCounter + "/3");
                    if(throwCounter <= 1){
                        checkBox1.setVisibility(View.VISIBLE);
                        checkBox2.setVisibility(View.VISIBLE);
                        checkBox3.setVisibility(View.VISIBLE);
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
                if(turnPlayer1 == true){
                    turnPlayer1 = false;
                    player1Turns = throwCounter;
                    textViewThrowCounter.setText("0/" + player1Turns);
                    visual_player.setText(string_player2);
                }else{
                    turnPlayer1 = true;
                    visual_player.setText(string_player1);
                    textViewThrowCounter.setText("0/3");
                    round++;
                    textViewround.setText("Round " + round);
                }
                endTurn.setVisibility(View.INVISIBLE);
                rollDices.setVisibility(View.VISIBLE);
                checkBox1.setVisibility(View.VISIBLE);
                checkBox2.setVisibility(View.VISIBLE);
                checkBox3.setVisibility(View.VISIBLE);

                throwCounter = 0;

                int image = getResources().getIdentifier("dicestart", "drawable", getPackageName());
                imageView1.setImageResource(image);
                imageView2.setImageResource(image);
                imageView3.setImageResource(image);
            }
        });

        Button quit_button = (Button) findViewById(R.id.quit_button);
        quit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
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
}
