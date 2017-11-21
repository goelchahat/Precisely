package com.example.pankaj.maukascholars.activity;

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

import com.example.pankaj.maukascholars.R;
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

        mAdapter = new CardViewAdapter(mItems, itemTouchListener);

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

        CardViewAdapter(List<EventDetails> cards, OnItemTouchListener onItemTouchListener) {
            this.cards = cards;
            this.onItemTouchListener = onItemTouchListener;
        }

        HamButton.Builder getHamButtonBuilder(int h) {
            HamButton.Builder hamB =new HamButton.Builder()
                    .normalImageRes(imageResources[h])
                    .normalText(normal[h])
                    .subNormalText(sub[h])
                    .normalColor(normal_color[h])
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            Log.e("BMB", index+"");
                        }
                    });
            return hamB;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.swipable_cards, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.title.setText(cards.get(i).getTitle());
            viewHolder.description.setText(cards.get(i).getDescription());
            viewHolder.name.setText(cards.get(i).getName());
            viewHolder.deadline.setText(cards.get(i).getDeadline());
            viewHolder.bmb.clearBuilders();
            for (int h = 0; h < viewHolder.bmb.getPiecePlaceEnum().pieceNumber(); h++) {
                viewHolder.bmb.addBuilder(getHamButtonBuilder(h));
            }
        }

        @Override
        public int getItemCount() {
            return cards == null ? 0 : cards.size();
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
