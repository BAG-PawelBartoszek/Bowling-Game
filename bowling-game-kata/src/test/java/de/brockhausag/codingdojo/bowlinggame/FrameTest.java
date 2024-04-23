package de.brockhausag.codingdojo.bowlinggame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    @Nested
    class SpareTest {

        protected Frame frame;

        @BeforeEach
        void setUp() {
            this.frame = new Frame();
        }

        @Test
        void testIsSpare_emptyFrame() {
            // arrange

            // act
            var actual = this.frame.isSpare();

            // assert
            assertThat(actual).isFalse();
        }

        @Test
        void testIsSpare_strike() {
            // arrange
            this.frame.addRoll(10);

            // act
            var actual = this.frame.isSpare();

            // assert
            assertThat(actual).isFalse();
        }

        @Test
        void testIsSpare_gutterFrame() {
            // arrange
            this.frame.addRoll(0);
            this.frame.addRoll(0);

            // act
            var actual = this.frame.isSpare();

            // assert
            assertThat(actual).isFalse();
        }

        @Test
        void testIsSpare_oneRoll() {
            // arrange
            this.frame.addRoll(1);

            // act
            var actual = this.frame.isSpare();

            // assert
            assertThat(actual).isFalse();
        }

        @Test
        void testIsSpare_spare() {
            // arrange
            this.frame.addRoll(1);
            this.frame.addRoll(9);

            // act
            var actual = this.frame.isSpare();

            // assert
            assertThat(actual).isTrue();
        }
    }

    @Nested
    class StrikeTest {

        protected Frame frame;

        @BeforeEach
        void setUp() {
            this.frame = new Frame();
        }

        @Test
        void testIsStrike_emptyFrame() {
            // arrange

            // act
            var actual = this.frame.isStrike();

            // assert
            assertThat(actual).isFalse();
        }

        @Test
        void testIsStrike_strike() {
            // arrange
            this.frame.addRoll(10);

            // act
            var actual = this.frame.isStrike();

            // assert
            assertThat(actual).isTrue();
        }

        @Test
        void testIsStrike_gutterFrame() {
            // arrange
            this.frame.addRoll(0);
            this.frame.addRoll(0);

            // act
            var actual = this.frame.isStrike();

            // assert
            assertThat(actual).isFalse();
        }

        @Test
        void testIsStrike_tenOnSecondRoll() {
            // arrange
            this.frame.addRoll(0);
            this.frame.addRoll(10);

            // act
            var actual = this.frame.isStrike();

            // assert
            assertThat(actual).isFalse();
        }

        @Test
        void testIsStrike_spare() {
            // arrange
            this.frame.addRoll(1);
            this.frame.addRoll(9);

            // act
            var actual = this.frame.isStrike();

            // assert
            assertThat(actual).isFalse();
        }
    }

    @Nested
    class DoneTest {
        private Frame frame;

        @BeforeEach
        void setUp() {
            this.frame = new Frame();
        }

        @Test
        void testIsNotDoneAfterCreation() {
            var actual = frame.isDone();

            assertThat(actual).isFalse();
        }

        @Test
        void testIsNotDoneAfterFirstRoll() {
            frame.addRoll(0);

            var actual = frame.isDone();

            assertThat(actual).isFalse();
        }

        @Test
        void testIsDoneAfterSecondRoll() {
            frame.addRoll(0);
            frame.addRoll(0);

            var actual = frame.isDone();

            assertThat(actual).isTrue();
        }

        @Test
        void testIsDoneAfterStrike() {
            frame.addRoll(10);

            var actual = frame.isDone();

            assertThat(actual).isTrue();
        }

        @Test
        void testIsDoneAfterSpare() {
            frame.addRoll(5);
            frame.addRoll(5);

            var actual = frame.isDone();

            assertThat(actual).isTrue();
        }
    }

    @Nested
    class BonusTest {
        private Frame frame;

        @BeforeEach
        void setUp() {
            this.frame = new Frame();
        }

        @Test
        void testNoBonusAfterGutterFrame() {
            frame.addRoll(0);
            frame.addRoll(0);
            var expectedBonusScore = 0;

            var actualBonusScore = frame.getBonusScore();

            assertThat(actualBonusScore).isEqualTo(expectedBonusScore);
        }

        @Test
        void testBonusAfterSpare() {
            frame.addRoll(5);
            frame.addRoll(5);
            var nextFrame = new Frame(frame);
            nextFrame.addRoll(1);
            frame.setNextFrame(nextFrame);
            var expectedBonusScore = 1;

            var actualBonusScore = frame.getBonusScore();

            assertThat(actualBonusScore).isEqualTo(expectedBonusScore);
        }

        @Test
        void testBonusAfterStrikeAndOneNextFrame() {
            frame.addRoll(10);

            var nextFrame = new Frame(frame);
            nextFrame.addRoll(1);
            nextFrame.addRoll(2);
            frame.setNextFrame(nextFrame);
            var expectedBonusScore = 3;

            var actualBonusScore = frame.getBonusScore();

            assertThat(actualBonusScore).isEqualTo(expectedBonusScore);
        }

        @Test
        void testBonusAfterTwoStrikes() {
            frame.addRoll(10);

            var nextFrame = new Frame(frame);
            var secondNextFrame = new Frame(nextFrame);
            nextFrame.addRoll(10);
            secondNextFrame.addRoll(10);
            frame.setNextFrame(nextFrame);
            nextFrame.setNextFrame(secondNextFrame);
            var expectedBonusScore = 20;

            var actualBonusScore = frame.getBonusScore();

            assertThat(actualBonusScore).isEqualTo(expectedBonusScore);
        }
    }
}