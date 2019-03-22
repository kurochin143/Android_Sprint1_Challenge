package com.example.israel.sprint1;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MovieListActivity extends AppCompatActivity {

    public static final String MOVIE_KEY = "movie";
    public static final int REQUEST_CODE_MOVIE_EDIT = 0;
    public static final int RESULT_CODE_MOVIE_EDIT_DELETE = 0;
    public static final int RESULT_CODE_MOVIE_EDIT_SAVE = 1;

    private LinearLayout movieListLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieListLinearLayout = findViewById(R.id.linear_layout_movie_list);

        Button addMovieButton = findViewById(R.id.button_add_movie);
        addMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieListActivity.this, MovieDetailsActivity.class);
                // adding a movie
                startActivityForResult(intent, REQUEST_CODE_MOVIE_EDIT);

            }
        });

        // create dummy movies
        createTextView(new Movie("Dino", true));
        createTextView(new Movie("Dragon", false));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (intent == null) { // this will actually delete the movie
            return;
        }

        switch (requestCode) {
            case REQUEST_CODE_MOVIE_EDIT: {
                if (resultCode == RESULT_CODE_MOVIE_EDIT_SAVE) {
                    // @TODO create movie textView
                } // delete textView. the text view was already deleted when the details activity was opened
            } break;
        }

    }

    private void createTextView(final Movie movie) {
        TextView newTextView = new TextView(this);
        newTextView.setTag(movie);
        newTextView.setText(movie.getName());
        newTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25.f);
        // draw a strike-through if this movie has been watched
        if (movie.isWatched()) {
            // @TODO
        }

        movieListLinearLayout.addView(newTextView);

        newTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieListActivity.this, MovieDetailsActivity.class);
                intent.putExtra(MOVIE_KEY, movie);
                startActivityForResult(intent, REQUEST_CODE_MOVIE_EDIT);

                // remove the textView
                ViewGroup viewGroup = (ViewGroup)v.getParent();
                viewGroup.removeView(v);
            }
        });

    }
}
