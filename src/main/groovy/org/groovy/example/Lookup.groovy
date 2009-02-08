package org.groovy.example
/*
 * author: Matthew Taylor
 */
class Lookup {

    static def lookup = [:]
    private static def lookupCrumbs = []

    static {
        // distance
        lookup.centimeters = [
                meters: 'x / 100'
        ]
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

    static boolean contains(name) {
        lookup.inject(lookup[name]) { found, entry ->
            if (found) return true
            def val = entry.value
            return val.inject(found) { innerFound, innerEntry ->
                if (innerEntry.key == name) {
                    return true
                }
            }
        }
    }

    static def formula(from, to) {
        lookup[from].inject(lookup[from][to]) { resultFormula, entry ->
            if (resultFormula) return resultFormula
            def (key, formula) = [entry.key, entry.value]
            if (key in lookupCrumbs) {
                return null
            }
            lookupCrumbs << from
            def iterFormula = this.formula(key, to)
            if (iterFormula) {
                return iterFormula.replaceAll('x', "($formula)")
            }
        }
    }

}