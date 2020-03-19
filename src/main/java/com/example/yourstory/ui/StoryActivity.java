package com.example.yourstory.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yourstory.R;
import com.example.yourstory.model.Page;
import com.example.yourstory.model.Story;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity {

    private Story story;
    private ImageView storyImageView;
    private TextView textView;
    Button choice1Button;
    Button choice2Button;
    String name;
    private Stack<Integer> pageStack = new Stack<Integer>();
    private static final String TAG = StoryActivity.class.getSimpleName() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyImageView= (ImageView) findViewById(R.id.storyImageView);
        textView = (TextView)findViewById(R.id.textView);
        choice1Button = (Button)findViewById(R.id.choice1Button1);
        choice2Button = (Button)findViewById(R.id.choice2Button);

        Intent intent = getIntent();
        name = intent.getStringExtra(getString(R.string.key_name));
        if (name == null || name.isEmpty())
        {
            name = "Friend";
        }
        Log.d(TAG, "onCreate: intent created");
        story = new Story();
        loadPage(0);
    }

    private void loadPage(final int pageNumber) {
        pageStack.push(pageNumber);
        final Page page = story.getPage(pageNumber);

        Drawable image = ContextCompat.getDrawable(this,page.getImageId());

        storyImageView.setImageDrawable(image);

        String pageText = getString(page.getTextId());
        pageText = String.format(pageText, name);

        textView.setText(pageText);

        if (page.isFinalPage()){
            choice1Button.setVisibility(View.INVISIBLE);
            choice2Button.setText(R.string.play_again_button_text);
            choice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    finish();
                    pageStack.clear();
                    loadPage(0);
                }
            });
        }
        else {
            choice1Button.setVisibility(View.VISIBLE);
            loadButtons(page);


        }


    }

    private void loadButtons(final Page page) {
        choice1Button.setText(page.getFirstChoice().getTextId());
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getFirstChoice().getNextPage();
                loadPage(nextPage);
            }
        });

        choice2Button.setText(page.getSecondChoice().getTextId());
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getSecondChoice().getNextPage();
                loadPage(nextPage);
            }
        });
    }

    @Override
    public void onBackPressed() {
        pageStack.pop();
        if (pageStack.isEmpty())
        {
            super.onBackPressed();
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }
        else {
            loadPage(pageStack.pop());
        }

    }
}
