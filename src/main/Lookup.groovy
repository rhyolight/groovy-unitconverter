/*
 * author: Matthew Taylor
 */
class Lookup {

    static def lookup = [:]
    private static def lookupCrumbs = []

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

    static boolean contains(name) {
        lookup[name]
    }

    static def formula(from, to) {
        def result = lookup[from][to]
        if (result) return result

        lookup[from].each { key, formula ->
            if (result) return
            if (key in lookupCrumbs) {
                println 'aborting'
                return
            }
            lookupCrumbs << from
            def iterFormula = formula(key, to)
            if (iterFormula) {
                result = iterFormula.replaceAll('x', "($formula)")
            }
        }
        result
    }

}