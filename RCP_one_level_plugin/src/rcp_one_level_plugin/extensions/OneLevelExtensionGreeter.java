package rcp_one_level_plugin.extensions;

import org.eclipse.jface.dialogs.MessageDialog;
import rcp_main_plugin.extension_points.IGreeter;

public class OneLevelExtensionGreeter implements IGreeter {

	@Override
	public void greet() {
		MessageDialog.openInformation(null, "OneLevelExtensionGreeter", "OneLevelExtensionGreeter");
	}
}
