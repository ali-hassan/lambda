package com.jnape.palatable.lambda.functor;

import com.jnape.palatable.lambda.adt.hlist.Tuple2;
import com.jnape.palatable.lambda.functions.Fn1;

/**
 * {@link Profunctor} strength in the cartesian product sense: <code>p a b -&gt; p (c ^ a) (c ^ b)</code> for any type
 * <code>c</code>.
 *
 * @param <A> the type of the left parameter
 * @param <B> the type of the left parameter
 * @param <P> the unification parameter
 * @see com.jnape.palatable.lambda.functions.Fn1
 * @see Cocartesian
 */
public interface Cartesian<A, B, P extends Cartesian<?, ?, P>> extends Profunctor<A, B, P> {

    /**
     * Pair some type <code>C</code> to this profunctor's carrier types.
     *
     * @param <C> the paired type
     * @return the cartesian-strengthened profunctor
     */
    <C> Cartesian<Tuple2<C, A>, Tuple2<C, B>, P> cartesian();

    /**
     * Pair the covariantly-positioned carrier type with the contravariantly-positioned carrier type. This can be
     * thought of as "carrying" or "inspecting" the left parameter.
     *
     * @return the profunctor with the first parameter carried
     */
    default Cartesian<A, Tuple2<A, B>, P> carry() {
        return this.<A>cartesian().contraMap(Tuple2::fill);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    <Z, C> Cartesian<Z, C, P> diMap(Fn1<? super Z, ? extends A> lFn, Fn1<? super B, ? extends C> rFn);

    /**
     * {@inheritDoc}
     */
    @Override
    default <Z> Cartesian<Z, B, P> diMapL(Fn1<? super Z, ? extends A> fn) {
        return (Cartesian<Z, B, P>) Profunctor.super.<Z>diMapL(fn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default <C> Cartesian<A, C, P> diMapR(Fn1<? super B, ? extends C> fn) {
        return (Cartesian<A, C, P>) Profunctor.super.<C>diMapR(fn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default <Z> Cartesian<Z, B, P> contraMap(Fn1<? super Z, ? extends A> fn) {
        return (Cartesian<Z, B, P>) Profunctor.super.<Z>contraMap(fn);
    }
}
