package model;

public enum XO {
    X, O;

    public XO getOther() {
        if(this == X)
            return O;
        return X;
    }
}
