package de.brockhausag.codingdojo.bowlinggame;

import java.util.Objects;

public class TenthFrame extends Frame {
    private Integer scoreThirdRoll;

    public TenthFrame() {
    }

    public TenthFrame(Frame previousFrame) {
        super(previousFrame);
    }

    @Override
    public void addRoll(int pins) {
        if (getScoreFirstRoll() == null) {
            scoreFirstRoll = pins;
            return;
        }

        if (getScoreSecondRoll() == null) {
            scoreSecondRoll = pins;
            return;
        }

        if (isThirdRollAllowed()) {
            scoreThirdRoll = pins;
        }
    }

    @Override
    public boolean isDone() {
        if (isStrike() && Objects.nonNull(getScoreSecondRoll()) && Objects.nonNull(getScoreThirdRoll())) {
            return true;
        }

        if (isSpare() && Objects.nonNull(getScoreThirdRoll())) {
            return true;
        }

        return !isStrike()
                && !isSpare()
                && Objects.nonNull(getScoreFirstRoll())
                && Objects.nonNull(getScoreSecondRoll());
    }

    protected boolean isThirdRollAllowed() {
        return this.isStrike() || this.isSpare();
    }

    protected int getBonusScore() {
        if (isStrike()) {
            return getScoreSecondRoll() + getScoreThirdRoll();
        }

        if (isSpare()) {
            return Objects.nonNull(getScoreThirdRoll()) ? getScoreThirdRoll() : 0;
        }

        return 0;
    }

    public Integer getScoreThirdRoll() {
        return scoreThirdRoll;
    }
}
