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