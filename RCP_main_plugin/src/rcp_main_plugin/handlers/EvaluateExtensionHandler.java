 
package rcp_main_plugin.handlers;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.e4.core.di.annotations.Execute;

import rcp_main_plugin.extension_points.IGreeter;

public class EvaluateExtensionHandler {
	
	private static final String SCHEMA_ID = "rcp_main_plugin.extension_points.one_level_ep";
    
    @Execute
    public void execute(IExtensionRegistry registry) {
        IConfigurationElement[] config = registry.getConfigurationElementsFor(SCHEMA_ID);
    	System.out.println("EvaluateExtensionHandler " + config.length);
    	Collection<Object> contributions = new ArrayList<Object>();
        try {
            for (IConfigurationElement e : config) {
                final Object o = e.createExecutableExtension("new_attribute"); 
//                executeExtension(o);
                contributions.add(o);
            }
        } catch (CoreException ex) {
            System.out.println(ex.getMessage());
        }
        
        for(Object o: contributions) {
        	executeExtension(o);
        }
    }

    private void executeExtension(final Object o) {
        ISafeRunnable runnable = new ISafeRunnable() {
            @Override
            public void handleException(Throwable e) {
                System.out.println("Exception in client");
            }

            @Override
            public void run() throws Exception {
                if (o instanceof IGreeter) {
                	((IGreeter) o).greet();
                }
            }
        };
        SafeRunner.run(runnable);
    }
}