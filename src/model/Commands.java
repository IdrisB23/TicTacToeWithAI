package model;

public enum Commands {
    START, EXIT, BAD_PARAMETERS;


    @Override
    public String toString() {
        return super.toString().toLowerCase().replace('_', ' ');
    }
}
