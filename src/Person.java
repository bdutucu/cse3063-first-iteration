public abstract class Person {
  protected String name;
  protected int ID;
  protected String password;

  public Person(String name, int ID, String password) {
      this.name = name;
      this.ID = ID;
      this.password = password;
  }
}