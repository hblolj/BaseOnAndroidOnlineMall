package com.app.baseonandroidonlinemall.injector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by hblolj on 2017/5/8.
 * 单例标示
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
