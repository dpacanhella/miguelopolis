package br.dpacanhella.miguelopolis.data.model;



import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by infra on 20/06/17.
 */

@Data
public class Promocao implements Parcelable{

    private int id;
    private String imagemProduto;
    private String nomeProduto;
    private String precoInicial;
    private String precoFinal;
    private String image64;
    private String imageByte;

    protected Promocao(Parcel in) {
        id = in.readInt();
        imagemProduto = in.readString();
        nomeProduto = in.readString();
        precoInicial = in.readString();
        precoFinal = in.readString();
        image64 = in.readString();
        imageByte = in.readString();
    }

    public Promocao(int id, String imagemProduto, String nomeProduto, String precoInicial, String precoFinal, String image64, String imageByte){
        this.id = id;
        this.imagemProduto = imagemProduto;
        this.nomeProduto = nomeProduto;
        this.precoInicial = precoInicial;
        this.precoFinal = precoFinal;
        this.image64 = image64;
        this.imageByte = imageByte;
    }

    public static final Creator<Promocao> CREATOR = new Creator<Promocao>() {
        @Override
        public Promocao createFromParcel(Parcel in) {
            return new Promocao(in);
        }

        @Override
        public Promocao[] newArray(int size) {
            return new Promocao[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(imagemProduto);
        dest.writeString(nomeProduto);
        dest.writeString(precoInicial);
        dest.writeString(precoFinal);
        dest.writeString(image64);
        dest.writeString(imageByte);
    }
}
