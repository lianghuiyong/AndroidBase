package net.liang.appbaselibrary.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import net.liang.appbaselibrary.R;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 图片加载工具类
 * 这里用的glid
 * Created by Dino on 11/17 0017.
 */

public class ImageLoadUtils {
    private static int defultImageID = R.mipmap.default_back_picture;

    /**
     * 加载原图片
     *
     * @param image
     * @param imageView
     */
    public static void loadImage(Object image, ImageView imageView) {
        loadImage(image,imageView,defultImageID);
    }

    public static void loadImage(Object image, ImageView imageView, int defultImage) {
        Glide.with(imageView.getContext())
                .load(image)
                .error(defultImage)
                .crossFade()
                .into(imageView);
    }


    /**
     * 处理成圆形
     *
     * @param image
     * @param imageView
     */
    public static void loadCropCircleImage(Object image, ImageView imageView) {
        loadCropCircleImage(image,imageView,defultImageID);
    }

    public static void loadCropCircleImage(Object image, ImageView imageView,int defultImage) {
        Glide.with(imageView.getContext())
                .load(image)
                .bitmapTransform(new CropCircleTransformation(imageView.getContext()))
                .error(defultImage)
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载本地图片，取消缓存
     * @param image
     * @param imageView
     * @param defultImage
     */
    public static void loadCropCircleImageLocal(Object image, ImageView imageView,int defultImage) {
        Glide.with(imageView.getContext())
                .load(image)
                .bitmapTransform(new CropCircleTransformation(imageView.getContext()))
                .error(defultImage)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .crossFade()
                .into(imageView);
    }

    /**
     * 高斯模糊效果
     *
     * @param image
     * @param imageView
     */
    public static void loadBlurImage(Object image, ImageView imageView) {
        loadBlurImage(image,imageView,defultImageID);
    }

    public static void loadBlurImage(Object image, ImageView imageView, int defultImage) {
        Glide.with(imageView.getContext())
                .load(image)
                .bitmapTransform(new BlurTransformation(imageView.getContext(), 25))
                .error(defultImageID)
                .crossFade()
                .into(imageView);
    }

    /**
     * 圆形+高斯模糊效果
     *
     * @param image
     * @param imageView
     */
    public static void loadBlurAndCropCircleImage(Object image, ImageView imageView) {
        loadBlurAndCropCircleImage(image,imageView,defultImageID);
    }

    public static void loadBlurAndCropCircleImage(Object image, ImageView imageView,int defultImage) {
        Glide.with(imageView.getContext())
                .load(image)
                .bitmapTransform(new BlurTransformation(imageView.getContext(), 25),
                        new CropCircleTransformation(imageView.getContext()))
                .error(defultImageID)
                .crossFade()
                .into(imageView);
    }


    /**
     * 四周圆角
     *
     * @param image
     * @param imageView
     */
    public static void loadRoundedCornersImage(Object image, ImageView imageView) {
        loadRoundedCornersImage(image,imageView,defultImageID);
    }

    public static void loadRoundedCornersImage(Object image, ImageView imageView,int defultImage) {
        Glide.with(imageView.getContext())
                .load(image)
                .bitmapTransform(new RoundedCornersTransformation(imageView.getContext(), 15, 0, RoundedCornersTransformation.CornerType.ALL))
                .error(defultImage)
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载本地图片，取消缓存
     * @param image
     * @param imageView
     * @param defultImage
     */
    public static void loadRoundedCornersImageLocal(Object image, ImageView imageView,int defultImage) {
        Glide.with(imageView.getContext())
                .load(image)
                .bitmapTransform(new RoundedCornersTransformation(imageView.getContext(), 15, 0, RoundedCornersTransformation.CornerType.ALL))
                .error(defultImage)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .crossFade()
                .into(imageView);
    }

}
