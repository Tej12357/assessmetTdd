public class StringCalculator {
       public int add(String numbers) {
           if (numbers.isEmpty()) {
               return 0;
           }

           if (numbers.startsWith("//")) {
               int delimiterEndIndex = numbers.indexOf("\n");
               String delimiter = numbers.substring(2, delimiterEndIndex);
               String numbersPart = numbers.substring(delimiterEndIndex + 1);
               return sum(numbersPart, delimiter);
           }

           return sum(numbers, "[,\n]");
       }

       private int sum(String numbers, String delimiter) {
           String[] numArray = numbers.split(delimiter);
           int sum = 0;
           StringBuilder negativeNumbers = new StringBuilder();

           for (String num : numArray) {
               int number = Integer.parseInt(num);
               if (number < 0) {
                   if (negativeNumbers.length() > 0) {
                       negativeNumbers.append(", ");
                   }
                   negativeNumbers.append(number);
               } else {
                   sum += number;
               }
           }

           if (negativeNumbers.length() > 0) {
               throw new IllegalArgumentException("Negative numbers not allowed: " + negativeNumbers);
           }

           return sum;
       }
   }
