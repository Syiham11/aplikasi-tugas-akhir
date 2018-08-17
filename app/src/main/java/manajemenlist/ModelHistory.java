package manajemenlist;

public class ModelHistory {
    private String tanggalKejadian;
    private String waktuKejadian;

    public ModelHistory(String tanggalKejadian, String waktuKejadian) {
        this.tanggalKejadian = tanggalKejadian;
        this.waktuKejadian = waktuKejadian;
    }

    public String getTanggalKejadian() {
        return tanggalKejadian;
    }

    public void setTanggalKejadian(String tanggalKejadian) {
        this.tanggalKejadian = tanggalKejadian;
    }

    public String getWaktuKejadian() {
        return waktuKejadian;
    }

    public void setWaktuKejadian(String waktuKejadian) {
        this.waktuKejadian = waktuKejadian;
    }
}
