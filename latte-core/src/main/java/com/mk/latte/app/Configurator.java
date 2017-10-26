package com.mk.latte.app;

import android.content.Context;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;


/**
 *
 * @author lenovo
 * @date 2017/10/15
 */

public class Configurator {

    /**
     * 配置集合
     */
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    /**
     * Icon集合
     */
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    /**
     * 拦截器集合
     */
    private static final ArrayList<Interceptor> INTERCEPTORS=new ArrayList<>();

    private Configurator() {
        //初始化配置
        //设置为false表示配置未完成
        putConfiguration(ConfigKeys.CONFIG_READY.name(), false);
    }

    /**
     * 单例--获取 实例
     *
     * @return
     */
    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 内部类 用于创建 实例
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    /**
     * 配置准备完成
     */
    public final void configure() {
        //初始化图标
        initIcons();
        putConfiguration(ConfigKeys.CONFIG_READY.name(), true);
    }

    /**
     * 获取配置的集合
     *
     * @return
     */
    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }


    /**
     * 初始化 Icons
     * 也是用来显示
     */
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }


    /**
     * LATTE_CONFIGS 存入数据
     * @param key
     * @param value
     */
    final  void putConfiguration(Object key,Object value) {
        LATTE_CONFIGS.put(key,value);
    }


    /**
     * 添加拦截器
     * @param interceptor 拦截器
     * @return
     */
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(),INTERCEPTORS);
        return this;
    }


    /**
     * 添加拦截器
     * @param interceptor 拦截器
     * @return
     */
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(),INTERCEPTORS);
        return this;
    }

    /**
     * 配置 host 属性
     *
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host) {
        putConfiguration(ConfigKeys.API_HOST.name(), host);
        return this;
    }

    public final Configurator withContext(Context context)
    {
        putConfiguration(ConfigKeys.APPLICATION_CONTEXT.name(),context);
        return this;
    }

    public final Configurator withLoaderDelayed(long delayed) {
        LATTE_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    /**
     * 添加字体图标
     *
     * @param descriptor 图标字体库
     * @return
     */
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 检测配置是否完成
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }


    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) value;
    }












}














