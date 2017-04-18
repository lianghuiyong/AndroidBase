package net.liang.androidbaseapplication.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by 梁惠涌
 * <p>
 * 控制Presenter的生命周期
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PresenterScoped {}
