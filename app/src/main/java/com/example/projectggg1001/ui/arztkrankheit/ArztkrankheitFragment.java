package com.example.projectggg1001.ui.arztkrankheit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectggg1001.R;

import org.w3c.dom.Text;

public class ArztkrankheitFragment extends Fragment {

    private ArztkrankheitViewModel arztkrankheitViewModel;
    private EditText teilname;
    private EditText beschwerdename;
    private TextView namearzt;
    private Button ergebnis;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        arztkrankheitViewModel =
                ViewModelProviders.of(this).get(ArztkrankheitViewModel.class);
        View root = inflater.inflate(R.layout.fragment_arztkrankheit, container, false);

        teilname = root.findViewById(R.id.teilname);
        beschwerdename = root.findViewById(R.id.beschwerdename);
        namearzt = root.findViewById(R.id.namearzt);
        ergebnis = root.findViewById(R.id.ergebnis);

        //Kullanıcının hangi doktora gideceğini gösterecek.
        ergebnis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_teil = teilname.getText().toString().toLowerCase();
                String name_beschwerde = beschwerdename.getText().toString().toLowerCase();
                arztkrankheit(name_teil,name_beschwerde);
            }
        });

        return root;
    }

    public void arztkrankheit(String nameteil,String namebeschwerde){
        if(nameteil.contains("bacak") || nameteil.contains("ayak") || nameteil.contains("bacagim") || nameteil.contains("ayagim")){
            if (namebeschwerde.contains("ağrı") || namebeschwerde.contains("agri")){
                if (namebeschwerde.contains("kızarıklık") || namebeschwerde.contains("sislik") || namebeschwerde.contains("şişlik")
                        || namebeschwerde.contains("kizariklik")) {
                    namearzt.setText("Enfeksiyon Hastalıkları Bölümüne gidebilirsiniz.");
                    return;
                }
                if (namebeschwerde.contains("ağrı") || namebeschwerde.contains("agri")||namebeschwerde.contains("morarma")) {
                    namearzt.setText("Kalp Damar Cerrahisi Bölümüne gidebilirsiniz.");
                    return;
                }
                if(namebeschwerde.contains("uyuşma") || namebeschwerde.contains("uyusma")){
                    namearzt.setText("Nöroloji Bölümüne gidebilirsiniz.");
                    return;
                }
            }
            if(namebeschwerde.contains("sislik")||namebeschwerde.contains("kizariklik")||namebeschwerde.contains("sislik")||namebeschwerde.contains("kızarıklık")){
                namearzt.setText("Enfeksiyon Hastalıkları Bölümüne gidebilirsiniz.");
                return;
            }
        }
        if(namebeschwerde.contains("terleme")){
            namearzt.setText("Dermatoloji (Deri Hastalıkları) Bölümüne gidebilirsiniz.");
            return;
        }
        if(namebeschwerde.contains("burkulma")||namebeschwerde.contains("burk")){
            namearzt.setText("Ortopedi Bölümüne gidebilirsiniz.");
            return;
        }
        if(namebeschwerde.contains("uyuşma")||namebeschwerde.contains("uyusma")||namebeschwerde.contains("uyusukluk")||namebeschwerde.contains("uyuşukluk")){
            namearzt.setText("Fizik Tedavi Bölümüne gidebilirsiniz.");
            return;
        }
        if(nameteil.contains("el")||nameteil.contains("göz")||nameteil.contains("goz")){
            if(namebeschwerde.contains("şişme")||namebeschwerde.contains("sisme")){
                namearzt.setText("Nefroloji Bölümüne gidebilirsiniz.");
                return;
            }
        }
        if(nameteil.contains("idrar")||namebeschwerde.contains("idrar")){
            namearzt.setText("Üroloji Bölümüne gidebilirsiniz.");
            return;
        }
        if(nameteil.contains("göğüs")||nameteil.contains("gogus")||nameteil.contains("kalp")||nameteil.contains("kalb")){
            namearzt.setText("Kardiyoloji Bölümüne gidebilirsiniz.");
            return;
        }
        if(nameteil.contains("göz")||nameteil.contains("goz")){
            namearzt.setText("Göz Hastalıkları Bölümüne gidebilirsiniz.");
            return;
        }
        if(nameteil.contains("testis")||namebeschwerde.contains("prostat")||nameteil.contains("böbrek")||nameteil.contains("bobrek")||nameteil.contains("penis")||nameteil.contains("mesane")||namebeschwerde.contains("böbrek taşı")||nameteil.contains("bobrek tası")){
            namearzt.setText("Üroloji Bölümüne gidebilirsiniz.");
            return;
        }
        if(nameteil.contains("eklem")||namebeschwerde.contains("romatizma")||nameteil.contains("kas")||namebeschwerde.contains("fıtık")||namebeschwerde.contains("fitik")){
            namearzt.setText("Ortopedi Bölümüne gidebilirsiniz.");
            return;
        }
        if(namebeschwerde.contains("akne")||namebeschwerde.contains("egzama")||nameteil.contains("cilt")||nameteil.contains("deri")||nameteil.contains("yüz")||
                nameteil.contains("yuz")||namebeschwerde.contains("mantar")||namebeschwerde.contains("nasır")||namebeschwerde.contains("nasir")||
                namebeschwerde.contains("siğil")||namebeschwerde.contains("sivilce")||nameteil.contains("saç")||nameteil.contains("sac")||nameteil.contains("uçuk")||
                nameteil.contains("ucuk")||nameteil.contains("tırnak")||nameteil.contains("tirnak")||namebeschwerde.contains("kıllanma")||namebeschwerde.contains("killanma")){
            namearzt.setText("Dermatoloji (Deri Hastalıkları) Bölümüne gidebilirsiniz.");
            return;
        }
        if(nameteil.contains("ağız")||nameteil.contains("agiz")){
            if(namebeschwerde.contains("yara")){
                namearzt.setText("Dermatoloji (Deri Hastalıkları) Bölümüne gidebilirsiniz.");
                return;
            }
        }
        if(namebeschwerde.contains("hafıza kaybı")||namebeschwerde.contains("hafiza kaybi")||namebeschwerde.contains("unutkanlik")||namebeschwerde.contains("unutkanlık")
                ||namebeschwerde.contains("titreme")||namebeschwerde.contains("bas agrisi")||namebeschwerde.contains("baş ağrısı")||namebeschwerde.contains("baş dönmesi")||
                namebeschwerde.contains("bas dönmesi")||namebeschwerde.contains("bas donmesi")||namebeschwerde.contains("istemsiz hareketler")||namebeschwerde.contains("çift görme")||
                namebeschwerde.contains("cift gorme")){
            namearzt.setText("Nöroloji Bölümüne gidebilirsiniz.");
            return;
        }
        if(nameteil.contains("meme")){
            namearzt.setText("Genel Cerrahi Bölümüne gidebilirsiniz.");
        }
        if(namebeschwerde.contains("hemoroid")||namebeschwerde.contains("basur")||nameteil.contains("karaciğer")||nameteil.contains("karaciger")||
                nameteil.contains("ince bagırsak")||nameteil.contains("ince bağırsak")||nameteil.contains("ince bagirsak")||nameteil.contains("kalın bagırsak")||
                        nameteil.contains("kalın bağırsak")||nameteil.contains("kalin bagirsak")){
            namearzt.setText("Genel Cerrahi Bölümüne gidebilirsiniz.");
            return;
        }
        if(nameteil.contains("mide")||nameteil.contains("karın")||nameteil.contains("karin")||nameteil.contains("göbek")||nameteil.contains("gobek")||nameteil.contains("kasik")
                ||nameteil.contains("kasık")){
            if(namebeschwerde.contains("siskinlik")||namebeschwerde.contains("sislik")||namebeschwerde.contains("şişlik")){
                namearzt.setText("Genel Cerrahi Bölümüne gidebilirsiniz.");
                return;
            }
        }
        if(namebeschwerde.contains("nezle")||namebeschwerde.contains("burun akintisi")||nameteil.contains("burun")||nameteil.contains("kulak")||
                namebeschwerde.contains("işitme kaybı")||namebeschwerde.contains("isitme kaybi")||namebeschwerde.contains("horlama")||namebeschwerde.contains("sinüzit")
                ||namebeschwerde.contains("sinuzit")||namebeschwerde.contains("baş dönmesi")||namebeschwerde.contains("bas donmesi")||namebeschwerde.contains("bogaz")
                ||namebeschwerde.contains("boğaz")){
                namearzt.setText("Kulak Burun Boğaz Bölümüne gidebilirsiniz.");
        }
        if(namebeschwerde.contains("ishal")||namebeschwerde.contains("kabizlik")||namebeschwerde.contains("tansiyon")
                ||((namebeschwerde.contains("agri")||namebeschwerde.contains("ağrı")||namebeschwerde.contains("agrı"))&&(nameteil.contains("karin")||nameteil.contains("karın")))){
            namearzt.setText("İç Hastalıkları (Dahiliye) Bölümüne gidebilirsiniz.");
        }
    }
}