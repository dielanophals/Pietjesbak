package be.dielanophals.pietjesbak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final Random random = new Random();
    private Button rollDices;
    private ImageView imageView1, imageView2, imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }
    public static int randomDiceValue(){
        return random.nextInt(6) + 1;
    }
}
