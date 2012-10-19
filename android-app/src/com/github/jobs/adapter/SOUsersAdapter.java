package com.github.jobs.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.codeslap.groundy.adapter.Layout;
import com.codeslap.groundy.adapter.ListBaseAdapter;
import com.codeslap.groundy.adapter.ResourceId;
import com.github.jobs.R;
import com.github.jobs.bean.SOUser;
import com.telly.wasp.BitmapHelper;
import com.telly.wasp.BitmapObserver;
import com.telly.wasp.BitmapUtils;

/**
 * @author cristian
 * @version 1.0
 */
public class SOUsersAdapter extends ListBaseAdapter<SOUser, SOUsersAdapter.SOUserViewHolder> {

    private final Handler mUiHandler;

    public SOUsersAdapter(Context context) {
        super(context, SOUserViewHolder.class);
        mUiHandler = new Handler();
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public void populateHolder(int position, View view, ViewGroup parent, SOUser item, SOUserViewHolder holder) {
        BitmapHelper bitmapHelper = BitmapHelper.getInstance();
        String avatarUrl = item.getProfileImage();
        Bitmap bitmap = bitmapHelper.getBitmap(avatarUrl);
        holder.avatar.setTag(avatarUrl);
        if (BitmapUtils.isBitmapValid(bitmap)) {
            holder.avatar.setImageBitmap(bitmap);
        } else {
            holder.avatar.setImageResource(R.drawable.ic_default_avatar);
            BitmapObserver observer = new BitmapObserver(holder.avatar, avatarUrl, mUiHandler);
            bitmapHelper.registerBitmapObserver(getContext(), observer);
        }
        holder.username.setText(item.getDisplayName());
        holder.reputation.setText("Reputation: " + item.getReputation());
    }

    @Layout(R.layout.so_user_row)
    public static class SOUserViewHolder {
        @ResourceId(R.id.img_user_avatar)
        ImageView avatar;
        @ResourceId(R.id.lbl_username)
        TextView username;
        @ResourceId(R.id.lbl_reputation)
        TextView reputation;
    }
}