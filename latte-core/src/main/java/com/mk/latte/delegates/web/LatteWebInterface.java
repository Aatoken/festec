package com.mk.latte.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.mk.latte.delegates.web.event.Event;
import com.mk.latte.delegates.web.event.EventManager;

/**
 * @author lenovo
 * @data 2017/11/9
 */

public class LatteWebInterface {

    private final  WebDelegate DELEGATE;

    private LatteWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static LatteWebInterface create(WebDelegate delegate)
    {
        return new LatteWebInterface(delegate);
    }


    @JavascriptInterface
    public String event(String params)
    {
        final String action= JSON.parseObject(params).getString("action");

        final Event event= EventManager.getInstance().createEvent(action);

        if(event!=null)
        {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.excute(params);
        }

        return null;
    }


}
