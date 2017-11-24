package com.example.pankaj.maukascholars.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pankaj.maukascholars.R;
import com.example.pankaj.maukascholars.database.DBHandler;
import com.example.pankaj.maukascholars.util.Constants;
import com.example.pankaj.maukascholars.util.EventDetails;
import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.pankaj.maukascholars.util.Constants.imageResources;
import static com.example.pankaj.maukascholars.util.Constants.normal;
import static com.example.pankaj.maukascholars.util.Constants.normal_color;
import static com.example.pankaj.maukascholars.util.Constants.sub;


public class Cards extends AppCompatActivity {
    private CardViewAdapter mAdapter;
    private List<EventDetails> mItems = new ArrayList<>(Arrays.asList(new EventDetails(), new EventDetails(), new EventDetails(), new EventDetails(), new EventDetails(), new EventDetails(), new EventDetails()));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        OnItemTouchListener itemTouchListener = new OnItemTouchListener() {
            @Override
            public void onCardViewTap(View view, int position) {
                String url = mItems.get(position).getLink();
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        };

        mAdapter = new CardViewAdapter(this, mItems, itemTouchListener);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        TODO: Add items for card adapter
        recyclerView.setAdapter(mAdapter);

        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(recyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipeLeft(int position) {
                                return true;
                            }

                            @Override
                            public boolean canSwipeRight(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    mItems.remove(position);
                                    mAdapter.notifyItemRemoved(position);
                                }
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    mItems.remove(position);
                                    mAdapter.notifyItemRemoved(position);
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        });

        recyclerView.addOnItemTouchListener(swipeTouchListener);
    }

    /**
     * Interface for the touch events in each item
     */
    public interface OnItemTouchListener {
        /**
         * Callback invoked when the user Taps one of the RecyclerView items
         *
         * @param view     the CardView touched
         * @param position the index of the item touched in the RecyclerView
         */
        void onCardViewTap(View view, int position);
    }

    /**
     * A simple adapter that loads a CardView layout with one TextView and two Buttons, and
     * listens to clicks on the Buttons or on the CardView
     */
    public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
        private List<EventDetails> cards;
        private OnItemTouchListener onItemTouchListener;
        private Context mContext;

        CardViewAdapter(Context context, List<EventDetails> cards, OnItemTouchListener onItemTouchListener) {
            this.cards = cards;
            this.onItemTouchListener = onItemTouchListener;
            mContext = context;

        }

        HamButton.Builder getHamButtonBuilder(int h, final int position) {
            return new HamButton.Builder()
                    .normalImageRes(imageResources[h])
                    .normalText(normal[h])
                    .subNormalText(sub[h])
                    .normalColor(normal_color[h])
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            if (index == 0) {
                                Intent sendIntent = new Intent();
                                sendIntent.setAction(Intent.ACTION_SEND);
                                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey! Found a great opportunity for you!\n" + cards.get(position).getLink());
                                sendIntent.setType("text/plain");
                                startActivity(sendIntent);
                            }else if (index == 1){
                                Log.e("BMBListener", " Star");
                                starEvent(position);
                            }else if (index == 2){
                                Log.e("BMBListener", " Following");
//                                TODO: Following
                            }else if (index == 3){
                                Log.e("BMBListener", "Send Mail");
                                composeEmail(position);
                            }
                        }
                    });
        }

        private void starEvent(int position) {
            DBHandler db = new DBHandler(mContext);
            if (db.getEvent(cards.get(position).getId()) != null){
                Toast.makeText(mContext, "Already starred!", Toast.LENGTH_SHORT).show();
            }else{
                cards.get(position).setStarred(1);
                Log.e("Setting position", "Starred");
                db.addEvent(cards.get(position));
                Log.e("Length: ", "" + db.getAllStarredEvents() + " is the number of starred events");
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            int position = i;
            viewHolder.title.setText(cards.get(position).getTitle());
            viewHolder.description.setText(cards.get(position).getDescription());
            viewHolder.name.setText(cards.get(position).getName());
            viewHolder.deadline.setText(cards.get(position).getDeadline());
            viewHolder.bmb.clearBuilders();
            for (int h = 0; h < viewHolder.bmb.getPiecePlaceEnum().pieceNumber(); h++) {
                viewHolder.bmb.addBuilder(getHamButtonBuilder(h, position));
            }
        }

        @Override
        public int getItemCount() {
            return cards == null ? 0 : cards.size();
        }

        private void composeEmail(int position) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, Constants.user_email);
            intent.putExtra(Intent.EXTRA_SUBJECT, cards.get(position).getTitle());
            intent.putExtra(Intent.EXTRA_TEXT,  "Spot On Opportunities presents to you:\n" + cards.get(position).getLink() + "\n" + cards.get(position).getDescription());
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }else
                Toast.makeText(Cards.this, "No Email app found!", Toast.LENGTH_SHORT).show();

        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView title, description, name, deadline;
            private ImageView event_image;
            private BoomMenuButton bmb;

            ViewHolder(final View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.event_title);
                description = itemView.findViewById(R.id.event_description);
                name = itemView.findViewById(R.id.name_poster);
                deadline = itemView.findViewById(R.id.date_posted);
                bmb = itemView.findViewById(R.id.bmb);
                for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
                    SimpleCircleButton.Builder builder = new SimpleCircleButton.Builder()
                            .listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {
                                    Log.e("BMB", index+"");
                                }
                            });
                    bmb.addBuilder(builder);
                }

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemTouchListener.onCardViewTap(v, getLayoutPosition());
                    }
                });
            }
        }
    }
}
