package de.stevenschwenke.java.writingawesomejavacodeworkshop.programmingstyle;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Null is a concept that has been in Java since forever. Although it has it's right of existence,
 * it's often misused and bad code is created with it.
 */
public class NullAndOptional {

    /*
        - Null is evil because:
            - is no object, hence doesn't really fit into object-oriented Java
            - null necessary to signal a variable that is not yet instantiated
                    String s;
               or an empty bucket in a map
                    myMap.get(1) == null ?
            - often misused to express absence of a value, for example optional parameters
            - can be passed as any object, for example as parameter into methods. Hence, methods have to check for null-parameters.
            - creates a lot of cluttered code with null-checks.
            - NullPointerExceptions happen at runtime instead warnings at compile time

        - however, no 100% fitting substitution in Java (yet).
        - Possible solution in the future: remove null and add setNull(ref) and getNull(ref).

        - from http://www.oracle.com/technetwork/articles/java/java8-optional-2175753.html :
        "To give some historical context, Tony Hoare—one of the giants of computer science—wrote,
         "I call it my billion-dollar mistake. It was the invention of the null reference in 1965.
         I couldn't resist the temptation to put in a null reference, simply because it was so easy to implement.""

    */

    private class Computer {

        private Soundcard soundcard;

        Soundcard getSoundcard() {
            return soundcard;
        }
    }

    private class Soundcard {

        private USB usb;

        public USB getUsb() {
            return usb;
        }

        public void setUsb(
            USB usb) {
            this.usb = usb;
        }
    }

    class USB {

        private String version;

        public USB(String version) {
            this.version = version;
        }

        public String getVersion() {
            return version;
        }
    }

    @Test(expected = NullPointerException.class)
    public void npe() {
        String version = new Computer().getSoundcard().getUsb().getVersion();
        System.out.println(version);
    }

    @Test
    public void avoidingNPETheUglyWay() {
        Computer computer = Math.random() < 0.5 ? null : new Computer();
        String version = "UNKNOWN";
        if (computer != null) {
            Soundcard soundcard = computer.getSoundcard();
            if (soundcard != null) {
                USB usb = soundcard.getUsb();
                if (usb != null) {
                    version = usb.getVersion();
                }
            }
        }
        System.out.println(version);
    }

    /*
        Solutions in other languages, for example Groovy has the "safe navigation operator":
            String version = computer?.getSoundcard()?.getUsb()?.getVersion();

            (version is null when either computer, soundcard, usb or version is null)

        Groovy also has the "Elvis operator" (look sideways to see Elvis):
            String version = computer?.getSoundcard()?.getUsb()?.getVersion() ?: "UNKNOWN";

            (version is "UNKNOWN" when either computer, soundcard, usb or version is null)
     */


    /*
        Workaround: Writing custom Null-Objects (https://sourcemaking.com/refactoring/introduce-null-object)
    */

    private class Order {
        private Customer customer;

        Customer getCustomer() {
            return customer;
        }
    }

    private class Plan {
        private String name;

        Plan(String name) {
            this.name = name;
        }
    }


    class Customer {
        private Plan plan;

        Plan getPlan() {
            return plan;
        }
    }

    @Test
    public void throwingNPE() {
        Order order = new Order();

        // we don't know if the customer is null
        Customer customer = order.getCustomer();

        Plan plan = null; // our return value
        if (customer == null) {
            plan = new Plan("basic");
        }
        else {
            plan = customer.getPlan();
        }

    }

    /**
     *  Special customer that is null. This class replaces the null for customers.
     */
    private class NullCustomer extends Customer {

        boolean isNull() {
            return true;
        }
        Plan getPlan() {
            return new Plan("Nullplan");
        }
    }

    @Test
    public void customNullObject() {

        Order order = new Order();

        // Replace null values with Null-object:
        Customer customer = (order.customer != null) ? order.customer : new NullCustomer();

        Plan plan = customer.getPlan(); // our return value
    }

