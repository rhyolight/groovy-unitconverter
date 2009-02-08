package org.groovy.example
/*
 * author: Matthew Taylor
 */
class InvalidConversionException extends Exception {

    def InvalidConversionException(s) {
        super(s.toString());
    }

}