package be.dielanophals.pietjesbak;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.Random;

public class Activity2 extends AppCompatActivity {

    public static final Random random = new Random();
    private Button rollDices;
    private ImageView imageView1, imageView2, imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = getIntent();
        String string_player1 = intent.getStringExtra(MainActivity.EXTRA_TEXT1);
        String string_player2 = intent.getStringExtra(MainActivity.EXTRA_TEXT2);

        TextView textView1 = (TextView) findViewById(R.id.player1);
        TextView textView2 = (TextView) findViewById(R.id.player2);
        TextView visual_player = (TextView) findViewById(R.id.visual_player);

        textView1.setText(string_player1);
        textView2.setText(string_player2);
        visual_player.setText(string_player1);

        rollDices  = (Button) findViewById(R.id.rollDices);
        imageView1 = (ImageView) findViewById(R.id.dice1);
        imageView2 = (ImageView) findViewById(R.id.dice2);
        imageView3 = (ImageView) findViewById(R.id.dice3);

        rollDices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value1 = randomDiceValue();
                int value2 = randomDiceValue();
                int value3 = randomDiceValue();

                int res1 = getResources().getIdentifier("dice" + value1, "drawable", getPackageName());
                int res2 = getResources().getIdentifier("dice" + value2, "drawable", getPackageName());
                int res3 = getResources().getIdentifier("dice" + value3, "drawable", getPackageName());

                imageView1.setImageResource(res1);
                imageView2.setImageResource(res2);
                imageView3.setImageResource(res3);
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
