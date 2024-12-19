import org.junit.jupiter.api.Test;
   import static org.junit.jupiter.api.Assertions.*;

   public class StringCalculatorTest {

       @Test
       public void testEmptyString() {
           StringCalculator calculator = new StringCalculator();
           assertEquals(0, calculator.add(""));
       }

       @Test
       public void testSingleNumber() {
           StringCalculator calculator = new StringCalculator();
           assertEquals(1, calculator.add("1"));
       }

       @Test
       public void testTwoNumbers() {
           StringCalculator calculator = new StringCalculator();
           assertEquals(6, calculator.add("1,5"));
       }

       @Test
       public void testNewLineDelimiter() {
           StringCalculator calculator = new StringCalculator();
           assertEquals(6, calculator.add("1\n2,3"));
       }

       @Test
       public void testCustomDelimiter() {
           StringCalculator calculator = new StringCalculator();
           assertEquals(3, calculator.add("//;\n1;2"));
       }

       @Test
       public void testNegativeNumbers() {
           StringCalculator calculator = new StringCalculator();
           Exception exception = assertThrows(IllegalArgumentException.class, () -> {
               calculator.add("1,-2,3,-4");
           });
           assertEquals("Negative numbers not allowed: -2, -4", exception.getMessage());
       }
   }
