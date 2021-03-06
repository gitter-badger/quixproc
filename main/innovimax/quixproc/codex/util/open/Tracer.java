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
package innovimax.quixproc.codex.util.open;

import innovimax.quixproc.codex.util.PipedDocument;
import innovimax.quixproc.codex.util.StepContext;
import innovimax.quixproc.codex.util.Tracing;

import com.xmlcalabash.core.XProcRuntime;
import com.xmlcalabash.io.Pipe;
import com.xmlcalabash.io.ReadablePipe;
import com.xmlcalabash.io.WritablePipe;
import com.xmlcalabash.runtime.XStep;

public class Tracer implements Tracing {   
  
    public void setRuntime(XProcRuntime runtime) { /* NOP */ }      
    public void out(String message) { /* NOP */ }
    public void err(String message) { /* NOP */ }
    public void debug(String message) { /* NOP */ }
    public void debug(String message, boolean force) { /* NOP */ }
    public void debug(XStep xstep, StepContext stepContext, int channel, ReadablePipe pipe, PipedDocument doc, String message) { /* NOP */ }
    public void debug(XStep xstep, StepContext stepContext, int channel, WritablePipe pipe, PipedDocument doc, String message) { /* NOP */ }      
    public void debug(XStep xstep, StepContext stepContext, int channel, Pipe pipe, PipedDocument doc, String message) { /* NOP */ }
    public void debug(XStep xstep, StepContext stepContext, int channel, ReadablePipe pipe, PipedDocument doc, String message, boolean force) { /* NOP */ }
    public void debug(XStep xstep, StepContext stepContext, int channel, WritablePipe pipe, PipedDocument doc, String message, boolean force) { /* NOP */ }
    public void debug(XStep xstep, StepContext stepContext, int channel, Pipe pipe, PipedDocument doc, String message, boolean force) { /* NOP */ }        
    
}