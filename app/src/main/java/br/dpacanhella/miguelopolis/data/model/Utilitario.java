package br.dpacanhella.miguelopolis.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

/**
 * Created by infra on 29/06/17.
 */

@Data
public class Utilitario implements Parcelable{

    private int id;
    private String nome;
    private String descricao;
    private String endereco;
    private String telefone;
    private String celular;
    private String imagem;

    public Utilitario(int id, String nome, String descricao, String endereco, String telefone, String celular, String imagem){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.endereco = endereco;
        this.telefone = telefone;
        this.celular = celular;
        this.imagem = imagem;
    }

    protected Utilitario(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        descricao = in.readString();
        endereco = in.readString();
        telefone = in.readString();
        celular = in.readString();
        imagem = in.readString();
    }

    public static final Creator<Utilitario> CREATOR = new Creator<Utilitario>() {
        @Override
        public Utilitario createFromParcel(Parcel in) {
            return new Utilitario(in);
        }

        @Override
        public Utilitario[] newArray(int size) {
            return new Utilitario[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(descricao);
        dest.writeString(endereco);
        dest.writeString(telefone);
        dest.writeString(celular);
        dest.writeString(imagem);
    }
}
