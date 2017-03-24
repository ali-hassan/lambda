package testsupport;

import com.jnape.palatable.lambda.functions.Fn1;

import java.util.function.Function;

import static java.util.Objects.hash;

public final class EqualityAwareFn1<A, B> implements Fn1<A, B> {
    private final A         a;
    private final Fn1<A, B> fn;

    public EqualityAwareFn1(A a, Fn1<A, B> fn) {
        this.a = a;
        this.fn = fn;
    }

    @Override
    public B apply(A a) {
        return fn.apply(a);
    }

    @Override
    public <C> EqualityAwareFn1<A, C> fmap(Function<? super B, ? extends C> f) {
        return new EqualityAwareFn1<>(a, fn.fmap(f));
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object other) {
        return other instanceof Fn1 && ((Fn1<A, B>) other).apply(a).equals(apply(a));
    }

    @Override
    public int hashCode() {
        return hash(a, fn);
    }
}
