package elevatorsystem.state;

import elevatorsystem.Elevator;
import elevatorsystem.enums.Direction;
import elevatorsystem.enums.RequestSource;
import elevatorsystem.models.Request;

public class MovingDownState implements ElevatorState {
    @Override
    // For DOWN state, the move() function only takes the first down request x , x < curr floor
    // If no x, then set to IDLE state
    public void move(Elevator elevator) {
        if (elevator.getDownRequests().isEmpty()) {
            elevator.setState(new IdleState());
            return;
        }

        Integer nextFloor = elevator.getDownRequests().first();
        elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);

        if (elevator.getCurrentFloor() == nextFloor) {
            System.out.println("Elevator " + elevator.getId() + " stopped at floor " + nextFloor);
            elevator.getDownRequests().pollFirst();
        }

        if (elevator.getDownRequests().isEmpty()) {
            elevator.setState(new IdleState());
        }
    }

    @Override
    public void addRequest(Elevator elevator, Request request) {
        // Internal requests always get added to the appropriate queue
        if (request.getSource() == RequestSource.INTERNAL) {
            if (request.getTargetFloor() > elevator.getCurrentFloor()) {
                elevator.getUpRequests().add(request.getTargetFloor());
            } else {
                elevator.getDownRequests().add(request.getTargetFloor());
            }
            return;
        }

        // External requests
        // If request is to go down and target floor x < curr floor --> add in down set
        // else put in up request. will do it later.
        if (request.getDirection() == Direction.DOWN && request.getTargetFloor() <= elevator.getCurrentFloor()) {
            elevator.getDownRequests().add(request.getTargetFloor());
        } else if (request.getDirection() == Direction.UP) {
            elevator.getUpRequests().add(request.getTargetFloor());
        }
    }

    @Override
    public Direction getDirection() { return Direction.DOWN; }
}
