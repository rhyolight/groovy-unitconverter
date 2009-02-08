package org.groovy.example
/*
 * author: Matthew Taylor
 */
class UnitConversionMixin {

    static def to(Number n, String units) {
        def formula = Lookup.formula(n.units, units)
        if (!formula) {
            throw new InvalidConversionException("Cannot convert ${n.units} to $units")
        }
        Eval.me('x', n, formula)
    }

}