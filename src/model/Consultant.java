package model;

/**
 * A class to represent the consultant object from the database.
 */
public class Consultant {
    private int id; // The id of the consultant.
    private String username; // The username of the consultant.
    private String hashedPassword; // The hashed password of the consultant.
    private String firstName; // The first name of the consultant.
    private String lastName; // The last name of the consultant.
    private String email; // The email of the consultant.
    private String salt; // The salt of the consultant.
    private int hourlyWage; // The hourly wage of the consultant.
    private String role; // The role of the consultant - either Admin or Consultant.
    private Integer rank; // The rank ( junior, normal or senior ) of the consultant.

    /**
     * All the setters and getters.
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(int hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
