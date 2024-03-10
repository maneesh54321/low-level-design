## Use case - 1
### Request an elevator car
### Actor 
Passenger
### Preconditions: 
Passenger is on one of the floors of the building and wants to go up. 
### Steps:
1. Passenger presses down button on one of the elevator's floor panel.
2. Down button starts glowing on all the elevators.
3. Elevator system assigns an elevator.
4. The assigned elevator's down indicator starts glowing.
5. Elevator sends the elevator car to requested floor.
6. Elevator car stops at the requested floor.
7. Elevator car door & floor door opens.
8. Passenger enter the elevator car.
9. Elevator car door & floor door closes automatically.
10. Down button stops glowing on all the elevators.

#### Nouns:
Passenger, Down Button, Elevator's Floor Panel, Elevator System, Elevator, Elevator's Down Indicator, Elevator car, Requested Floor, Elevator car Door, Floor Door.

#### Verbs:
presses down button, starts glowing, assigns elevator, starts glowing, send elevator car, stops at floor, door opens, Passenger enters, closes automatically, stops glowing.

## Use case - 2
### Request a stop from an elevator car
### Actor
Passenger
### Preconditions
Passenger is in one the assigned elevator
### Steps
1. Passenger presses a floor button on elevator navigation panel.
2. Elevator validates if it can send elevator car to the floor.
3. Floor button starts glowing.
4. Elevator sends Elevator car to the floor.
5. Elevator car stops at the floor.
6. Floor button stops glowing.
7. Elevator car door opens.
8. Elevator car door closes automatically.

#### Nouns
Passenger, Floor Button, Elevator, Elevator Navigation Panel, Elevator Car, Elevator car door.

#### Verbs
presses, validates, starts glowing, stops, opens, closes