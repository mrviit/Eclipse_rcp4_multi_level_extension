package RCP_indirect_contributor_plugin.extensions;

import org.eclipse.jface.dialogs.MessageDialog;

import RCP_model_fragment_Case_2_menu_plugin.extension_points.IMultiLevelGreeter;

public class IndirectGreeter implements IMultiLevelGreeter {

	@Override
	public void indirectGreet() {
		MessageDialog.openInformation(null, "IndirectGreeter", "IndirectGreeter");
	}

}
