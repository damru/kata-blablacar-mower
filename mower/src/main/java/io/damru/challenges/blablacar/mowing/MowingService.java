package io.damru.challenges.blablacar.mowing;

import io.damru.challenges.blablacar.mowing.model.Action;
import io.damru.challenges.blablacar.mowing.model.Lawn;
import io.damru.challenges.blablacar.mowing.model.Mower;
import io.damru.challenges.blablacar.mowing.model.Orientation;
import org.springframework.stereotype.Service;

@Service
class MowingService {


    public void mow(Lawn lawn) {
        lawn.getMowersCourses().forEach((mower, actions) -> actions.forEach(action -> move(lawn, mower, action)));
    }

    private void move(Lawn lawn, Mower mower, Action action) {
        if (Action.FORWARD.equals(action)) {
            go(lawn, mower);
        } else if (Action.RIGHT.equals(action) || Action.LEFT.equals(action)) {
            turn(mower, action);
        }
    }

    private void turn(Mower mower, Action action) {
        switch (mower.getOrientation()) {
            case NORTH:
                if (Action.LEFT.equals(action)) {
                    mower.setOrientation(Orientation.WEST);
                } else if (Action.RIGHT.equals(action)) {
                    mower.setOrientation(Orientation.EAST);
                }
                break;
            case SOUTH:
                if (Action.LEFT.equals(action)) {
                    mower.setOrientation(Orientation.EAST);
                } else if (Action.RIGHT.equals(action)) {
                    mower.setOrientation(Orientation.WEST);
                }
                break;
            case EAST:
                if (Action.LEFT.equals(action)) {
                    mower.setOrientation(Orientation.NORTH);
                } else if (Action.RIGHT.equals(action)) {
                    mower.setOrientation(Orientation.SOUTH);
                }
                break;
            case WEST:
                if (Action.LEFT.equals(action)) {
                    mower.setOrientation(Orientation.SOUTH);
                } else if (Action.RIGHT.equals(action)) {
                    mower.setOrientation(Orientation.NORTH);
                }
                break;
        }
    }

    private void go(Lawn lawn, Mower mower) {
        switch (mower.getOrientation()) {
            case NORTH:
                if (mower.getY() + 1 <= lawn.getYMax()) {
                    mower.setY(mower.getY() + 1);
                }
                break;
            case SOUTH:
                if (mower.getY() - 1 >= lawn.getYMin()) {
                    mower.setY(mower.getY() - 1);
                }
                break;
            case EAST:
                if (mower.getX() + 1 <= lawn.getXMax()) {
                    mower.setX(mower.getX() + 1);
                }
                break;
            case WEST:
                if (mower.getX() - 1 <= lawn.getXMin()) {
                    mower.setX(mower.getX() - 1);
                }
                break;
        }
    }
}
