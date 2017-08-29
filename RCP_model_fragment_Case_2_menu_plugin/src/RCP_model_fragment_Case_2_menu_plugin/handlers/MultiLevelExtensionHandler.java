 
package RCP_model_fragment_Case_2_menu_plugin.handlers;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.e4.core.di.annotations.Execute;
import RCP_model_fragment_Case_2_menu_plugin.extension_points.IMultiLevelGreeter;

public class MultiLevelExtensionHandler {
private static final String SCHEMA_ID = "RCP_model_fragment_Case_2_menu_plugin.extension_points.multilevel_ep";
    
    @Execute
    public void execute(IExtensionRegistry registry) {
        IConfigurationElement[] config = registry.getConfigurationElementsFor(SCHEMA_ID);
    	System.out.println("MultiLevelExtensionHandler " + config.length);
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
                if (o instanceof IMultiLevelGreeter) {
                	((IMultiLevelGreeter) o).indirectGreet();
                }
            }
        };
        SafeRunner.run(runnable);
    }
}