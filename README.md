# Environment

Java 9 or above is required

# How to build the application

- Build: ./gradlew clean build

- Execute Integration tests: ./gradlew integrationtest

- Execute unit tests: ./gradlew test

# How to run the application

./gradlew run --args='./src/integration-test/resources/market.csv 1000'

or

./gradlew clean build
java -jar ./build/libs/loan-challenge-1.0-SNAPSHOT.jar ./src/integration-test/resources/market.csv 1000

### Implementation

The application has been developed following Outside-In TDD. You can find the acceptance tests that started the
 "Outside-In flow" in integration-test/java/com.zopa.challenge.acceptance.CalculateQuoteFeature
 
All the lenders contained in the market file are loaded and ordered in memory. If we wanted to support files with a
 large volume of data this should be changed.  
 
I have implemented the algorithm needed to match the specified output, but with the given output "Total repayment" is
 not equal to "Monthly repayment" * 36. In my opinion, "Total repayment" should be calculated with "Monthly repayment" already rounded.
 
 
 # Zopa Technical Test
 
 There is need for an application to find a quote from Zopa’s market of lenders for 36-month loans
 that apply interest on a monthly basis.
 
 Each lender in the market offers an amount of money to lend and the annual interest rate they
 expect in return. A list of all lenders and their offers will be provided in a CSV file.
 
 The application should provide as low a rate as is possible to ensure that Zopa's quotes are as
 competitive as they can be against our competitors'. Details of the monthly repayment amount
 and the total repayment amounts should be shown in addition to the amount requested and the
 annual interest rate for the quote.
 
 Repayment amounts should be displayed to two decimal places and the annual interest rate
 displayed to one decimal place.
 
 A quote may be requested in any £100 increment between £1000 and £15000 inclusive. If the
 market does not have enough offers to fulfil the request, then the application should state that it is
 not possible to provide a quote.
 
 The application should take arguments in the form:
 
 [market_file_path] [loan_amount]
 And produce outputs in the form:
 Requested amount: £XXXX
 Annual Interest Rate: X.X%
 Monthly repayment: £XXXX.XX
 Total repayment: £XXXX.XX
 Example output:
 > $ ./zopa-rate market.csv 1000
 > Requested amount: £1000
 > Annual Interest Rate: 7.0%
 > Monthly repayment: £30.78
 > Total repayment: £1108.10
 
 ## Remarks
 * We accept submissions in any of the following languages: C#, F#, Java, Kotlin, Scala, Python,
 JavaScript, though we recommend you choose the language most suitable to the role you are
 applying for.
 * The monthly repayments should spread the total repayment cost over the term of the loan.
 Here is an explanation of how to calculate this:
 https://en.wikipedia.org/wiki/Amortization_calculator#The_formula
 * We will review your code and run it against further test cases to see how it handles them
 * The market.csv file used for the example above will be given alongside this specification. It is
 acceptable to submit your program if it returns an answer that does not exactly match the
 example given.