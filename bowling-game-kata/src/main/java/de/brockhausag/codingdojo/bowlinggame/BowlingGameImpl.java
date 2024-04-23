package de.brockhausag.codingdojo.bowlinggame;

import java.util.ArrayList;
import java.util.List;

public class BowlingGameImpl implements BowlingGame {

    private final List<Frame> frames;

    public BowlingGameImpl() {
        this.frames = new ArrayList<>();
        addNewFrame();
    }

    public void roll(int pins) {
        if (getActualFrame().isDone()) {
            addNewFrame().addRoll(pins);
            return;
        }

        getActualFrame().addRoll(pins);
    }

    @Override
    public int getScore() {
        return getActualFrame().getScore();
    }

    public Frame addNewFrame() {
        if (this.frames.size() == 10) {
            throw new IllegalStateException("GAME OVER, CHEAT!!!");
        }

        Frame frame = null;

        if (this.frames.isEmpty()) {
            frame = new Frame();
        }

        if (!this.frames.isEmpty() && this.frames.size() < 9) {
            frame = new Frame(getActualFrame());
            getActualFrame().setNextFrame(frame);
        }

        if (this.frames.size() == 9) {
            frame = new TenthFrame(getActualFrame());
            getActualFrame().setNextFrame(frame);
        }

        this.frames.add(frame);
        return frame;
    }

    private Frame getActualFrame() {
        return frames.get(frames.size() - 1);
    }
}