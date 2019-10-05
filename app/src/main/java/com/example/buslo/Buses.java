package com.example.buslo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.time.LocalDateTime;

public class Buses extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView stops;
    LinearLayout invi;
    private String mParam1;
    private String mParam2;
    private ImageView locimage;

    private OnFragmentInteractionListener mListener;

    public Buses() {
        // Required empty public constructor
    }
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference, ref2;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    public static Buses newInstance(String param1, String param2) {
        Buses fragment = new Buses();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }
    private View vieww;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Bus");

        View view= inflater.inflate(R.layout.fragment_buses, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        locimage = (ImageView) view.findViewById(R.id.location) ;
        stops = (TextView) view.findViewById(R.id.stops);
        vieww  = view;
        invi = (LinearLayout) view.findViewById(R.id.invi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Bus").child("111");
        FirebaseRecyclerOptions<Bus> options = new FirebaseRecyclerOptions.Builder<Bus>()
                .setQuery(query, Bus.class)
                .build();
        FirebaseRecyclerAdapter FBRA = new FirebaseRecyclerAdapter<Bus, BusViewHolder>(options){

            @Override
            public BusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus, parent, false);
                return new BusViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final BusViewHolder holder, int position, @NonNull final Bus model) {
                final String busno = getRef(position).getKey();
                holder.setavgspeed(model.getAvgtime());
                holder.setcap(model.getCap());
                holder.setocc(model.getOcc());
                holder.setbusno(busno);
                Log.v("AAA", busno);
                stops = (TextView) holder.mView.findViewById(R.id.stops);
                stops.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.setRoute(model.getStops(), busno);
                        invi = (LinearLayout) holder.mView.findViewById(R.id.invi);
//                        invi.setVisibility(View.INVISIBLE);
                        invi.animate()
                                .translationY(0 )
                                .setDuration(500)
                                .alpha(1.0f)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {
                                        super.onAnimationStart(animation);
                                        invi.setVisibility(View.VISIBLE);
                                    }
                                });

                    }
                });
                locimage = (ImageView) holder.mView.findViewById(R.id.location);
                locimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), Location.class);
                        intent.putExtra("lat", model.getLat());
                        intent.putExtra("lon", model.getLon());
                        intent.putExtra("busno", busno);
                        startActivity(intent);
                    }
                });
            }
        };
        FBRA.startListening();
        recyclerView.setAdapter(FBRA);
        return view;
    }

    public class BusViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public BusViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setavgspeed(String avgtime){
            TextView avgtimee = (TextView) mView.findViewById(R.id.avgtime);
            avgtimee.setText("Avg. Speed: " + avgtime);
        }

        public void setbusno(String busno){
            TextView busnoo = (TextView) mView.findViewById(R.id.busno);
            busnoo.setText("Bus No.: "+ busno);
        }

        public void setocc(String avgtime){
            TextView avgtimee = (TextView) mView.findViewById(R.id.occ);
            avgtimee.setText("Current Occupancy: " + avgtime);
        }
        public void setcap(String avgtime){
            TextView avgtimee = (TextView) mView.findViewById(R.id.cap);
            avgtimee.setText("Seating Capacity: " + avgtime);
        }

        public void setRoute(final String route, String busno){
            Log.v("AAA", route);
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Stops");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String res = "";
                        for(int i=0; i<route.length(); i++){
                            String x = dataSnapshot.child(route.charAt(i)+"").getValue().toString() + "-->";
                            res+=x;
                        }
                        TextView routee = mView.findViewById(R.id.route);
                        routee.setText(res);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getContext(), "Failed to retrieve route", Toast.LENGTH_SHORT).show();
                    }
                });

//            ValueEventListener valueEventListener = new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    for (DataSnapshot ds: dataSnapshot.getChildren()) {
//                        String data = dataSnapshot.getValue(String.class);
//                        Log.v("AAA", data);
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            };
//            ref.addListenerForSingleValueEvent(valueEventListener);
        }


//        public void setavgspeed(String avgtime){
//            TextView avgtimee = (TextView) mView.findViewById(R.id.avgtime);
//            avgtimee.setText("Avg. Speed: " + avgtime);
//        }

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }





    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
