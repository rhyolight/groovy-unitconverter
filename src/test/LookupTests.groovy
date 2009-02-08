/*
 * author: Matthew Taylor
 */
class LookupTests extends GroovyTestCase {

    def lookup = new Lookup()

    void test_Meters_to_Kilometers() {
        assertEquals 'x / 1000', lookup.formula('meters', 'kilometers')
    }

    void test_Kilometers_to_Meters() {
        assertEquals 'x * 1000', lookup.formula('kilometers','meters')
    }

    void test_Celcius_to_Fahrenheit() {
        assertEquals '(x * 1.8) + 32', lookup.formula('celcius', 'fahrenheit')
    }

    void test_Fahrenheit_to_Celcius() {
        assertEquals '(x - 32) / 1.8', lookup.formula('fahrenheit', 'celcius')
    }

    void test_Meters_to_Miles() {
        assertEquals '((x) / 1000) * 0.621371192', lookup.formula('meters', 'miles')
    }

    void testRecursiveMapLookup() {
        def m = [
                a: [b:'x,b'],
                b: [a:'x,a', c:'x,c'],
                c: [a:'x,a', b:'x,b', z:'x,z'],
                z: [answer:'x,bingo!']
        ]

        def oldLookup = Lookup.lookup

        Lookup.lookup = m

        assertEquals 'x,b', Lookup.formula('a','b')
        assertEquals '(x,b),c', Lookup.formula('a','c')
        assertEquals '(((x,b),c),z),bingo!', Lookup.formula('a','answer')

        Lookup.lookup = oldLookup
    }

    void testContains() {
        def m = [
                a: [b:'x,b'],
                b: [a:'x,a', c:'x,c'],
                c: [a:'x,a', b:'x,b', z:'x,z'],
                z: [answer:'x,bingo!']
        ]

        def oldLookup = Lookup.lookup

        Lookup.lookup = m

        assertTrue Lookup.contains('a')
        assertTrue Lookup.contains('b')
        assertTrue Lookup.contains('c')
        assertTrue Lookup.contains('z')
        assertTrue Lookup.contains('answer')

        Lookup.lookup = oldLookup
    }
}