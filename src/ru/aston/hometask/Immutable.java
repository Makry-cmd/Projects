public final class Immutable implements Cloneable {
    private final Mutable mutable;

    public Immutable(Mutable mutable) {

        this.mutable = mutable == null ? null : mutable.clone(); // копия входного объекта
    }

    @Override
    public Immutable clone() {
        Mutable mutableCopy = mutable;
        return new Immutable(mutableCopy);
    }

    public Mutable getMutable() {
        return mutable;
    }

}