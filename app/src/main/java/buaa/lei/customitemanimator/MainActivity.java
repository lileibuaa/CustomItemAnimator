package buaa.lei.customitemanimator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> mStrs;
    private MyAdapter mAdapter;
    private static final long ANIM_DURATION = 2 * DateUtils.SECOND_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStrs = new ArrayList<>();
        mAdapter = new MyAdapter();
        CustomItemAnimation itemAnimation = new CustomItemAnimation();
        itemAnimation.setAddDuration(ANIM_DURATION);
        itemAnimation.setMoveDuration(ANIM_DURATION);
        itemAnimation.setRemoveDuration(ANIM_DURATION);
        itemAnimation.setChangeDuration(ANIM_DURATION);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rcv_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(itemAnimation);
        recyclerView.setAdapter(mAdapter);
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                initAdapterData();
                mAdapter.notifyDataSetChanged();
            }
        }, 2 * DateUtils.SECOND_IN_MILLIS);
    }

    private void initAdapterData() {
        mStrs.add("hello world");
        mStrs.add("hello android");
        mStrs.add("hello lei");
        mStrs.add("hello recycler view");
        mStrs.add("hello item animation");
    }

    private class MyAdapter extends RecyclerView.Adapter {
        public MyAdapter() {
            setHasStableIds(true);
        }

        @Override
        public long getItemId(int position) {
            return mStrs.get(position).hashCode();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            View view = inflater.inflate(R.layout.item_content, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MyViewHolder) holder).tvText.setText(mStrs.get(position));
        }

        @Override
        public int getItemCount() {
            return mStrs.size();
        }

        private class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tvText;

            public MyViewHolder(View view) {
                super(view);
                tvText = (TextView) view.findViewById(R.id.tv_text);
            }
        }
    }
}
