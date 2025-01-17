package com.eco4ndly.githubtrendingrepo.infra.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.eco4ndly.githubtrendingrepo.common.extensions.put
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * A Sayan Porya code on 05/06/20
 */
class FragmentArgumentDelegate<T: Any>: ReadWriteProperty<Fragment, T> {

  @Suppress("UNCHECKED_CAST")
  override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
    val key = property.name
    return thisRef.arguments
      ?.get(key) as? T
      ?: throw IllegalStateException("Property ${property.name} could not be read")
  }

  override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
    val args = thisRef.arguments
      ?: Bundle().also(thisRef::setArguments)
    val key = property.name
    args.put(key, value)
  }

}

fun <T : Any> argument(): ReadWriteProperty<Fragment, T> =
  FragmentArgumentDelegate()