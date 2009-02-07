/*
 * author: Matthew Taylor
 */
class Lookup {

    static def lookup = [:]

    static {
        // distance
        lookup.meters = [
                meters:'x',
                kilometers: 'x / 1000'
        ]
        lookup.kilometers = [
                meters: 'x * 1000',
                kilometers: 'x',
                miles: 'x * 0.621371192'
        ]
        lookup.miles = [
                kilometers: 'x / 0.621371192',
                feet: 'x * 5280'
        ]
        lookup.feet = [
                inches: 'x * 12'
        ]
        // temp
        lookup.celcius = [
                fahrenheit: '(x * 1.8) + 32'
        ]
        lookup.fahrenheit = [
                celcius: '(x - 32) / 1.8',
                kelvin: '(x + 459.67) * 5/9'
        ]
        lookup.kelvin = [
                fahrenheit: '(x * 9/5) - 459.67'
        ]
    }

    static def formula(from, to) {
        println "formula($from, $to)"
        if (!lookup[from]) {
            throw new InvalidConversionException("Cannot convert ${from} to anything")
        }
//        def primary = lookup[from][to]
//        if (primary) return primary


        def lookupLoop
        lookupLoop = { fromUnit, toUnit ->
            println "looking up $fromUnit ==> $toUnit"
            def result = lookup[fromUnit][toUnit]
            if (result) {
                println " -- FOUND ${result}!"
                return result
            } else {
                println "-- no result!"
            }
            def found = false
            lookup[fromUnit].each { unit, formula ->
                println "lookup loop ($unit, $formula) found? $found"
                if (found) return
                println " ... investigating ${fromUnit} ==> $unit ..."
                // ignoring same units
                if (unit == fromUnit) {
                    found = true
                    return
                }
                def partialFormula = lookupLoop(unit, toUnit)
                if (partialFormula) found = true
                println "found partial formula: \"${partialFormula}\""
                result = partialFormula.replaceAll('x', "($formula)")
                println "new result: $result"
            }
            println "returning $result"
            result
        }

        lookupLoop(from, to)

//        // if there is no simple result, we'll stroll through the lookup map a bit and try to find something that works
//        def result
//        lookup[from].each { fromUnit, fromFormula ->
//            println "$fromUnit ==> $fromFormula"
//            // check to see if this 'to' unit has a link to the 'to' unit we're working with
//            lookup[fromUnit].each { innerKey, innerFormula ->
//                println "\t$innerKey ==> $innerFormula"
//                if (innerKey == to) {
//                    // gotcha!
//                    result = innerFormula.replaceAll('x', "($fromFormula)")
//                }
//            }
//        }
//        println "complex formula: $result"
//        result
    }

}