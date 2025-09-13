package sc.example.geminiproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    TextView textView;
    GeminiManager geminiManager=GeminiManager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        editText=findViewById(R.id.editText);
        textView=findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Question1 = editText.getText().toString();
                if (Question1.isEmpty()) {
                    textView.setText("Please enter a question");

                }
                String prompt = " Answer the following question in less then 50 words: " + Question1;
                geminiManager.sentTextPrompt(prompt, new GeminiCallback() {
                    @Override
                    public void onSuccess(String result) {
                        textView.setText(result);
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        textView.setText("Error: " + error.getMessage());
                    }
                });
            }
        });
    }
}