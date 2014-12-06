package com.memo.mock;

import org.mockito.Matchers;
import org.mockito.Mockito;

import com.memo.interpolator.MessageInterpolator;

public abstract class MockMessageIntegrpolator extends MessageInterpolator {

    public static void mock() {
        MessageInterpolator interpolator = Mockito.mock(MessageInterpolator.class);
        MockMessageIntegrpolator.interpolator = interpolator;
        Mockito.when(interpolator.interpolate(Matchers.anyString())).thenReturn("value");
    }

}
