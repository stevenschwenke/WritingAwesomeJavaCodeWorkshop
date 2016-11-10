package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods;

import org.junit.Test;

import java.io.Serializable;

/**
 * Often, the usage of the serial version UID is not understood. This chapter explains its usage.
 */
public class SerialVersionUID {

    /*
     * From API of Serializable and Joshua Blochs famous "Effective Java":
     * - Serializable = marker interface
     * - every serializable class must have a non-argument (default) constructor. Absence of this is
     *   only detected at runtime.
     * - special handling possible with writeObject and readObject methods
     * - each serializable class: version number "serialVersionUID"
     * - during deserialization sender and receiver compatible classes?
     * - if different serialVersionUID InvalidClassException
     * - declaration of serialVersionUID by static, final long field serialVersionUID
     * - if no serialVersionUID, calculation of default serialVersionUID
     * - serialVersionUID strongly recommended
     *
     * - let IDE generate id for you. In IntelliJ IDEA, enable
     * File => Settings... => Inspections => Serialization issues =>  Serializable class without 'serialVersionUID'
     *
     */

    private class SerializableClass implements Serializable {
        // Adding attributes here will change the generated UID ...

//        private static final long serialVersionUID = -6622066385258934578L;
    }
}
