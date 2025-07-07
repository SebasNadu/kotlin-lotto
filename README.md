# kotlin-lotto

## Features

### Lotto Class

- [x] 6 unique numbers
- [x] Lotto instance has toString
- [x] Add type automatic or manual to the class

### LottoNumber Class

- [x] valid number in range 1–45

### LottoMachine Class

- [x] validate Purchase Amount
- [x] generate Lotto tickets from the purchase amount
- [x] minimum ticket: 1000 KRW
- [ ] LottoMachine handle manual and automatic tickets generation

### WinningNumbers Class

- [x] valid lotto tickets inside winning numbers class
- [x] valid 1 bonus unique number inside winning numbers class

### ResultAnalyzer Class

- [x] analyze the winning numbers with the lotto tickets and return a list of Rank
- [x] calculate the return rate based on the purchase amount and the total winning amount

### Rank

- [x] return valid Rank depend on match count, bonus number and if required
- [x] implement valueOf

### PurchaseSession

- [x] Add a class that wraps the information about the purchase
- [x] Should contain the purchase amount, manual tickets, automatic tickets etc..

### Controller

- [x] Add the controller where happens the flow of the app
- [x] Use View classes for input and output
- [x] Create a retry help function to validate de input and in case of error, retry it.
- [x] Create a method or service to create the Lotto tickets
- [x] Create a retry helper function to get user input
- [x] Add a generateLottoTickets service

### InputView

- [x] Get input
    - [x] Please enter the purchase amount.
    - [x] Please enter last week’s winning numbers.
    - [x] Please enter the bonus number.
    - [x] Enter the number of manual tickets to purchase.
    - [x] Enter the numbers for a manual ticket.

### OutputView

- [x] Print out the purchased tickets
- [x] lotto statistics
    - [x] lotto statistics with prices
    - [x] print lotto statistics
- [ ] when the tickets are printing add a header with information about how many manual and auto tickets they are.
- [ ] Print the return rate with more detailed message `Total return rate is 0.35 (A rate below 1 means a loss)`

### refactors

- [x] create Const value, no magic number
- [x] customize exception for DRY
- [x] add documentation
- [x] separate controller logics
- [x] refactor methods

---

### TODOS

- [ ] add **E2E test**
