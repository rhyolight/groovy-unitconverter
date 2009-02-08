/*
 * author: Matthew Taylor
 */
class InputConverterTests extends GroovyTestCase {

    def converter

    void setUp() {
        converter = new InputConverter()
    }

    void testInputWithSpaces() {
        assertEquals '1.meters.to.kilometers', converter.convert('1 meters to kilometers') 
    }

    void testInputWithManySpaces() {
        assertEquals '1.meters.to.kilometers', converter.convert('1   meters   to    kilometers')
    }

    void testInputWithLeadingAndTrailingSpaces() {
        assertEquals '1.meters.to.kilometers', converter.convert('    1   meters   to    kilometers    ')
    }

    void testInputWithDots() {
        assertEquals '1.meters.to.kilometers', converter.convert('1.meters.to.kilometers') 
    }

    void testInputWithSingleUnit() {
        assertEquals '1.meters.to.kilometers', converter.convert('1 meter to kilometers') 
    }
}