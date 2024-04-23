package de.brockhausag.codingdojo.bowlinggame;

import java.util.Objects;

public class Frame {
    protected Integer scoreFirstRoll;
    protected Integer scoreSecondRoll;
    private Frame previousFrame;
    private Frame nextFrame;

    public Frame() {
    }

    public Frame(Frame previousFrame) {
        this.previousFrame = previousFrame;
    }

    public void addRoll(int pins) {
        if (scoreFirstRoll == null) {
            scoreFirstRoll = pins;
            return;
        }

        if (scoreSecondRoll == null) {
            scoreSecondRoll = pins;
        }
    }

    public int getScore() {
        var sumScoreOfThisFrame = (Objects.nonNull(scoreFirstRoll) ? scoreFirstRoll : 0)
                    + (Objects.nonNull(scoreSecondRoll) ? scoreSecondRoll : 0);

        return getScoreOfPreviousFrame()
                + sumScoreOfThisFrame
                + getBonusScore();
    }

    public boolean isDone() {
        return isStrike()
                || (Objects.nonNull(getScoreFirstRoll()) && Objects.nonNull(getScoreSecondRoll()));
    }

    public void setNextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

    protected int getScoreOfPreviousFrame() {
        return getPreviousFrame() != null ? getPreviousFrame().getScore() : 0;
    }

    protected boolean isStrike() {
        return Objects.nonNull(scoreFirstRoll) && scoreFirstRoll.equals(10) ;
    }

    protected boolean isSpare() {
        return Objects.nonNull(scoreFirstRoll)
                && Objects.nonNull(scoreSecondRoll)
                && scoreFirstRoll + scoreSecondRoll == 10;
    }

    protected int getScoreOfNextTwoRolls() {
        var scoreOfNextRoll = getScoreofNextRoll();
        var scoreOfSecondToNextRoll = Objects.nonNull(getNextFrame()) ? getScoreOfSecondToNextRoll() : 0;

        return scoreOfNextRoll + scoreOfSecondToNextRoll;
    }

    protected int getScoreofNextRoll() {
        return Objects.nonNull(getNextFrame()) ? getNextFrame().getScoreFirstRoll() : 0;
    }

    protected Integer getScoreFirstRoll() {
        return scoreFirstRoll;
    }

    protected Integer getScoreSecondRoll() {
        return scoreSecondRoll;
    }

    protected Frame getPreviousFrame() {
        return previousFrame;
    }

    protected int getBonusScore() {
        if (isSpare()) {
            return getScoreofNextRoll();
        }

        if (isStrike()) {
            return getScoreOfNextTwoRolls();
        }

        return 0;
    }

    private Frame getNextFrame() {
        return nextFrame;
    }

    private int getScoreOfSecondToNextRoll() {
        return getNextFrame().isStrike() ?
                getNextFrame().getScoreofNextRoll()
                : getNextFrame().getScoreSecondRoll();
    }
}
