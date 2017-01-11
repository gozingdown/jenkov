package com.rabbitmq.amqp_client.util;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class CustomMatcher {

	public static Matcher matches(final Object expected) {
		
		return new BaseMatcher() {
			protected Object theExpected = expected;

			@Override
			public boolean matches(Object o) {
				return expected.equals(o);
			}

			@Override
			public void describeTo(Description d) {
				d.appendText(theExpected.toString());
			}
			
		};
	}
}
