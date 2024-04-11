package de.brockhausag.codingdojo.bowlinggame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameImplTest {

    BowlingGame game;

    @BeforeEach
    void setUp() {
        this.game = new BowlingGameImpl();
    }

    @Test
    void testGutterGame() {
        // arrange
        var expectedScore = 0;
        rollMany(20, 0);

        // act
        var actualScore = game.getScore();

        // assert
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @Test
    void testAllOnes() {
        // arrange
        var expectedScore = 20;
        rollMany(20, 1);

        // act
        var actualScore = game.getScore();

        // assert
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @Test
    void testOneSpare() {
        // arrange
        var expectedScore = 16;
        rollSpare();
        game.roll(3);
        rollMany(17, 0);

        // act
        var actualScore = game.getScore();

        // assert
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @Test
    void testOneStrike() {
        // arrange
        var expectedScore = 24;
        rollStrike();
        game.roll(3);
        game.roll(4);
        rollMany(16, 0);

        // act
        var actualScore = game.getScore();

        // assert
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @Test
    void testPerfectGame() {
        // arrange
        var expectedScore = 300;
        rollMany(12, 10);

        // act
        var actualScore = game.getScore();

        // assert
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    private void rollMany(int numberOfRolls, int numberOfPins) {
        for (int rollIndex = 0; rollIndex < numberOfRolls; rollIndex++) {
            game.roll(numberOfPins);
        }
    }

    private void rollSpare() {
        game.roll(5);
        game.roll(5);
    }

    private void rollStrike() {
        game.roll(10);
    }
}