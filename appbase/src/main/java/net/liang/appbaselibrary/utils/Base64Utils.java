package net.liang.appbaselibrary.utils;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by lenovo on 2016/10/28.
 * Base64工具类
 */

public class Base64Utils {
    /**
     * 将bitmap转换成Base64字符串，便于上传
     */
    public static String Bitmap2StrByBase64(File imagePath){
        Bitmap bit = ImageUtils.getBitmap(imagePath);
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 40, bos);//参数100表示不压缩
        byte[] bytes=bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
