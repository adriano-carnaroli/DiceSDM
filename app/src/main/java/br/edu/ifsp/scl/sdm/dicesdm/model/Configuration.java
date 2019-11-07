package br.edu.ifsp.scl.sdm.dicesdm.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Configuration implements Serializable {

    private Integer numDados;
    private Integer numFaces;

    public Configuration() {
        this.numDados = 1;
        this.numFaces = 6;
    }

    public Configuration(Integer numDados, Integer numFaces) {
        this.numDados = numDados;
        this.numFaces = numFaces;
    }

//    protected Configuration(Parcel in) {
//        if (in.readByte() == 0) {
//            numDados = null;
//        } else {
//            numDados = in.readInt();
//        }
//        if (in.readByte() == 0) {
//            numFaces = null;
//        } else {
//            numFaces = in.readInt();
//        }
//    }
//
//    public static final Creator<Configuration> CREATOR = new Creator<Configuration>() {
//        @Override
//        public Configuration createFromParcel(Parcel in) {
//            return new Configuration(in);
//        }
//
//        @Override
//        public Configuration[] newArray(int size) {
//            return new Configuration[size];
//        }
//    };

    public Integer getNumDados() {
        return numDados;
    }

    public void setNumDados(Integer numDados) {
        this.numDados = numDados;
    }

    public Integer getNumFaces() {
        return numFaces;
    }

    public void setNumFaces(Integer numFaces) {
        this.numFaces = numFaces;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeInt(this.numDados);
//        parcel.writeInt(this.numFaces);
//    }
}
