package de.stevenschwenke.java.writingawesomejavacodeworkshop.part2MethodsForLegacyCode.mikadoMethod.spaceExercise.test;

import de.stevenschwenke.java.writingawesomejavacodeworkshop.part2MethodsForLegacyCode.mikadoMethod.spaceExercise.src.PhysicalObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * This example code is taken from https://github.com/mikadomethod/space.
 */
class TestMergeObjects {
    @Test
    void mergeWithoutSpeed() {
        PhysicalObject one = new PhysicalObject(1, 1, 0, 0, 0, 1);
        PhysicalObject other = new PhysicalObject(1, 0, 1, 0, 0, 1);
        PhysicalObject merge = one.absorb(other);
        assertEquals(0.5, merge.x, 0.00001);
        assertEquals(0.5, merge.y, 0.00001);
        assertEquals(0.0, merge.vx, 0.00001);
        assertEquals(0.0, merge.vy, 0.00001);
    }

    @Test
    void mergeWithSpeed() {
        PhysicalObject one = new PhysicalObject(1, 1, 0, 1, 0, 1);
        PhysicalObject other = new PhysicalObject(1, 0, 1, 0, 1, 1);
        PhysicalObject merge = one.absorb(other);
        assertEquals(0.5, merge.x, 0.00001);
        assertEquals(0.5, merge.y, 0.00001);
        assertEquals(0.5, merge.vx, 0.00001);
        assertEquals(0.5, merge.vy, 0.00001);
        assertEquals(2, merge.mass, 0.00001);
    }

    @Test
    void mergeWithSpeedAndDifferentMasses() {
        PhysicalObject one = new PhysicalObject(1, 1, 1, 1, 0, 1);
        PhysicalObject other = new PhysicalObject(4, 0, 0, 0, 1, 1);
        PhysicalObject merge = one.absorb(other);
        assertEquals(0.2, merge.x, 0.00001);
        assertEquals(0.2, merge.y, 0.00001);
        assertEquals(0.2, merge.vx, 0.00001);
        assertEquals(0.8, merge.vy, 0.00001);
        assertEquals(5, merge.mass, 0.00001);
    }

    @Test
    void headsOnMergeConservesZeroSumMomentum() {
        PhysicalObject one = new PhysicalObject(10, 0, 0, 100, 100, 1);
        PhysicalObject other = new PhysicalObject(100, 0, 0, -10, -10, 1);
        PhysicalObject merge = one.absorb(other);
        assertEquals(0, merge.x, 0.00001);
        assertEquals(0, merge.y, 0.00001);
        assertEquals(0, merge.vx, 0.00001);
        assertEquals(0, merge.vy, 0.00001);
        assertEquals(110, merge.mass, 0.00001);
    }

    @Test
    void headsOnMergeConservesMomentum() {
        PhysicalObject one = new PhysicalObject(10, 0, 0, 10, 10, 1);
        PhysicalObject other = new PhysicalObject(100, 0, 0, 0, 0, 1);
        PhysicalObject merge = one.absorb(other);
        assertEquals(0, merge.x, 0.00001);
        assertEquals(0, merge.y, 0.00001);
        assertEquals(100 / 110.0, merge.vx, 0.00001);
        assertEquals(100 / 110.0, merge.vy, 0.00001);
        assertEquals(110, merge.mass, 0.00001);
    }

}
