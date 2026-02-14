/**
 * 
 */
package com.diamant.idftransportpathfinder.idfm;

import java.text.MessageFormat;
import java.util.Objects;

/** A transport stop data.
 * 
 * @author Emmanuel Bigeon */
public class StopEntry implements Comparable<StopEntry> {
    public final String lname;
    public final double longitude;
    public final double latitude;
    public final String operatorName;
    public final String city;

    /** Create the stop
     * 
     * @param lname
     * @param longitude
     * @param latitude */
    public StopEntry(String lname, double longitude, double latitude, String operatorName, String city) {
        super();
        this.lname = lname;
        this.longitude = longitude;
        this.latitude = latitude;
        this.operatorName = operatorName;
        this.city = city;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} [{1}, {2}]", this.lname, this.longitude, //$NON-NLS-1$
                this.latitude);
    }

    @Override
    public int compareTo(StopEntry o) {
        if (latitude < o.latitude) {
            return -1;
        }
        if (latitude > o.latitude) {
            return 1;
        }
        if (longitude < o.longitude) {
            return -1;
        }
        if (longitude > o.longitude) {
            return 1;
        }
        return lname.compareTo(o.lname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, lname, longitude);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StopEntry other = (StopEntry) obj;
        return Double.doubleToLongBits(latitude) == Double
                .doubleToLongBits(other.latitude) && Objects.equals(lname, other.lname)
                && Double.doubleToLongBits(longitude) == Double
                        .doubleToLongBits(other.longitude);
    }
}
