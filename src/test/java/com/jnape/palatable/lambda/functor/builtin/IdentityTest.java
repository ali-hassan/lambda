package com.jnape.palatable.lambda.functor.builtin;

import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import org.junit.runner.RunWith;
import testsupport.traits.FunctorLaws;

@RunWith(Traits.class)
public class IdentityTest {

    @TestTraits({FunctorLaws.class})
    public Identity testSubject() {
        return new Identity<>("");
    }
}