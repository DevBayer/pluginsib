package org.fundaciobit.plugins.utils.ldap;

/**
 * Information about a LDAP user.
 * 
 * @author anadal
 * @version 1.0
 */
public class LDAPUser implements Comparable<LDAPUser>, Cloneable {

  /** Name of this user. */
  private String userName;

  /** User's e-mail. */
  private String email = "";

  /**
   * Name
   */
  private String name;

  private String surname;

  private String[] memberOf;

  private String administrationID;
  
  private String telephoneNumber;

  /**
   * Default constructor.
   */
  public LDAPUser() {
  }

  /**
   * Sets the name of this user.
   * 
   * @param userName
   *          a string with the name of this user
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * Gets the name of this user.
   * 
   * @return a string with the name of this user.
   */
  public String getUserName() {
    return this.userName;
  }

  /**
   * Sets this user's e-mail.
   * 
   * @param mailAddress
   *          a string with the e-mail address of this user
   */
  public void setEmail(String mailAddress) {
    this.email = mailAddress;
  }

  /**
   * Gets the e-mail of this user.
   * 
   * @return a string with the e-mail address of the user.
   */
  public String getEmail() {
    return this.email;
  }

  public String[] getMemberOf() {
    return memberOf;
  }

  public void setMemberOf(String[] memberOf) {
    this.memberOf = memberOf;
  }

  public String getAdministrationID() {
    return administrationID;
  }

  public void setAdministrationID(String administrationID) {
    this.administrationID = administrationID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }


  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  public void setTelephoneNumber(String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof LDAPUser) {
      LDAPUser that = (LDAPUser) obj;
      return this.getUserName().equals(that.getUserName());
    } else {
      return false;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return this.getUserName().hashCode();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "User::" + this.getUserName();
  }

  /**
   * {@inheritDoc}
   */
  public int compareTo(LDAPUser that) {
    if (that == null) {
      return 1;
    } else if (this == that) {
      return 0;
    } else {
      return this.getUserName().compareTo(that.getUserName());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object clone() {
    try {
      return super.clone();
    } catch (CloneNotSupportedException e) {
      throw new InternalError("Failed to clone a User instance");
    }
  }

}
