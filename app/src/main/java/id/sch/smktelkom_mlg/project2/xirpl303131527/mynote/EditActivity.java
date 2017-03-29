package id.sch.smktelkom_mlg.project2.xirpl303131527.mynote;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Locale;

import id.sch.smktelkom_mlg.project2.xirpl303131527.mynote.db.DatabaseAccess;

public class EditActivity extends AppCompatActivity {
    private EditText etText;
    private Button btnSave;
    private Button btnCancel;
    private Memo memo;
    private final int REQ_CODE_SPEECH_INPUT = 10;
    private FloatingActionButton voice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        this.etText = (EditText) findViewById(R.id.etText);
        this.btnSave = (Button) findViewById(R.id.btnSave);
        this.btnCancel = (Button) findViewById(R.id.btnCancel);
        this.voice = (FloatingActionButton) findViewById(R.id.voices);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            memo = (Memo) bundle.get("MEMO");
            if(memo != null) {
                this.etText.setText(memo.getText());
            }
        }

        this.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClicked();
            }
        });

        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelClicked();
            }
        });

        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askSpeechInput();

            }
        });
    }

    private void askSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
               RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Hi speak something");
        try {
           startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    public void onSaveClicked() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        if(memo == null) {
            // Add new memo
            Memo temp = new Memo();
            temp.setText(etText.getText().toString());
            databaseAccess.save(temp);
        } else {
            // Update the memo
            memo.setText(etText.getText().toString());
            databaseAccess.update(memo);
        }
        databaseAccess.close();
        this.finish();
    }

    public void onCancelClicked() {
        this.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    etText.setText(result.get(0));
                }
                break;
            }

        }
    }
}
