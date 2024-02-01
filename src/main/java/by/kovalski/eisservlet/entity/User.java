package by.kovalski.eisservlet.entity;


import java.time.LocalDate;
import java.util.StringJoiner;

public class User extends Entity {
  private long id;
  private String login;
  private String password;
  private String firstName;
  private String lastName;
  private String email;

  public User() {

  }

  public User(String login, String password, String firstName, String lastName, String email) {
    this.login = login;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public User(long id, String login, String password, String firstName, String lastName, String email) {
    this.id = id;
    this.login = login;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    if (login != null ? !login.equals(user.login) : user.login != null) return false;
    if (password != null ? !password.equals(user.password) : user.password != null) return false;
    if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
    if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
    if (email != null ? !email.equals(user.email) : user.email != null) return false;
    return id == user.id ;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (int) id;
    result = 31 * result + (login != null ? login.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return 31 * result + (email != null ? email.hashCode() : 0);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("login='" + login + "'")
            .add("password='" + password + "'")
            .add("firstName='" + firstName + "'")
            .add("lastName='" + lastName + "'")
            .add("email='" + email + "'")
            .toString();
  }
}
