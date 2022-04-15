/*
 * generated by Xtext 2.25.0
 */
package org.healthydrone.dsl.expressions.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.xtext.util.Modules2;
import org.healthydrone.dsl.expressions.ExpressionsRuntimeModule;
import org.healthydrone.dsl.expressions.ExpressionsStandaloneSetup;

/**
 * Initialization support for running Xtext languages as language servers.
 */
public class ExpressionsIdeSetup extends ExpressionsStandaloneSetup {

	@Override
	public Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(new ExpressionsRuntimeModule(), new ExpressionsIdeModule()));
	}
	
}
