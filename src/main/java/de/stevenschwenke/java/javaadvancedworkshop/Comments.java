package de.stevenschwenke.java.javaadvancedworkshop;

/**
 * Created by schwenks on 22.12.2015.
 */
public class Comments {

  // This comment is often seen, however it is the wrong choice. Line comments like these are there
  // to add non-technical information and are not parsed in any way by the IDE.
  private int x = 0;

  /** This comment is additional technical information that gets connected with the variable. See
   * how your IDE displays this comment when you hover over the variable y. */
  private int y = 1;

  /*
  Can you see how this is not a JavaDoc? Also, there is no IDE support for the parameters or
  return type of this method.
   */
  public String someMethodWithWrongComment(String anyString) {
    return "blubber";
  }

  /**
   * This is a "good" comment because it is recognized as JavaDoc. You can automatically create
   * such a comment by typing "/**" above a method and let your IDE fill in the reset of the text
   * like the parameters or return type. In Eclipse this is done by Alt + Enter, in IntelliJ IDEA
   * you simply have to press Enter.
   *
   * @param anyString you can put anything in here, it will not be used. :)
   * @return some return value
   */
  public String someMethodWithRightComment(String anyString) {
    return "blubber";
  }

}
