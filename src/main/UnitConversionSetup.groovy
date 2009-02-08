/*
 * author: Matthew Taylor
 */
class UnitConversionSetup {

    static {
        Number.mixin UnitConversionMixin

        def properties = Collections.synchronizedMap([:])

        Number.metaClass.setConverting = { String value ->
           properties[System.identityHashCode(delegate) + "converting"] = value
        }

        Number.metaClass.getConverting = { ->
           properties[System.identityHashCode(delegate) + "converting"]
        }

        Number.metaClass.setUnits = { String value ->
           properties[System.identityHashCode(delegate) + "units"] = value
        }

        Number.metaClass.getUnits = { ->
           def result = properties[System.identityHashCode(delegate) + "units"]
           result ?: 'meters'
        }

        Number.metaClass.getTo = { ->
           properties[System.identityHashCode(delegate) + "converting"] = true
           delegate
        }

        // stores any units being declared on a number
        Number.metaClass.propertyMissing = { String name ->
//            println "propertyMissing: $name (converting? ${delegate.converting})"
            if (!Lookup.contains(name)) throw new InvalidConversionException("Cannot convert $name to anything")
            if (delegate.converting) {
                delegate.converting = false
                return delegate.to(name)
            } else {
                delegate.units = name
                return delegate
            }
        }

    }

}