package framework.Mvp;

public abstract 
class Repository <M extends Imodel>{
    protected M model;

    public Repository() {
        createModel();
    }

    protected abstract void createModel();

}