package de.stevenschwenke.java.writingawesomejavacodeworkshop.visibility;

import de.stevenschwenke.java.writingawesomejavacodeworkshop.visibility.another.Subclass;

import org.junit.Test;

/**
 * This class demonstrates different visibility modifiers.
 */
public class VisibilityTest {

    @Test
    public void visibilityTest() {

        // Because this test class is in the same package as the class under test, this is visible:
        PackagePrivateClass packagePrivateClass;

        // Public classes are visible everywhere - no surprise here.
        PublicClass publicClass = new PublicClass();

        // Only the private method is not visible:
        publicClass.publicMethod(); // everywhere
//        publicClass.privateMethod(); // not visible
        publicClass.protectedMethod();      // because same package
        publicClass.packagePrivateMethod(); // because same package

        // Question: What is the difference between protected and package-private?
    }

    @Test
    public void differenceProtectedAndPackagePrivate() {

        // This class is a subclass of PublicClass. To see a difference to the test above, it's
        // located in another package.
        Subclass subclass = new Subclass();

        subclass.publicMethod(); // everywhere => OK
        // subclass.privateMethod(); // not visible => OK
        subclass.protectedMethod(); // OOOPS! If we would've marked this method as "protected"
                                    // to make it visible for this test, our API leaked into our
                                    // subclass! Subclasses can see protected methods, but not
                                    // package-private ones:
        //subclass.packagePrivateMethod(); // not visible because other package => OK

        // => This is the reason why "private" methods that have JUnit-tests have to be package-
        // private instead of protected!
    }

    // Last thing to learn here: Inner classes are often marked as public. Don't do that if you
    // are using them only inside the class!

    /**
     * I'm a very private class and would like to be used only inside of my containing class. I'm
     * very appropriate to be used as a specialized listener or renderer.
     */
    private class X {

    }

    // Other modifiers also possible, but I see little use of them:
    class Y {

    }

    protected class Z {

    }

    /////////////////
    // One more thing: overuse of getters and setters
    //
    // Often, the first thing after creating a class and adding its attributes, getter and setter
    // methods are created for each of the attributes. This destroys the encapsulation principle
    // for which those methods have been defined! Only write getter and setter for those attributes
    // you really want to access.
    //
    /////////////////
}
