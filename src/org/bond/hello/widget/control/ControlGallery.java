package org.bond.hello.widget.control;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import org.bond.hello.R;

/**
 * Created by Administrator on 2015-6-20.
 */
public class ControlGallery extends Activity {
    private int[] mImageResourceIds = {R.drawable.address_book, R.drawable.calendar,
            R.drawable.camera, R.drawable.clock, R.drawable.games_control,
            R.drawable.messenger, R.drawable.ringtone, R.drawable.settings,
            R.drawable.speech_balloon, R.drawable.weather, R.drawable.world,
            R.drawable.youtube};
    private ImageView mImageView = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.ctl_gallery);
        Gallery gallery1 = (Gallery) findViewById(R.id.gallery);
        mImageView = (ImageView) findViewById(R.id.imageView);

        ImageAdapter adapter = new ImageAdapter(this);

        gallery1.setAdapter(adapter);
        gallery1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mImageView.setBackgroundResource(mImageResourceIds[position]);
            }
        });
        mImageView.setBackgroundResource(mImageResourceIds[0]);
    }

    class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context context) {
            this.mContext = context;
        }

        //获取图片的个数
        @Override
        public int getCount() {
            return mImageResourceIds.length;
        }

        //获取图片在库中的位置
        @Override
        public Object getItem(int position) {
            return mImageResourceIds[position];
        }

        //获取图片在库中的位置
        @Override
        public long getItemId(int position) {
            return position;
        }

        //获取适配器中指定位置的视图对象
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(mImageResourceIds[position]);
            imageView.setLayoutParams(new Gallery.LayoutParams(120, 120));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            return imageView;
        }
    }
}