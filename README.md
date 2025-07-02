# kotlin-lotto

## Features

- [ ] minimum ticket: 1000 KRW
### Lotto Class
- [x] 6 unique numbers
- [x] in range 1 - 45 
- [x] Lotto instance has toString
### WinningNumbers Class
- [x] valid lotto tickets inside winning numbers class
- [x] valid 1 bonus unique number inside winning numbers class

### Rank
- [x] return valid Rank depend on match count, bonus number and if required
- [x] implement valueOf

### Controller
- [x] Add the controller where happen the flow of the app
- [x] Use View classes for input and output
- [x] Create a retry help functions to validate de input and in case of error retry it.
- [x] Create a method or service to create the Lotto tickets
- [x] Create retry helper function to get user input
- [x] Add a generateLottoTickets service

### Services
- [x] add Lotto service
- [x] add Validator for the purchase amount user input
- [x] Add a service to generate the lotto tickets from the amount passed

### InputView
- [ ] Get input
  - [x] Please enter the purchase amount.
  - [ ] Please enter last week’s winning numbers.
  - [ ] Please enter the bonus number.

###  OutputView
- [x] Print out the purchased tickets
- [ ] lotto statistics
  - [ ] lotto statistics with prices
  - [ ] print lotto statistics