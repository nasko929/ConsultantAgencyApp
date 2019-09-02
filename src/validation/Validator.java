package validation;

public class Validator {
    public static boolean isUsernameValid(String username) {
        if(username.length() >= 6 && username.length() <= 64 && username.matches("^[a-zA-Z0-9_]+$"))
            return true;
        return false;
    }

    public static boolean isPasswordValid(String password) {
        if(password.length() >= 8 && password.length() <= 64 && password.matches("^[a-zA-Z0-9_]+$"))
            return true;
        return false;
    }

    public static boolean isTextValid(String text) {
        if(text.matches(".+"))
            return true;
        return false;
    }

    public static boolean isNameValid(String anyName) {
        if(anyName.matches(".+") && anyName.length() < 64)
            return true;
        return false;
    }

    public static boolean isEmailValid(String email) {
        if(email.matches("^.+@.+\\..+$"))
            return true;
        return false;
    }

    public static boolean isNumberValid(String number) {
        if(number.matches("^[0-9]+$"))
            return true;
        return false;
    }
}
