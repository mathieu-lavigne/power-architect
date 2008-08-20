/*
 * Copyright (c) 2008, SQL Power Group Inc.
 *
 * This file is part of Power*Architect.
 *
 * Power*Architect is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * Power*Architect is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>. 
 */

package ca.sqlpower.architect.swingui.olap;

import java.util.Collections;
import java.util.List;

import ca.sqlpower.architect.olap.OLAPObject;
import ca.sqlpower.architect.olap.MondrianModel.VirtualCube;
import ca.sqlpower.architect.swingui.ContainerPaneUI;
import ca.sqlpower.architect.swingui.PlayPenContentPane;

public class VirtualCubePane extends OLAPPane<VirtualCube, OLAPObject> {

    public VirtualCubePane(VirtualCube model, PlayPenContentPane parent) {
        super(parent);
        this.model = model;
        PaneSection<OLAPObject> cubeSection = new PaneSectionImpl(model.getCubeUsage() == null ? Collections.emptyList() : model.getCubeUsage().getCubeUsages(), "Cube Usages:");
        PaneSection<OLAPObject> dimensionSection = new PaneSectionImpl(model.getDimensions(), "Dimensions:");
        PaneSection<OLAPObject> measureSection = new PaneSectionImpl(model.getMeasures(), "Measures:");
        sections.add(cubeSection);
        sections.add(dimensionSection);
        sections.add(measureSection);
        setDashed(true);
        updateUI();
    }
    
    @Override
    protected List<OLAPObject> getItems() {
        return model.getChildren();
    }

    public void updateUI() {
        ContainerPaneUI ui = (ContainerPaneUI) BasicVirtualCubePaneUI.createUI();
        ui.installUI(this);
        setUI(ui);
    }

    public VirtualCube getCube() {
        return model;
    }

    @Override
    public String toString() {
        return "VirtualCubePane: " + model.getName(); //$NON-NLS-1$
    }

}