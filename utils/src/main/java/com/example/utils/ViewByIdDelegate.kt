package com.example.utils

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import java.lang.IllegalStateException
import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

class ViewByIdDelegate<out T : View>(
    private val rootGetter: () -> View?,
    private val viewId: Int
) {

    //ссылка на root
    private var rootRef: WeakReference<View>? = null

    //ссылка на view
    private var viewRef: T? = null

    //при каждом обращении к переменной вызывается этот метод
    operator fun getValue(thisRef: Any, kProperty: KProperty<*>): T {
        var view = viewRef
        val cachedRoot = rootRef?.get()
        // Получаем root c текущим расположнием View
        val currentRoot = rootGetter()

        if (currentRoot != cachedRoot || view == null) {
            if (currentRoot == null) {
                if (view != null) {
                    //Failsafe, возвращаем хотя бы последнюю View
                    return view
                }
                throw IllegalStateException("Cannot get View, there is no root yet")
            }
            //если не выполнилось верхнее условие, то создаем нашу view из текущего местоположения
            view = currentRoot.findViewById(viewId)
            //сохраняем ссылку на view? чтобы не создавать ее каждый раз заново
            viewRef = view
            //сохраняем ссылку на root, чтобы не искать его каждый раз заново
            rootRef = WeakReference(currentRoot)
        }

        checkNotNull(view) { "view with id: \"$viewId\" not found in root" }
        //возвращаем view в момет обращения к ней
        return view
    }
}

fun <T : View> Activity.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
    //Возвращаем корневую View
    return ViewByIdDelegate({ window.decorView.findViewById(android.R.id.content) }, viewId)
}

fun <T : View> Fragment.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
    return ViewByIdDelegate({ view }, viewId)
}
