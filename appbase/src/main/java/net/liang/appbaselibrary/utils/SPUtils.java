package net.liang.appbaselibrary.utils;

/**
 * Created by lenovo on 2017/1/9.
 */

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.socks.library.KLog;

import java.util.Map;

/**
 * author: 梁惠涌
 * desc  : SP相关工具类
 * <p>
 * desc  : SP相关工具类
 */
public class SPUtils {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String name = "APP_SP_File.db";

    private Application application;
    @SuppressLint("StaticFieldLeak")
    private static SPUtils instances;

    /**
     * SPUtils构造函数
     *
     * @param application
     */
    private SPUtils(Application application) {
        this.application = application;
        sp = this.application.getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.apply();
    }

    public static void init(Application application) {
        instances = new SPUtils(application);
    }

    /**
     * SP中写入String类型value
     *
     * @param key   键
     * @param value 值
     */
    public static void putString(String key, String value) {
        if (instances == null) {
            KLog.e("SPUtils instances is null, you need call init() method.");
            return;
        }
        instances.editor.putString(key, value).apply();
    }

    /**
     * SP中读取String
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code null}
     */
    public static String getString(String key) {
        return getString(key, null);
    }

    /**
     * SP中读取String
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public static String getString(String key, String defaultValue) {
        if (instances == null) {
            KLog.e("SPUtils instances is null, you need call init() method.");
            return defaultValue;
        }
        return instances.sp.getString(key, defaultValue);
    }

    /**
     * SP中写入int类型value
     *
     * @param key   键
     * @param value 值
     */
    public static void putInt(String key, int value) {
        if (instances == null) {
            KLog.e("SPUtils instances is null, you need call init() method.");
            return;
        }
        instances.editor.putInt(key, value).apply();
    }

    /**
     * SP中读取int
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public static int getInt(String key) {
        return getInt(key, -1);
    }

    /**
     * SP中读取int
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public static int getInt(String key, int defaultValue) {
        if (instances == null) {
            KLog.e("SPUtils instances is null, you need call init() method.");
            return defaultValue;
        }
        return instances.sp.getInt(key, defaultValue);
    }

    /**
     * SP中写入long类型value
     *
     * @param key   键
     * @param value 值
     */
    public static void putLong(String key, long value) {
        if (instances == null) {
            KLog.e("SPUtils instances is null, you need call init() method.");
            return;
        }
        instances.editor.putLong(key, value).apply();
    }

    /**
     * SP中读取long
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public static long getLong(String key) {
        return getLong(key, -1L);
    }

    /**
     * SP中读取long
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public static long getLong(String key, long defaultValue) {
        if (instances == null) {
            KLog.e("SPUtils instances is null, you need call init() method.");
            return defaultValue;
        }
        return instances.sp.getLong(key, defaultValue);
    }

    /**
     * SP中写入float类型value
     *
     * @param key   键
     * @param value 值
     */
    public static void putFloat(String key, float value) {
        if (instances == null) {
            KLog.e("SPUtils instances is null, you need call init() method.");
            return;
        }

        instances.editor.putFloat(key, value).apply();
    }

    /**
     * SP中读取float
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public static float getFloat(String key) {
        return getFloat(key, -1f);
    }

    /**
     * SP中读取float
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public static float getFloat(String key, float defaultValue) {
        if (instances == null) {
            KLog.e("SPUtils instances is null, you need call init() method.");
            return defaultValue;
        }
        return instances.sp.getFloat(key, defaultValue);
    }

    /**
     * SP中写入boolean类型value
     *
     * @param key   键
     * @param value 值
     */
    public static void putBoolean(String key, boolean value) {
        if (instances == null) {
            KLog.e("SPUtils instances is null, you need call init() method.");
            return;
        }
        instances.editor.putBoolean(key, value).apply();
    }

    /**
     * SP中读取boolean
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code false}
     */
    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * SP中读取boolean
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        if (instances == null) {
            KLog.e("SPUtils instances is null, you need call init() method.");
            return defaultValue;
        }
        return instances.sp.getBoolean(key, defaultValue);
    }

    /**
     * SP中获取所有键值对
     *
     * @return Map对象
     */
    public static Map<String, ?> getAll() {
        if (instances == null) {
            KLog.e("SPUtils instances is null, you need call init() method.");
            return null;
        }
        return instances.sp.getAll();
    }

    /**
     * SP中移除该key
     *
     * @param key 键
     */
    public static void remove(String key) {
        if (instances == null) {
            KLog.e("SPUtils instances is null, you need call init() method.");
            return;
        }
        instances.editor.remove(key).apply();
    }

    /**
     * SP中是否存在该key
     *
     * @param key 键
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean contains(String key) {
        if (instances == null) {
            KLog.e("SPUtils instances is null, you need call init() method.");
            return false;
        }
        return instances.sp.contains(key);
    }

    /**
     * SP中清除所有数据
     */
    public static void clear() {
        if (instances == null) {
            KLog.e("SPUtils instances is null, you need call init() method.");
            return;
        }

        instances.editor.clear().apply();
    }
}
