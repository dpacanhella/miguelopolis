package br.dpacanhella.miguelopolis.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

/**
 * Created by infra on 05/07/17.
 */

@Data
public class Cardapio implements Parcelable{

    private int id;
    private String descricao;
    private String imagem;

    protected Cardapio(Parcel in) {
        id = in.readInt();
        descricao = in.readString();
        imagem = in.readString();
    }

    public Cardapio(int id, String descricao, String imagem){
        this.id = id;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    public static final Creator<Cardapio> CREATOR = new Creator<Cardapio>() {
        @Override
        public Cardapio createFromParcel(Parcel in) {
            return new Cardapio(in);
        }

        @Override
        public Cardapio[] newArray(int size) {
            return new Cardapio[size];
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
    }
}
