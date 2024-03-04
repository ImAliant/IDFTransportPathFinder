# Code Conventions for Java [Oracle] (https://www.oracle.com/java/technologies/javase/codeconventions-contents.html)

## Files
 - Java source **.java*
 - Java bytecode **.class*
 - Begining Comments :

        /*
        * Classname
        * 
        * Version information
        *
        * Date
        * 
        * Copyright notice
        */

## Indentation

1. Avoid lines longer than 80 characters.
2. When an expression will not fit on a single line, break it according to these general principles:

    - Break after a comma.
    - Break before an operator.
    - Prefer higher-level breaks to lower-level breaks.
    - Align the new line with the beginning of the expression at the same level on the previous line.
    - If the above rules lead to confusing code or to code that's squished up against the right margin, just indent 8 spaces instead.
3. Use camelCase notation.

## Example 

/**
 * Classame
 * This class represents an example to illustrate Oracle's Java code convention.
 */
public class Example {

    // Constants declaration in uppercase separated by underscores
    private static final int INITIAL_VALUE = 10;

    // Member variables declaration using camelCase notation
    private int currentValue;

    // Main method to illustrate functionality
    public static void main(String[] args) {
        // Using the class
        Example example = new Example();
        example.setCurrentValue(INITIAL_VALUE);
        System.out.println("The current value is: " + example.getCurrentValue());
    }

    // Getter method to access currentValue
    public int getCurrentValue() {
        return currentValue;
    }

    // Setter method to modify currentValue
    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

}

`Check out [Oracle] (https://www.oracle.com/java/technologies/javase/codeconventions-contents.html) for more information`