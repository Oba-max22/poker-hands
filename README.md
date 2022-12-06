# poker-hands

Question at https://projecteuler.net/problem=54

## How the solution works (at a high level): 
-  The code starts by creating a FileService object.
- The file service is used to read files from the hard drive.
- Next, it creates a List of Lists that will hold all the hands for each player.
- For each hand in this list, it creates a Player object and sets its name and hand value.
- It then calls the PokerService to determine who won the game of poker between two players using their names as identifiers.
- Finally, it determines how many times each player wins and displays to the standard output.

## Object-oriented programming concepts applied:
- Encapsulation
- Polymorphism
- Interfaces/Abstract classes
- Single Responsibility for classes.
- Abstraction
- Composition
- Builder Design pattern.