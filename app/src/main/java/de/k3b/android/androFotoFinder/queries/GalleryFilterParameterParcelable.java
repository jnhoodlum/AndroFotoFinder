/*
 * Copyright (c) 2015 by k3b.
 *
 * This file is part of AndroFotoFinder.
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>
 */
 
package de.k3b.android.androFotoFinder.queries;

import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import de.k3b.io.DirectoryFormatter;
import de.k3b.io.GalleryFilterParameter;
import de.k3b.io.IGeoRectangle;

/**
 * Created by k3b on 11.07.2015.
 */
public class GalleryFilterParameterParcelable extends GalleryFilterParameter implements Parcelable {
    /**
     * Classes implementing the Parcelable
     * interface must also have a static field called <code>CREATOR</code>, which
     * is an object implementing the {@link Parcelable.Creator Parcelable.Creator}
     * interface.
     */
    public static final Parcelable.Creator<GalleryFilterParameterParcelable> CREATOR
            = new Parcelable.Creator<GalleryFilterParameterParcelable>() {
        public GalleryFilterParameterParcelable createFromParcel(Parcel in) {
            return new GalleryFilterParameterParcelable(in);
        }

        public GalleryFilterParameterParcelable[] newArray(int size) {
            return new GalleryFilterParameterParcelable[size];
        }
    };

    public GalleryFilterParameterParcelable() {};

    /************* parcable support **********************/

    /** to desirialize from Parcel */
    private GalleryFilterParameterParcelable(Parcel in) {
        setPath(in.readString());

        setLatitudeMin(in.readDouble());
        setLatitudeMax(in.readDouble());
        setLogituedMin(in.readDouble());
        setLogituedMax(in.readDouble());
        setDateMin(in.readLong());
        setDateMax(in.readLong());
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getPath());

        dest.writeDouble(getLatitudeMin());
        dest.writeDouble(getLatitudeMax());
        dest.writeDouble(getLogituedMin());
        dest.writeDouble(getLogituedMax());
        dest.writeLong(getDateMin());
        dest.writeLong(getDateMax());

    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    public boolean set(String selectedAbsolutePath, int queryTypeId) {
        switch (queryTypeId) {
            case FotoSql.QUERY_TYPE_GROUP_ALBUM:
                setPath(selectedAbsolutePath + "%");
                return true;
            case FotoSql.QUERY_TYPE_GROUP_DATE:
                Date from = new Date();
                Date to = new Date();

                DirectoryFormatter.getDates(selectedAbsolutePath, from, to);
                setDateMin(from.getTime());
                setDateMax(to.getTime());
                return true;
            case FotoSql.QUERY_TYPE_GROUP_PLACE_MAP:
            case FotoSql.QUERY_TYPE_GROUP_PLACE:
                IGeoRectangle geo = DirectoryFormatter.parseLatLon(selectedAbsolutePath);
                if (geo != null) {
                    this.get(geo);
                }
                return true;
        }
        return false;
    }

     private static final String SHARED_KEY_LogituedMin  = "filter_LogituedMin";
     private static final String SHARED_KEY_LatitudeMin  = "filter_LatitudeMin";
     private static final String SHARED_KEY_LatitudeMax  = "filter_LatitudeMax";
     private static final String SHARED_KEY_LogituedMax  = "filter_LogituedMax";
     private static final String SHARED_KEY_DateMax      = "filter_DateMax";
     private static final String SHARED_KEY_DateMin      = "filter_DateMin";
     private static final String SHARED_KEY_Path         = "filter_Path";

    /** workaroud because SharedPreferences cannot handle Parcable */
    public void saveSettings(SharedPreferences.Editor edit) {

        if (edit != null) {
            edit.putFloat(SHARED_KEY_LogituedMin, (float) this.getLogituedMin());
            edit.putFloat(SHARED_KEY_LatitudeMin, (float) this.getLatitudeMin());
            edit.putFloat(SHARED_KEY_LatitudeMax, (float) this.getLatitudeMax());
            edit.putFloat(SHARED_KEY_LogituedMax, (float) this.getLogituedMax());
            edit.putLong(SHARED_KEY_DateMax, this.getDateMax());
            edit.putLong(SHARED_KEY_DateMin, this.getDateMin());
            edit.putString(SHARED_KEY_Path, this.getPath());
        }
    }

    /** workaroud because SharedPreferences cannot handle Parcable */
    public void loadSettings(SharedPreferences sharedPref) {
        if (sharedPref != null) {
            this.setLogituedMin(sharedPref.getFloat(SHARED_KEY_LogituedMin, (float) this.getLogituedMin()));
            this.setLatitudeMin(sharedPref.getFloat(SHARED_KEY_LatitudeMin, (float) this.getLatitudeMin()));
            this.setLatitudeMax(sharedPref.getFloat(SHARED_KEY_LatitudeMax, (float) this.getLatitudeMax()));
            this.setLogituedMax(sharedPref.getFloat(SHARED_KEY_LogituedMax, (float) this.getLogituedMax()));
            this.setDateMax(sharedPref.getLong(SHARED_KEY_DateMax, this.getDateMax()));
            this.setDateMin(sharedPref.getLong(SHARED_KEY_DateMin, this.getDateMin()));
            this.setPath(sharedPref.getString(SHARED_KEY_Path, this.getPath()));
        }
    }
}
