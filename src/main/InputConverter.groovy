/*
 * author: Matthew Taylor
 */
class InputConverter {

    def convert(input) {
        input.trim().split(/\s+|\.+/).collect {
            if (it != 'to' && !it.isNumber() && !it.endsWith('s')) {
                return it + 's'
            } else {
                return it
            }
        }.join('.')
    }
}