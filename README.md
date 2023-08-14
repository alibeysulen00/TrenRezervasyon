<h1> Program Hakkında Genel Bilgiler  </h1>
<ul>
    <li>Bir tren rezervasyonu uygulaması için, istenilen rezervasyonunun yapılıp yapılamayacağını ve yapılabiliyorsa hangi vagon için rezervasyon onaylanabileceğini belirleyen bir HTTP API geliştirilecektir.  </li>
    <li>API, HTTP Post isteklerine yanıt verecektir.   </li>
    <li> HTTP API'a tren bilgileri ve kaç kişilik rezervasyon istenildiği gönderilecek, geliştirilecek algoritma rezervasyon yapılıp yapılamayacağı bilgisini dönecektir. 
 </li>
    </ul>
    <br>

<h2> Gereksinimler </h2>
<ul>
    <li> Bir tren içinde birden fazla vagon bulunabilir </li>
    <li>  Her vagonun farklı kişi kapasitesi olabilir </li>
    <li>Online rezervasyonlarda, bir vagonun doluluk kapasitesi %70'i geçmemelidir. Yani vagon kapasitesi 100 ise ve 70 koltuk dolu ise, o vagona rezervasyon yapılamaz.
   </li>
    <li> Bir rezervasyon isteği içinde birden fazla kişi olabilir.  </li>
    <li> Rezervasyon isteği yapılırken, kişilerin farklı vagonlara yerleşip yerleştirilemeyeceği belirtilir. Bazı rezervasyonlarda tüm yolcuların aynı vagonda olması istenilirken, bazılarında farklı vagonlar da kabul edilebilir.  </li>
    <li>Rezervasyon yapılabilir durumdaysa, API hangi vagonlara kaçar kişi yerleşeceği bilgisini dönecektir. </li>
    </ul>

<h2> API Request ve Response Formatı  </h2>

<p>Input formatı aşağıdaki gibidir. Rezervasyon istenilen trenin bilgileri, vagon ayrıntıları, kaç kişilik rezervasyon istenildiği ve kişilerin farklı vagonlara yerleştirilip yerleştirilemeyeceği bilgileri input içinde yer alır;

```xml
{
    "Tren":
    {
        "Ad":"Başkent Ekspres",
        "Vagonlar":
        [
            {"Ad":"Vagon 1", "Kapasite":100, "DoluKoltukAdet":68},
            {"Ad":"Vagon 2", "Kapasite":90, "DoluKoltukAdet":50},
            {"Ad":"Vagon 3", "Kapasite":80, "DoluKoltukAdet":80}
        ]
    },
    "RezervasyonYapilacakKisiSayisi":3,
    "KisilerFarkliVagonlaraYerlestirilebilir":true
}

```
<p> Dönüş formatı aşağıdaki gibidir.

```xml
{
    "RezervasyonYapilabilir":true,
    "YerlesimAyrinti":[
        {"VagonAdi":"Vagon 1","KisiSayisi":2},
        {"VagonAdi":"Vagon 2","KisiSayisi":1}
    ]
}

```

<p>Rezervasyon yapılamıyorsa YerlesimAyrinti bos array olacaktır; 


```xml
{
    "RezervasyonYapilabilir":true,
    "YerlesimAyrinti":[    ]
}


```

![Add user ](image.png) 
