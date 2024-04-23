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

    @Test
    void testExampleGame() {
        var expectedScore = 133;

        game.roll(1);
        game.roll(4);
        game.roll(4);
        game.roll(5);
        game.roll(6);
        game.roll(4);
        game.roll(5);
        game.roll(5);
        game.roll(10);
        game.roll(0);
        game.roll(1);
        game.roll(7);
        game.roll(3);
        game.roll(6);
        game.roll(4);
        game.roll(10);
        game.roll(2);
        game.roll(8);
        game.roll(6);

        var actualScore = game.getScore();

        assertThat(actualScore).isEqualTo(expectedScore);
    }

    // Drehbuchtest
    @Test
    void testExampleGameWithFrames() {
        // Game starts
        assertThat(game.getScore()).isEqualTo(0);

        // First frame
        game.roll(1);
        game.roll(4);

        assertThat(game.getScore()).isEqualTo(5);

        // Second frame
        game.roll(4);
        game.roll(5);

        assertThat(game.getScore()).isEqualTo(14);

        // Third frame - spare
        game.roll(6);
        game.roll(4);

        assertThat(game.getScore()).isEqualTo(24);

        // Fourth frame - spare
        game.roll(5);
        game.roll(5);

        assertThat(game.getScore()).isEqualTo(39);

        // Fifth frame - strike
        game.roll(10);

        assertThat(game.getScore()).isEqualTo(59);

        // Sixth frame
        game.roll(0);
        game.roll(1);

        assertThat(game.getScore()).isEqualTo(61);

        // Seventh frame - spare
        game.roll(7);
        game.roll(3);

        assertThat(game.getScore()).isEqualTo(71);

        // Eighth frame - spare
        game.roll(6);
        game.roll(4);

        assertThat(game.getScore()).isEqualTo(87);

        // Ninth frame - strike
        game.roll(10);

        assertThat(game.getScore()).isEqualTo(107);

        // Tenth frame - spare
        game.roll(2);
        game.roll(8);
        game.roll(6);

        assertThat(game.getScore()).isEqualTo(133);
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