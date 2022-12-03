package utils;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class CachedSupplier<T> {

    private final AtomicReference<T> reference;
    private final Supplier<T> supplier;

    public CachedSupplier(Supplier<T> supplier) {
        this.supplier = supplier;
        reference = new AtomicReference<>();
    }

    public T get() {
        T val = reference.get();
        if (val == null) {
            synchronized (reference) {
                val = reference.get();
                if (val == null) {
                    val = Objects.requireNonNull(supplier.get());
                    reference.set(val);
                }
            }
        }
        return val;
    }

}
