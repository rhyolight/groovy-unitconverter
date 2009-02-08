package org.groovy.example
/*
 * author: Matthew Taylor
 */
class Runner {
    public static void main(String[] args) {
        Class.forName('org.groovy.example.UnitConversionSetup')
        def combinedInput = args.size() > 1 ? args.join('.') : args[0]
        def convertedInput = new InputConverter().convert(combinedInput)
        println Eval.me(convertedInput)
    }
}