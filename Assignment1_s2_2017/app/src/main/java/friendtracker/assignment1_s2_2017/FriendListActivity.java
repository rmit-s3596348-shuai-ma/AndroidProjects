package friendtracker.assignment1_s2_2017;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.helper.ItemTouchHelper;
import friendtracker.assignment1_s2_2017.entities.Friend;
import friendtracker.assignment1_s2_2017.entities.FriendList;
import friendtracker.assignment1_s2_2017.services.FriendAdapter;
import java.util.ArrayList;

public class FriendListActivity extends AppCompatActivity implements FriendAdapter.ListItemClickListener
{

    private int num_list_items ;
    private FriendAdapter mFriendAdapter;
    private RecyclerView mFriendList;
    private ArrayList<Friend> arrListFriends;
    private static final String EXTRA_ID = "EXTRA_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        num_list_items = FriendList.getFriends().size();
        mFriendList = (RecyclerView) findViewById(R.id.friend_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mFriendList.setLayoutManager(layoutManager);

        arrListFriends = FriendList.getFriendArrayList();
        mFriendAdapter = new FriendAdapter(arrListFriends,this);
        mFriendList.setAdapter(mFriendAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(mFriendList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_friend, menu);
        return true;
    }

    @Override
    public void onListItemClick(int clickedItemIndex)
    {
        String id = FriendList.getFriendArrayList().get(clickedItemIndex).getId();
        Intent intent = new Intent(FriendListActivity.this,EditFriendActivity.class);
        intent.putExtra(EXTRA_ID, id);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.add_friend)
        {
            Context context = FriendListActivity.this;
            Class destinationActivity = EditFriendActivity.class;
            Intent startEditFriend = new Intent(context,destinationActivity);
            startActivityForResult(startEditFriend,0);

        }
        return super.onOptionsItemSelected(item);
    }

    private ItemTouchHelper.Callback createHelperCallback()
    {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
                {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        onListItemSwiped(position);
                    }
                };
        return simpleItemTouchCallback;
    }
    public void onListItemSwiped (int position)
    {
        Friend f  = arrListFriends.get(position);
        FriendList.getFriends().remove(f.getId());

        arrListFriends=FriendList.getFriendArrayList();
        this.mFriendAdapter.setFriends(arrListFriends);
        this.mFriendAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        arrListFriends=FriendList.getFriendArrayList();
        this.mFriendAdapter.setFriends(arrListFriends);
        this.mFriendAdapter.notifyDataSetChanged();
    }
}




