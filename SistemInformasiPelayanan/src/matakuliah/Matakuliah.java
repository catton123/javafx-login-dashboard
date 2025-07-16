package matakuliah;

public class Matakuliah {
    private String id;
    private String kode_matakuliah;
    private String nama_matakuliah;
    private String sks;

    public Matakuliah(String id, String kode_matakuliah, String nama_matakuliah, String sks) {
        this.id = id;
        this.kode_matakuliah = kode_matakuliah;
        this.nama_matakuliah = nama_matakuliah;
        this.sks = sks;
    }

    public String getId() {
        return id;
    }

    public String getKode_matakuliah() {
        return kode_matakuliah;
    }

    public String getNama_matakuliah() {
        return nama_matakuliah;
    }

    public String getSks() {
        return sks;
    }
}
