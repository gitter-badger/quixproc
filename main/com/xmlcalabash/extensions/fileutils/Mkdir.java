/*
QuiXProc: efficient evaluation of XProc Pipelines.
Copyright (C) 2011-2012 Innovimax
2008-2012 Mark Logic Corporation.
Portions Copyright 2007 Sun Microsystems, Inc.
All rights reserved.

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/
package com.xmlcalabash.extensions.fileutils;

import java.io.File;
import java.net.URI;

import net.sf.saxon.s9api.QName;
import net.sf.saxon.s9api.SaxonApiException;

import com.xmlcalabash.core.XProcConstants;
import com.xmlcalabash.core.XProcException;
import com.xmlcalabash.core.XProcRuntime;
import com.xmlcalabash.io.WritablePipe;
import com.xmlcalabash.library.DefaultStep;
import com.xmlcalabash.model.RuntimeValue;
import com.xmlcalabash.runtime.XAtomicStep;
import com.xmlcalabash.util.TreeWriter;

/**
 * Created by IntelliJ IDEA.
 * User: ndw
 * Date: May 24, 2009
 * Time: 3:17:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Mkdir extends DefaultStep {
    private static final QName _href = new QName("href");

    private WritablePipe result = null;

    /**
     * Creates a new instance of UriInfo
     */
    public Mkdir(XProcRuntime runtime, XAtomicStep step) {
        super(runtime,step);
    }

    public void setOutput(String port, WritablePipe pipe) {
        result = pipe;
    }

    public void reset() {
        result.resetWriter(stepContext);
    }

    public void gorun() throws SaxonApiException {
        super.gorun();

        if (runtime.getSafeMode()) {
            throw XProcException.dynamicError(21);
        }

        RuntimeValue href = getOption(_href);
        URI uri = href.getBaseURI().resolve(href.getString());
        File file;
        if (!"file".equals(uri.getScheme())) {
            throw new XProcException(step.getNode(), "Only file: scheme URIs are supported by the mkdir step.");
        } else {
            file = new File(uri.getPath());
        }

        TreeWriter tree = new TreeWriter(runtime);
        tree.startDocument(step.getNode().getBaseURI());
        tree.addStartElement(XProcConstants.c_result);
        tree.startContent();

        tree.addText(file.toURI().toASCIIString());

        if (file.exists() && file.isDirectory()) {
            // nop
        } else if (file.exists()) {
             throw new XProcException(step.getNode(), "Cannot mkdir: file exists: " + file.getAbsolutePath());
        } else if (!file.mkdirs()) {
            throw new XProcException(step.getNode(), "Mkdir failed for: " + file.getAbsolutePath());
        }

        tree.addEndElement();
        tree.endDocument();

        result.write(stepContext,tree.getResult());
    }
}