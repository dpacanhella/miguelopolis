package br.dpacanhella.miguelopolis.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

/**
 * Created by infra on 05/07/17.
 */

@Data
public class ImagemLoja implements Parcelable{

    private int id;
    private String descricao;
    private String imagem;
    private String imageByte;

    protected ImagemLoja(Parcel in) {
        id = in.readInt();
        descricao = in.readString();
        imagem = in.readString();
        imageByte = in.readString();
    }

    public ImagemLoja(int id, String descricao, String imagem, String imageByte){
        this.id = id;
        this.descricao = descricao;
        this.imagem = imagem;
        this.imageByte = imageByte;
    }

    public static final Creator<ImagemLoja> CREATOR = new Creator<ImagemLoja>() {
        @Override
        public ImagemLoja createFromParcel(Parcel in) {
            return new ImagemLoja(in);
        }

        @Override
        public ImagemLoja[] newArray(int size) {
            return new ImagemLoja[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(descricao);
        dest.writeString(imagem);
        dest.writeString(imageByte);
    }
}
