package com.loopj.android.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmartImageView extends ImageView {
    private static final int LOADING_THREADS = 4;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(LOADING_THREADS);

    private SmartImageTask currentTask;


    public SmartImageView(Context context) {
        super(context);
    }

    public SmartImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    /**
     * 加载网络图片
     *
     * @param url 图片url
     */
    public void setImageUrl(String url) {
        setImage(new WebImage(url));
    }

    /**
     * 加载网络图片
     *
     * @param url              图片url
     * @param completeListener 加载成功之后的处理
     */
    public void setImageUrl(String url, SmartImageTask.OnCompleteListener completeListener) {
        setImage(new WebImage(url), completeListener);
    }

    /**
     * 加载网络图片
     *
     * @param url              图片url
     * @param fallbackResource 加载失败的图片ID
     */
    public void setImageUrl(String url, final Integer fallbackResource) {
        setImage(new WebImage(url), fallbackResource);
    }

    /**
     * 加载网络图片
     *
     * @param url              图片url
     * @param fallbackResource 加载失败的图片ID
     * @param completeListener 加载成功之后的处理
     */
    public void setImageUrl(String url, final Integer fallbackResource, SmartImageTask.OnCompleteListener completeListener) {
        setImage(new WebImage(url), fallbackResource, completeListener);
    }

    /**
     * 加载网络图片
     *
     * @param url              图片url
     * @param fallbackResource 加载失败的图片ID
     * @param loadingResource  加载中的图片ID
     */
    public void setImageUrl(String url, final Integer fallbackResource, final Integer loadingResource) {
        setImage(new WebImage(url), fallbackResource, loadingResource);
    }

    /**
     * 加载网络图片
     *
     * @param url              图片url
     * @param fallbackResource 加载失败的图片ID
     * @param loadingResource  加载中的图片ID
     * @param completeListener 加载成功之后的处理
     */
    public void setImageUrl(String url, final Integer fallbackResource, final Integer loadingResource, SmartImageTask.OnCompleteListener completeListener) {
        setImage(new WebImage(url), fallbackResource, loadingResource, completeListener);
    }


    // Helpers to set image by contact address book id
    public void setImageContact(long contactId) {
        setImage(new ContactImage(contactId));
    }

    public void setImageContact(long contactId, final Integer fallbackResource) {
        setImage(new ContactImage(contactId), fallbackResource);
    }

    public void setImageContact(long contactId, final Integer fallbackResource, final Integer loadingResource) {
        setImage(new ContactImage(contactId), fallbackResource, fallbackResource);
    }


    // Set image using SmartImage object
    public void setImage(final SmartImage image) {
        setImage(image, null, null, null);
    }

    public void setImage(final SmartImage image, final SmartImageTask.OnCompleteListener completeListener) {
        setImage(image, null, null, completeListener);
    }

    public void setImage(final SmartImage image, final Integer fallbackResource) {
        setImage(image, fallbackResource, fallbackResource, null);
    }

    public void setImage(final SmartImage image, final Integer fallbackResource, SmartImageTask.OnCompleteListener completeListener) {
        setImage(image, fallbackResource, fallbackResource, completeListener);
    }

    public void setImage(final SmartImage image, final Integer fallbackResource, final Integer loadingResource) {
        setImage(image, fallbackResource, loadingResource, null);
    }

    public void setImage(final SmartImage image, final Integer fallbackResource, final Integer loadingResource, final SmartImageTask.OnCompleteListener completeListener) {
        // Set a loading resource
        if (loadingResource != null) {
            setImageResource(loadingResource);
        }

        // Cancel any existing tasks for this image view
        if (currentTask != null) {
            currentTask.cancel();
            currentTask = null;
        }

        // Set up the new task
        currentTask = new SmartImageTask(getContext(), image);
        currentTask.setOnCompleteHandler(new SmartImageTask.OnCompleteHandler() {
            @Override
            public void onComplete(Bitmap bitmap) {
                if (bitmap != null) {
                    setImageBitmap(bitmap);
                } else {
                    // Set fallback resource
                    if (fallbackResource != null) {
                        setImageResource(fallbackResource);
                    }
                }

                if (completeListener != null) {
                    completeListener.onComplete(bitmap);
                }
            }
        });

        // Run the task in a threadpool
        threadPool.execute(currentTask);
    }

    public static void cancelAllTasks() {
        threadPool.shutdownNow();
        threadPool = Executors.newFixedThreadPool(LOADING_THREADS);
    }
}