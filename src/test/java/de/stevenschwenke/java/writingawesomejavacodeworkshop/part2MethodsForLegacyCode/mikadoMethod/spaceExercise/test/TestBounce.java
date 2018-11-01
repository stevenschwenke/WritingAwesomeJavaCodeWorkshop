package de.stevenschwenke.java.writingawesomejavacodeworkshop.part2MethodsForLegacyCode.mikadoMethod.spaceExercise.test;

import de.stevenschwenke.java.writingawesomejavacodeworkshop.part2MethodsForLegacyCode.mikadoMethod.spaceExercise.src.PhysicalObject;
import org.junit.jupiter.api.Test;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * This example code is taken from https://github.com/mikadomethod/space.
 */
class TestBounce {


    @Test
    void straightOn() {
        PhysicalObject one = new PhysicalObject(1, 0, 0, 0, 0, 1);
        PhysicalObject two = new PhysicalObject(1, -1, 0, 1, 0, 1);
        one.hitBy(two);
        assertEquals(1.0, one.vx, 0.0001);
        assertEquals(0.0, one.vy, 0.0001);
        assertEquals(0.0, two.vx, 0.0001);
        assertEquals(0.0, two.vy, 0.0001);
        assertTrue(-2.0 < two.x);
        assertEquals(1.0, one.x, 0.0001);
    }

    @Test
    void straightOnVerticalDifferentMass() {
        PhysicalObject one = new PhysicalObject(1, 0, 0, 0, -1, 0.5);
        PhysicalObject two = new PhysicalObject(2, 0, -1, 0, 1, 0.5);
        one.hitBy(two);
        assertEquals(5.0 / 3, one.vy, 0.0001);
        assertEquals(-1.0 / 3, two.vy, 0.0001);
    }

    @Test
    void straightOnDifferentMass1() {
        PhysicalObject one = new PhysicalObject(1, 0, 0, -1, 0, 0.5);
        PhysicalObject two = new PhysicalObject(2, -1, 0, 1, 0, 0.5);
        one.hitBy(two);
        assertEquals(5.0 / 3, one.vx, 0.0001);
        assertEquals(-1.0 / 3, two.vx, 0.0001);
    }

    @Test
    void straightOnDifferentMass2() {
        PhysicalObject one = new PhysicalObject(1, 0, 0, -1, 0, 0.5);
        PhysicalObject two = new PhysicalObject(2, -1, 0, 1, 0, 0.5);
        one.hitBy(two);
        assertEquals(-1.0 / 3, two.vx, 0.0001);
        assertEquals(0, two.vy, 0.0001);
    }

    @Test
    void with90degImpactAngle() {
        PhysicalObject one = new PhysicalObject(1, 1, 0, 0, 1, sqrt(0.5));
        PhysicalObject two = new PhysicalObject(1, 0, 1, 1, 0, sqrt(0.5));
        one.hitBy(two);
        assertEquals(1, one.vx, 0.0001);
        assertEquals(0, one.vy, 0.0001);
        assertEquals(0, two.vx, 0.0001);
        assertEquals(1, two.vy, 0.0001);
    }

    @Test
    void with90degImpactAngleTurned() {
        PhysicalObject one = new PhysicalObject(1, 0, 0, 1, 1, 0.5);
        PhysicalObject two = new PhysicalObject(1, 1, 0, -1, 1, 0.5);
        one.hitBy(two);
        assertEquals(-1, one.vx, 0.0001);
        assertEquals(1, one.vy, 0.0001);
        assertEquals(1, two.vx, 0.0001);
        assertEquals(1, two.vy, 0.0001);
    }


    @Test
    void with45degImpactAngle() {
        PhysicalObject one = new PhysicalObject(1, 0, 0, 0, 0, 0.5);
        PhysicalObject two = new PhysicalObject(1, -1, 1, 1, 0, 0.5);
        one.hitBy(two);
        assertEquals(0.5, one.vx, 0.0001);
        assertEquals(-0.5, one.vy, 0.0001);
        assertEquals(0.5, two.vx, 0.0001);
        assertEquals(0.5, two.vy, 0.0001);
    }

    @Test
    void with45degImpactAngleFromBelow() {
        PhysicalObject one = new PhysicalObject(1, 0, 0, 0, 0, 0.5);
        PhysicalObject two = new PhysicalObject(1, -1, 0, 1, 1, 0.5);
        one.hitBy(two);
        assertEquals(1, one.vx, 0.0001);
        assertEquals(0, one.vy, 0.0001);
        assertEquals(0, two.vx, 0.0001);
        assertEquals(1, two.vy, 0.0001);
    }

}