    /*
        Best option: Java 8 Optional
        (http://www.oracle.com/technetwork/articles/java/java8-optional-2175753.html)

        - possibility to express if a variable can be null or not
        - forces the programmer to think about null-values
        - "the intention of the Optional class is not to replace every single null reference"
     */

    private class BetterComputer {

        private Optional<BetterSoundcard> soundcard;

        public Optional<BetterSoundcard> getSoundcard() {
            return soundcard;
        }

        public void setSoundcard(
            Optional<BetterSoundcard> soundcard) {
            this.soundcard = soundcard;
        }
    }

    private class BetterSoundcard {

        private Optional<USB> usb;

        public Optional<USB> getUsb() {
            return usb;
        }

        public void setUsb(
            Optional<USB> usb) {
            this.usb = usb;
        }
    }

    @Test
    public void usingOptionals() {

        // Creating

        Optional<Soundcard> emptyOptional = Optional.empty();

        BetterSoundcard soundcard = new BetterSoundcard();
        Optional<BetterSoundcard> nonEmptyOptional = Optional.of(soundcard);

        Optional<BetterSoundcard> emptyOrNonEmptyOptional = Optional.ofNullable(soundcard);

        // Using

        assertTrue(emptyOrNonEmptyOptional.isPresent());

        emptyOrNonEmptyOptional.ifPresent(System.out::println);

        assertEquals(soundcard, emptyOrNonEmptyOptional.get());
    }

    @Test(expected = NoSuchElementException.class)
    public void emptyOptionalThrowsNoSuchElementException() {
        Optional<Soundcard> emptyOptional = Optional.empty();
        emptyOptional.get();
    }

    @Test(expected = NullPointerException.class)
    public void creatingAnEmptyOptionalTheWrongWay() {
        Optional.of(null);
    }

    @Test
    public void defaultValuesAndActions() {

        BetterComputer computer = new BetterComputer();
        computer
            .setSoundcard(Optional.ofNullable(Math.random() < 0.5 ? null : new BetterSoundcard()));

        // Default value:
        BetterSoundcard soundcard = computer.getSoundcard().orElse(new BetterSoundcard());

        // Throwing an exception if optional is empty:
        computer.getSoundcard().orElseThrow(() -> new RuntimeException("I need a soundcard!"));

    }

    @Test
    public void filtering() {

        // cumbersome:
        USB usb = Math.random() < 0.5 ? null : new USB("3.0");

        if (usb != null && "3.0".equals(usb.getVersion())) {
            System.out.println("ok");
        }

        // awesome:
        Optional<USB> maybeUSB = Optional.ofNullable(Math.random() < 0.5 ? null : new USB("3.0"));

        maybeUSB.filter(x -> "3.0".equals(x.getVersion())).ifPresent(x -> System.out.println("ok"));
    }

    @Test
    public void map() {

        Soundcard soundcard = Math.random() < 0.5 ? null : new Soundcard();

        if(soundcard != null){
            USB usb = soundcard.getUsb();
            if(usb != null && "3.0".equals(usb.getVersion())){
                System.out.println("ok");
            }
        }

        Optional<Soundcard> maybeSoundcard = Optional.ofNullable(Math.random() < 0.5 ? null : new Soundcard());
        maybeSoundcard.map(Soundcard::getUsb)
            .filter(usb -> "3.0".equals(usb.getVersion()))
                .ifPresent((x) -> System.out.println("ok"));
    }

    @Test(expected = NullPointerException.class)
    public void flatMap() {

        // error-prone:
        Computer computer = Math.random() < 0.5 ? null : new Computer();
        String version = computer.getSoundcard().getUsb().getVersion();

        // awesome:
        // Because of multiple nesting of Optionals in these objects, flatMap has to be used:
        Optional<BetterComputer> betterComputer2 = Optional.ofNullable(Math.random() < 0.5 ? null : new BetterComputer());
        version = betterComputer2.flatMap(BetterComputer::getSoundcard)
            .flatMap(BetterSoundcard::getUsb)
            .map(USB::getVersion)
            .orElse("UNKNOWN");
    }
}
