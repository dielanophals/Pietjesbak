package be.dielanophals.pietjesbak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT1 = "be.example.application.pietjesbak.EXTRA_TEXT1";
    public static final String EXTRA_TEXT2 = "be.example.application.pietjesbak.EXTRA_TEXT2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.start_game);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    public void openActivity2(){
        EditText player1 = (EditText) findViewById(R.id.edittext1);
        String string_player1 = player1.getText().toString();

        EditText player2 = (EditText) findViewById(R.id.edittext2);
        String string_player2 = player2.getText().toString();

        TextView name_check = (TextView) findViewById(R.id.name_check);

        if(string_player1.isEmpty() && string_player2.isEmpty()){
            name_check.setText("Gelieve een naam in te vullen voor beide spelers");
        }else{
            Intent intent = new Intent(this, Activity2.class);
            intent.putExtra(EXTRA_TEXT1, string_player1);
            intent.putExtra(EXTRA_TEXT2, string_player2);
            startActivity(intent);
        }
    }

}