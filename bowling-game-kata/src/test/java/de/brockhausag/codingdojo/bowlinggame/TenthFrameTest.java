package de.brockhausag.codingdojo.bowlinggame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TenthFrameTest {

    @Nested
    class SpareTest {

        protected TenthFrame frame;

        @BeforeEach
        void setUp() {
            this.frame = new TenthFrame();
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

        protected TenthFrame frame;

        @BeforeEach
        void setUp() {
            this.frame = new TenthFrame();
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
        void testIsStrike_tenOnThirdRoll() {
            // arrange
            this.frame.addRoll(0);
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
        private TenthFrame frame;

        @BeforeEach
        void setUp() {
            this.frame = new TenthFrame();
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
        void testIsNotDoneAfterStrike() {
            frame.addRoll(10);

            var actual = frame.isDone();

            assertThat(actual).isFalse();
        }

        @Test
        void testIsNotDoneAfterStrikeAndSecondRoll() {
            frame.addRoll(10);
            frame.addRoll(0);

            var actual = frame.isDone();

            assertThat(actual).isFalse();
        }

        @Test
        void testIsDoneAfterStrikeAndThirdRoll() {
            frame.addRoll(10);
            frame.addRoll(0);
            frame.addRoll(0);

            var actual = frame.isDone();

            assertThat(actual).isTrue();
        }

        @Test
        void testIsNotDoneAfterSpare() {
            frame.addRoll(5);
            frame.addRoll(5);

            var actual = frame.isDone();

            assertThat(actual).isFalse();
        }

        @Test
        void testIsDoneAfterSpareAndThirdRoll() {
            frame.addRoll(5);
            frame.addRoll(5);
            frame.addRoll(0);

            var actual = frame.isDone();

            assertThat(actual).isTrue();
        }

    }


    @Nested
    class BonusTest {
        private TenthFrame frame;

        @BeforeEach
        void setUp() {
            this.frame = new TenthFrame();
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
            frame.addRoll(1);

            var expectedBonusScore = 1;

            var actualBonusScore = frame.getBonusScore();

            assertThat(actualBonusScore).isEqualTo(expectedBonusScore);
        }

        @Test
        void testBonusAfterStrike() {
            frame.addRoll(10);
            frame.addRoll(1);
            frame.addRoll(2);

            var expectedBonusScore = 3;

            var actualBonusScore = frame.getBonusScore();

            assertThat(actualBonusScore).isEqualTo(expectedBonusScore);
        }
    }
}