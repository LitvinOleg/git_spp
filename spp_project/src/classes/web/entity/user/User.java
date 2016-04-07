package classes.web.entity.user;

import classes.web.entity.Transport;

import java.util.List;
import classes.web.entity.Load;

public class User {
    protected String name;
    protected String surname;
    protected String login;
    protected String password;
    protected UserType userType;

    protected List<Load> allLoads;
    protected List<Transport> allTransports;

    public User() {}

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public UserType getUserType() {
        return userType;
    }
    public List<Load> getAllLoads() {
        return allLoads;
    }
    public List<Transport> getAllTransports() {
        return allTransports;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public void setAllLoads(List<Load> allLoads) {
        this.allLoads = allLoads;
    }
    public void setAllTransports(List<Transport> allTransports) {
        this.allTransports = allTransports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;

    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }


    /**
     * User types that can exist
     */
    public static enum UserType {
        ADMIN("Admin", 1), // 1
        DISPATCHER("Dispatcher", 2), // 2
        CLIENT("Client", 3), // 3
        VISITOR("Visitor", 4);

        private String userType;
        private int enumValue;

        private UserType(String userType, int enumValue) {
            this.userType = userType;
            this.enumValue = enumValue;
        }

        public int getEnumValue() {
            return enumValue;
        }

        public String getUserType() {
            return userType;
        }
    }
}
