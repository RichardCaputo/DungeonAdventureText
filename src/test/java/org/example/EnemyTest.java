package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {


    @Test
    void getRandomIntReturnsZeroWhenMinBoundIsZeroAndMaxBoundIsZero() {
        // Arrange
        Enemy enemy = new Enemy("","",10,10,10,false,false,false);

        // Act
        int result = enemy.getRandomInt(0,1);

        // Assert
        assertTrue(result <= 1);
    }

    @Test
    void getRandomIntReturns5WhenMinBoundIs5AndMaxBoundIs5() {
        // Arrange
        Enemy enemy = new Enemy("","",10,10,10,false,false,false);

        // Act
        int result = enemy.getRandomInt(5,5);

        // Assert
        assertEquals(5, result);
    }
}