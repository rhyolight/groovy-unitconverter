/*
* author: Matthew Taylor
*/
class UnitConversionTests extends GroovyTestCase {

    void setUp() {
        Class.forName('UnitConversionSetup')
    }

    void testKmToMeters() {
        assertEquals 1000, 1.kilometers.to.meters
        assertEquals 500, 0.5.kilometers.to.meters
    }

    void testMetersToKm() {
        assertEquals 1, 1000.meters.to.kilometers
        assertEquals 1.75, 1750.meters.to.kilometers
    }

    void testKilometersToMiles() {
        assertEquals 31.0685596, 50.kilometers.to.miles
    }

    void testMetersToMiles() {
        assertEquals 0.0310685596, 50.meters.to.miles
    }

    void testMilesToMeters() {
        assertEquals 24140.1600092000, 15.miles.to.meters
    }

    void testCelciusToFahrenheit() {
        assertEquals 0, 32.fahrenheit.to.celcius
    }

    void testFahrenheitToCelcius() {
        assertEquals 32, 0.celcius.to.fahrenheit
    }

    void testFahrenheitToKelvin() {
        assertEquals 273.15, 32.fahrenheit.to.kelvin
    }

    void testKelvintoCelcius() {
        assertEquals 275, 1.85.celcius.to.kelvin
    }

    void testBadConversionThrowsException_WhenCannotConvertFrom() {
        def msg = shouldFail(InvalidConversionException) {
            10.celcius.to.meters
        }
        assertEquals 'Cannot convert celcius to meters', msg
    }

    void testBadConversionThrowsException_WhenNoLookupForFrom() {
        def msg = shouldFail(InvalidConversionException) {
            10.pizzas.to.meters
        }
        assertEquals 'Cannot convert pizzas to anything', msg
    }

}