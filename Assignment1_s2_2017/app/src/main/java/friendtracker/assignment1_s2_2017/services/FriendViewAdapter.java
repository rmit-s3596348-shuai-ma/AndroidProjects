package friendtracker.assignment1_s2_2017.services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Jason on 8/4/2017.
 */

public class FriendViewAdapter extends BaseAdapter
{
    private LayoutInflater mInflater;

    public FriendViewAdapter(Context context)
    {
        this.mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount()
    {
        return 0;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return null;
    }
}
