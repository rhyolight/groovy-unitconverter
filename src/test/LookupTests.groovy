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
        assertEquals '(x / 1000) * 0.621371192', lookup.formula('meters', 'miles')
    }
}