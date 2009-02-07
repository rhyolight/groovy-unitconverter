/*
 * author: Matthew Taylor
 */
class Runner {
    public static void main(String[] args) {
        Class.forName('UnitConversionSetup')
        println Eval.me(args[0])
    }
}