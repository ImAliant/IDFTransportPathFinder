/**
 * 
 */
package com.diamant.idftransportpathfinder.idfm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Representation of a transport line
 * 
 * @author Emmanuel Bigeon */
public final class TraceEntry {
    public final String shortname;
    public final String longname;
    public final String routetype;
    public final String color;
    private List<List<StopEntry>> paths = new ArrayList<>();

    /** Create a transport line.
     * 
     * @param shortname the short name of the line
     * @param longname the long name of the line */
    public TraceEntry(String shortname, String longname, String routetype, String color) {
        super();
        this.shortname = shortname;
        this.longname = longname;
        this.routetype = routetype;
        this.color = color;
    }

    // FIXME list of lists are bad practice in direct access...
    /** @return the list of paths */
    public List<List<StopEntry>> getPaths() {
        return Collections.unmodifiableList(paths);
    }

    public void addPath(List<StopEntry> path) {
        paths.add(new ArrayList<>(path));
    }
    public void addAll(List<List<StopEntry>> newPaths){

        this.paths.addAll(newPaths);
    }
}
